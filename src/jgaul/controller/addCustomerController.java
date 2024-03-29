package jgaul.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import jgaul.DAO.ClientScheduleInsert;
import jgaul.model.Country;
import jgaul.model.Division;
import jgaul.utility.Helper;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/** This class is used to add customers to the database.*/
public class addCustomerController implements Initializable {
    public ComboBox<Country> countryCB;
    public TextField customerNameTF;
    public TextField addressTF;
    public TextField postalTF;
    public TextField phoneTF;
    public ComboBox<Division> divisionCB;

    /** Initializes the add customer controller and sets the country combo-box.*/
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        countryCB.setItems(Helper.allCountries);
    }

    /** Adds a new customer to the database only if all fields are valid.
     * @param actionEvent the submit customer button
     */
    public void submitCustomer(ActionEvent actionEvent) throws IOException {
        String name = customerNameTF.getText();
        if (Helper.checkForBlankString("Name field is blank.", name)) {
            return;
        }
        String address = addressTF.getText();
        if (Helper.checkForBlankString("Address field is blank.", address)) {
            return;
        }
        String postalCode = postalTF.getText();
        if (Helper.checkForBlankString("Postal Code is blank.", postalCode)) {
            return;
        }
        String phone = phoneTF.getText();
        if (Helper.checkForBlankString("Phone number is blank.", phone)) {
            return;
        }
        Division division = divisionCB.getValue();
        if (Helper.checkForNullValue("Country and State/Province fields both need a valid selection.", division)) {
            return;
        }
        ClientScheduleInsert.insertIntoCustomers(name, address, postalCode, phone, division);

        backToMain(actionEvent);
    }

    /** Sends the user back to the main menu.
     * @param actionEvent the submit or cancel button
     */
    public void backToMain(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("view/mainMenu.fxml"));
        Stage window = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        window.setScene(new Scene(root));
        window.show();
    }

    /** After a user selects a country this function sets the division combo-box with appropriate values.*/
    public void countrySelected() {
        int countryID = countryCB.getValue().getCountryID();
        if (countryID == 1) {
            divisionCB.setItems(Helper.americaDivision);
        } else if (countryID == 2) {
            divisionCB.setItems(Helper.ukDivision);
        } else if (countryID == 3) {
            divisionCB.setItems(Helper.canadaDivision);
        }
    }

}
