package org.sprint.workoutclasses;

public class WorkoutClass {
    private int workoutClassId;
    private String workoutClassType;
    private String workoutClassDescription;
    private int trainerId;
    private String schedule;

    // Constructor
    public WorkoutClass(int workoutClassId, String workoutClassType, String workoutClassDescription, int trainerId, String schedule) {
        this.workoutClassId = workoutClassId;
        this.workoutClassType = workoutClassType;
        this.workoutClassDescription = workoutClassDescription;
        this.trainerId = trainerId;
        this.schedule = schedule;
    }

    // Getters and setters
    public int getWorkoutClassId() {
        return workoutClassId;
    }

    public void setWorkoutClassId(int workoutClassId) {
        this.workoutClassId = workoutClassId;
    }

    public String getWorkoutClassType() {
        return workoutClassType;
    }

    public void setWorkoutClassType(String workoutClassType) {
        this.workoutClassType = workoutClassType;
    }

    public String getWorkoutClassDescription() {
        return workoutClassDescription;
    }

    public void setWorkoutClassDescription(String workoutClassDescription) {
        this.workoutClassDescription = workoutClassDescription;
    }

    public int getTrainerId() {
        return trainerId;
    }

    public void setTrainerId(int trainerId) {
        this.trainerId = trainerId;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    // toString for workout class output
    @Override
    public String toString() {
        return "Workout Class Information: " +
               "Workout Class ID: " + workoutClassId + ", Workout Class Type: " + workoutClassType + ", Workout Class Description: " + workoutClassDescription + ", Trainer ID: " + trainerId + ", Schedule: " + schedule;
    }
}
