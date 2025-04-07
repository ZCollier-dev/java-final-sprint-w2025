package org.sprint.user;

import org.sprint.database.DBConnection;

import java.sql.*;

public class UserDAO {
    //NOTE: All passwords should be hashed via BCrypt prior to coming into the DAO.
    //C
    public void addUser(User user) throws SQLException { //Insert new user into database - Users should be properly validated prior to entering the DAO.
        String sql = "INSERT INTO users (username, password, email, phone_no, address, role) VALUES (?, ?, ?, ?, ?, ?)";
        try (
                Connection conn = DBConnection.getConnection();
                PreparedStatement prepstat = conn.prepareStatement(sql)
        )
        {
            prepstat.setString(1, user.getUsername());
            prepstat.setString(2, user.getHashedPassword());
            prepstat.setString(3, user.getEmail());
            prepstat.setString(4, user.getPhoneNumber());
            prepstat.setString(5, user.getAddress());
            prepstat.setString(6, user.getRole());
            prepstat.executeUpdate();
        }
    }
    //R
    public User getAllUsers() throws SQLException { //Gets all users. Used for Admins.
        String sql = "SELECT * FROM users";
        try (
                Connection conn = DBConnection.getConnection();
                PreparedStatement prepstat = conn.prepareStatement(sql)
        )
        {
            try (ResultSet resultset = prepstat.executeQuery())
            {
                if (resultset.next()){
                    return new User(
                            resultset.getInt("id"),
                            resultset.getString("username"),
                            resultset.getString("password"),
                            resultset.getString("email"),
                            resultset.getString("phone_no"),
                            resultset.getString("address"),
                            resultset.getString("role")
                    );
                }
            }
        }
        return null;
    }

    public User getUserByUsername(String username) throws SQLException { //Gets users via username.
        String sql = "SELECT * FROM users WHERE username = ?";
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
                            resultset.getInt("id"),
                            resultset.getString("username"),
                            resultset.getString("password"),
                            resultset.getString("email"),
                            resultset.getString("phone_no"),
                            resultset.getString("address"),
                            resultset.getString("role")
                    );
                }
            }
        }
        return null;
    }
    //U
    public void updateUser(User user) throws SQLException { //Updates a user with new data from a User object
        String sql = "UPDATE users SET username = ?, password = ?, email = ?, phone_no = ?, address = ?, role = ? WHERE id = ?";
        try (
                Connection conn = DBConnection.getConnection();
                PreparedStatement prepstat = conn.prepareStatement(sql)
        )
        {
            prepstat.setString(1, user.getUsername());
            prepstat.setString(2, user.getHashedPassword());
            prepstat.setString(3, user.getEmail());
            prepstat.setString(4, user.getPhoneNumber());
            prepstat.setString(5, user.getAddress());
            prepstat.setString(6, user.getRole());
            prepstat.setInt(7, user.getUserId());
            prepstat.executeUpdate();
        }
    }
    //D
    public void deleteUserByUsername(String username) throws SQLException { //Deletes a user from the database via username. Admin only.
        String sql = "DELETE FROM users WHERE username = ?";
        try (
                Connection conn = DBConnection.getConnection();
                PreparedStatement prepstat = conn.prepareStatement(sql)
        )
        {
            prepstat.setString(1, username);
            prepstat.executeUpdate();
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
