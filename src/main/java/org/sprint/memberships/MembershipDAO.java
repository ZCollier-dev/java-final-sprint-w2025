package org.sprint.memberships;

import org.sprint.database.DBConnection;

import java.sql.*;

public class MembershipDAO {

    public void addMembership(Membership membership) throws SQLException {
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
}
