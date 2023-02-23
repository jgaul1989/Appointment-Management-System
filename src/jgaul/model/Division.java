package jgaul.model;

/** This class contains information about divisions which are states or provinces.*/
public class Division {
    private final String name;
    private final int countryID;
    private final int divisionID;

    public Division(String name, int countryID, int divisionID) {
        this.name = name;
        this.countryID = countryID;
        this.divisionID = divisionID;
    }

    /** Gets the division name.
     * @return the name
     */
    public String getName() {
        return name;
    }

    /** Gets the countryID used in the database.
     * @return the countryID
     */
    public int getCountryID() {
        return countryID;
    }

    /** Gets the divisionID used in the database.
     * @return the divisionID
     */
    public int getDivisionID() {
        return divisionID;
    }

    /** Overridden toString method for javaFX combo-boxes.
     * @return the name
     */
    @Override
    public String toString() {
        return getName();
    }
}
