/*
Class: Evaluation
Author: Vedant Joshi
Date: Jan 9 2023
School: AY Jackson SS
Purpose: Represents an Evaluation object. For expandability and organization.
 */

public class Evaluation extends Task{
    // Constructor
    // Initializes an Evaluation object with specified attributes.
    // Parameters:
    //   - due: The due date of the task.
    //   - assigned: The assignment date of the task.
    //   - weight: The weight of the task as a percentage of the total course (0 <= weight <= 1).
    //   - outline: The outline associated with the task.
    public Evaluation(Date due, Date assigned, double weight, Outline outline, SubmissionBox box) {
        super(due, assigned, weight, outline, box);
    }
}
