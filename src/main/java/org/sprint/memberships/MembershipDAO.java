package org.sprint.memberships;

import org.sprint.database.DBConnection;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MembershipDAO {

    public void addMembership(Membership membership) throws SQLException {
        String sql = "INSERT INTO memberships (member_id, type, description, cost, purchase_date) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement prepstat = conn.prepareStatement(sql))
        {
            prepstat.setInt(1, membership.getUserId());
            // etc etc
            // prepstat.executeUpdate();
        }
    }
}
