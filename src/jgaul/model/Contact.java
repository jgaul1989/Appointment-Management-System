package jgaul.model;

import java.util.Objects;

/** This class is used to store information about contacts for appointments and customers.*/
public class Contact {

    private final int contactID;
    private final String name;

    public Contact(int contactID, String name) {
        this.contactID = contactID;
        this.name = name;
    }

    /** Gets the contact ID.
     * @return the contactID
     */
    public int getContactID() {
        return contactID;
    }

    /** Gets the contact name.
     * @return the name
     */
    public String getName() {
        return name;
    }

    /** Overridden toString method for combo-boxes.
     * @return the name
     */
    @Override
    public String toString() {
        return name;
    }

    /** Overridden equals method for comparing contacts.*/
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contact contact = (Contact) o;
        return contactID == contact.contactID && Objects.equals(name, contact.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(contactID, name);
    }
}
