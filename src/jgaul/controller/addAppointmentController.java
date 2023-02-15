package jgaul.controller;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import jgaul.model.Contact;
import jgaul.model.User;
import jgaul.utility.Helper;

import java.net.URL;
import java.util.ResourceBundle;

public class addAppointmentController implements Initializable {

    public ComboBox<Contact> contactCB;
    public ComboBox<User> userCB;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        contactCB.setItems(Helper.allContacts);
        userCB.setItems(Helper.allUsers);

    }

    public void submitAppointment(ActionEvent actionEvent) {
    }

    public void backToMain(ActionEvent actionEvent) {
    }
}
