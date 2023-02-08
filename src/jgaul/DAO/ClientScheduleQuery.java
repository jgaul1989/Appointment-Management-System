package jgaul.DAO;

import jgaul.model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
}
