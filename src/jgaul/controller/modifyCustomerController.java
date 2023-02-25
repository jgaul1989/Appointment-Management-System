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
import jgaul.DAO.ClientScheduleUpdate;
import jgaul.model.Country;
import jgaul.model.Division;
import jgaul.utility.Helper;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/** This class is the controller for modifying customer information.*/
public class modifyCustomerController implements Initializable {
    public ComboBox<Country> countryCB;
    public TextField customerNameTF;
    public TextField addressTF;
    public TextField postalTF;
    public TextField phoneTF;
    public ComboBox<Division> divisionCB;
    public TextField customerIDTF;

    /** Initializes the modify customer controller.*/
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        countryCB.setItems(Helper.allCountries);
        setTextFields();
    }

    /** Sets all text fields with information of the customer to be modified.
     * This function contains a lambda expression that filters through a list of countries and when a match is found
     * to the customer's country it uses that value to set the country combo-box or sets it to a  default value if a customer does not have
     * a country set in the database. The lambda expression enhances code readability compared to using a loop or an iterator and uses less lines of code.*/
    private void setTextFields(){
        customerIDTF.setText(String.valueOf(Helper.getCustomerToModify().getCustomerID()));
        customerNameTF.setText(Helper.getCustomerToModify().getCustomerName());
        addressTF.setText(Helper.getCustomerToModify().getAddress());
        postalTF.setText(Helper.getCustomerToModify().getPostalCode());
        phoneTF.setText(Helper.getCustomerToModify().getPhoneNumber());

        String countryName = Helper.getCustomerToModify().getCountry();
        Country userCountry = Helper.allCountries.stream()
                .filter(country -> countryName.equals(country.getCountryName()))
                .findFirst().orElse(Helper.allCountries.get(0));
        countryCB.setValue(userCountry);

        String divisionName = Helper.getCustomerToModify().getDivision();
        setDivision(userCountry, divisionName);
    }

    /** Submits customer information to the database if all data is valid.
     * @param actionEvent the submit button is clicked
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
        ClientScheduleUpdate.modifyCustomer(Integer.parseInt(customerIDTF.getText()), name, address, postalCode, phone, division);

        backToMain(actionEvent);
    }

    /** Sends the user back to the main menu.
     * @param actionEvent the cancel button is clicked
     */
    public void backToMain(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("view/mainMenu.fxml"));
        Stage window = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        window.setScene(new Scene(root));
        window.show();
    }

    /** After a user selects a country from the combo box this function populates the division combo box with the
     * correct values.
     */
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

    /** This function sets the division combo box with the appropriate value for the customer to be modified.
     * This function contains a lambda expression that filters through a division list and when a match is found to
     * the customer's division it uses that value to initialize the division combo box. The lambda expression is more compact and readable
     * than using a loop or iterator.
     */
    private void setDivision(Country country, String divisionName) {
        int countryID = country.getCountryID();
        if (countryID == 1) {
            divisionCB.setItems(Helper.americaDivision);
            Division userDivision = Helper.americaDivision.stream()
                    .filter(division -> divisionName.equals(division.getName()))
                    .findFirst().orElse(Helper.americaDivision.get(0));
            divisionCB.setValue(userDivision);
        } else if (countryID == 2) {
            divisionCB.setItems(Helper.ukDivision);
            Division userDivision = Helper.ukDivision.stream()
                    .filter(division -> divisionName.equals(division.getName()))
                    .findFirst().orElse(Helper.ukDivision.get(0));
            divisionCB.setValue(userDivision);
        } else if (countryID == 3) {
            divisionCB.setItems(Helper.canadaDivision);
            Division userDivision = Helper.canadaDivision.stream()
                    .filter(division -> divisionName.equals(division.getName()))
                    .findFirst().orElse(Helper.canadaDivision.get(0));
            divisionCB.setValue(userDivision);
        }
    }

}
