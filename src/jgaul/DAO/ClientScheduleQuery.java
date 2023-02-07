package jgaul.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class ClientScheduleQuery {

    public static void getAllAppointments() {

        try {
            String sql = "SELECT * FROM appointments";
            PreparedStatement appointmentQuery = JDBC.getConnection().prepareStatement(sql);
            ResultSet resultSet = appointmentQuery.executeQuery();

            while(resultSet.next()) {
                int id = resultSet.getInt("Appointment_ID");
                String title = resultSet.getString("Title");
                System.out.println(id + " " + title);
            }
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        }

    }
}
