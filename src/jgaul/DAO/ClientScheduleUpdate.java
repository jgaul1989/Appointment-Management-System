package jgaul.DAO;

import jgaul.model.Appointment;
import jgaul.model.Division;
import jgaul.utility.Helper;

import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/** This class is used to update information in the database.*/
public abstract class ClientScheduleUpdate {

    /** Modifies information about customers in the database.
     * @param customerId the id
     * @param name the name
     * @param address the address
     * @param postalCode the postal code
     * @param division the division
     * @param phone the phone number
     */
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
            updateStatement.setString(6, Helper.getCurrentUser().getUsername());
            updateStatement.setInt(7, division.getDivisionID());
            updateStatement.setInt(8, customerId);
            updateStatement.executeUpdate();
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /** Modifies appointment information in the database.
     * @param appointmentToModify the appointment to modify
     */
    public static void modifyAppointment(Appointment appointmentToModify) {
        String sql ="Update appointments SET Title = ?, Description = ?, Location = ?, " +
                "Type = ?, Start = ?, End = ?, Last_Update = ?, Last_Updated_By = ?, Customer_ID = ?, User_ID = ?, Contact_ID = ? " +
                "WHERE Appointment_ID = ?";
        try{
            PreparedStatement updateStatement = JDBC.getConnection().prepareStatement(sql);
            updateStatement.setString(1, appointmentToModify.getTitle());
            updateStatement.setString(2, appointmentToModify.getDescription());
            updateStatement.setString(3, appointmentToModify.getLocation());
            updateStatement.setString(4, appointmentToModify.getType());
            updateStatement.setString(5, appointmentToModify.getStartDateAsDateTime().toString());
            updateStatement.setString(6, appointmentToModify.getEndDateAsDateTime().toString());
            updateStatement.setTimestamp(7, Timestamp.valueOf(LocalDateTime.now()));
            updateStatement.setString(8, Helper.getCurrentUser().getUsername());
            updateStatement.setInt(9, appointmentToModify.getCustomerID());
            updateStatement.setInt(10, appointmentToModify.getUserID());
            updateStatement.setInt(11, appointmentToModify.getContactAsContact().getContactID());
            updateStatement.setInt(12, appointmentToModify.getAppointmentID());
            updateStatement.executeUpdate();
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
