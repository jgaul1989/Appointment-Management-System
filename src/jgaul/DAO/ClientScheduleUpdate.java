package jgaul.DAO;

import jgaul.model.Division;
import jgaul.utility.Helper;

import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public abstract class ClientScheduleUpdate {

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
}
