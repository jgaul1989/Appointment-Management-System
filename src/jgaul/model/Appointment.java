package jgaul.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class Appointment {

    private int appointmentID;
    private String title;
    private String description;
    private String location;
    private AppointmentType type;
    private Contact contact;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private int customerID;
    private int userID;

    public Appointment(int appointmentID, String title, String description, String location, String type,
                       String contact, LocalDateTime startTime, LocalDateTime endTime, int customerID, int userID, int contactID) {
        this.appointmentID = appointmentID;
        this.title = title;
        this.description = description;
        this.location = location;
        this.type = new AppointmentType(type);
        this.contact = new Contact(contactID, contact);
        this.startTime = startTime;
        this.endTime = endTime;
        this.customerID = customerID;
        this.userID = userID;
    }

    public int getAppointmentID() {
        return appointmentID;
    }

    public void setAppointmentID(int appointmentID) {
        this.appointmentID = appointmentID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getType() {
        return type.getType();
    }

    public String getContact() {
        return contact.getName();
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public String getStartTime() {
        return DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT).format(startTime);
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT).format(endTime);
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }
}
