package jgaul;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import jgaul.DAO.ClientScheduleQuery;
import jgaul.DAO.JDBC;
import jgaul.utility.Helper;

import java.time.ZoneId;
import java.util.Locale;
import java.util.ResourceBundle;


public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../view/login.fxml"));
        stage.setScene(new Scene(root, 920, 600));
        stage.show();
    }


    public static void main(String[] args) {

        //Locale.setDefault(new Locale("fr"));
        JDBC.makeConnection();
        Helper.initializeConstantFields();
        launch(args);
        JDBC.closeConnection();
    }
}
