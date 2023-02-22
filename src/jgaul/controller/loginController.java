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
import jgaul.DAO.ClientScheduleSelectQry;
import jgaul.model.User;
import jgaul.utility.Helper;

import java.io.IOException;
import java.net.URL;
import java.time.ZoneId;
import java.util.Locale;
import java.util.ResourceBundle;

public class loginController implements Initializable {
    public Button loginButton;
    public Label usernameLabel;
    public TextField userNameTextField;
    public TextField passwordTextField;
    public Label passwordLabel;
    public Label loginErrorMessage;
    public Label locationLabel;
    private ResourceBundle frenchLanguageTranslation;
    public Label zoneIDLabel;
    private User currentUser;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try  {
            frenchLanguageTranslation = ResourceBundle.getBundle("jgaul/languages/Nat", Locale.getDefault());
            if (Locale.getDefault().getLanguage().equals("fr")) {
                setFrenchLanguage();
            }
        } catch (Exception ignored) {

        }
        zoneIDLabel.setText(ZoneId.systemDefault().getId());
    }

    public void loginButtonClicked(ActionEvent actionEvent) throws IOException {
        String username = userNameTextField.getText();
        String password = passwordTextField.getText();
        currentUser = new User(username, password);

        if (ClientScheduleSelectQry.selectValidUser(currentUser)) {
            Helper.setCurrentUser(currentUser);
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("view/mainMenu.fxml"));
            Stage window = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
            window.setScene(new Scene(root));
            window.show();
        } else {
            setLoginErrorMessage();
        }
    }
    private void setFrenchLanguage(){
        usernameLabel.setText(frenchLanguageTranslation.getString("Username"));
        passwordLabel.setText(frenchLanguageTranslation.getString("Password"));
        loginButton.setText(frenchLanguageTranslation.getString("Login"));
        locationLabel.setText(frenchLanguageTranslation.getString("Location"));
    }

    private void setLoginErrorMessage() {
        if(Locale.getDefault().getLanguage().equals("en")) {
            if (!currentUser.isValidUsername()) {
                loginErrorMessage.setText("Invalid username");
            } else {
                loginErrorMessage.setText("Invalid password");
            }
        } else {
            if (!currentUser.isValidUsername()) {
                String invalidUsername = frenchLanguageTranslation.getString("Invalid") + " " +
                        frenchLanguageTranslation.getString("username");
                loginErrorMessage.setText(invalidUsername);
            } else {
                String invalidPassword = frenchLanguageTranslation.getString("Invalid") + " " +
                        frenchLanguageTranslation.getString("password");
                loginErrorMessage.setText(invalidPassword);
            }
        }
    }

}
