package org.sprint.user;

import org.sprint.database.DBConnection;
import org.sprint.memberships.Membership;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
    public List<User> getAllUsers() throws SQLException { //Gets all users. Used for Admins.
        String sql = "SELECT * FROM users"; //needs to return a list
        List<User> resultList = new ArrayList<>();
        try (
                Connection conn = DBConnection.getConnection();
                PreparedStatement prepstat = conn.prepareStatement(sql)
        )
        {
            try (ResultSet resultset = prepstat.executeQuery())
            {
                while (resultset.next()){
                    User entry = new User(
                            resultset.getInt("id"),
                            resultset.getString("username"),
                            resultset.getString("password"),
                            resultset.getString("email"),
                            resultset.getString("phone_no"),
                            resultset.getString("address"),
                            resultset.getString("role")
                    );
                    resultList.add(entry);
                }
                return resultList;
            }
        }
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
    public boolean deleteUserByUsername(String username) throws SQLException { //Deletes a user from the database via username. Admin only.
        String sql = "DELETE FROM users WHERE username = ?";
        try (
                Connection conn = DBConnection.getConnection();
                PreparedStatement prepstat = conn.prepareStatement(sql)
        )
        {
            prepstat.setString(1, username);
            int rowsAffected = prepstat.executeUpdate();
            if (rowsAffected > 0){
                return true;
            }
            return false;
        }
    }
}
