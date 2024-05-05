/*
Class: Evaluation
Author: Vedant Joshi
Date: Jan 9 2023
School: AY Jackson SS
Purpose: Represents an Evaluation object. For expandability and organization.
 */

public class Evaluation extends Task{

    public Evaluation(Date due, Date assigned, double weight, Outline outline, SubmissionBox box) {
        super(due, assigned, weight, outline, box);
    }
}
