/*
Class: Task
Author: Vedant Joshi
Date: Jan 9 2023
School: AY Jackson SS
Purpose: Represents a task object. Used for copying to taskCopies for students to work on
 */

public abstract class Task {
    private Date due;
    private Date assigned;
    private final Outline OUTLINE;
    private double weight; //Weight of task as a percentage of total course. 0 <= weight <= 1
    private SubmissionBox submissionBox;

    // Constructor
    // Initializes a Task object with specified attributes.
    // Parameters:
    //   - due: The due date of the task.
    //   - assigned: The assignment date of the task.
    //   - weight: The weight of the task as a percentage of the total course (0 <= weight <= 1).
    //   - outline: The outline associated with the task.
    public Task(Date due, Date assigned, double weight, Outline outline, SubmissionBox submissionBox) {
        this.due = due;
        this.assigned = assigned;
        this.weight = weight;
        OUTLINE = outline;
        this.submissionBox = submissionBox;
    }

    // Getter for due field.
    // Return value:
    //   - The due date of the task.
    public Date getDue() {
        return due;
    }

    // Setter for due field.
    // Parameters:
    //   - due: The new due date to set.
    public void setDue(Date due) {
        this.due = due;
    }

    // Getter for assigned field.
    // Return value:
    //   - The assignment date of the task.
    public Date getAssigned() {
        return assigned;
    }

    // Setter for assigned field.
    // Parameters:
    //   - assigned: The new assignment date to set.
    public void setAssigned(Date assigned) {
        this.assigned = assigned;
    }

    // Getter for weight field.
    // Return value:
    //   - The weight of the task as a percentage of the total course.
    public double getWeight() {
        return weight;
    }

    // Setter for weight field.
    // Parameters:
    //   - weight: The new weight to set (0 <= weight <= 1).
    public void setWeight(double weight) {
        this.weight = weight;
    }

    // Getter for outline field.
    // Return value:
    //   - The outline associated with the task.
    public Outline getOutline() {
        return OUTLINE;
    }

    // Getter for submissionBox field.
    // Return value:
    //   - The submission box associated with the task.
    public SubmissionBox getSubmissionBox() {
        return submissionBox;
    }

    // Setter for submissionBox field.
    // Parameters:
    //   - submissionBox: The new submission box to set.
    public void setSubmissionBox(SubmissionBox submissionBox) {
        this.submissionBox = submissionBox;
    }
}