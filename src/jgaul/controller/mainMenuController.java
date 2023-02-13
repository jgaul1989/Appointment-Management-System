package jgaul.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import jgaul.DAO.ClientScheduleQuery;
import jgaul.model.Customer;
import jgaul.utility.Helper;

import java.io.IOException;
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
    public Tab customerTab;

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

    public void addSelectedObject(ActionEvent actionEvent) throws IOException {
        if (customerTab.isSelected()) {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("view/addCustomer.fxml"));
            Stage window = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
            window.setScene(new Scene(root));
            window.show();
        }
    }

    public void modifySelectedObject(ActionEvent actionEvent) throws IOException {
        if (customerTab.isSelected()) {
            if (customerTableView.getSelectionModel().getSelectedItem() == null) {
                return;
            } else {
                Helper.setCustomerToModify(customerTableView.getSelectionModel().getSelectedItem());
                Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("view/modifyCustomer.fxml"));
                Stage window = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
                window.setScene(new Scene(root));
                window.show();
            }
        }
    }
}
