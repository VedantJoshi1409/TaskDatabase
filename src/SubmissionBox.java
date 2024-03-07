import java.util.ArrayList;

public class SubmissionBox {
    private ArrayList<TaskCopy> taskCopies = new ArrayList<TaskCopy>();
    private Task task;

    public SubmissionBox(Task task) {
        this.task = task;
    }

    public SubmissionBox() {

    }

    public void setTask (Task task) {
        this.task = task;
    }

    public ArrayList<TaskCopy> getTaskCopies() {
        return taskCopies;
    }

    public void mark() {
        for (TaskCopy taskCopy : taskCopies) {
            //implement marking through gui
        }
    }

    public boolean upload(TaskCopy taskCopy) {
        if (taskCopy.getTask() == task && taskCopy.getPercentComplete() == 100) {
            taskCopies.add(taskCopy);
            return true;
        }
        return false;
    }
}
