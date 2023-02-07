package jgaul.DAO;

import javafx.scene.control.Label;
import jgaul.controller.loginController;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class ClientScheduleQuery {

    public static boolean loginValidation(String username, String password) {
        String validUserPassword = "";
        loginController.isValidUsername = false;

        try {
            String sql = "SELECT Password FROM users WHERE User_Name = ?";
            PreparedStatement userSearch = JDBC.getConnection().prepareStatement(sql);
            userSearch.setString(1, username);
            ResultSet resultSet = userSearch.executeQuery();

            while(resultSet.next()) {
                validUserPassword = resultSet.getString("Password");
                loginController.isValidUsername = true;
            }
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        }
        if (!loginController.isValidUsername) {
            return false;
        } else return validUserPassword.equals(password);
    }
}
