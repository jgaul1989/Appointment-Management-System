package jgaul.model;

/** This class is used to represent users of the application.*/
public class User {
    private String username;
    private String password;
    private int userID;
    private boolean isValidUsername = false;

    public User(String username, String password) {
        this(username, password, 0);
    }

    public User(String username, String password, int userID) {
        this.username = username;
        this.password = password;
        this.userID = userID;
    }

    /** Sets the userID.
     * @param userID
     */
    public void setUserID(int userID) {
        this.userID = userID;
    }

    /** Gets the username.
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /** Gets the password.
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /** Gets the userID
     * @return the userID
     */
    public int getUserID() {
        return userID;
    }

    /** Returns true if the username is found in the database.
     * @return isValidUsername
     */
    public boolean isValidUsername() {
        return isValidUsername;
    }

    /** Sets isValidUsername to true or false if found in the database.
     * @param validUsername
     */
    public void setValidUsername(boolean validUsername) {
        isValidUsername = validUsername;
    }

    /** Overrides the toString method for javafx tables and combo-boxes.
     * @return the userID
     */
    @Override
    public String toString() {
        return String.valueOf(userID);
    }
}
