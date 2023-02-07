package jgaul;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import jgaul.DAO.JDBC;


public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../view/login.fxml"));
        stage.setTitle("Hello World");
        stage.setScene(new Scene(root, 920, 600));
        stage.show();
    }


    public static void main(String[] args) {
        JDBC.makeConnection();

        launch(args);
        JDBC.closeConnection();
    }
}
