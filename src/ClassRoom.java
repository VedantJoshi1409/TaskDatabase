import java.util.ArrayList;

public class ClassRoom {
    private Teacher teacher;
    private ArrayList<Student> students = new ArrayList<Student>();
    private ActiveTasks activeTasks;
    private ArchivedTasks archivedTasks;
    private int id;


    public ClassRoom (Teacher teacher, int id, Outlines outlines, Persons persons) {
        this.teacher = teacher;
        this.id = id;
        activeTasks = new ActiveTasks("Saves/activeSave"+id, outlines, persons);
        archivedTasks = new ArchivedTasks("Saves/archivedSave" + id, outlines, persons);
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public void assignAssignment(Date due, Date assigned, double weight, Outline outline, SubmissionBox submissionBox) {
        activeTasks.addTask(new Assignment(due, assigned, weight, outline, submissionBox));
    }

    public void assignEvaluation(Date due, Date assigned, double weight, Outline outline, SubmissionBox submissionBox) {
        activeTasks.addTask(new Evaluation(due, assigned, weight, outline, submissionBox));
    }

    public void submitWork(TaskCopy taskCopy) {
        taskCopy.getTask().getSubmissionBox().upload(taskCopy);
    }
}