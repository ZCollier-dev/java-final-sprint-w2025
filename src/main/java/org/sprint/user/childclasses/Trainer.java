package org.sprint.user.childclasses;

public class Trainer extends User {
    public Trainer(int userId, String username, String hashedPassword, String email, String phoneNumber, String address) {
        super(userId, username, hashedPassword, email, phoneNumber, address, "Trainer");
    }
}
