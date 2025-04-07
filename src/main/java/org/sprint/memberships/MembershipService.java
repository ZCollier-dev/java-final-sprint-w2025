package org.sprint.memberships;

import java.sql.SQLException;
import java.util.List;

public class MembershipService {
    private final MembershipDAO membershipDAO;

    public MembershipService() {
        this.membershipDAO = new MembershipDAO();
    }
// adding a membership
    public void addMembership(Membership membership) {
        try {
            membershipDAO.addMembership(membership);
            System.out.println("Membership added!");
        } catch (SQLException exception) {
            System.out.println("Error: unable to add membership: " + exception.getMessage());
        }
    }
// shows ALL memberships in database
    public void displayAllMemberships() {
        try {
            List<Membership> memberships = membershipDAO.getAllMemberships();
            if (memberships.isEmpty()) {
                System.out.println("No memberships found.");
            } else {
                System.out.println("All gym memberships:");
                for (Membership m : memberships) {
                    System.out.println(m);
                }
            }
        } catch (SQLException exception) {
            System.out.println("Error: unable to retrieve memberships: " + exception.getMessage());
        }
    }
// gets a membership FROM a member id
    public void displayMembershipsByMemberId(int memberId) {
        try {
            List<Membership> memberships = membershipDAO.getMembershipsByMemberId(memberId);
            if (memberships.isEmpty()) {
                System.out.println("No memberships found for member ID: " + memberId);
            } else {
                System.out.println("Memberships for member ID " + memberId + ":");
                for (Membership m : memberships) {
                    System.out.println(m);
                }
            }
        } catch (SQLException exception) {
            System.out.println("Unable to retrieve memberships: " + exception.getMessage());
        }
    }
// display total revenue from memberships (for trainers)
    public void displayTotalRevenue() {
        try {
            double total = membershipDAO.getTotalRevenue();
            System.out.printf("Total revenue from all memberships: $%.2f%n", total);
        } catch (SQLException exception) {
            System.out.println("Unable to calculate revenue: " + exception.getMessage());
        }
    }
// show the total amt of money spent by a nember
    public void displayTotalSpentByMember(int memberId) {
        try {
            double total = membershipDAO.getTotalSpentByMember(memberId);
            System.out.printf("You have spent a total of $%.2f on memberships.%n", total);
        } catch (SQLException exception) {
            System.out.println("Unable to calculate total expenses: " + exception.getMessage());
        }
    }

    // update the membership
    public void updateMembership(Membership membership) {
        try {
            membershipDAO.updateMembership(membership);
            System.out.println("Membership updated successfully!");
        } catch (SQLException exception) {
            System.out.println("Unable to update membership: " + exception.getMessage());
        }
    }

    // delete a membership
    public void deleteMembership(int membershipId) {
        try {
            membershipDAO.deleteMembershipById(membershipId);
            System.out.println("Membership deleted successfully!");
        } catch (SQLException exception) {
            System.out.println("Unable to delete membership: " + exception.getMessage());
        }
    }
}
