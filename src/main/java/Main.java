import Model.*;
import Repository.*;
import Service.*;
import Ui.LoginCtr;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
import java.sql.Timestamp;

import java.text.SimpleDateFormat;
import java.util.Properties;

public class Main extends Application
{
    @Override
    public void start(Stage stage) throws Exception {
        Properties props=new Properties();
        try {
            props.load(new FileInputStream("bd.propieties"));
        } catch (IOException e) {
            System.out.println("Eroare: "+e);
        }
        RepoUser r= new RepoUser(props,"User");
        ServiceUser su = new ServiceUser(r);

        RepoDest rDest= new RepoDest(props,"Destinatie");
        ServiceDest sd = new ServiceDest(rDest);

        RepoCursa repoCursa= new RepoCursa(props,"Cursa");
        ServiceCursa sc = new ServiceCursa(repoCursa);

        RepoRezervare rRez= new RepoRezervare(props,"Rezervare");
        ServiceRez sr = new ServiceRez(rRez);

        RepoClient repoClient= new RepoClient(props,"Client");
        ServiceClient scl = new ServiceClient(repoClient);


        FXMLLoader loader = new FXMLLoader(ClassLoader.getSystemClassLoader().getResource("login.fxml"));
        VBox rootLayout = (VBox) loader.load();
        LoginCtr logCtr = loader.getController();
        logCtr.setService(su);
        logCtr.setServiceDest(sd);
        logCtr.setServiceCursa(sc);
        logCtr.setServiceRez(sr);
        logCtr.setServiceClient(scl);
        Scene scene = new Scene(rootLayout);
        stage.setScene(scene);
        stage.show();
        logCtr.setStage(stage);
    }

    public static void main( String args[] )
    {

        launch(args);
    }
}

