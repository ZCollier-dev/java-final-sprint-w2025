package org.sprint.user.childclasses;

public class Member extends User {
    public Member(int userId, String username, String hashedPassword, String email, String phoneNumber, String address) {
        super(userId, username, hashedPassword, email, phoneNumber, address, "Member");
    }
}
