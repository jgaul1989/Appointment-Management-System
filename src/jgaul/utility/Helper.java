package jgaul.utility;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import jgaul.DAO.ClientScheduleSelectQry;
import jgaul.model.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Comparator;

/** This class contains static fields and methods that are used throughout the application.*/
public abstract class Helper {
    /**
     * Contains all countries in the database.
     */
    public static final ObservableList<Country> allCountries = FXCollections.observableArrayList();
    /**
     * Contains all divisions in the database.
     */
    public static final ObservableList<Division> allDivisions = FXCollections.observableArrayList();
    /**
     * Contains all states in America.
     */
    public static final ObservableList<Division> americaDivision = FXCollections.observableArrayList();
    /**
     * Contains all provinces in Canada.
     */
    public static final ObservableList<Division> canadaDivision = FXCollections.observableArrayList();
    /**
     * Contains all provinces in the UK.
     */
    public static final ObservableList<Division> ukDivision = FXCollections.observableArrayList();
    /**
     * Contains all contacts from the database.
     */
    public static final ObservableList<Contact> allContacts = FXCollections.observableArrayList();
    /**
     * Contains all users from the database.
     */
    public static final ObservableList<User> allUsers = FXCollections.observableArrayList();
    /**
     * Contains all customers from the database.
     */
    public static ObservableList<Customer> allCustomers = FXCollections.observableArrayList();
    /**
     * Contains all appointments from the database.
     */
    public static ObservableList<Appointment> allAppointments = FXCollections.observableArrayList();
    /**
     * Contains all the appointment types available in the application.
     */
    public static final ObservableList<AppointmentType> allAppointmentTypes = FXCollections.observableArrayList();
    private static User currentUser;
    private static Customer customerToModify;
    private static Appointment appointmentToModify;
    private static boolean isAlerted = false;

    /** Returns true if a user has been alerted about an upcoming appointment.
     * @return isAlerted
     */
    public static boolean getIsAlerted() {
        return isAlerted;
    }

    /** Used to set isAlerted to true once a user gets alerted about an upcoming appointment.
     * @param isAlerted
     */
    public static void setIsAlerted(boolean isAlerted) {
        Helper.isAlerted = isAlerted;
    }

    /** This method initializes final static arraylists that contain information from the database.*/
    public static void initializeConstantFields() {
        ClientScheduleSelectQry.selectAllUsers(allUsers);
        allUsers.sort(Comparator.comparingInt(User::getUserID));

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
    }

    /** Used to check if a text field contains a blank string.
     * @param errorMessage the message to be printed
     * @param toCheck string to be checked
     * @return true if blank
     */
    public static boolean checkForBlankString(String errorMessage, String toCheck) {
        if (toCheck.isBlank()) {
            generateBlankFieldAlert(errorMessage);
            return true;
        }
        return false;
    }

    /** Used to check if a combo-box contains a null value.
     * @param errorMessage the message to be printed
     * @param object to be checked for null
     * @return true if blank
     */
    public static boolean checkForNullValue(String errorMessage, Object object) {
        if (object == null) {
            generateBlankFieldAlert(errorMessage);
            return true;
        }
        return false;
    }

    /** Generates an alert if a field is blank.
     * @param errorMessage the message to be printed
     */
    private static void generateBlankFieldAlert(String errorMessage) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("A Text Field is Blank");
        alert.setHeaderText(errorMessage);
        alert.setContentText("Fill in all fields to proceed.");
        alert.show();
    }

    /** Generates an alert if there is a conflict with appointment times.
     * @param errorMessage the message to be printed
     */
    public static void generateTimeConflictAlert(String errorMessage) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Conflict with another appointment");
        alert.setHeaderText(errorMessage);
        alert.setContentText("Choose a different appointment time.");
        alert.show();
    }

    /** Converts database UTC to localtime.
     * @param databaseTime the database time
     * @return the converted time
     */
    public static LocalDateTime convertToUserTime(LocalDateTime databaseTime) {
        return databaseTime.atZone(ZoneId.of("UTC")).withZoneSameInstant(ZoneId.systemDefault()).toLocalDateTime();
    }

    /** Converts local tim to UTC time for the database.
     * @param userTime local time
     * @return the converted time
     */
    public static LocalDateTime convertToDatabaseTime(LocalDateTime userTime) {
        return userTime.atZone(ZoneId.systemDefault()).withZoneSameInstant(ZoneId.of("UTC")).toLocalDateTime();
    }

    /** Sets the user currently using the application.
     * @param  user
     */
    public static void setCurrentUser(User user) {
        Helper.currentUser = user;
    }

    /** Gets the user currently using the application.
     * @return the currentUser
     */
    public static User getCurrentUser() {
        return currentUser;
    }

    /** Gets the customer to be modified.
     * @return the customerToModify
     */
    public static Customer getCustomerToModify() {
        return customerToModify;
    }

    /** Sets the customer to be modified.
     * @param customerToModify
     */
    public static void setCustomerToModify(Customer customerToModify) {
        Helper.customerToModify = customerToModify;
    }

    /** Sets the appointment to be modified.
     * @param appointmentToModify
     */
    public static void setAppointmentToModify(Appointment appointmentToModify) {
        Helper.appointmentToModify = appointmentToModify;
    }

    /** Gets the appointment to be modified.
     * @return appointmentToModify*/
    public static Appointment getAppointmentToModify() {
        return appointmentToModify;
    }
}
