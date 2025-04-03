package org.sprint.user;

public class User {
    private int userId;
    private String username;
    private String hashedPassword;
    private String email;
    private String phoneNumber;
    private String address;
    private String role; // Role can be admin, trainer, or member

    // Constructor
    public User(int userId, String username, String hashedPassword, String email, String phoneNumber, String address, String role) {
        this.userId = userId;
        this.username = username;
        this.hashedPassword = hashedPassword;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.role = role;
    }

    // Getters and setters
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String toString() {
        return "User ID: " + userId + ", Username: " + username + ", Email: " + email + ", Phone Number: " + phoneNumber + ", Address: " + address + ", Role: " + role;
    }
}
