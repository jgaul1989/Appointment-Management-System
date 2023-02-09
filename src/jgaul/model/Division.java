package jgaul.model;

public class Division {
    private String name;
    private int countryID;

    public Division(String name, int countryID) {
        this.name = name;
        this.countryID = countryID;
    }

    public String getName() {
        return name;
    }


    public int getCountryID() {
        return countryID;
    }

    @Override
    public String toString() {
        return getName();
    }
}
