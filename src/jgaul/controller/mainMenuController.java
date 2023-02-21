package jgaul.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import jgaul.DAO.ClientScheduleDelete;
import jgaul.DAO.ClientScheduleSelectQry;
import jgaul.model.Appointment;
import jgaul.model.Customer;
import jgaul.utility.Helper;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.WeekFields;
import java.util.Comparator;
import java.util.Locale;
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
    public Tab customerTab;
    public Tab allAppointmentsTab;
    public TableView<Appointment> allAppointmentsTableView;
    public TableColumn<Appointment, Integer> appointmentIDCol;
    public TableColumn<Appointment, String> titleCol;
    public TableColumn<Appointment, String> descriptionCol;
    public TableColumn<Appointment, String> contactCol;
    public TableColumn<Appointment, String> locationCol;
    public TableColumn<Appointment, String> typeCol;
    public TableColumn<Appointment, String> startDateTimeCol;
    public TableColumn<Appointment, String> endDateTimeCol;
    public TableColumn<Appointment, Integer> customerAppointmentIDCol;
    public TableColumn<Appointment, Integer> userIDCol;
    public ObservableList<Appointment> allAppointments = FXCollections.observableArrayList();
    public Tab monthlyAppointmentsTab;
    public TableView<Appointment> monthlyAppointmentsTableView;
    public TableColumn<Appointment, Integer> monthAppointmentIDCol;
    public TableColumn<Appointment, String> monthTitleCol;
    public TableColumn<Appointment, String> monthDescriptionCol;
    public TableColumn<Appointment, String> monthLocationCol;
    public TableColumn<Appointment, String> monthContactCol;
    public TableColumn<Appointment, String> monthTypeCol;
    public TableColumn<Appointment, String> monthStartDateTimeCol;
    public TableColumn<Appointment, String> monthEndDateTimeCol;
    public TableColumn<Appointment, Integer> monthCustomerAppointmentIDCol;
    public TableColumn<Appointment, Integer> monthUserIDCol;
    public ObservableList<Appointment> monthlyAppointments = FXCollections.observableArrayList();
    public Tab weeklyAppointmentsTab;
    public TableView<Appointment> weeklyAppointmentsTableView;
    public TableColumn<Appointment, Integer> weekAppointmentIDCol;
    public TableColumn<Appointment, String> weekTitleCol;
    public TableColumn<Appointment, String> weekDescriptionCol;
    public TableColumn<Appointment, String> weekLocationCol;
    public TableColumn<Appointment, String> weekContactCol;
    public TableColumn<Appointment, String> weekTypeCol;
    public TableColumn<Appointment, String> weekStartDateTimeCol;
    public TableColumn<Appointment, String> weekEndDateTimeCol;
    public TableColumn<Appointment, Integer> weekCustomerAppointmentIDCol;
    public TableColumn<Appointment, Integer> weekUserIDCol;
    public ObservableList<Appointment> weeklyAppointments = FXCollections.observableArrayList();
    public Label deleteStatusUpdate;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initializeCustomerTable();
        initializeAppointmentTable();
        initializeMonthlyAppointments();
        initializeWeeklyAppointments();
    }

    private void initializeCustomerTable() {
        Helper.allCustomers.clear();
        ClientScheduleSelectQry.selectAllCustomers(Helper.allCustomers);
        Helper.allCustomers.sort((customer1, customer2) -> customer1.getCustomerID() - customer2.getCustomerID());
        customerTableView.setItems(Helper.allCustomers);
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

    private void initializeMonthlyAppointments() {
        allAppointments.stream()
                .filter(appointment -> appointment.getStartDateAsDateTime().getMonth() == LocalDateTime.now().getMonth())
                .forEach(appointment -> monthlyAppointments.add(appointment));
        monthlyAppointmentsTableView.setItems(monthlyAppointments);
        monthAppointmentIDCol.setCellValueFactory(new PropertyValueFactory<>("appointmentID"));
        monthTitleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        monthDescriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        monthLocationCol.setCellValueFactory(new PropertyValueFactory<>("location"));
        monthTypeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        monthContactCol.setCellValueFactory(new PropertyValueFactory<>("contact"));
        monthStartDateTimeCol.setCellValueFactory(new PropertyValueFactory<>("startTime"));
        monthEndDateTimeCol.setCellValueFactory(new PropertyValueFactory<>("endTime"));
        monthCustomerAppointmentIDCol.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        monthUserIDCol.setCellValueFactory(new PropertyValueFactory<>("userID"));

    }

    private void initializeWeeklyAppointments() {
        allAppointments.stream()
                .filter(appointment -> appointment.getStartDateAsDateTime().toLocalDate().get(WeekFields.of(Locale.getDefault()).weekOfWeekBasedYear()) ==
                        LocalDate.now().get(WeekFields.of(Locale.getDefault()).weekOfWeekBasedYear()))
                .forEach(appointment -> weeklyAppointments.add(appointment));
        weeklyAppointmentsTableView.setItems(weeklyAppointments);
        weekAppointmentIDCol.setCellValueFactory(new PropertyValueFactory<>("appointmentID"));
        weekTitleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        weekDescriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        weekLocationCol.setCellValueFactory(new PropertyValueFactory<>("location"));
        weekTypeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        weekContactCol.setCellValueFactory(new PropertyValueFactory<>("contact"));
        weekStartDateTimeCol.setCellValueFactory(new PropertyValueFactory<>("startTime"));
        weekEndDateTimeCol.setCellValueFactory(new PropertyValueFactory<>("endTime"));
        weekCustomerAppointmentIDCol.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        weekUserIDCol.setCellValueFactory(new PropertyValueFactory<>("userID"));
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
        } else if (allAppointmentsTab.isSelected()) {
            if (allAppointmentsTableView.getSelectionModel().getSelectedItem() == null) {
                return;
            } else {
                Helper.setAppointmentToModify(allAppointmentsTableView.getSelectionModel().getSelectedItem());
                loadModifyAppointment(actionEvent);
            }
        } else if (monthlyAppointmentsTab.isSelected()) {
            if (monthlyAppointmentsTableView.getSelectionModel().getSelectedItem() == null) {
                return;
            } else {
                Helper.setAppointmentToModify(monthlyAppointmentsTableView.getSelectionModel().getSelectedItem());
                loadModifyAppointment(actionEvent);
            }
        } else if (weeklyAppointmentsTab.isSelected()) {
            if (weeklyAppointmentsTableView.getSelectionModel().getSelectedItem() == null) {
                return;
            } else {
                Helper.setAppointmentToModify(weeklyAppointmentsTableView.getSelectionModel().getSelectedItem());
                loadModifyAppointment(actionEvent);
            }
        }
    }
    private void loadModifyAppointment(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("view/modifyAppointment.fxml"));
        Stage window = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        window.setScene(new Scene(root));
        window.show();
    }

    public void deleteSelectedObject(ActionEvent actionEvent) {
        if (customerTab.isSelected()) {
            if (customerTableView.getSelectionModel().getSelectedItem() == null) {
                return;
            } else {
                Customer customer = customerTableView.getSelectionModel().getSelectedItem();
                if (ClientScheduleSelectQry.checkCustomerAppointments(customer.getCustomerID())) {
                    deleteStatusUpdate.setText("Delete failed. Customer has scheduled appointments.");
                } else {
                    ClientScheduleDelete.deleteCustomer(customer.getCustomerID());
                    deleteStatusUpdate.setText("Successfully deleted Customer ID # " + customer.getCustomerID());
                }
            }
        } else if (allAppointmentsTab.isSelected()) {
            if (allAppointmentsTableView.getSelectionModel().getSelectedItem() == null) {
                return;
            } else {
                Appointment appointment = allAppointmentsTableView.getSelectionModel().getSelectedItem();
                ClientScheduleDelete.deleteAppointment(appointment.getAppointmentID());
                deleteStatusUpdate.setText("Successfully deleted Appointment ID " + appointment.getAppointmentID());
            }
        } else if (monthlyAppointmentsTab.isSelected()) {
            if (monthlyAppointmentsTableView.getSelectionModel().getSelectedItem() == null) {
                return;
            } else {
                Appointment appointment = monthlyAppointmentsTableView.getSelectionModel().getSelectedItem();
                ClientScheduleDelete.deleteAppointment(appointment.getAppointmentID());
                deleteStatusUpdate.setText("Successfully deleted Appointment ID " + appointment.getAppointmentID());
            }
        } else if (weeklyAppointmentsTab.isSelected()) {
            if (weeklyAppointmentsTableView.getSelectionModel().getSelectedItem() == null) {
                return;
            } else {
                Appointment appointment = weeklyAppointmentsTableView.getSelectionModel().getSelectedItem();
                ClientScheduleDelete.deleteAppointment(appointment.getAppointmentID());
                deleteStatusUpdate.setText("Successfully deleted Appointment ID " + appointment.getAppointmentID());
            }
        }
    }
}
