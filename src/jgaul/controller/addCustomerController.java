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
    private ObservableList<Country> allCountries = FXCollections.observableArrayList();
    private ObservableList<Division> americaDivision = FXCollections.observableArrayList();
    private ObservableList<Division> canadaDivision = FXCollections.observableArrayList();
    private ObservableList<Division> ukDivision = FXCollections.observableArrayList();

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

        for (int i = 0; i < allDivisions.size(); i++) {
            int divisionNum = allDivisions.get(i).getCountryID();
            switch (divisionNum) {
                case 1 -> americaDivision.add(allDivisions.get(i));
                case 2 -> ukDivision.add(allDivisions.get(i));
                case 3 -> canadaDivision.add(allDivisions.get(i));
            }
        }
    }

    public void submitCustomer(ActionEvent actionEvent) {
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
