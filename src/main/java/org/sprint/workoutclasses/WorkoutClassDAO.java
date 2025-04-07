package org.sprint.workoutclasses;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.sprint.database.DBConnection;

public class WorkoutClassDAO {

    // create a new workout class to the database
    public void addWorkoutClass(WorkoutClass workoutClass) throws SQLException {
        String sql = "INSERT INTO workoutclasses (trainer_id, type, description, schedule) VALUES (?, ?, ?, ?)";
        try (
            Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setInt(1, workoutClass.getTrainerId());
            stmt.setString(2, workoutClass.getWorkoutClassType());
            stmt.setString(3, workoutClass.getWorkoutClassDescription());
            stmt.setString(4, workoutClass.getSchedule());
            stmt.executeUpdate();
        }
    }

    // read all workout classes of a specified type
    public List<WorkoutClass> getWorkoutClassByType(String type) throws SQLException {
        List<WorkoutClass> classes = new ArrayList<>();
        String sql = "SELECT * FROM workoutclasses WHERE type = ?";
        try (
            Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setString(1, type);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    WorkoutClass wc = new WorkoutClass(
                        rs.getInt("id"),
                        rs.getString("type"),
                        rs.getString("description"),
                        rs.getInt("trainer_id"),
                        rs.getString("schedule")
                    );
                    classes.add(wc);
                }
            }
        }
        return classes;
    }

    // read all workout classes by trainer ID
    public List<WorkoutClass> getWorkoutClassByTrainerId(int trainerId) throws SQLException {
        List<WorkoutClass> classes = new ArrayList<>();
        String sql = "SELECT * FROM workoutclasses WHERE trainer_id = ?";
        try (
            Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setInt(1, trainerId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    WorkoutClass wc = new WorkoutClass(
                        rs.getInt("id"),
                        rs.getString("type"),
                        rs.getString("description"),
                        rs.getInt("trainer_id"),
                        rs.getString("schedule")
                    );
                    classes.add(wc);
                }
            }
        }
        return classes;
    }

    // update an existing workout class
    public void updateWorkoutClass(WorkoutClass workoutClass) throws SQLException {
        String sql = "UPDATE workoutclasses SET trainer_id = ?, type = ?, description = ?, schedule = ? WHERE id = ?";
        try (
            Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setInt(1, workoutClass.getTrainerId());
            stmt.setString(2, workoutClass.getWorkoutClassType());
            stmt.setString(3, workoutClass.getWorkoutClassDescription());
            stmt.setString(4, workoutClass.getSchedule());
            stmt.setInt(5, workoutClass.getWorkoutClassId());
            stmt.executeUpdate();
        }
    }

    // delete a workout class by ID
    public boolean deleteWorkoutClassById(int id) throws SQLException {
        String sql = "DELETE FROM workoutclasses WHERE id = ?";
        try (
            Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setInt(1, id);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        }
    }
}
