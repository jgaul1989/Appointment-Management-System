package jgaul.DAO;

import jgaul.model.*;
import jgaul.utility.Helper;

import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public abstract class ClientScheduleInsert {

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
            insertStatement.setString(6, Helper.getCurrentUser().getUsername());
            insertStatement.setTimestamp(7,Timestamp.valueOf(LocalDateTime.now()));
            insertStatement.setString(8, Helper.getCurrentUser().getUsername());
            insertStatement.setInt(9, division.getDivisionID());
            insertStatement.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void insertIntoAppointments(String title, String description, String location, AppointmentType type, LocalDateTime start,
                                              LocalDateTime end, Customer customer, User user, Contact contact) {

        String sql = "INSERT into appointments(Title, Description, Location, Type, Start, End, Create_Date, Created_By, " +
                "Last_Update, Last_Updated_By, Customer_ID, User_ID, Contact_ID) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement insertStatement = JDBC.getConnection().prepareStatement(sql);
            insertStatement.setString(1, title);
            insertStatement.setString(2, description);
            insertStatement.setString(3, location);
            insertStatement.setString(4, type.getType());
            insertStatement.setString(5, start.toString());
            insertStatement.setString(6, end.toString());
            insertStatement.setTimestamp(7, Timestamp.valueOf(LocalDateTime.now()));
            insertStatement.setString(8, Helper.getCurrentUser().getUsername());
            insertStatement.setTimestamp(9, Timestamp.valueOf(LocalDateTime.now()));
            insertStatement.setString(10, Helper.getCurrentUser().getUsername());
            insertStatement.setInt(11, customer.getCustomerID());
            insertStatement.setInt(12, user.getUserID());
            insertStatement.setInt(13, contact.getContactID());
            insertStatement.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
