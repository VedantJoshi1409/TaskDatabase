/*
Class: SubmissionBox
Author: Vedant Joshi
Date: Jan 10 2023
School: AY Jackson SS
Purpose: The SubmissionBox class represents a container for storing task copies and managing their marks.
 */
import java.util.ArrayList;

public class SubmissionBox {
    private ArrayList<TaskCopy> taskCopies = new ArrayList<TaskCopy>();
    private Task task;

    public SubmissionBox(Task task) {
        this.task = task;
    }

    public SubmissionBox() {
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public ArrayList<TaskCopy> getTaskCopies() {
        return taskCopies;
    }

    /**
     * Marks task copies and allows assigning marks to students.
     */
    public void mark() {
        int size = taskCopies.size(), selection;
        double mark;
        TaskCopy taskCopy;

        // Displaying task copies and existing marks
        System.out.println("[0] Go back");
        if (size > 0) {
            for (int i = 0; i < size; i++) {
                taskCopy = taskCopies.get(i);
                if (taskCopy.getMark() != -1) {
                    System.out.printf("[%d] %-20s%.1f%%\n", i + 1, taskCopy.getStudent().getName(), taskCopy.getMark());
                } else {
                    System.out.printf("[%d] %-20sN/A%%\n", i + 1, taskCopy.getStudent().getName());
                }
            }
        } else {
            System.out.println("No tasks submitted!");
        }

        // Selecting a task copy for marking
        System.out.print("Select choice: ");
        selection = DatabaseRunner.sc.nextInt();

        // Setting mark for the selected task copy
        if (selection != 0) {
            System.out.print("Set mark: ");
            mark = DatabaseRunner.sc.nextDouble();
            taskCopies.get(selection - 1).setMark(mark);
        }
        System.out.println();
    }

    /**
     * Uploads a task copy to the submission box.
     *
     * @param taskCopy The task copy to be uploaded.
     */
    public void upload(TaskCopy taskCopy) {
        taskCopies.add(taskCopy);
    }
}
