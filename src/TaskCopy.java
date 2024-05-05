/*
Class: TaskCopy
Author: Vedant Joshi
Date: Jan 10 2023
School: AY Jackson SS
Purpose: The TaskCopy class represents a copy of a task associated with a specific student.
It tracks the progress and marks for the associated task copy.
 */

public class TaskCopy {
    private int percentComplete;
    private final Task TASK;
    private final Student STUDENT;
    private double mark;

    public TaskCopy(Student student, Task task) {
        TASK = task;
        STUDENT = student;
        percentComplete = 0;
        mark = -1;
    }

    public double getMark() {
        return mark;
    }

    public void setMark(double mark) {
        this.mark = mark;
    }

    public Task getTask() {
        return TASK;
    }

    public Student getStudent() {
        return STUDENT;
    }

    public int getPercentComplete() {
        return percentComplete;
    }

    /**
     * Updates the percentage completion of the task copy by adding the specified percentage.
     * If the task copy is completed (100% or more), it is submitted to the submission box.
     *
     * @param percent The percentage of work completed.
     */
    public void work(int percent) {
        percentComplete += percent;
        if (percentComplete >= 100) {
            System.out.println("Task completed and submitted!");
            TASK.getSubmissionBox().upload(this);
        }
    }
}