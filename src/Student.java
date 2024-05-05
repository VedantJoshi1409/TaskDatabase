import java.util.ArrayList;

public class Student extends Person{
    private int stemProficiency;
    private int humanitiesProficiency;
    private int burstLength;
    private int dailyLength;
    private ArrayList<TaskCopy> taskCopies = new ArrayList<TaskCopy>();
    private ArrayList<Classroom> classrooms = new ArrayList<Classroom>();

    public Student(String name, int age, int id, String password, int stemProficiency, int humanitiesProficiency, int burstLength, int dailyLength) {
        super(name, age, id, password);
        this.stemProficiency = stemProficiency;
        this.humanitiesProficiency = humanitiesProficiency;
        this.burstLength = burstLength;
        this.dailyLength = dailyLength;
    }

    public ArrayList<Classroom> getClassrooms() {
        return classrooms;
    }

    public void setClassrooms(ArrayList<Classroom> classrooms) {
        this.classrooms = classrooms;
    }

    public int getStemProficiency() {
        return stemProficiency;
    }

    public void setStemProficiency(int stemProficiency) {
        this.stemProficiency = stemProficiency;
    }

    public int getHumanitiesProficiency() {
        return humanitiesProficiency;
    }

    public void setHumanitiesProficiency(int humanitiesProficiency) {
        this.humanitiesProficiency = humanitiesProficiency;
    }

    public int getBurstLength() {
        return burstLength;
    }

    public void setBurstLength(int burstLength) {
        this.burstLength = burstLength;
    }

    public int getDailyLength() {
        return dailyLength;
    }

    public void setDailyLength(int dailyLength) {
        this.dailyLength = dailyLength;
    }

    public ArrayList<TaskCopy> getTaskCopies() {
        return taskCopies;
    }

    public void setTaskCopies(ArrayList<TaskCopy> taskCopies) {
        this.taskCopies = taskCopies;
    }

    public void startTask(Task task) {
        taskCopies.add(new TaskCopy(this, task));
    }

    public TaskCopy getCopyOfTask(Task task) {
        for (TaskCopy taskCopy : taskCopies) {
            if (taskCopy.getTask() == task) return taskCopy;
        }
        return null;
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
