package jgaul.model;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/** This class is used to create available business hour times for customer appointments.*/
public class UserAppointmentTimes {

    private final LocalTime time;

    public UserAppointmentTimes(LocalTime time) {
        this.time = time;
    }

    /** Gets the time.
     * @return the time
     */
    public LocalTime getTime() {
        return time;
    }

    /** Overrides the toString method for javaFX combo-boxes and tables.*/
    public String toString() {
        String timePattern= "hh:mm a";
        DateTimeFormatter formattedPattern = DateTimeFormatter.ofPattern(timePattern);
        return formattedPattern.format(time);
    }
}
