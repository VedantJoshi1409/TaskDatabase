/*
Class: ActiveTask
Author: Vedant Joshi
Date: Jan 10 2023
School: AY Jackson SS
Purpose: Used for managing all tasks that are not yet overdue
 */

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;

public class ActiveTasks extends TaskSaves{
    private ArchivedTasks archivedTasks;

    public ActiveTasks(String save, Outlines outlines, Persons persons) {
        super(save, outlines, persons);
    }

    public ActiveTasks(String save, Outlines outlines, Persons persons, ArchivedTasks archivedTasks) {
        super(save, outlines, persons);
        this.archivedTasks = archivedTasks;
    }

    /**
     * Archives tasks that have due dates earlier than the current date.
     * Each task with a due date earlier than the current date is removed
     * from the current task list and added to the archived task list.
     *
     * The operation is performed based on the current date.
     */
    public void archiveOld() {
        // Get the current date
        Date today = new Date(LocalDate.now(ZoneId.of("-05:00")));

        // Create a clone of the current task list to prevent ConcurrentModificationException
        ArrayList<Task> copy = (ArrayList<Task>) getTasks().clone();

        // Iterate through the cloned list to avoid ConcurrentModificationException
        for (Task task : copy) {
            // Check if the due date of the task is earlier than the current date
            if (today.laterThan(task.getDue())) {
                // Archive the task by adding it to the archived task list
                archivedTasks.addTask(task);

                // Remove the task from the current task list
                getTasks().remove(task);
            }
        }
    }
}
