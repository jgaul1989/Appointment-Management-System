package jgaul.DAO;

import jgaul.model.Division;
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
}
