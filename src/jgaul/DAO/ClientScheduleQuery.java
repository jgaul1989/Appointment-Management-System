package jgaul.DAO;

import javafx.beans.Observable;
import javafx.collections.ObservableList;
import jgaul.model.*;
import jgaul.utility.Helper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;

public abstract class ClientScheduleQuery {

    public static boolean loginValidation(User currentUser) {
        String validUserPassword = "";

        try {
            String sql = "SELECT Password FROM users WHERE User_Name = ?";
            PreparedStatement userSearch = JDBC.getConnection().prepareStatement(sql);
            userSearch.setString(1, currentUser.getUsername());
            ResultSet resultSet = userSearch.executeQuery();

            while(resultSet.next()) {
                validUserPassword = resultSet.getString("Password");
                currentUser.setValidUsername(true);
            }
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        }
        if (!currentUser.isValidUsername()) {
            return false;
        } else return validUserPassword.equals(currentUser.getPassword());
    }

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

    public static void insertIntoCustomers(String name, String address, String postalCode,
                                           String phone, Division division){

        String sql = "INSERT into customers(Customer_Name, Address, Postal_Code, Phone, Create_Date," +
                " Created_By, Last_Update, Last_Updated_By, Division_ID) " +
                "VALUES (?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement insertStatement = JDBC.getConnection().prepareStatement(sql);
            insertStatement.setString(1, name);
            insertStatement.setString(2, address);
            insertStatement.setString(3, postalCode);
            insertStatement.setString(4, phone);
            insertStatement.setTimestamp(5, Timestamp.valueOf(LocalDateTime.now()));
            insertStatement.setString(6, Helper.getCurrentUser());
            insertStatement.setTimestamp(7,Timestamp.valueOf(LocalDateTime.now()));
            insertStatement.setString(8, Helper.getCurrentUser());
            insertStatement.setInt(9, division.getDivisionID());
            insertStatement.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void modifyCustomer(int customerId, String name, String address, String postalCode,
                                      String phone, Division division) {

        String sql ="Update customers SET Customer_Name = ?, Address = ?, Postal_Code = ?, Phone = ?, " +
                "Last_Update = ?, Last_Updated_By = ?, Division_ID = ? " +
                "WHERE Customer_ID = ?";
        try{
            PreparedStatement updateStatement = JDBC.getConnection().prepareStatement(sql);
            updateStatement.setString(1, name);
            updateStatement.setString(2, address);
            updateStatement.setString(3, postalCode);
            updateStatement.setString(4, phone);
            updateStatement.setTimestamp(5, Timestamp.valueOf(LocalDateTime.now()));
            updateStatement.setString(6, Helper.getCurrentUser());
            updateStatement.setInt(7, division.getDivisionID());
            updateStatement.setInt(8, customerId);
            updateStatement.executeUpdate();
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void selectAllAppointments(ObservableList<Appointment> allAppointments) {

        String sql = "SELECT Appointment_ID, Title, Description, Location, Type, Start, End, Customer_ID, User_ID, Contact_Name " +
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
                String contactName = resultSet.getString("Contact_Name");

                LocalDateTime startConvertedDateTime = Helper.convertToUserTime(start);
                LocalDateTime endConvertedDateTime = Helper.convertToUserTime(end);

                Appointment nextAppointment = new Appointment(appointmentID, title, description, location, type,
                        contactName, startConvertedDateTime, endConvertedDateTime, customerID, userID);
                allAppointments.add(nextAppointment);
            }

        } catch(SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}
