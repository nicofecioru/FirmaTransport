package Ui;

import Model.Client;
import Model.Cursa;
import Model.Destinatie;
import Model.Rezervare;
import Service.ServiceClient;
import Service.ServiceCursa;
import Service.ServiceDest;
import Service.ServiceRez;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.StringConverter;
import javafx.util.converter.DateTimeStringConverter;

import javax.swing.text.TableView;
import java.security.AllPermission;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Created by NicoF on 3/22/2017.
 */
public class Controller {

    private final String pattern = "yyyy-MM-dd";

    private final String patternHour = "hh:mm:ss";

    @FXML
    private Button btn;

    @FXML
    private javafx.scene.control.TableView<Cursa> table;

    @FXML
    private javafx.scene.control.TableView<Rezervare> tableRez;

    @FXML
    private TableColumn<Rezervare,Integer> loc;

    @FXML
    private TableColumn<Rezervare,String> numeClient;

    @FXML
    private TextField destinatia;

    @FXML
    private TextField ora;

    @FXML
    private ComboBox<Client> numeClientNou;

    @FXML
    private ComboBox<Destinatie> numeDestinatie;

    @FXML
    private TextField nrLocuri;

    @FXML
    private TableColumn<Cursa,String> data;

    @FXML
    private TableColumn<Cursa,String> numeDest;

    @FXML
    private TableColumn<Cursa,Integer> nr_locuri;

    @FXML
    private DatePicker datePicker;

    @FXML
    private Button add;

    ServiceCursa servCursa;
    ServiceDest serviceDest;

    ServiceRez serviceRez;
    ServiceClient serviceClient;

    Integer id_Cursa;

    ObservableList<Cursa> model;

    ObservableList<Rezervare> modelRez;

    @FXML
    private void initialize() {
        data.setCellValueFactory(new PropertyValueFactory<Cursa,String>("dataPlecarii"));
        numeDest.setCellValueFactory((TableColumn.CellDataFeatures<Cursa, String> param) -> {
                    Cursa c = param.getValue();

                    return new ReadOnlyObjectWrapper<>(serviceDest.getName(c.getId_dest()));
                }
        );
        nr_locuri.setCellValueFactory(new PropertyValueFactory<Cursa,Integer>("nr_locuri"));

        numeClient.setCellValueFactory((TableColumn.CellDataFeatures<Rezervare, String> param) -> {
                    Rezervare c = param.getValue();

                    return new ReadOnlyObjectWrapper<>(serviceClient.getName(c.getIdClient()));
                }
        );
        loc.setCellValueFactory(new PropertyValueFactory<Rezervare,Integer>("loc"));


        StringConverter converter = new StringConverter<LocalDate>() {
            DateTimeFormatter dateFormatter =
                    DateTimeFormatter.ofPattern(pattern);
            @Override
            public String toString(LocalDate date) {
                if (date != null) {
                    return dateFormatter.format(date);
                } else {
                    return "";
                }
            }
            @Override
            public LocalDate fromString(String string) {
                if (string != null && !string.isEmpty()) {
                    return LocalDate.parse(string, dateFormatter);
                } else {
                    return null;
                }
            }
        };
        datePicker.setConverter(converter);
        datePicker.setPromptText(pattern.toLowerCase());
        datePicker.requestFocus();

        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        try {
            ora.setTextFormatter(new TextFormatter<>(new DateTimeStringConverter(format), format.parse("00:00:00")));
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    public void setServiceRez(ServiceRez serviceRez) {
        this.serviceRez = serviceRez;
    }

    public void setServiceClient(ServiceClient serviceClient) {
        this.serviceClient = serviceClient;
        modelRez = FXCollections.observableArrayList((List<Rezervare>)serviceRez.getAll(1));
        id_Cursa = modelRez.get(0).getIdCursa();

        tableRez.setItems(modelRez);

        numeClientNou.setItems(FXCollections.observableArrayList(serviceClient.getList()));
    }

    public void setServCursa(ServiceCursa servCursa) {
        this.servCursa = servCursa;
        model = FXCollections.observableArrayList((List<Cursa>)servCursa.getList());
        table.setItems(model);
    }

    public void setServiceDest(ServiceDest serviceDest) {
        this.serviceDest = serviceDest;
        numeDestinatie.setItems(FXCollections.observableArrayList(serviceDest.getList()));
    }

    public void update() {
        model.setAll(servCursa.getList());
        modelRez.setAll(serviceRez.getAll(id_Cursa));
    }

    public void search() {
        try {
            id_Cursa = servCursa.searchId(numeDestinatie.getValue().getId(), datePicker.getValue() + " " + ora.getText());
            modelRez = FXCollections.observableArrayList((List<Rezervare>) serviceRez.getAll(id_Cursa));

            tableRez.setItems(modelRez);
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Eroare");
            alert.showAndWait();
        }
    }

    public void add() {
        try {
            if (servCursa.search(id_Cursa).getNr_locuri()-Integer.parseInt(nrLocuri.getText())>=0)
                for (int i = 0; i < Integer.parseInt(nrLocuri.getText()); i++) {
                serviceRez.adauga(numeClientNou.getValue().getId(), serviceRez.getMax() + 1, id_Cursa);
                servCursa.update(id_Cursa);
                update();
            }
            else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Nu sunt suficiente locuri disponibile!");
                alert.showAndWait();
            }
      } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Eroare");
            alert.showAndWait();
        }
    }
}
