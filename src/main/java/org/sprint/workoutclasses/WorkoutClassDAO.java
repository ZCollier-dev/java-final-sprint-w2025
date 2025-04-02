package org.sprint.workoutclasses;

import org.sprint.database.DBConnection;

import java.sql.*;

public class WorkoutClassDAO {
    public WorkoutClass getWorkoutClassByType(String type) throws SQLException {
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
                            // resultset.getInt("id"),
                            //etc etc
                    );
                }
            }
        }
    }
}
