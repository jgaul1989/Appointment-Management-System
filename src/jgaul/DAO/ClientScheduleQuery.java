package jgaul.DAO;

import javafx.scene.control.Label;
import jgaul.controller.loginController;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class ClientScheduleQuery {

    public static boolean loginValidation(String username, String password, Label errorMessage) {
        String validUserPassword = "";
        boolean userNameFound = false;

        try {
            String sql = "SELECT Password FROM users WHERE User_Name = ?";
            PreparedStatement userSearch = JDBC.getConnection().prepareStatement(sql);
            userSearch.setString(1, username);
            ResultSet resultSet = userSearch.executeQuery();

            while(resultSet.next()) {
                validUserPassword = resultSet.getString("Password");
                userNameFound = true;
            }
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        }
        if (!userNameFound) {
            errorMessage.setText("Invalid username");
            return false;
        } else if (!(validUserPassword.equals(password))) {
            errorMessage.setText("Invalid password");
            return false;
        }
        return true;
    }
}
