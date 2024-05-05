public class TaskCopy {
    private int percentComplete;
    private final Task TASK;
    private final Student STUDENT;
    private double mark;

    // Constructor
    // Initializes a TaskCopy object with specified student and task, setting initial values for percentComplete and mark.
    // Parameters:
    //   - student: The student assigned the task.
    //   - task: The original task being copied.
    public TaskCopy(Student student, Task task) {
        TASK = task;
        STUDENT = student;
        percentComplete = 0;
        mark = -1;
    }

    // Getter for mark field.
    // Return value:
    //   - The mark received for the task copy.
    public double getMark() {
        return mark;
    }

    // Setter for mark field.
    // Parameters:
    //   - mark: The mark to set for the task copy.
    public void setMark(double mark) {
        this.mark = mark;
    }

    public Task getTask() {
        return TASK;
    }

    // Getter for student field.
    // Return value:
    //   - The student assigned the task.
    public Student getStudent() {
        return STUDENT;
    }

    public int getPercentComplete() {
        return percentComplete;
    }

    // Method to simulate the student working on the task copy and updating the percentComplete.
    // Parameters:
    //   - percent: The percentage of work done by the student on the task.
    public void work(int percent) {
        percentComplete += percent;
        if (percentComplete >= 100) {
            System.out.println("Task completed and submitted!");
            TASK.getSubmissionBox().upload(this);
        }
    }
}
