/*
Class: Task
Author: Vedant Joshi
Date: Jan 9 2023
School: AY Jackson SS
Purpose: Represents a task object. Used for copying to taskCopies for students to work on
 */

public abstract class Task {
    public static final String ASSIGNMENT = "Assignment";
    public static final String EVALUATION = "Evaluation";
    private Date due;
    private Date assigned;
    private final Outline OUTLINE;
    private double weight; //Weight of task as a percentage of total course. 0 <= weight <= 1
    private SubmissionBox submissionBox;

    public Task(Date due, Date assigned, double weight, Outline outline, SubmissionBox submissionBox) {
        this.due = due;
        this.assigned = assigned;
        this.weight = weight;
        OUTLINE = outline;
        this.submissionBox = submissionBox;
    }

    public Date getDue() {
        return due;
    }

    public Date getAssigned() {
        return assigned;
    }

    public double getWeight() {
        return weight;
    }

    public Outline getOutline() {
        return OUTLINE;
    }

    public SubmissionBox getSubmissionBox() {
        return submissionBox;
    }

}