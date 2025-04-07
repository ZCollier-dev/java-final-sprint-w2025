package org.sprint.user;

public class User {
    private int userId;
    private String username;
    private String password;
    private String email;
    private String phoneNumber;
    private String address;
    private String role;

    public User(int userId, String username, String password, String email, String phoneNumber, String address, String role) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.role = role;
    }
    
    // Constructor for new users (doesn't require userId as it wouldn't be created until after registration)
    public User(String username, String password, String email, String phoneNumber, String address, String role) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.role = role;
    }

    // get all user information from database
    public int getUserId() {
        return userId;
    }

    public String getUserName() {
        return username;
    }

    public String getUsername() {
        return username;
    }

    public String getHashedPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public String getUserRole() {
        return role;
    }

    public String getRole() {
        return role;
    }

    // set user info in database
    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setUserName(String username) {
        this.username = username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setHashedPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setUserRole(String role) {
        this.role = role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    // to string for output in gymapp
    @Override
    public String toString() {
        return "User ID: " + userId + ", Username: " + username + ", Role: " + role + ", Email: " + email + ", Phone: " + phoneNumber + ", Address: " + address;
    }
}
