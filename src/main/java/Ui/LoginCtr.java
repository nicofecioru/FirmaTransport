package Ui;

import Service.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by NicoF on 3/22/2017.
 */
public class LoginCtr {
    @FXML
    private TextField text;

    @FXML
    private PasswordField pass;

    @FXML
    private Button logIn;

    private ServiceUser service;

    private ServiceDest serviceDest;

    private ServiceCursa serviceCursa;

    private Stage stg;

    private ServiceRez serviceRez;

    public void setText(TextField text) {
        this.text = text;
    }

    public void setServiceRez(ServiceRez serviceRez) {
        this.serviceRez = serviceRez;
    }

    public void setServiceClient(ServiceClient serviceClient) {
        this.serviceClient = serviceClient;
    }

    private ServiceClient serviceClient;

    private Controller ctr;

    public void logIN() throws IOException {

            Alert a;

        if (service.search(text.getText(), pass.getText()) != null){
            FXMLLoader loader = new FXMLLoader(ClassLoader.getSystemClassLoader().getResource("window.fxml"));
            VBox rootLayout = (VBox) loader.load();
            ctr = loader.getController();
            ctr.setServCursa(serviceCursa);
            ctr.setServiceDest(serviceDest);
            ctr.setServiceRez(serviceRez);
            ctr.setServiceClient(serviceClient);


            Stage stage = new Stage();
            Scene scene = new Scene(rootLayout);
            stage.setScene(scene);
            stage.show();
            stg.close();
        }
        else {
            a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("gresita!");
            a.showAndWait();
        }
    }

    public void setService(ServiceUser service) {
        this.service = service;
    }

    public void setServiceCursa(ServiceCursa serviceCursa) {
        this.serviceCursa = serviceCursa;
    }

    public void setServiceDest(ServiceDest serviceDest) {
        this.serviceDest = serviceDest;
    }

    public void setStage(Stage stage) {
        stg = stage;
    }
}
