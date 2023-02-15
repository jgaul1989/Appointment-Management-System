package jgaul.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import jgaul.model.Contact;
import jgaul.model.User;
import jgaul.utility.Helper;

import java.net.URL;
import java.time.*;
import java.util.ResourceBundle;

public class addAppointmentController implements Initializable {

    public ComboBox<Contact> contactCB;
    public ComboBox<User> userCB;
    public ComboBox<LocalTime> startTimeCB;
    public ObservableList<LocalTime> times = FXCollections.observableArrayList();
    public DatePicker appointmentDateDP;
    public LocalDate selectedDate;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        contactCB.setItems(Helper.allContacts);
        userCB.setItems(Helper.allUsers);

    }

    public void submitAppointment(ActionEvent actionEvent) {
    }

    public void backToMain(ActionEvent actionEvent) {
    }

    public void dateSelected() {
        selectedDate = appointmentDateDP.getValue();
        ZoneId timeZoneEST = ZoneId.of("America/New_York");
        LocalTime initial = LocalTime.of(8,0);
        LocalTime closing = LocalTime.of(22,15);

        while(initial.isBefore(closing)) {
            ZonedDateTime easternTime = ZonedDateTime.of(selectedDate, initial, timeZoneEST);
            ZonedDateTime zoneConversion = easternTime.withZoneSameInstant(ZoneId.systemDefault());
            LocalTime userTime = zoneConversion.toLocalTime();
            times.add(userTime);
            initial = initial.plusMinutes(15);
        }
        startTimeCB.setItems(times);

    }
}
