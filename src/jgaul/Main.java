package jgaul;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import jgaul.DAO.JDBC;
import jgaul.utility.Helper;

public class Main extends Application {

    /** This is the method that launches the JavaFX GUI. */
    @Override
    public void start(Stage stage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../view/login.fxml"));
        stage.setScene(new Scene(root, 1000, 600));
        stage.show();
    }

    /** This is the main method that starts the program - JavaDoc files are in the separate zip file named javadoc. */
    public static void main(String[] args) {
        JDBC.makeConnection();
        Helper.initializeConstantFields();
        launch(args);
        JDBC.closeConnection();
    }
}
