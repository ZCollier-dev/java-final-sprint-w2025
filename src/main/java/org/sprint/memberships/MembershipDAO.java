package org.sprint.memberships;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.sprint.database.DBConnection;

public class MembershipDAO {

    public void addMembership(Membership membership) throws SQLException {
        String sql = "INSERT INTO memberships (member_id, type, description, cost, purchase_date) VALUES (?, ?, ?, ?, ?)";
        try (
            Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setInt(1, membership.getMemberId());
            stmt.setString(2, membership.getMembershipType());
            stmt.setString(3, membership.getMembershipDescription());
            stmt.setDouble(4, membership.getMembershipCost());
            stmt.setDate(5, membership.getPurchaseDate());
            stmt.executeUpdate();
        }
    }

    public List<Membership> getAllMemberships() throws SQLException {
        List<Membership> memberships = new ArrayList<>();
        String sql = "SELECT * FROM memberships";
        try (
            Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()
        ) {
            while (rs.next()) {
                Membership membership = new Membership(
                    rs.getInt("id"),
                    rs.getString("type"),
                    rs.getString("description"),
                    rs.getDouble("cost"),
                    rs.getInt("member_id"),
                    rs.getDate("purchase_date")
                );
                memberships.add(membership);
            }
        }
        return memberships;
    }

    public List<Membership> getMembershipsByMemberId(int memberId) throws SQLException {
        List<Membership> memberships = new ArrayList<>();
        String sql = "SELECT * FROM memberships WHERE member_id = ?";
        try (
            Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setInt(1, memberId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Membership membership = new Membership(
                        rs.getInt("id"),
                        rs.getString("type"),
                        rs.getString("description"),
                        rs.getDouble("cost"),
                        rs.getInt("member_id"),
                        rs.getDate("purchase_date")
                    );
                    memberships.add(membership);
                }
            }
        }
        return memberships;
    }

    public double getTotalRevenue() throws SQLException {
        String sql = "SELECT SUM(cost) AS total FROM memberships";
        try (
            Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()
        ) {
            if (rs.next()) {
                return rs.getDouble("total");
            }
        }
        return 0.0;
    }

    public double getTotalSpentByMember(int memberId) throws SQLException {
        String sql = "SELECT SUM(cost) AS total FROM memberships WHERE member_id = ?";
        try (
            Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setInt(1, memberId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getDouble("total");
                }
            }
        }
        return 0.0;
    }

    public void updateMembership(Membership membership) throws SQLException {
        String sql = "UPDATE memberships SET type = ?, description = ?, cost = ?, purchase_date = ? WHERE id = ?";
        try (
            Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setString(1, membership.getMembershipType());
            stmt.setString(2, membership.getMembershipDescription());
            stmt.setDouble(3, membership.getMembershipCost());
            stmt.setDate(4, membership.getPurchaseDate());
            stmt.setInt(5, membership.getMembershipId());
            stmt.executeUpdate();
        }
    }

    public void deleteMembershipById(int id) throws SQLException {
        String sql = "DELETE FROM memberships WHERE id = ?";
        try (
            Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}
