package jgaul.model;

/** This class is used for storing information about countries.*/
public  class Country {
    private final String countryName;
    private final int countryID;

    public Country(String countryName, int countryID) {
        this.countryName = countryName;
        this.countryID = countryID;
    }

    /** Gets the name of the country.
     * @return the countryName
     */
    public String getCountryName() {
        return countryName;
    }

    /** Gets the countryID used in the database.
     * @return the countryID
     */
    public int getCountryID() {
        return countryID;
    }

    /** Overridden toString method for combo-boxes in JAVAFX.
     * @return the country name
     */
    @Override
    public String toString() {
        return getCountryName();
    }
}
