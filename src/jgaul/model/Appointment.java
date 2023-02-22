package jgaul.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class Appointment {

    private final int appointmentID;
    private final String title;
    private final String description;
    private final String location;
    private final AppointmentType type;
    private final Contact contact;
    private final LocalDateTime startTime;
    private final LocalDateTime endTime;
    private final int customerID;
    private final int userID;

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

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getLocation() {
        return location;
    }

    public String getType() {
        return type.getType();
    }

    public AppointmentType getTypeAsType() {
        return type;
    }

    public String getContact() {
        return contact.getName();
    }

    public Contact getContactAsContact() {
        return contact;
    }

    public String getStartTime() {
        return DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT).format(startTime);
    }

    public LocalDateTime getStartDateAsDateTime() {
        return startTime;
    }

    public String getEndTime() {
        return DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT).format(endTime);
    }

    public LocalDateTime getEndDateAsDateTime() {
        return endTime;
    }


    public int getCustomerID() {
        return customerID;
    }

    public int getUserID() {
        return userID;
    }

}
