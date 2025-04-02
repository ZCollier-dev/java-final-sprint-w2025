package org.sprint.user;

import org.sprint.database.DBConnection;

import java.sql.*;

public class UserDAO {
    public User getUserByUsername(String username) throws SQLException {
        String sql = "SELECT * FROM users WHERE username = ?";
        DriverManager DatabaseConnector;
        try (
            Connection conn = DBConnection.getConnection();
            PreparedStatement prepstat = conn.prepareStatement(sql)
        )
        {
            prepstat.setString(1, username);
            try (ResultSet resultset = prepstat.executeQuery())
            {
                if (resultset.next()){
                    return new User(
                        // resultset.getInt("id"),
                        //etc etc
                    );
                }
            }
        }
    }
}
