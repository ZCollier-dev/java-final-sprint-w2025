package org.sprint;

import java.sql.SQLException;
import java.util.Scanner;

import org.sprint.memberships.MembershipService;
import org.sprint.user.User;
import org.sprint.user.UserService;
import org.sprint.workoutclasses.WorkoutClassService;

public class GymApp {
    public static void main(String[] args) {
        UserService userService = new UserService();
        MembershipService membershipService = new MembershipService();
        WorkoutClassService workoutService = new WorkoutClassService();

        try (Scanner scanner = new Scanner(System.in)) {
            int choice;
            do {
                System.out.println("\n=== Gym Management System ===");
                System.out.println("1. Register new user");
                System.out.println("2. Login");
                System.out.println("3. Exit");
                System.out.print("Enter your choice: ");

                while (!scanner.hasNextInt()) {
                    System.out.println("Invalid input. Please enter a number!");
                    scanner.next();
                }

                choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1 -> addNewUser(scanner, userService);
                    case 2 -> logInAsUser(scanner, userService, membershipService, workoutService);
                    case 3 -> System.out.println("Goodbye!");
                    default -> System.out.println("Invalid choice. Try again.");
                }
            } while (choice != 3);
        }
    }

    private static void logInAsUser(Scanner scanner, UserService userService, MembershipService membershipService, WorkoutClassService workoutService) {
        System.out.print("Username: ");
        String username = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();

            User user = userService.loginUser(username, password);
            if (user != null) {
                System.out.println("Welcome, " + user.getUserName() + " (" + user.getUserRole() + ")");
                switch (user.getUserRole().toLowerCase()) {
                    case "admin" -> showAdminMenu(scanner, userService, membershipService);
                    case "trainer" -> showTrainerMenu(scanner, user, workoutService);
                    case "member" -> showMemberMenu(scanner, user, membershipService);
                    default -> System.out.println("Unrecognized role. Please try again!");
                }
            } else {
                System.out.println("Login failed. Check credentials.");
            }
    }

    private static void showAdminMenu(Scanner scanner, UserService userService, MembershipService membershipService) {
        int choice;
        do {
            System.out.println("\n--- Admin Menu ---");
            System.out.println("1. View all users");
            System.out.println("2. View all memberships");
            System.out.println("3. Delete user by username");
            System.out.println("4. Logout");
            System.out.print("Your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            // different cases depending on input
            try {
                switch (choice) {
                    case 1 -> userService.displayAllUsers();
                    case 2 -> membershipService.displayAllMemberships();
                    case 3 -> {
                        System.out.print("Enter username to delete: ");
                        String username = scanner.nextLine();
                        userService.deleteUserByUsername(username); //gets the username, deletes the user by username
                    }
                    case 4 -> membershipService.displayTotalRevenue();
                    case 5 -> System.out.println("Logging out..."); //logs you out
                    default -> System.out.println("Invalid option."); //error handler
                }
            } catch (SQLException e) {
                System.out.println("Error: " + e.getMessage());
            }
        } while (choice != 5);
    }
// trainer menu
    private static void showTrainerMenu(Scanner scanner, User user, WorkoutClassService workoutService) {
        int choice;
        do {
            System.out.println("\n--- Trainer Menu ---");
            System.out.println("1. View your classes");
            System.out.println("2. Add a new class");
            System.out.println("3. Update a class");
            System.out.println("4. Delete a class");
            System.out.println("5. Logout");
            System.out.print("Your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();
// gets everything from database regarding the trainer
            try {
                switch (choice) {
                    case 1 -> workoutService.displayWorkoutClassesByTrainer(user.getUserId());
                    case 2 -> workoutService.addWorkoutClass(scanner, user.getUserId());
                    case 3 -> workoutService.updateWorkoutClass(scanner);
                    case 4 -> workoutService.deleteWorkoutClass(scanner);
                    case 5 -> System.out.println("Logging out...");
                    default -> System.out.println("Invalid option.");
                }
            } catch (SQLException e) {
                System.out.println("Error: " + e.getMessage());
            }
        } while (choice != 5);
    }

    // member menu
    private static void showMemberMenu(Scanner scanner, User user, MembershipService membershipService) {
        int choice;
        do {
            System.out.println("\n--- Member Menu ---");
            System.out.println("1. View your memberships");
            System.out.println("2. Logout");
            System.out.print("Your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            
            switch (choice) {
                case 1 -> membershipService.displayMembershipsByMemberId(user.getUserId());
                case 2 -> System.out.println("Logging out...");
                default -> System.out.println("Invalid option.");
            }
        } while (choice != 2);
    }
// adding a new user
    private static void addNewUser(Scanner scanner, UserService userService) {
        System.out.print("Username: ");
        String username = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Phone number: ");
        String phone = scanner.nextLine();
        System.out.print("Address: ");
        String address = scanner.nextLine();
        System.out.print("Role (Admin/Trainer/Member): ");
        String role = scanner.nextLine();

        User user = new User(0, username, password, email, phone, address, role);
            userService.registerUser(user);

    }
}