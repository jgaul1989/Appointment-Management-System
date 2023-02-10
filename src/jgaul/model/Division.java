package jgaul.model;

public class Division {
    private final String name;
    private final int countryID;
    private final int divisionID;

    public Division(String name, int countryID, int divisionID) {
        this.name = name;
        this.countryID = countryID;
        this.divisionID = divisionID;
    }

    public String getName() {
        return name;
    }


    public int getCountryID() {
        return countryID;
    }

    public int getDivisionID() {
        return divisionID;
    }

    @Override
    public String toString() {
        return getName();
    }
}
