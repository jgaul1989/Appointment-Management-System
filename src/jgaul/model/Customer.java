package jgaul.model;

/** This class is used for storing information about customers.*/
public class Customer {
    private final int customerID;
    private final String customerName;
    private final String address;
    private final String postalCode;
    private final String phoneNumber;
    private final String division;
    private final String country;

    public Customer(int customerID, String customerName, String address, String postalCode,
                    String phoneNumber, String division, String country) {
        this.customerID = customerID;
        this.customerName = customerName;
        this.address = address;
        this.postalCode = postalCode;
        this.phoneNumber = phoneNumber;
        this.division = division;
        this.country = country;
    }

    /** Gets the customerID.
     * @return the customerID
     */
    public int getCustomerID() {
        return customerID;
    }

    /** Gets the name of the customer.
     * @return the customerName
     */
    public String getCustomerName() {
        return customerName;
    }

    /** Gets the address of the customer.
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /** Gets the postal code of the customer.
     * @return the postalCode
     */
    public String getPostalCode() {
        return postalCode;
    }

    /** Gets the phone number of the customer.
     * @return the phoneNumber
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /** Gets the state or province of the customer.
     * @return the division
     */
    public String getDivision() {
        return division;
    }

    /** Gets the country of the customer.
     * @return the country
     */
    public String getCountry() {
        return country;
    }

    /** Overrides the toString method for table views
     * @return the customerID
     */
    @Override
    public String toString() {
        return String.valueOf(customerID);
    }
}
