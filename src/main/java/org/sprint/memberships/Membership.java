package org.sprint.memberships;

import java.time.LocalDate;

public class Membership {
    private int membershipId;
    private String membershipType;
    private String membershipDescription;
    private double membershipCost;
    private int memberId; // To link the membership to the user
    private LocalDate purchaseDate;

    // Constructor
    public Membership(int membershipId, String membershipType, String membershipDescription, double membershipCost, int memberId) {
        this.membershipId = membershipId;
        this.membershipType = membershipType;
        this.membershipDescription = membershipDescription;
        this.membershipCost = membershipCost;
        this.memberId = memberId;
        this.purchaseDate = LocalDate.now();
    }

    public Membership(int membershipId, String membershipType, String membershipDescription, double membershipCost, int memberId, LocalDate purchaseDate) {
        this.membershipId = membershipId;
        this.membershipType = membershipType;
        this.membershipDescription = membershipDescription;
        this.membershipCost = membershipCost;
        this.memberId = memberId;
        this.purchaseDate = purchaseDate;
    }

    // Getters and setters
    public int getMembershipId() {
        return membershipId;
    }

    public void setMembershipId(int membershipId) {
        this.memberId = membershipId;
    }

    public String getMembershipType() {
        return membershipType;
    }

    public void setMembershipType(String membershipType) {
        this.membershipType = membershipType;
    }

    public String getMembershipDescription() {
        return membershipDescription;
    }

    public void setMembershipDescription(String membershipDescription) {
        this.membershipDescription = membershipDescription;
    }

    public double getMembershipCost() {
        return membershipCost;
    }

    public void setMembershipCost(double membershipCost) {
        this.membershipCost = membershipCost;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public LocalDate getPurchaseDate() { return purchaseDate; }

    // toString for membership output
    public String toString() {
        return "Membership ID: " + membershipId + ", Membership Type: " + membershipType + ", Membership Description: " + membershipDescription + ", Membership Cost: " + membershipCost + ", Member ID: " + memberId + ", Purchase Date: " + purchaseDate;
    }
}
