/*
Class: Assignment
Author: Vedant Joshi
Date: Jan 9 2023
School: AY Jackson SS
Purpose: Represents an Assignment object. For expandability and organization.
 */

public class Assignment extends Task{

    public Assignment(Date due, Date assigned, double weight, Outline outline, SubmissionBox box) {
        super(due, assigned, weight, outline, box);
    }
}
