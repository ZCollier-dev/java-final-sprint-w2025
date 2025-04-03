package org.sprint.workoutclasses;

import org.sprint.database.DBConnection;

import java.sql.*;

public class WorkoutClassDAO {
    //C
    public void addWorkoutClass(WorkoutClass workoutClass) throws SQLException { //Insert new workout class into database
        String sql = "INSERT INTO workoutclasses (trainer_id, type, description, schedule) VALUES (?, ?, ?, ?)";
        try (
                Connection conn = DBConnection.getConnection();
                PreparedStatement prepstat = conn.prepareStatement(sql)
        )
        {
            prepstat.setInt(1, workoutClass.getTrainerId());
            prepstat.setString(2, workoutClass.getWorkoutClassType());
            prepstat.setString(3, workoutClass.getWorkoutClassDescription());
            prepstat.setString(4, workoutClass.getSchedule());
            prepstat.executeUpdate();
        }
    }

    //R
    public WorkoutClass getWorkoutClassByType(String type) throws SQLException { //Get all workout classes of a specified type.
        String sql = "SELECT * FROM workoutclasses WHERE type = ?";
        try (
                Connection conn = DBConnection.getConnection();
                PreparedStatement prepstat = conn.prepareStatement(sql)
        )
        {
            prepstat.setString(1, type);
            try (ResultSet resultset = prepstat.executeQuery())
            {
                if (resultset.next()){
                    return new WorkoutClass(
                            resultset.getInt("id"),
                            resultset.getString("type"),
                            resultset.getString("description"),
                            resultset.getInt("trainer_id"),
                            resultset.getString("schedule")
                    );
                }
            }
        }
        return null;
    }

    public WorkoutClass getWorkoutClassByTrainerId(int id) throws SQLException { //Get all workout classes from a specified trainer's id.
        String sql = "SELECT * FROM workoutclasses WHERE trainer_id = ?";
        try (
                Connection conn = DBConnection.getConnection();
                PreparedStatement prepstat = conn.prepareStatement(sql)
        )
        {
            prepstat.setInt(1, id);
            try (ResultSet resultset = prepstat.executeQuery())
            {
                if (resultset.next()){
                    return new WorkoutClass(
                            resultset.getInt("id"),
                            resultset.getString("type"),
                            resultset.getString("description"),
                            resultset.getInt("trainer_id"),
                            resultset.getString("schedule")
                    );
                }
            }
        }
        return null;
    }
    //U
    public void updateWorkoutClass(WorkoutClass workoutClass) throws SQLException { //Update workout class with new data
        String sql = "UPDATE workoutclasses SET trainer_id = ?, type = ?, description = ?, schedule = ? WHERE id = ?";
        try (
                Connection conn = DBConnection.getConnection();
                PreparedStatement prepstat = conn.prepareStatement(sql)
        )
        {
            prepstat.setInt(1, workoutClass.getTrainerId());
            prepstat.setString(2, workoutClass.getWorkoutClassType());
            prepstat.setString(3, workoutClass.getWorkoutClassDescription());
            prepstat.setString(4, workoutClass.getSchedule());
            prepstat.setInt(5, workoutClass.getWorkoutClassId());
            prepstat.executeUpdate();
        }
    }

    //D
    public void deleteWorkoutClassById(int id) throws SQLException { //Deletes a workout class from the database via id.
        String sql = "DELETE FROM workoutclasses WHERE id = ?";
        try (
                Connection conn = DBConnection.getConnection();
                PreparedStatement prepstat = conn.prepareStatement(sql)
        )
        {
            prepstat.setInt(1, id);
            prepstat.executeUpdate();
        }
    }
}
