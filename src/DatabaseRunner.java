import java.util.ArrayList;
import java.util.Scanner;

public class DatabaseRunner {
    private static Database selectedDatabase;
    private static Person selectedPerson;
    private static Classroom selectedClass;
    private static Task selectedTask;
    public final static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        Databases.load();
        selectDatabase();
    }

    public static void selectDatabase() {
        Databases.listDatabases();
        System.out.print("Select Database: ");
        int select = sc.nextInt();
        selectedDatabase = Databases.getDatabases().get(select - 1);
        System.out.println();
        logIn();
    }

    public static void logIn() {
        int id;
        String pass;
        boolean firstRun = true;
        selectedPerson = null;
        do {
            if (!firstRun) {
                System.out.println("Incorrect ID/Pass\n");
            } else {
                firstRun = false;
            }
            System.out.print("Enter your ID or [0] to go back: ");
            id = sc.nextInt();
            if (id != 0) {
                System.out.print("Enter your password: ");
                sc.nextLine();
                pass = sc.nextLine();
                selectedPerson = selectedDatabase.logIn(id, pass);
            }
        } while ((selectedPerson == null) && (id != 0));
        System.out.println();
        if (id == 0) {
            selectDatabase();
        } else {
            if (selectedPerson instanceof Student student) {
                selectClass(student);
            }
        }
    }

    public static void selectClass(Student student) {
        System.out.println("[0] Go back");
        for (int i = 0; i < student.getClassrooms().size(); i++) {
            System.out.printf("[%d] %s\n", i + 1, student.getClassrooms().get(i).getName());
        }
        System.out.print("Select class: ");
        int selection = sc.nextInt();
        System.out.println();
        if (selection == 0) {
            logIn();
        } else {
            selectedClass = student.getClassrooms().get(selection - 1);
            classChoices(selectedClass, student);
        }
    }

    public static void classChoices(Classroom classroom, Student student) {
        System.out.println("[0] Go back\n[1] View tasks\n[2] View marks");
        int selection = sc.nextInt();
        System.out.println();
        if (selection == 0) {
            selectClass(student);
        } else if (selection == 1) {
            selectActiveTask(classroom, student);
        } else {
            viewMarks(classroom, student);
        }
    }

    public static void viewMarks(Classroom classroom, Student student) {
        double totalWeight = 0, totalMark = 0;
        for (TaskCopy taskCopy : student.getTaskCopies()) {
            if (taskCopy.getMark() != -1) {
                System.out.printf("%s: %.1f%%\n",taskCopy.getTask().getOutline().getName(), taskCopy.getMark());
                totalWeight+= taskCopy.getTask().getWeight();
                totalMark += taskCopy.getMark()*taskCopy.getTask().getWeight();
            }
        }
        System.out.printf("Grade Average: %.1f%%\n", totalMark/totalWeight);
        System.out.println();
    }

    public static void selectActiveTask(Classroom classroom, Student student) {
        ArrayList<Task> tasks = classroom.getActiveTasks().getTasks();
        System.out.println("[0] Go back");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.printf("[%d] %s\n", i + 1, tasks.get(i).getOutline().getName());
        }
        System.out.print("Select task: ");
        int selection = sc.nextInt();
        System.out.println();
        if (selection == 0) {
            classChoices(classroom,student);
        } else {
            selectedTask = tasks.get(selection - 1);
            taskChoices(classroom, student, selectedTask);
        }
    }

    public static void taskChoices(Classroom classroom, Student student, Task task) {
        TaskCopy taskCopy = student.getCopyOfTask(task);
        int selection = -1;
        if (taskCopy == null) {
            System.out.print("[0] Go back\n[1] Start on task\nSelect choice: ");
            selection = sc.nextInt();
            System.out.println();
            if (selection == 1) {
                student.startTask(task);
            } else if (selection == 0) {
                selectActiveTask(classroom, student);
            }
        }
        if (selection != 0) {
            taskCopy = student.getCopyOfTask(task);
            System.out.print("[0] Go back\n[1] Work on task (" + taskCopy.getPercentComplete() + "% complete)");
            selection = sc.nextInt();
            if (selection == 1) {
                System.out.print("How much progress?: ");
                taskCopy.work(DatabaseRunner.sc.nextInt());
                System.out.println();
            }
            selectActiveTask(classroom, student);
        }
    }
}