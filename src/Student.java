/*
Class: Student
Author: Vedant Joshi
Date: Jan 9 2023
School: AY Jackson SS
Purpose: The Student class represents a student, inheriting from the Person class.
It includes methods specific to students, such as starting a task, working on a task, and submitting a task.
 */
import java.util.ArrayList;

public class Student extends Person {
    private ArrayList<TaskCopy> taskCopies = new ArrayList<TaskCopy>();

    public Student(String name, int age, int id, String password) {
        super(name, age, id, password);
    }

    public ArrayList<TaskCopy> getTaskCopies() {
        return taskCopies;
    }

    /**
     * Starts a new task for the student by creating a TaskCopy instance.
     *
     * @param task The task to be started.
     */
    public void startTask(Task task) {
        taskCopies.add(new TaskCopy(this, task));
    }

    /**
     * Retrieves the task copy associated with a specific task.
     *
     * @param task The task to find the copy of.
     * @return The TaskCopy associated with the specified task, or null if not found.
     */
    public TaskCopy getCopyOfTask(Task task) {
        for (TaskCopy taskCopy : taskCopies) {
            if (taskCopy.getTask() == task) return taskCopy;
        }
        return null;
    }
}
