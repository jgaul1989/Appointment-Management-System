package jgaul.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import jgaul.DAO.ClientScheduleQuery;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class loginController implements Initializable {
    public Button loginButton;
    public Label usernameLabel;
    public TextField userNameTextField;
    public TextField passwordTextField;
    public Label passwordLabel;
    public Label loginErrorMessage;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("I am initialized");
    }

    public void loginButtonClicked(ActionEvent actionEvent) throws IOException {
        String username = userNameTextField.getText();
        String password = passwordTextField.getText();

        if (ClientScheduleQuery.loginValidation(username, password, loginErrorMessage)) {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("view/success.fxml"));
            Stage window = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
            window.setScene(new Scene(root));
            window.show();
        }
    }

}
