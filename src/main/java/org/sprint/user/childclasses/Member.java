package org.sprint.user.childclasses;

import org.sprint.user.User;

// a member is a user
public class Member extends User {
    public Member(int userId, String username, String hashedPassword, String email, String phoneNumber, String address) {
        super(userId, username, hashedPassword, email, phoneNumber, address, "Member");
    }

    @Override
    public String toString() {
        return "Member: " + super.toString();
    }
}
