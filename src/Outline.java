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

    public Outline(String name, String description, String subjectType, double timeEstimate) {
        this.name = name;
        this.description = description;
        this.subjectType = subjectType;
        this.timeEstimate = timeEstimate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public String getSubjectType() {
        return subjectType;
    }

    public double getTimeEstimate() {
        return timeEstimate;
    }
}