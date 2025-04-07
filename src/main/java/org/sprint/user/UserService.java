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
    public boolean registerUser(String username, String password, String email, String phoneNumber, String address, String role) {
        try {
            // Ensuring the username isn't already in the system
            if (userDAO.getUserByUsername(username) != null) {
                System.out.println("Username already exists.");
                return false;
            }

            // Hashing the user's password
            String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());

            User user = new User(0, username, hashedPassword, email, phoneNumber, address, role);
            userDAO.addUser(user);
            System.out.println("New user added successfully!");
            return true;
        } catch (SQLException exception) {
            System.out.println("Error adding user: " + exception.getMessage());
            return false;
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

    // Deleting a user -- Admin use only
    public void deleteUserById(int userId) {
        try {
            userDAO.deleteUser(userId);
            System.out.println("User deleted.");
        } catch (SQLException exception) {
            System.out.println("Error: unable to delete user: " + exception.getMessage());
        }
    }

    // Checking if the user is an admin
    public boolean isAdmin(User user) {
        return user.getRole().equalsIgnoreCase("Admin");
    }

    // Checking if the user is a member
    public boolean isMember(User user) {
        return user.getRole().equalsIgnoreCase("Member");
    }

    // Checking if the user is a trainer
    public boolean isTrainer(User user) {
        return user.getRole().equalsIgnoreCase("Trainer");
    }

    // register (includes password hashing)
    public void addUser(User user) throws SQLException {
        String hashedPassword = BCrypt.hashpw(user.getHashedPassword(), BCrypt.gensalt());
        user.setHashedPassword(hashedPassword);
        userDAO.addUser(user);
    }

    // login with password checking
    public User loginForUser(String username, String plainPassword) throws SQLException {
        User user = userDAO.getUserByUsername(username);
        if (user != null && BCrypt.checkpw(plainPassword, user.getHashedPassword())) {
            return user;
        }
        return null;
    }

    // Admin privs ONLY- Delete user
    public boolean deleteUserByUsername(String username) throws SQLException {
        return userDAO.deleteUserByUsername(username);
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