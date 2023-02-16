package jgaul.model;

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

    public int getCustomerID() {
        return customerID;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getAddress() {
        return address;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getDivision() {
        return division;
    }

    public String getCountry() {
        return country;
    }

    @Override
    public String toString() {
        return String.valueOf(customerID);
    }
}
