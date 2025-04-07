package org.sprint.memberships;

import java.sql.Date;

public class Membership {
    private final int membershipId;
    private String membershipType;
    private String membershipDescription;
    private double membershipCost;
    private int memberId;
    private final Date purchaseDate;

    public Membership(int membershipId, String membershipType, String membershipDescription, double membershipCost, int memberId, Date purchaseDate) {
        this.membershipId = membershipId;
        this.membershipType = membershipType;
        this.membershipDescription = membershipDescription;
        this.membershipCost = membershipCost;
        this.memberId = memberId;
        this.purchaseDate = purchaseDate;
    }

    // get membership info from database and set / insert into database
    public int getMembershipId() {
        return membershipId;
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

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    @Override
    public String toString() {
        return "Membership ID: " + membershipId + ", Membership Type: " + membershipType + ", Membership Description: " + membershipDescription + ", Membership Cost: " + membershipCost + ", Member ID: " + memberId + ", Purchase Date: " + purchaseDate;
    }
}
