package jgaul.utility;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import jgaul.DAO.ClientScheduleSelectQry;
import jgaul.model.*;

import java.time.LocalDateTime;
import java.time.ZoneId;


public abstract class Helper {

    public static final ObservableList<Country> allCountries = FXCollections.observableArrayList();
    public static final ObservableList<Division> allDivisions = FXCollections.observableArrayList();
    public static final ObservableList<Division> americaDivision = FXCollections.observableArrayList();
    public static final ObservableList<Division> canadaDivision = FXCollections.observableArrayList();
    public static final ObservableList<Division> ukDivision = FXCollections.observableArrayList();
    public static final ObservableList<Contact> allContacts = FXCollections.observableArrayList();
    public static final ObservableList<User> allUsers = FXCollections.observableArrayList();
    public static ObservableList<Customer> allCustomers = FXCollections.observableArrayList();
    public static final ObservableList<AppointmentType> allAppointmentTypes = FXCollections.observableArrayList();
    private static User currentUser;
    private static Customer customerToModify;
    private static Appointment appointmentToModify;

    public static void initializeConstantFields() {
        ClientScheduleSelectQry.selectAllUsers(allUsers);
        allUsers.sort((user1, user2) -> user1.getUserID() - user2.getUserID());

        ClientScheduleSelectQry.selectAllCountries(allCountries);
        ClientScheduleSelectQry.selectAllDivisions(allDivisions);
        ClientScheduleSelectQry.selectAllContacts(allContacts);

        for (Division division : allDivisions) {
            int divisionNum = division.getCountryID();
            switch (divisionNum) {
                case 1 -> americaDivision.add(division);
                case 2 -> ukDivision.add(division);
                case 3 -> canadaDivision.add(division);
            }
        }
        allAppointmentTypes.add(new AppointmentType("Planning Session"));
        allAppointmentTypes.add(new AppointmentType("De-Briefing"));
        allAppointmentTypes.add(new AppointmentType("Risk Management"));
        allAppointmentTypes.add(new AppointmentType("Coaching"));
    }

    public static boolean checkForBlankString(String errorMessage, String toCheck) {
        if (toCheck.isBlank()) {
            generateBlankFieldAlert(errorMessage);
            return true;
        }
        return false;
    }

    public static boolean checkForNullValue(String errorMessage, Object object) {
        if (object == null) {
            generateBlankFieldAlert(errorMessage);
            return true;
        }
        return false;
    }

    private static void generateBlankFieldAlert(String errorMessage) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("A Text Field is Blank");
        alert.setHeaderText(errorMessage);
        alert.setContentText("Fill in all fields to proceed.");
        alert.show();
    }

    public static void generateTimeConflictAlert(String errorMessage) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Conflict with another appointment");
        alert.setHeaderText(errorMessage);
        alert.setContentText("Choose a different appointment time.");
        alert.show();
    }

    public static LocalDateTime convertToUserTime(LocalDateTime databaseTime) {
        return databaseTime.atZone(ZoneId.of("UTC")).withZoneSameInstant(ZoneId.systemDefault()).toLocalDateTime();
    }

    public static LocalDateTime convertToDatabaseTime(LocalDateTime userTime) {
        return userTime.atZone(ZoneId.systemDefault()).withZoneSameInstant(ZoneId.of("UTC")).toLocalDateTime();
    }

    public static void setCurrentUser(User user) {
        Helper.currentUser = user;
    }

    public static User getCurrentUser() {
        return currentUser;
    }

    public static Customer getCustomerToModify() {
        return customerToModify;
    }

    public static void setCustomerToModify(Customer customerToModify) {
        Helper.customerToModify = customerToModify;
    }
    public static void setAppointmentToModify(Appointment appointmentToModify) {
        Helper.appointmentToModify = appointmentToModify;
    }
    public static Appointment getAppointmentToModify() {
        return appointmentToModify;
    }
}
