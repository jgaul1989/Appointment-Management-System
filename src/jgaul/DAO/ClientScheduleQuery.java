package jgaul.DAO;

import javafx.beans.Observable;
import javafx.collections.ObservableList;
import jgaul.model.Customer;
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
}
