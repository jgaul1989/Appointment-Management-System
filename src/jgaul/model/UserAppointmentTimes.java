package jgaul.model;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class UserAppointmentTimes {

    private final LocalTime time;

    public UserAppointmentTimes(LocalTime time) {
        this.time = time;
    }

    public LocalTime getTime() {
        return time;
    }

    public String toString() {
        String timePattern= "hh:mm a";
        DateTimeFormatter formattedPattern = DateTimeFormatter.ofPattern(timePattern);
        return formattedPattern.format(time);
    }
}
