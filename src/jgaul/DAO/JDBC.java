package jgaul.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/** This class is used for opening and closing the database connection.*/
public abstract class JDBC {
    private static final String protocol = "jdbc";
    private static final String vendor = ":mysql:";
    private static final String location = "//localhost/";
    private static final String databaseName = "client_schedule";
    private static final String jdbcUrl = protocol + vendor + location + databaseName + "?connectionTimeZone = SERVER"; // LOCAL
    private static final String driver = "com.mysql.cj.jdbc.Driver"; // Driver reference
    private static final String userName = "sqlUser"; // Username
    private static final String password = "Passw0rd!"; // Password
    private static Connection connection;

    /** This method is used connecting to the database.*/
    public static void makeConnection() {
        try {
            Class.forName(driver); // Locate Driver
            connection = DriverManager.getConnection(jdbcUrl, userName, password);
        } catch (Exception e) {
            System.out.println("Error:" + e.getMessage());
        }
    }
    /** Gets the database connection.
     * @return the connection
     */
    public static Connection getConnection() {
        return connection;
    }

    /** Closes the database connection.*/
    public static void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}