package jgaul.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import jgaul.DAO.ClientScheduleQuery;
import jgaul.model.Country;
import jgaul.model.Division;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class addCustomerController implements Initializable {
    public ComboBox<Country> countryCB;
    public TextField customerNameTF;
    public TextField addressTF;
    public TextField postalTF;
    public TextField phoneTF;
    public ComboBox<Division> divisionCB;
    private final ObservableList<Country> allCountries = FXCollections.observableArrayList();
    private final ObservableList<Division> americaDivision = FXCollections.observableArrayList();
    private final ObservableList<Division> canadaDivision = FXCollections.observableArrayList();
    private final ObservableList<Division> ukDivision = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadCountries();
        loadDivisions();
    }

    private void loadCountries() {
        ClientScheduleQuery.selectAllCountries(allCountries);
        countryCB.setItems(allCountries);
    }

    private void loadDivisions() {
        ObservableList<Division> allDivisions = FXCollections.observableArrayList();
        ClientScheduleQuery.selectAllDivisions(allDivisions);

        for (Division division : allDivisions) {
            int divisionNum = division.getCountryID();
            switch (divisionNum) {
                case 1 -> americaDivision.add(division);
                case 2 -> ukDivision.add(division);
                case 3 -> canadaDivision.add(division);
            }
        }
    }

    public void submitCustomer(ActionEvent actionEvent) throws IOException {
        String name = customerNameTF.getText();
        String address = addressTF.getText();
        String postalCode = postalTF.getText();
        String phone = phoneTF.getText();
        Country country = countryCB.getValue();
        Division division = divisionCB.getValue();
        ClientScheduleQuery.insertIntoCustomers(name, address, postalCode, phone, division);

        cancelAddCustomer(actionEvent);
    }

    public void cancelAddCustomer(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("view/mainMenu.fxml"));
        Stage window = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        window.setScene(new Scene(root));
        window.show();
    }

    public void countrySelected(ActionEvent actionEvent) {
        int countryID = countryCB.getValue().getCountryID();
        if (countryID == 1) {
            divisionCB.setItems(americaDivision);
        } else if (countryID == 2) {
            divisionCB.setItems(ukDivision);
        } else if (countryID == 3) {
            divisionCB.setItems(canadaDivision);
        }
    }

}
