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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import jgaul.DAO.ClientScheduleInsert;
import jgaul.DAO.ClientScheduleSelectQry;
import jgaul.DAO.ClientScheduleUpdate;
import jgaul.model.*;
import jgaul.utility.Helper;

import java.io.IOException;
import java.net.URL;
import java.time.*;
import java.util.ResourceBundle;

public class modifyAppointmentController implements Initializable {

    public ComboBox<Contact> contactCB;
    public ComboBox<User> userCB;
    public ComboBox<UserAppointmentTimes> startTimeCB;
    public ObservableList<UserAppointmentTimes> startTimes = FXCollections.observableArrayList();
    public LocalTime appointmentStartTime;
    public DatePicker appointmentDateDP;
    public LocalDate selectedDate;
    public ComboBox<UserAppointmentTimes> endTimeCB;
    public ObservableList<UserAppointmentTimes> endTimes = FXCollections.observableArrayList();
    public ComboBox<Customer> customerCB;
    public TextField titleTF;
    public TextField descriptionTF;
    public TextField locationTF;
    public ComboBox<AppointmentType> typeCB;
    public TextField appointmentIDTF;
    private int appointmentID;
    private String title;
    private String description;
    private String location;
    private Contact contact;
    private AppointmentType appointmentType;
    private UserAppointmentTimes startTime;
    private UserAppointmentTimes endTime;
    private Customer customer;
    private User user;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        contactCB.setItems(Helper.allContacts);
        userCB.setItems(Helper.allUsers);
        customerCB.setItems(Helper.allCustomers);
        typeCB.setItems(Helper.allAppointmentTypes);
        setAllFields();
    }

    private void setAllFields() {
        appointmentIDTF.setText(String.valueOf(Helper.getAppointmentToModify().getAppointmentID()));
        titleTF.setText(Helper.getAppointmentToModify().getTitle());
        descriptionTF.setText(Helper.getAppointmentToModify().getDescription());
        locationTF.setText(Helper.getAppointmentToModify().getLocation());
        contactCB.setValue(Helper.getAppointmentToModify().getContactAsContact());
        typeCB.setValue(Helper.getAppointmentToModify().getTypeAsType());
        appointmentDateDP.setValue(Helper.getAppointmentToModify().getStartDateAsDateTime().toLocalDate());
        dateSelected();
        UserAppointmentTimes setStartTime = new UserAppointmentTimes(Helper.getAppointmentToModify().getStartDateAsDateTime().toLocalTime());
        startTimeCB.setValue(setStartTime);
        startTimeSelected();
        UserAppointmentTimes setEndTime = new UserAppointmentTimes(Helper.getAppointmentToModify().getEndDateAsDateTime().toLocalTime());
        endTimeCB.setValue(setEndTime);
        for (Customer customer: Helper.allCustomers) {
            if (customer.getCustomerID() == Helper.getAppointmentToModify().getCustomerID()) {
                customerCB.setValue(customer);
            }
        }
        for (User user: Helper.allUsers) {
            if (user.getUserID() == Helper.getAppointmentToModify().getUserID()) {
                userCB.setValue(user);
            }
        }

    }

    public void submitAppointment(ActionEvent actionEvent) throws IOException {
        if(!checkAllValues()) {
            return;
        }
        LocalDateTime startDateTime = Helper.convertToDatabaseTime(LocalDateTime.of(selectedDate,startTime.getTime()));
        LocalDateTime endDateTime = Helper.convertToDatabaseTime(LocalDateTime.of(selectedDate, endTime.getTime()));
        if (endDateTime.isBefore(startDateTime)) {
            Helper.generateTimeConflictAlert("End time is before start time.");
            return;
        }

        if (ClientScheduleSelectQry.checkAppointmentsConflicts(customer, startDateTime, appointmentID)) {
            Helper.generateTimeConflictAlert("Start time conflicts with existing appointment.");
            return;
        }
        if (ClientScheduleSelectQry.checkAppointmentConflicts(customer, startDateTime, endDateTime, true, appointmentID)) {
            Helper.generateTimeConflictAlert("Start time conflicts with existing appointment.");
            return;
        }
        if (ClientScheduleSelectQry.checkAppointmentsConflicts(customer, endDateTime, appointmentID)) {
            Helper.generateTimeConflictAlert("End time conflicts with existing appointment.");
            return;
        }
        if (ClientScheduleSelectQry.checkAppointmentConflicts(customer, startDateTime, endDateTime, false, appointmentID)) {
            Helper.generateTimeConflictAlert("End time conflicts with existing appointment.");
            return;
        }
        Appointment modifiedAppointment = new Appointment(appointmentID,title, description, location,
                appointmentType.getType(), contact.getName(), startDateTime, endDateTime,
                customer.getCustomerID(), user.getUserID(),contact.getContactID());
        ClientScheduleUpdate.modifyAppointment(modifiedAppointment);

        backToMain(actionEvent);
    }

    private boolean checkAllValues() {
        appointmentID = Integer.parseInt(appointmentIDTF.getText());
        title = titleTF.getText();
        if (Helper.checkForBlankString("Title field is blank.", title)) {
            return false;
        }
        description = descriptionTF.getText();
        if (Helper.checkForBlankString("Description field is blank.", description)) {
            return false;
        }
        location = locationTF.getText();
        if (Helper.checkForBlankString("Location field is blank.", location)) {
            return false;
        }
        contact = contactCB.getValue();
        if (Helper.checkForNullValue("Contact field is blank.", contact)) {
            return false;
        }
        appointmentType = typeCB.getValue();
        if (Helper.checkForNullValue("Type field is blank.", appointmentType)) {
            return false;
        }
        selectedDate = appointmentDateDP.getValue();
        if (Helper.checkForNullValue("Date field is blank.", selectedDate)) {
            return false;
        }
        startTime = startTimeCB.getValue();
        if (Helper.checkForNullValue("Start time is blank.", startTime)) {
            return false;
        }
        endTime = endTimeCB.getValue();
        if (Helper.checkForNullValue("End time is blank.", endTime)) {
            return false;
        }
        customer = customerCB.getValue();
        if (Helper.checkForNullValue("Customer ID is blank.", customer)) {
            return false;
        }
        user = userCB.getValue();
        if (Helper.checkForNullValue("User ID is blank.", user)) {
            return false;
        }
        return true;
    }

    public void backToMain(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("view/mainMenu.fxml"));
        Stage window = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        window.setScene(new Scene(root));
        window.show();
    }

    public void dateSelected() {
        selectedDate = appointmentDateDP.getValue();
        startTimes.clear();
        endTimes.clear();
        ZoneId timeZoneEST = ZoneId.of("America/New_York");
        LocalTime initial = LocalTime.of(8,0);
        LocalTime closing = LocalTime.of(22,15);

        while(initial.isBefore(closing)) {
            ZonedDateTime easternTime = ZonedDateTime.of(selectedDate, initial, timeZoneEST);
            ZonedDateTime zoneConversion = easternTime.withZoneSameInstant(ZoneId.systemDefault());
            LocalTime userTime = zoneConversion.toLocalTime();
            UserAppointmentTimes nextTime = new UserAppointmentTimes(userTime);
            startTimes.add(nextTime);
            initial = initial.plusMinutes(15);
        }
        startTimeCB.setItems(startTimes);
    }

    public void startTimeSelected() {
        if (startTimeCB.getValue() == null) {
            return;
        }
        appointmentStartTime = startTimeCB.getValue().getTime();
        endTimes.clear();
        startTimes.stream()
                .filter(time -> time.getTime().isAfter(appointmentStartTime))
                .forEach(time -> endTimes.add(time));
        endTimeCB.setItems(endTimes);
    }
}
