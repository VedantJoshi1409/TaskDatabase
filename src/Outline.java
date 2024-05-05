/*
Class: Outline
Author: Vedant Joshi
Date: Jan 9 2023
School: AY Jackson SS
Purpose: Represents an outline for a task. Used for easy replication of tasks as well as organization.
 */

public class Outline {
    private String name;
    private String description;
    private String subjectType;
    private double timeEstimate;

    // Constructor to initialize an Outline object with specified attributes.
    // Parameters:
    //   - name: The name of the outline.
    //   - description: The description of the outline.
    //   - subjectType: The subject type of the outline.
    //   - timeEstimate: The time estimate for the outline.
    public Outline(String name, String description, String subjectType, double timeEstimate) {
        this.name = name;
        this.description = description;
        this.subjectType = subjectType;
        this.timeEstimate = timeEstimate;
    }

    // Getter for name field.
    // Return value:
    //   - The name of the outline.
    public String getName() {
        return name;
    }

    // Setter for name field.
    // Parameters:
    //   - name: The new name to set.
    public void setName(String name) {
        this.name = name;
    }

    // Getter for description field.
    // Return value:
    //   - The description of the outline.
    public String getDescription() {
        return description;
    }

    // Setter for description field.
    // Parameters:
    //   - description: The new description to set.
    public void setDescription(String description) {
        this.description = description;
    }

    // Getter for subjectType field.
    // Return value:
    //   - The subject type of the outline.
    public String getSubjectType() {
        return subjectType;
    }

    // Setter for subjectType field.
    // Parameters:
    //   - subjectType: The new subject type to set.
    public void setSubjectType(String subjectType) {
        this.subjectType = subjectType;
    }

    // Getter for timeEstimate field.
    // Return value:
    //   - The time estimate for the outline.
    public double getTimeEstimate() {
        return timeEstimate;
    }

    // Setter for timeEstimate field.
    // Parameters:
    //   - timeEstimate: The new time estimate to set.
    public void setTimeEstimate(double timeEstimate) {
        this.timeEstimate = timeEstimate;
    }
}