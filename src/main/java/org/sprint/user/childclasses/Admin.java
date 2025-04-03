package org.sprint.user.childclasses;

import org.sprint.user.User;

public class Admin extends User {
    public Admin(int userId, String username, String hashedPassword, String email, String phoneNumber, String address) {
        super(userId, username, hashedPassword, email, phoneNumber, address, "Admin");
    }

    @Override
    public String toString() {
        return "Admin: " + super.toString();
    }
}
