/*
Class: TaskSaves
Author: Vedant Joshi
Date: Jan 16 2023
School: AY Jackson SS
Purpose: The TaskSaves class is an abstract class extending Save and provides functionality for saving and loading tasks.
It contains methods to save, load, and manage tasks along with their associated data.
 */

import java.io.*;
import java.util.ArrayList;

public abstract class TaskSaves extends Save {
    private ArrayList<Task> tasks = new ArrayList<Task>();
    private Outlines outlines;
    private Persons persons;

    public TaskSaves(String save, Outlines outlines, Persons persons) {
        super(save);
        this.outlines = outlines;
        this.persons = persons;
        load();
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Saves the tasks and associated data to a file.
     */
    public void save() {
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(SAVE_NAME));

            // Write the number of tasks
            out.write("" + tasks.size());
            out.newLine();

            // Iterate over each task and write its details
            for (Task task : tasks) {
                // Write task outline name
                out.write(task.getOutline().getName());
                out.newLine();

                // Write task type (Assignment or Evaluation)
                if (task instanceof Assignment) {
                    out.write("Assignment");
                } else {
                    out.write("Evaluation");
                }
                out.newLine();

                // Write task due date, assigned date, weight, and number of task copies
                out.write("" + task.getDue());
                out.newLine();
                out.write("" + task.getAssigned());
                out.newLine();
                out.write("" + task.getWeight());
                out.newLine();
                out.write("" + task.getSubmissionBox().getTaskCopies().size());
                out.newLine();

                // Iterate over each task copy and write student ID and mark
                for (TaskCopy taskCopy : task.getSubmissionBox().getTaskCopies()) {
                    out.write("" + taskCopy.getStudent().getId());
                    out.newLine();
                    out.write("" + taskCopy.getMark());
                    out.newLine();
                }
            }
            out.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    /**
     * Loads tasks and associated data from a file.
     */
    public void load() {
        String name, type;
        int size, sizeBox, id;
        double weight, mark;
        Date due, assigned;
        SubmissionBox box;
        Task task;
        TaskCopy taskCopy;
        Student student;
        try {
            BufferedReader in = new BufferedReader(new FileReader(SAVE_NAME));

            // Read the number of tasks
            String firstLine = in.readLine();
            if (firstLine != null) {
                size = Integer.parseInt(firstLine);

                // Iterate over each task in the file
                for (int i = 0; i < size; i++) {
                    // Read task details
                    name = in.readLine();
                    type = in.readLine();
                    due = new Date(in.readLine());
                    assigned = new Date(in.readLine());
                    weight = Double.parseDouble(in.readLine());
                    sizeBox = Integer.parseInt(in.readLine());

                    // Create a new SubmissionBox for the task
                    box = new SubmissionBox();

                    // Create the appropriate task type (Assignment or Evaluation)
                    if (type.equals("Assignment")) {
                        task = new Assignment(due, assigned, weight, outlines.getOutline(name), box);
                    } else {
                        task = new Evaluation(due, assigned, weight, outlines.getOutline(name), box);
                    }

                    // Set the task for the SubmissionBox
                    box.setTask(task);

                    // Iterate over each task copy in the file
                    for (int j = 0; j < sizeBox; j++) {
                        // Read student ID and mark
                        id = Integer.parseInt(in.readLine());
                        mark = Double.parseDouble(in.readLine());

                        // Retrieve the student using the ID
                        student = (Student) persons.getByID(id);

                        // Create a new TaskCopy for the student and task
                        taskCopy = new TaskCopy(student, task);
                        taskCopy.setMark(mark);

                        // Ensure the task copy is added to the SubmissionBox and Student
                        if (!box.getTaskCopies().contains(taskCopy)) box.upload(taskCopy);
                        if (!student.getTaskCopies().contains(taskCopy)) student.getTaskCopies().add(taskCopy);
                    }

                    // Add the task to the TaskSaves tasks list
                    tasks.add(task);
                }
            }
            in.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    /**
     * Adds a task to the list of tasks.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        getTasks().add(task);
    }
}