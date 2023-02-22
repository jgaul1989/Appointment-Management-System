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
import jgaul.DAO.ClientScheduleSelectQry;
import jgaul.model.Appointment;
import jgaul.model.AppointmentType;
import jgaul.model.Contact;
import jgaul.model.Customer;
import jgaul.utility.Helper;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class reportController implements Initializable {
    public ComboBox<Contact> contactCB;
    public ComboBox<AppointmentType> appointmentTypeCB;
    public final ObservableList<String> monthsList = FXCollections.observableArrayList();
    public ComboBox<String> monthCB;
    public Label numAppointmentsByMonthAndTypeLabel;
    public ComboBox<Customer> customerCB;
    public ComboBox<AppointmentType> customerAppointmentTypeCB;
    public Label numCustomerAptByType;
    public TableView<Appointment> appointmentTableView;
    public TableColumn<Appointment, Integer> appointmentIDCol;
    public TableColumn<Appointment, String> titleCol;
    public TableColumn<Appointment, String> typeCol;
    public TableColumn<Appointment, String> descriptionCol;
    public TableColumn<Appointment, String> startCol;
    public TableColumn<Appointment, String> endCol;
    public TableColumn<Appointment, Integer> customerCol;
    public Label contactLabel;
    public Label appointmentType;
    public ObservableList<Appointment> contactAppointments = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        contactCB.setItems(Helper.allContacts);
        appointmentTypeCB.setItems(Helper.allAppointmentTypes);
        initializeMonthCB();
        customerCB.setItems(Helper.allCustomers);
        customerAppointmentTypeCB.setItems(Helper.allAppointmentTypes);
    }

    private void initializeMonthCB() {
        monthsList.add("January");
        monthsList.add("February");
        monthsList.add("March");
        monthsList.add("April");
        monthsList.add("May");
        monthsList.add("June");
        monthsList.add("July");
        monthsList.add("August");
        monthsList.add("September");
        monthsList.add("October");
        monthsList.add("November");
        monthsList.add("December");
        monthCB.setItems(monthsList);
    }

    public void calculateCustomerApt() {
        if (customerCB.getValue() != null && customerAppointmentTypeCB.getValue() != null) {
            int customerID = customerCB.getValue().getCustomerID();
            String appointmentType = customerAppointmentTypeCB.getValue().getType();
            int appointments = ClientScheduleSelectQry.selectCustomerAptByType(customerID, appointmentType);
            numCustomerAptByType.setText(String.valueOf(appointments));
        }
    }

    public void calculateAppointmentTypeByMonth() {
        if (monthCB.getValue() != null && appointmentTypeCB.getValue() != null) {
            String month = monthCB.getValue();
            String appointmentType = appointmentTypeCB.getValue().getType();
            int appointments = ClientScheduleSelectQry.selectAptByTypeAndMonth(month, appointmentType);
            numAppointmentsByMonthAndTypeLabel.setText(String.valueOf(appointments));
        }
    }

    public void generateSchedule() {
        if (contactCB.getValue() != null) {
            Contact contact = contactCB.getValue();
            contactAppointments.clear();
            Helper.allAppointments.stream()
                    .filter(appointment -> appointment.getContactAsContact().equals(contact))
                    .forEach(appointment -> contactAppointments.add(appointment));
            appointmentTableView.setItems(contactAppointments);
            appointmentIDCol.setCellValueFactory(new PropertyValueFactory<>("appointmentID"));
            titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
            descriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
            typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
            startCol.setCellValueFactory(new PropertyValueFactory<>("startTime"));
            endCol.setCellValueFactory(new PropertyValueFactory<>("endTime"));
            customerCol.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        }
    }

    public void backToMain(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("view/mainMenu.fxml"));
        Stage window = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        window.setScene(new Scene(root));
        window.show();
    }
}
