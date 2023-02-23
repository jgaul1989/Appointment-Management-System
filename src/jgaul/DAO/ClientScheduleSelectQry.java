package jgaul.DAO;

import javafx.collections.ObservableList;
import jgaul.model.*;
import jgaul.utility.Helper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/** This class is used for Select Queries in the database.*/
public abstract class ClientScheduleSelectQry {

    /** This method checks if a user trying to login to the application exists in the database.
     * @param currentUser user login credentials
     * @return true if valid user
     */
    public static boolean isValidUser(User currentUser) {
        String validUserPassword = "";
        try {
            String sql = "SELECT User_ID, Password FROM users WHERE User_Name = ?";
            PreparedStatement userSearch = JDBC.getConnection().prepareStatement(sql);
            userSearch.setString(1, currentUser.getUsername());
            ResultSet resultSet = userSearch.executeQuery();

            while(resultSet.next()) {
                validUserPassword = resultSet.getString("Password");
                int id = resultSet.getInt("User_ID");
                currentUser.setUserID(id);
                currentUser.setValidUsername(true);
            }
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        }
        if (!currentUser.isValidUsername()) {
            return false;
        } else return validUserPassword.equals(currentUser.getPassword());
    }

    /** Selects all customers from the database and adds them to a list.
     * @param allCustomers the list of customers
     */
    public static void selectAllCustomers(ObservableList<Customer> allCustomers) {
        String sql = "SELECT Customer_ID, Customer_Name, Address, Postal_Code, Phone, Division, Country " +
                "FROM customers " +
                "INNER JOIN first_level_divisions ON customers.Division_ID = first_level_divisions.Division_ID " +
                "INNER JOIN countries ON first_level_divisions.Country_ID = countries.Country_ID";
        try {
            PreparedStatement selectCustomers = JDBC.getConnection().prepareStatement(sql);
            ResultSet resultSet = selectCustomers.executeQuery();
            while(resultSet.next()) {
                int customerID = resultSet.getInt("Customer_ID");
                String name = resultSet.getString("Customer_Name");
                String address = resultSet.getString("Address");
                String postalCode = resultSet.getString("Postal_Code");
                String phoneNumber = resultSet.getString("Phone");
                String division = resultSet.getString("Division");
                String country = resultSet.getString("Country");
                Customer nextCustomer = new Customer(customerID, name, address, postalCode, phoneNumber, division, country);
                allCustomers.add(nextCustomer);
            }
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /** Selects all countries from the database and adds them to a list.
     * @param allCountries the list of countries
     */
    public static void selectAllCountries(ObservableList<Country> allCountries) {
        String sql = "SELECT Country, Country_ID FROM countries";
        try {
            PreparedStatement selectCountries = JDBC.getConnection().prepareStatement(sql);
            ResultSet resultSet = selectCountries.executeQuery();
            while(resultSet.next()) {
                String name = resultSet.getString("Country");
                int id = resultSet.getInt("Country_ID");
                allCountries.add(new Country(name, id));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /** Selects all contacts from the database and adds them to a list.
     * @param allContacts the contact list
     */
    public static void selectAllContacts(ObservableList<Contact> allContacts) {
        String sql = "SELECT Contact_ID, Contact_Name FROM contacts";
        try {
            PreparedStatement selectContacts = JDBC.getConnection().prepareStatement(sql);
            ResultSet resultSet = selectContacts.executeQuery();
            while(resultSet.next()) {
                String name = resultSet.getString("Contact_Name");
                int id = resultSet.getInt("Contact_ID");
                allContacts.add(new Contact(id, name));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /** Selects all users from the database and adds them to a list.
     * @param allUsers the user list
     */
    public static void selectAllUsers(ObservableList<User> allUsers) {
        String sql = "SELECT User_ID, User_Name FROM users";
        try {
            PreparedStatement selectUsers = JDBC.getConnection().prepareStatement(sql);
            ResultSet resultSet = selectUsers.executeQuery();
            while(resultSet.next()) {
                String name = resultSet.getString("User_Name");
                int id = resultSet.getInt("User_ID");
                allUsers.add(new User(name, "restricted", id));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /** Selects all divisions from the database and adds them to a list.
     * @param allDivisions the division list
     */
    public static void selectAllDivisions(ObservableList<Division> allDivisions) {
        String sql = "SELECT Division_ID, Division, Country_ID FROM first_level_divisions";
        try {
            PreparedStatement selectDivisions = JDBC.getConnection().prepareStatement(sql);
            ResultSet resultSet = selectDivisions.executeQuery();
            while(resultSet.next()) {
                String name = resultSet.getString("Division");
                int countryID = resultSet.getInt("Country_ID");
                int divisionID = resultSet.getInt("Division_ID");
                allDivisions.add(new Division(name, countryID, divisionID));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /** Selects all appointments from the database and adds them to a list.
     * @param allAppointments the appointment list
     */
    public static void selectAllAppointments(ObservableList<Appointment> allAppointments) {
        String sql = "SELECT Appointment_ID, Title, Description, Location, Type, Start, End, Customer_ID, User_ID, contacts.Contact_ID, Contact_Name " +
                "FROM appointments " +
                "INNER JOIN contacts ON appointments.Contact_ID = contacts.Contact_ID";
        try {
            PreparedStatement selectAppointments = JDBC.getConnection().prepareStatement(sql);
            ResultSet resultSet = selectAppointments.executeQuery();
            while(resultSet.next()) {
                int appointmentID = resultSet.getInt("Appointment_ID");
                String title = resultSet.getString("Title");
                String description = resultSet.getString("Description");
                String location = resultSet.getString("Location");
                String type = resultSet.getString("Type");
                LocalDateTime start = resultSet.getObject("Start", LocalDateTime.class);
                LocalDateTime end = resultSet.getObject("End", LocalDateTime.class);
                int customerID = resultSet.getInt("Customer_ID");
                int userID = resultSet.getInt("User_ID");
                int contactID = resultSet.getInt("Contact_ID");
                String contactName = resultSet.getString("Contact_Name");

                LocalDateTime startConvertedDateTime = Helper.convertToUserTime(start);
                LocalDateTime endConvertedDateTime = Helper.convertToUserTime(end);

                Appointment nextAppointment = new Appointment(appointmentID, title, description, location, type,
                        contactName, startConvertedDateTime, endConvertedDateTime, customerID, userID, contactID);
                allAppointments.add(nextAppointment);
            }
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /** Checks if an appointment time conflicts with an existing appointment in the database.
     * @param customer the customer for the appointment
     * @param appointment the appointment time
     * @param appointmentID the primary key of the appointment being checked
     * @return true if there is a time conflict
     */
    public static boolean checkAppointmentsConflicts(Customer customer, LocalDateTime appointment, int appointmentID) {
        int count = 0;
        String sql = "SELECT * FROM appointments " +
                "WHERE Not Appointment_ID = ? AND Customer_ID = ? AND ? BETWEEN Start AND End";
        try {
            PreparedStatement selectAppointments = JDBC.getConnection().prepareStatement(sql);
            selectAppointments.setInt(1, appointmentID);
            selectAppointments.setInt(2, customer.getCustomerID());
            selectAppointments.setString(3, Timestamp.valueOf(appointment).toString());
            ResultSet resultSet = selectAppointments.executeQuery();
            while(resultSet.next()) {
                count += 1;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return count > 0;
    }

    /** Checks if an appointment time conflicts with an existing appointment in the database.
     * @param customer the customer for the appointment
     * @param startTime the appointment start time
     * @param endTime the appointment end time
     * @param appointmentID the primary key of the appointment being checked
     * @param checkStart a boolean flag to indicate if the start or end time of an existing appointment should be checked
     * @return true if there is a time conflict
     */
    public static boolean checkAppointmentConflicts(Customer customer, LocalDateTime startTime, LocalDateTime endTime,
                                                    boolean checkStart, int appointmentID) {
        int count = 0;
        String sql;
        if (checkStart) {
            sql = "SELECT * FROM appointments " +
                    "WHERE NOT Appointment_ID = ? AND Customer_ID = ? AND Start BETWEEN ? AND ?";
        } else {
            sql = "SELECT * FROM appointments " +
                    "WHERE NOT Appointment_ID = ? AND Customer_ID = ? AND End BETWEEN ? AND ?";
        }
        try {
            PreparedStatement selectAppointments = JDBC.getConnection().prepareStatement(sql);
            selectAppointments.setInt(1, appointmentID);
            selectAppointments.setInt(2, customer.getCustomerID());
            selectAppointments.setString(3, Timestamp.valueOf(startTime).toString());
            selectAppointments.setString(4, Timestamp.valueOf(endTime).toString());
            ResultSet resultSet = selectAppointments.executeQuery();
            while(resultSet.next()) {
                count += 1;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return count > 0;
    }

    /** This method is used to check if a customer has any appointments before they can be deleted.
     * @param customerID the customer ID
     * @return true if appointments exist
     */
    public static boolean checkCustomerAppointments(int customerID) {
        String sql = "SELECT * FROM appointments WHERE Customer_ID = ?";
        int count = 0;
        try {
            PreparedStatement selectAppointments = JDBC.getConnection().prepareStatement(sql);
            selectAppointments.setInt(1, customerID);
            ResultSet resultSet = selectAppointments.executeQuery();
            while(resultSet.next()) {
                count += 1;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return count > 0;
    }

    /** Counts the number of appointments by type for each month.
     * @param month the month
     * @param appointmentType the type
     * @return the total number of appointments
     */
    public static int selectAptByTypeAndMonth(String month, String appointmentType) {
        String sql = "SELECT COUNT(*) AS NUM FROM appointments " +
                "WHERE Type = ? AND monthname(START) = ?"; //monthname is not a typo. It is the correct syntax for mysql.
        int count = 0;
        try {
            PreparedStatement selectAppointments = JDBC.getConnection().prepareStatement(sql);
            selectAppointments.setString(1, appointmentType);
            selectAppointments.setString(2, month);
            ResultSet resultSet = selectAppointments.executeQuery();
            while(resultSet.next()) {
                count = resultSet.getInt("NUM");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return count;
    }

    /** Counts the amount of appointments for a given customer by a given type
     * @param customerID the customer ID
     * @param appointmentType the appointment type
     * @return the amount of appointments
     */
    public static int selectCustomerAptByType(int customerID, String appointmentType) {
        String sql = "SELECT COUNT(*) AS NUM FROM appointments " +
                "WHERE Type = ? AND Customer_ID = ?";
        int count = 0;
        try {
            PreparedStatement selectAppointments = JDBC.getConnection().prepareStatement(sql);
            selectAppointments.setString(1, appointmentType);
            selectAppointments.setInt(2, customerID);
            ResultSet resultSet = selectAppointments.executeQuery();
            while(resultSet.next()) {
                count = resultSet.getInt("NUM");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return count;
    }

}
