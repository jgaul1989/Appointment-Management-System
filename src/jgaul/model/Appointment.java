package jgaul.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

/** This class is used to store information about each appointment.*/
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

    /** Gets the appointment ID.
     * @return the appointmentID
     */
    public int getAppointmentID() {
        return appointmentID;
    }

    /** Gets the title of the appointment.
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /** Gets the appointment description.
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /** Gets the appointment location.
     * @return the location
     */
    public String getLocation() {
        return location;
    }

    /** Gets the type of appointment as a strings for table views.
     * @return the type
     */
    public String getType() {
        return type.getType();
    }

    /** Gets the appointment type as an object for combo boxes.
     * @return the type
     */
    public AppointmentType getTypeAsType() {
        return type;
    }

    /** Gets the appointment contact name as a string for table views.
     * @return the contact name
     */
    public String getContact() {
        return contact.getName();
    }

    /** Gets the appointment contact as a contact object.
     * @return the contact
     */
    public Contact getContactAsContact() {
        return contact;
    }

    /** Gets the start time as a formatted string for table views.
     * @return the formatted start time
     */
    public String getStartTime() {
        return DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT).format(startTime);
    }

    /** Gets the appointment start time as LocalDateTime
     * @return the startTime
     */
    public LocalDateTime getStartDateAsDateTime() {
        return startTime;
    }

    /** Gets the appointment end time as a formatted string for table views.
     * @return the formatted end time
     */
    public String getEndTime() {
        return DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT).format(endTime);
    }

    /** Gets the appointment end time as LocalDateTime.
     * @return the endTime
     */
    public LocalDateTime getEndDateAsDateTime() {
        return endTime;
    }

    /** Gets the appointment customer ID.
     * @return the customerID
     */
    public int getCustomerID() {
        return customerID;
    }

    /** Gets the UserID.
     * @return the userID
     */
    public int getUserID() {
        return userID;
    }

}
