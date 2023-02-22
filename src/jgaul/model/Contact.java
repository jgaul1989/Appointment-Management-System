package jgaul.model;

import java.util.Objects;

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
