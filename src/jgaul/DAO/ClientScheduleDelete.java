package jgaul.DAO;

import java.sql.PreparedStatement;

/** This class contains SQL statements for deleting objects from the database.*/
public abstract class ClientScheduleDelete {

    /** Used to delete appointments from the database.
     * @param appointmentID appointment to be deleted
     */
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

    /** Used to delete customers from the database.
     * @param customerID customer to be deleted
     */
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
