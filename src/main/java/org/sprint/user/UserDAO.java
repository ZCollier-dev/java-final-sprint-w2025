package org.sprint.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.sprint.database.DBConnection;

public class UserDAO {
    // NOTE: All passwords should be hashed via BCrypt prior to entering the DAO.

    // creates a new user to the database
    public void addUser(User user) throws SQLException {
        String sql = "INSERT INTO users (username, password, email, phone_no, address, role) VALUES (?, ?, ?, ?, ?, ?)";
        try (
            Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getHashedPassword());
            stmt.setString(3, user.getEmail());
            stmt.setString(4, user.getPhoneNumber());
            stmt.setString(5, user.getAddress());
            stmt.setString(6, user.getRole());
            stmt.executeUpdate();
        }
    }

    // reads all users (Admin role ONLY!)
    public List<User> getAllUsers() throws SQLException {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM users";
        try (
            Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()
        ) {
            while (rs.next()) {
                User user = new User(
                    rs.getInt("id"),
                    rs.getString("username"),
                    rs.getString("password"),
                    rs.getString("email"),
                    rs.getString("phone_no"),
                    rs.getString("address"),
                    rs.getString("role")
                );
                users.add(user);
            }
        }
        return users;
    }

    // reads a single user by username
    public User getUserByUsername(String username) throws SQLException {
        String sql = "SELECT * FROM users WHERE username = ?";
        try (
            Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setString(1, username);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new User(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("email"),
                        rs.getString("phone_no"),
                        rs.getString("address"),
                        rs.getString("role")
                    );
                }
            }
        }
        return null;
    }

    // Update - Updates user data in the database
    public void updateUser(User user) throws SQLException {
        String sql = "UPDATE users SET username = ?, password = ?, email = ?, phone_no = ?, address = ?, role = ? WHERE id = ?";
        try (
            Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getHashedPassword());
            stmt.setString(3, user.getEmail());
            stmt.setString(4, user.getPhoneNumber());
            stmt.setString(5, user.getAddress());
            stmt.setString(6, user.getRole());
            stmt.setInt(7, user.getUserId());
            stmt.executeUpdate();
        }
    }

    // Delete - Removes a user by their username
    public boolean deleteUserByUsername(String username) throws SQLException {
        String sql = "DELETE FROM users WHERE username = ?";
        try (
            Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setString(1, username);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        }
    }

    public void deleteUser(int userId) throws SQLException { // Deletes a user from the database by ID. Admin only
        String sql = "DELETE FROM users WHERE id = ?";
        try (
                Connection conn = DBConnection.getConnection();
                PreparedStatement prepstat = conn.prepareStatement(sql)
        )
        {
            prepstat.setInt(1, userId);
            prepstat.executeUpdate();
        }
    }
}
