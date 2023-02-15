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
import jgaul.DAO.ClientScheduleSelectQry;
import jgaul.model.Appointment;
import jgaul.model.Customer;
import jgaul.utility.Helper;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
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
    public Tab allAppointmentsTab;
    public TableView<Appointment> allAppointmentsTableView;
    public TableColumn<Appointment, Integer> appointmentIDCol;
    public TableColumn<Appointment, String> titleCol;
    public TableColumn<Appointment, String> descriptionCol;
    public TableColumn<Appointment, String> contactCol;
    public TableColumn<Appointment, String> locationCol;
    public TableColumn<Appointment, String> typeCol;
    public TableColumn<Appointment, LocalDateTime> startDateTimeCol;
    public TableColumn<Appointment, LocalDateTime> endDateTimeCol;
    public TableColumn<Appointment, Integer> customerAppointmentIDCol;
    public TableColumn<Appointment, Integer> userIDCol;
    public ObservableList<Appointment> allAppointments = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initializeCustomerTable();
        initializeAppointmentTable();
    }

    private void initializeCustomerTable() {
        ClientScheduleSelectQry.selectAllCustomers(allCustomers);
        customerTableView.setItems(allCustomers);
        customerTableIDCol.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        customerNameCol.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        addressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
        postalCodeCol.setCellValueFactory(new PropertyValueFactory<>("postalCode"));
        phoneCol.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        divisionCol.setCellValueFactory(new PropertyValueFactory<>("division"));
        countryCol.setCellValueFactory(new PropertyValueFactory<>("country"));
    }

    private void initializeAppointmentTable() {
        ClientScheduleSelectQry.selectAllAppointments(allAppointments);
        allAppointmentsTableView.setItems(allAppointments);
        appointmentIDCol.setCellValueFactory(new PropertyValueFactory<>("appointmentID"));
        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        descriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        locationCol.setCellValueFactory(new PropertyValueFactory<>("location"));
        typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        contactCol.setCellValueFactory(new PropertyValueFactory<>("contact"));
        startDateTimeCol.setCellValueFactory(new PropertyValueFactory<>("startTime"));
        endDateTimeCol.setCellValueFactory(new PropertyValueFactory<>("endTime"));
        customerAppointmentIDCol.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        userIDCol.setCellValueFactory(new PropertyValueFactory<>("userID"));
    }

    public void addSelectedObject(ActionEvent actionEvent) throws IOException {
        if (customerTab.isSelected()) {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("view/addCustomer.fxml"));
            Stage window = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
            window.setScene(new Scene(root));
            window.show();
        } else {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("view/addAppointment.fxml"));
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
