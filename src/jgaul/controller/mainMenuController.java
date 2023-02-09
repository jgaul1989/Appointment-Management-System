package jgaul.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import jgaul.DAO.ClientScheduleQuery;
import jgaul.model.Customer;

import java.net.URL;
import java.util.ResourceBundle;

public class mainMenuController implements Initializable {

    public TableView<Customer> customerTableView;
    public TableColumn<Customer, Integer> customerTableIDCol;
    public TableColumn<Customer, String> customerNameCol;
    public TableColumn<Customer, String> addressCol;
    public TableColumn<Customer, String> postalCodeCol;
    public TableColumn<Customer, String> phoneCol;
    public TableColumn<Customer, String> divisionCol;
    public TableColumn<Customer, String> countryCol;
    public ObservableList<Customer> allCustomers = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ClientScheduleQuery.selectAllCustomers(allCustomers);
        customerTableView.setItems(allCustomers);
        customerTableIDCol.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        customerNameCol.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        addressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
        postalCodeCol.setCellValueFactory(new PropertyValueFactory<>("postalCode"));
        phoneCol.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        divisionCol.setCellValueFactory(new PropertyValueFactory<>("division"));
        countryCol.setCellValueFactory(new PropertyValueFactory<>("country"));

    }
}
