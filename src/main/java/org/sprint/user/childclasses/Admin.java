package org.sprint.user.childclasses;

public class Admin extends User {
    public Admin(int userId, String username, String hashedPassword, String email, String phoneNumber, String address) {
        super(userId, username, hashedPassword, email, phoneNumber, address, "Admin");
    }
}
