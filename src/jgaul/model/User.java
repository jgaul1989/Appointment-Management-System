package jgaul.model;

public class User {
    private String username;
    private String password;
    private int userID;
    private boolean isValidUsername = false;

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public User(String username, String password) {
        this(username, password, 0);
    }

    public User(String username, String password, int userID) {
        this.username = username;
        this.password = password;
        this.userID = userID;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getUserID() {
        return userID;
    }

    public boolean isValidUsername() {
        return isValidUsername;
    }

    public void setValidUsername(boolean validUsername) {
        isValidUsername = validUsername;
    }

    @Override
    public String toString() {
        return String.valueOf(userID);
    }
}
