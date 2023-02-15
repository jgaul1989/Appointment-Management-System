package jgaul.model;

public class User {
    private String username;
    private String password;
    private boolean isValidUsername = false;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public boolean isValidUsername() {
        return isValidUsername;
    }

    public void setValidUsername(boolean validUsername) {
        isValidUsername = validUsername;
    }

}
