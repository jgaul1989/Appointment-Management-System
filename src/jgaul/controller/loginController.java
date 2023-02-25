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

import java.io.*;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Locale;
import java.util.ResourceBundle;

/** This is the class controller for the login menu.*/
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

    /** Initializes the login controller.*/
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

    /** Logs the user into the application if the credentials are valid.
     * @param actionEvent the login button
     */
    public void loginButtonClicked(ActionEvent actionEvent) throws IOException {
        String username = userNameTextField.getText();
        String password = passwordTextField.getText();
        currentUser = new User(username, password);

        if (ClientScheduleSelectQry.isValidUser(currentUser)) {
            generateLog(true);
            Helper.setCurrentUser(currentUser);
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("view/mainMenu.fxml"));
            Stage window = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
            window.setScene(new Scene(root));
            window.show();
        } else {
            generateLog(false);
            setLoginErrorMessage();
        }
    }

    /** Appends login information to a log file.
     * @param isSuccessfulLogin boolean flag to indicate login success or fail
     */
    private void generateLog(boolean isSuccessfulLogin) {
        File file = new File("login_activity.txt");
        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(file, true)))) {
            writer.printf("Login attempt at %s. Successful login = %s\n", LocalDateTime.now(), isSuccessfulLogin);
        } catch (IOException e) {
            System.out.printf("An exception occurred %s", e.getMessage());
        }
    }

    /** Sets the text fields to the french language.*/
    private void setFrenchLanguage(){
        usernameLabel.setText(frenchLanguageTranslation.getString("Username"));
        passwordLabel.setText(frenchLanguageTranslation.getString("Password"));
        loginButton.setText(frenchLanguageTranslation.getString("Login"));
        locationLabel.setText(frenchLanguageTranslation.getString("Location"));
    }

    /** Generates an error message in English or French if there is an unsuccessful login attempt.*/
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
