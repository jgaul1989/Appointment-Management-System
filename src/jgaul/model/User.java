package jgaul.model;

public class User {
    private String username;
    private String password;
    private boolean isValidUsername = false;
    private boolean isValidPassword = false;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isValidUsername() {
        return isValidUsername;
    }

    public void setValidUsername(boolean validUsername) {
        isValidUsername = validUsername;
    }

    public boolean isValidPassword() {
        return isValidPassword;
    }

    public void setValidPassword(boolean validPassword) {
        isValidPassword = validPassword;
    }
}
