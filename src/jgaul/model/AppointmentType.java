package jgaul.model;

/** This class is used to store the appointment type as an object for JAVAFX combo-boxes.*/
public class AppointmentType {

    private final String type;

    public AppointmentType(String type) {
        this.type = type;
    }

    /** Gets the type of appointment.
     * @return the type
     */
    public String getType() {
        return type;
    }

    /** Overridden toString method for javaFX combo-boxes.
     * @return the type
     */
    @Override
    public String toString() {
        return type;
    }
}
