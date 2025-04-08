package org.sprint.user;

import java.sql.SQLException;
import java.util.List;

import org.mindrot.jbcrypt.BCrypt;

public class UserService {
    private final UserDAO userDAO;

    public UserService() {
        this.userDAO = new UserDAO();
    }

    // To register a new user
    public void registerUser(User user) {
        try {
            // Ensuring the username isn't already in the system
            if (userDAO.getUserByUsername(user.getUsername()) != null) {
                System.out.println("Username already exists.");
                return;
            }

            // Hashing the user's password
            String hashedPassword = BCrypt.hashpw(user.getHashedPassword(), BCrypt.gensalt());
            user.setHashedPassword(hashedPassword);

            userDAO.addUser(user);
            System.out.println("New user added successfully!");
        } catch (SQLException exception) {
            System.out.println("Error adding user: " + exception.getMessage());
        }
    }

    // Authenticating the user's login
    public User loginUser(String username, String password) {
        try {
            User user = userDAO.getUserByUsername(username);

            if (user != null && BCrypt.checkpw(password, user.getHashedPassword())) {
                System.out.println("Logged in successfully. Hello, " + user.getUsername() + "!");
                return user;
            } else {
                System.out.println("Invalid username or password. Please try again.");
                return null;
            }
        } catch (SQLException exception) {
            System.out.println("Login error: " + exception.getMessage());
            return null;
        }
    }

    // Admin privs ONLY- Delete user
    public void deleteUserByUsername(String username) throws SQLException {
        try {
            if (userDAO.deleteUserByUsername(username)) {
                System.out.println("User deleted.");
                return;
            }
            System.out.println("User not found.");
        } catch (SQLException exception) {
            System.out.println("Login error: " + exception.getMessage());
        }
    }

    // Admin function - View all users
    public void displayAllUsers() {
        try {
            List<User> users = userDAO.getAllUsers();
            for (User user : users) {
                System.out.println(user);
            }
        } catch (SQLException exception) {
            System.out.println("Error: unable to retrieve users: " + exception.getMessage());
        }
    }
}