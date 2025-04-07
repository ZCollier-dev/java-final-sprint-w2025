package org.sprint.workoutclasses;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class WorkoutClassService {
    private final WorkoutClassDAO workoutClassDAO;

    public WorkoutClassService() {
        this.workoutClassDAO = new WorkoutClassDAO();
    }

    // View classes from the Trainer.java
    public void displayWorkoutClassesByTrainer(int trainerId) throws SQLException {
        List<WorkoutClass> classes = workoutClassDAO.getWorkoutClassByTrainerId(trainerId);
        if (classes.isEmpty()) {
            System.out.println("You have no classes assigned.");
        } else {
            for (WorkoutClass wc : classes) {
                System.out.println(wc);
            }
        }
    }

    // Add the new class
    public void addWorkoutClass(Scanner scanner, int trainerId) throws SQLException {
        System.out.print("Enter class type: ");
        String type = scanner.nextLine();
        System.out.print("Enter class description: ");
        String desc = scanner.nextLine();
        System.out.print("Enter class schedule: ");
        String schedule = scanner.nextLine();

        WorkoutClass newClass = new WorkoutClass(0, type, desc, trainerId, schedule);
        workoutClassDAO.addWorkoutClass(newClass);
        System.out.println("Workout class added successfully.");
    }

    // Update 
    public void updateWorkoutClass(Scanner scanner) throws SQLException {
        System.out.print("Enter the ID of the class you want to update: ");
        int classId = Integer.parseInt(scanner.nextLine());

        System.out.print("Enter new type: ");
        String type = scanner.nextLine();
        System.out.print("Enter new description: ");
        String desc = scanner.nextLine();
        System.out.print("Enter new trainer ID: ");
        int trainerId = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter new schedule: ");
        String schedule = scanner.nextLine();

        WorkoutClass updatedClass = new WorkoutClass(classId, type, desc, trainerId, schedule);
        workoutClassDAO.updateWorkoutClass(updatedClass);
        System.out.println("Workout class updated.");
    }

    // Delete a class by ID
    public void deleteWorkoutClass(Scanner scanner) throws SQLException {
        System.out.print("Enter the ID of the class you want to delete: ");
        int classId = Integer.parseInt(scanner.nextLine());

        boolean success = workoutClassDAO.deleteWorkoutClassById(classId);
        if (success) {
            System.out.println("Workout class deleted.");
        } else {
            System.out.println("Workout class not found.");
        }
    }
}
