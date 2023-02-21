package jgaul.DAO;

import jgaul.utility.Helper;

import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public abstract class ClientScheduleDelete {

    public static void deleteAppointment(int appointmentID) {
        String sql = "DELETE FROM appointments WHERE Appointment_ID = ?";
        try {
            PreparedStatement deleteStatement = JDBC.getConnection().prepareStatement(sql);
            deleteStatement.setInt(1, appointmentID);
            deleteStatement.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void deleteCustomer(int customerID) {
        String sql = "DELETE FROM customers WHERE Customer_ID = ?";
        try {
            PreparedStatement deleteStatement = JDBC.getConnection().prepareStatement(sql);
            deleteStatement.setInt(1, customerID);
            deleteStatement.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
