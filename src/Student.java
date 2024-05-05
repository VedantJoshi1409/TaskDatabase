import java.util.ArrayList;

public class Student extends Person{
    private int stemProficiency;
    private int humanitiesProficiency;
    private int burstLength;
    private int dailyLength;
    private ArrayList<TaskCopy> taskCopies = new ArrayList<TaskCopy>();

    public Student(String name, int age, int id, String password, int stemProficiency, int humanitiesProficiency, int burstLength, int dailyLength) {
        super(name, age, id, password);
        this.stemProficiency = stemProficiency;
        this.humanitiesProficiency = humanitiesProficiency;
        this.burstLength = burstLength;
        this.dailyLength = dailyLength;
    }

    public void startTask(Task task) {
        taskCopies.add(new TaskCopy(this, task));
    }

    public void workOnTask(TaskCopy taskCopy, int work) {
        taskCopy.work(work);
    }

    public void submitTask(TaskCopy taskCopy, SubmissionBox submissionBox) {
        submissionBox.upload(taskCopy);
    }

    public void viewTasks() {
        //graphics prob
    }

    public void viewInOrder() {

    }

    public void viewByField() {

    }

    public void mostOptimal() {

    }
}
