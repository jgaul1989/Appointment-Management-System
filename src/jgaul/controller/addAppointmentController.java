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
import jgaul.model.*;
import jgaul.utility.Helper;

import java.io.IOException;
import java.net.URL;
import java.time.*;
import java.util.ResourceBundle;

public class addAppointmentController implements Initializable {

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        contactCB.setItems(Helper.allContacts);
        userCB.setItems(Helper.allUsers);
        customerCB.setItems(Helper.allCustomers);
        typeCB.setItems(Helper.allAppointmentTypes);
    }

    public void submitAppointment(ActionEvent actionEvent) throws IOException {
        String title = titleTF.getText();
        if (Helper.checkForBlankString("Title field is blank.", title)) {
            return;
        }
        String description = descriptionTF.getText();
        if (Helper.checkForBlankString("Description field is blank.", description)) {
            return;
        }
        String location = locationTF.getText();
        if (Helper.checkForBlankString("Location field is blank.", location)) {
            return;
        }
        Contact contact = contactCB.getValue();
        if (Helper.checkForNullValue("Contact field is blank.", contact)) {
            return;
        }
        AppointmentType appointmentType = typeCB.getValue();
        if (Helper.checkForNullValue("Type field is blank.", appointmentType)) {
            return;
        }
        LocalDate appointmentDate = appointmentDateDP.getValue();
        if (Helper.checkForNullValue("Date field is blank.", appointmentDate)) {
            return;
        }
        UserAppointmentTimes startTime = startTimeCB.getValue();
        if (Helper.checkForNullValue("Start time is blank.", startTime)) {
            return;
        }
        UserAppointmentTimes endTime = endTimeCB.getValue();
        if (Helper.checkForNullValue("End time is blank.", endTime)) {
            return;
        }
        Customer customer = customerCB.getValue();
        if (Helper.checkForNullValue("Customer ID is blank.", customer)) {
            return;
        }
        User user = userCB.getValue();
        if (Helper.checkForNullValue("User ID is blank.", user)) {
            return;
        }
        backToMain(actionEvent);
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

    public void startTimeSelected(ActionEvent actionEvent) {
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
