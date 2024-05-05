import java.io.*;
import java.util.ArrayList;

public abstract class TaskSaves {
    public final String SAVE_NAME;
    private ArrayList<Task> tasks = new ArrayList<Task>();
    private Outlines outlines;
    private Persons persons;

    public TaskSaves(String save, Outlines outlines, Persons persons) {
        this.outlines = outlines;
        this.persons = persons;
        SAVE_NAME = save;
        load();
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public void save() {
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(SAVE_NAME));
            out.write(tasks.size());
            out.newLine();
            for (Task task : tasks) {
                out.write(task.getOutline().getName());
                out.newLine();
                if (task instanceof Assignment) {
                    out.write("Assignment");
                } else {
                    out.write("Evaluation");
                }
                out.newLine();
                out.write("" + task.getDue());
                out.newLine();
                out.write("" + task.getAssigned());
                out.newLine();
                out.write("" + task.getWeight());
                out.newLine();
                out.write(task.getSubmissionBox().getTaskCopies().size());
                out.newLine();
                for (TaskCopy taskCopy : task.getSubmissionBox().getTaskCopies()) {
                    out.write(taskCopy.getStudent().getName());
                    out.newLine();
                    out.write("" + taskCopy.getMark());
                    out.newLine();
                }
                out.write("BoxEnd");
            }
            out.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public void load() {
        String name, type;
        int size, weight, sizeBox, mark, id;
        Date due, assigned;
        SubmissionBox box;
        Task task;
        TaskCopy taskCopy;
        try {
            BufferedReader in = new BufferedReader(new FileReader(SAVE_NAME));
            size = Integer.parseInt(in.readLine());
            for (int i = 0; i < size; i++) {
                name = in.readLine();
                type = in.readLine();
                due = new Date(in.readLine());
                assigned = new Date(in.readLine());
                weight = Integer.parseInt(in.readLine());
                in.readLine();
                sizeBox = Integer.parseInt(in.readLine());
                box = new SubmissionBox();
                if (type.equals("Assignment")) {
                    task = new Assignment(due, assigned, weight, outlines.getOutline(name), box);
                } else {
                    task = new Evaluation(due, assigned, weight, outlines.getOutline(name), box);
                }
                box.setTask(task);
                for (int j = 0; j < sizeBox; j++) {
                    id = Integer.parseInt(in.readLine());
                    mark = Integer.parseInt(in.readLine());
                    taskCopy = new TaskCopy((Student) persons.getByID(id), task);
                    taskCopy.setMark(mark);
                    box.upload(taskCopy);
                }
                tasks.add(task);
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
