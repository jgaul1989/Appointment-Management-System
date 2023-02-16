package jgaul.model;

public class AppointmentType {

    private String type;

    public AppointmentType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return type;
    }
}
