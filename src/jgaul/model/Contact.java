package jgaul.model;

public class Contact {

    private final int contactID;
    private final String name;

    public Contact(int contactID, String name) {
        this.contactID = contactID;
        this.name = name;
    }

    public int getContactID() {
        return contactID;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}
