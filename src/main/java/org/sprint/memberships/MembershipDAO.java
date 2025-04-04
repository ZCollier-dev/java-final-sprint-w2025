package org.sprint.memberships;

import org.sprint.database.DBConnection;
import org.sprint.user.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MembershipDAO {
    //C
    public void addMembership(Membership membership) throws SQLException { //Adds a new membership to the database
        String sql = "INSERT INTO memberships (member_id, type, description, cost, purchase_date) VALUES (?, ?, ?, ?, ?)";
        try (
            Connection conn = DBConnection.getConnection();
            PreparedStatement prepstat = conn.prepareStatement(sql)
        )
        {
            prepstat.setInt(1, membership.getMemberId());
            prepstat.setString(2, membership.getMembershipType());
            prepstat.setString(3, membership.getMembershipDescription());
            prepstat.setDouble(4, membership.getMembershipCost());
            prepstat.setDate(5, membership.getPurchaseDate());
            prepstat.executeUpdate();
        }
    }
    //R - One for members, all for Admins
    public List<Membership> getAllMemberships() throws SQLException { //Gets all memberships. Admins only.
        String sql = "SELECT * FROM memberships"; //needs to return a list
        List<Membership> resultList = new ArrayList<>();
        try (
                Connection conn = DBConnection.getConnection();
                PreparedStatement prepstat = conn.prepareStatement(sql)
        ) {
            try (ResultSet resultset = prepstat.executeQuery()) {
                while (resultset.next()) {
                    Membership entry = new Membership(
                            resultset.getInt("id"),
                            resultset.getString("type"),
                            resultset.getString("description"),
                            resultset.getDouble("cost"),
                            resultset.getInt("member_id"),
                            resultset.getDate("purchase_date")
                    );
                    resultList.add(entry);
                }
                return resultList;
            }
        }
    }

    public Membership getMembershipByMemberId(int memberId) throws SQLException { //Gets one membership based on member id.
        String sql = "SELECT * FROM memberships WHERE member_id = ?";
        try (
                Connection conn = DBConnection.getConnection();
                PreparedStatement prepstat = conn.prepareStatement(sql)
        ) {
            prepstat.setInt(1, memberId);
            try (ResultSet resultset = prepstat.executeQuery()) {
                if (resultset.next()) {
                    return new Membership(
                            resultset.getInt("id"),
                            resultset.getString("type"),
                            resultset.getString("description"),
                            resultset.getDouble("cost"),
                            resultset.getInt("member_id"),
                            resultset.getDate("purchase_date")
                    );
                }
            }
        }
        return null;
    }
    //U
    public void updateMembership(Membership membership) throws SQLException { //Updates a membership with new data from a Membership object
        String sql = "UPDATE memberships SET type = ?, description = ?, cost = ?, purchase_date = ? WHERE id = ?";
        try (
                Connection conn = DBConnection.getConnection();
                PreparedStatement prepstat = conn.prepareStatement(sql)
        )
        {
            prepstat.setString(1, membership.getMembershipType());
            prepstat.setString(2, membership.getMembershipDescription());
            prepstat.setDouble(3, membership.getMembershipCost());
            prepstat.setDate(4, membership.getPurchaseDate());
            prepstat.setInt(5, membership.getMembershipId());
            prepstat.executeUpdate();
        }
    }
    //D
    public boolean deleteMembershipById(int id) throws SQLException { //Deletes a membership from the database via id.
        String sql = "DELETE FROM memberships WHERE id = ?";
        try (
                Connection conn = DBConnection.getConnection();
                PreparedStatement prepstat = conn.prepareStatement(sql)
        )
        {
            prepstat.setInt(1, id);
            int rowsAffected = prepstat.executeUpdate();
            if (rowsAffected > 0){
                return true;
            }
            return false;
        }
    }
}
