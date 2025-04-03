package org.sprint.memberships;

import java.lang.reflect.Member;
import java.sql.SQLException;
import java.util.List;

import org.sprint.workoutclasses.WorkoutClass;

public class MembershipService {
    private final MembershipDAO membershipDAO;

    public MembershipService() {
        this.membershipDAO = new MembershipDAO();
    }

    // To add a new membership
    public void addMembership(Membership membership) {
        try {
            membershipDAO.addMembership(membership);
            System.out.println("Membership added!");
        } catch (SQLException exception) {
            System.out.println("Error: unable to add membership: " + exception.getMessage());
        }
    }

    // To retrieve all memberships (should only work for admins)
    public void displayAllMemberships() {
        try {
            List<Membership> memberships = membershipDAO.getAllMemberships();
            if (memberships.isEmpty()) {
                System.out.println("No memberships found.");
            } else {
                System.out.println("All gym memberships: ");
                for (Membership m : memberships) {
                    System.out.println(m);
                }
            }
        } catch (SQLException exception) {
            System.out.println("Error: unable to retrieve memberships: " + exception.getMessage());
        }
    }

    // To retreive a membership by ID
    public void displayMembershipByMemberId(int memberId) {
        try {
            Membership membership = membershipDAO.getMembershipByMemberId(memberId);
            if (membership != null) {
                System.out.println("Membership Information: " + membership);
            } else {
                System.out.println("Membership not found.");
            }
        } catch (SQLException exception) {
            System.out.println("Unable to retrieve membership: " + exception.getMessage());
        }
    }

    // To update membership details
    public void updateMembership(Membership membership) {
        try {
            membershipDAO.updateMembership(membership);
            System.out.println("Membership updated successfully!");
        } catch (SQLException exception) {
            System.out.println("Unable to update membership: " + exception.getMessage());
        }
    }

    // To delete a membership by ID
    public void deleteMembership(int membershipId) {
        try {
            boolean deleted = membershipDAO.deleteMembershipById(membershipId);
            if (deleted) {
                System.out.println("Membership deleted successfully!");
            } else {
                System.out.println("Unable to find a member with this ID.");
            }
        } catch (SQLException exception) {
            System.out.println("Unable to delete membership: " + exception.getMessage());
        }
    }
}
