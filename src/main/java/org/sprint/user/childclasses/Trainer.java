package org.sprint.user.childclasses;

import org.sprint.user.User;

// a trainer is a user
public class Trainer extends User {
    public Trainer(int userId, String username, String hashedPassword, String email, String phoneNumber, String address) {
        super(userId, username, hashedPassword, email, phoneNumber, address, "Trainer");
    }

    @Override
    public String toString() {
        return "Trainer: " + super.toString();
    }
}
