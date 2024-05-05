/*
Class: DatabaseRunner
Author: Vedant Joshi
Date: Jan 16 2023
School: AY Jackson SS
Purpose: The DatabaseRunner class handles the main functionality of the database system.
 */

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Scanner;

public class DatabaseRunner {
    private static Database selectedDatabase;
    public final static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        Databases.load();
        // Loads all systems
        selectDatabase();
    }

    /**
     * Allows the user to select a database or save and exit.
     */
    public static void selectDatabase() {
        System.out.println("[0] Save and exit");
        Databases.listDatabases();
        System.out.print("Select Database: ");
        int select = sc.nextInt();
        if (select != 0) {
            selectedDatabase = Databases.getDatabases().get(select - 1);
            System.out.println();
            logIn();
        } else {
            Databases.save();
        }
    }

    /**
     * Handles user login or account creation.
     */
    public static void logIn() {
        int id, selection;
        String pass;
        boolean firstRun = true;
        Person selectedPerson = null;

        // Loop until a valid login or account creation is performed
        do {
            // Display error message if it's not the first run and the login was unsuccessful
            if (!firstRun) {
                System.out.println("Incorrect ID/Pass\n");
            } else {
                firstRun = false;
            }

            // Prompt the user to enter ID or create an account
            System.out.print("Enter your ID, [0] to go back, or [-1] to create an account: ");
            id = sc.nextInt();

            // Process login or account creation based on user input
            if (id > 0) {
                System.out.print("Enter your password: ");
                sc.nextLine();
                pass = sc.nextLine();
                // Check if the entered ID exists in the database
                if (selectedDatabase.getPersons().getByID(id) != null) {
                    // Attempt to log in with the entered ID and password
                    selectedPerson = selectedDatabase.logIn(id, pass);
                }
            } else if (id == -1) {
                int code, subjectType, age;
                String name;

                // Display options for user role (teacher or student)
                System.out.print("[1] Teacher\n[2] Student\nSelect role: ");
                selection = sc.nextInt();
                System.out.print("Enter name: ");
                sc.nextLine();
                name = sc.nextLine();
                System.out.print("Enter age: ");
                age = sc.nextInt();
                System.out.print("Enter password: ");
                sc.nextLine();
                pass = sc.nextLine();

                if (selection == 1) {
                    // If user chooses to be a teacher, prompt for additional information
                    System.out.print("Enter teacher code: ");
                    code = sc.nextInt();
                    System.out.print("[1] Stem\n[2] Humanities\nSelect subject type: ");
                    subjectType = sc.nextInt();
                    // Sign up the user as a teacher
                    selectedPerson = selectedDatabase.signUpTeacher(name, age, code, pass, subjectType);
                } else {
                    // Sign up the user as a student
                    selectedPerson = selectedDatabase.signUpStudent(name, age, pass);
                }
            }
        } while ((selectedPerson == null) && (id != 0));
        // If the user doesn't choose to go back
        if (id != 0) {
            // Display a welcome message and proceed based on the user's role
            System.out.println("Welcome " + selectedPerson.getName() + "!\n");

            if (selectedPerson instanceof Student student) {
                // If the user is a student, proceed to class selection
                selectClass(student);
            } else {
                // If the user is a teacher, proceed to teacher-specific choices
                teacherChoices((Teacher) selectedPerson);
            }
        }
        logIn();
    }

    /**
     * Allows a teacher to choose actions such as selecting or creating a classroom.
     *
     * @param teacher The teacher accessing the system.
     */
    public static void teacherChoices(Teacher teacher) {
        int selection;

        // Display menu options for the teacher
        System.out.print("[0] Go back\n[1] Select Classroom\n[2] Create Classroom\nSelect option: ");
        selection = sc.nextInt();
        System.out.println();

        // Process the selected option
        if (selection == 0) {
            // If the teacher chooses to go back, return to the login screen
            logIn();
        } else if (selection == 1) {
            // If the teacher chooses to select a classroom, proceed to class selection
            selectClass(teacher);
        } else if (selection == 2) {
            // If the teacher chooses to create a classroom, proceed to classroom creation
            createClass(teacher);
        } else {
            // If an invalid option is selected, prompt the teacher again
            teacherChoices(teacher);
        }
    }

    /**
     * Allows a person to select a classroom from the list of classrooms they are associated with.
     * If the person is not in any classes, a message is displayed.
     * Depending on the type of person (Student or Teacher), further actions are taken.
     * @param person The person (Student or Teacher) making the class selection.
     */
    public static void selectClass(Person person) {
        System.out.println("[0] Go back");

        // Retrieve the list of classrooms associated with the person
        ArrayList<Classroom> classrooms = person.getClassrooms();
        int size = classrooms.size();

        if (size == 0) {
            // Display a message if the person is not in any classes
            System.out.println("You are not in any classes currently!");
        } else {
            // Display the list of available classrooms
            for (int i = 0; i < size; i++) {
                System.out.printf("[%d] %s\n", i + 1, person.getClassrooms().get(i).getName());
            }
        }

        // Prompt the user to select a class
        System.out.print("Select class: ");
        int selection = sc.nextInt();
        System.out.println();

        if (selection == 0) {
            // If the person chooses to go back, return to the login screen
            logIn();
        } else {
            // Retrieve the selected classroom
            Classroom selectedClass = person.getClassrooms().get(selection - 1);

            // Perform actions based on the person's type
            if (person instanceof Student) {
                studentClassChoices(selectedClass, (Student) person);
            } else {
                teacherClassChoices(selectedClass, (Teacher) person);
            }
        }
    }

    /**
     * Allows a teacher to create a new class.
     *
     * @param teacher The teacher creating the class.
     */
    public static void createClass(Teacher teacher) {
        // Prompt the teacher to enter the name of the new classroom
        System.out.print("Name: ");
        sc.nextLine(); // Consume the newline character left in the buffer
        String name = sc.nextLine(); // Read the name of the new classroom
        System.out.println();

        // Create a new classroom and add it to the selected database's list of classrooms
        selectedDatabase.getClassrooms().add(new Classroom("Database" + selectedDatabase.getId(), teacher, name, selectedDatabase.getClassrooms().size() + 1, selectedDatabase.getOutlines(), selectedDatabase.getPersons()));

        // Redirect the teacher to the teacher choices menu
        teacherChoices(teacher);
    }

    /**
     * Handles various actions a teacher can take in a classroom.
     *
     * @param classroom The classroom in which the actions are performed.
     * @param teacher   The teacher performing the actions.
     */
    public static void teacherClassChoices(Classroom classroom, Teacher teacher) {
        // Display the menu of choices
        System.out.print("[0] Go back\n[1] Add student to class\n[2] Mark task\n[3] Create task\n[4] View marks\n[5] Delete task\nSelect option: ");
        int selection = sc.nextInt(); // Read the teacher's choice
        System.out.println();

        // Execute the appropriate action based on the teacher's choice
        if (selection == 1) {
            addStudent(classroom, teacher);
        } else if (selection == 2) {
            markTasks(classroom, teacher);
        } else if (selection == 3) {
            createTask(classroom, teacher);
        } else if (selection == 4) {
            viewMarks(classroom, teacher);
        } else if (selection == 5) {
            deleteTask(classroom, teacher);
        }

        // Redirect the teacher to the class selection menu
        selectClass(teacher);
    }

    /**
     * Allows a teacher to add a student to the classroom.
     *
     * @param classroom The classroom to which the student will be added.
     * @param teacher   The teacher performing the action.
     */
    public static void addStudent(Classroom classroom, Teacher teacher) {
        // Display the menu of options for viewing students
        System.out.println("[0] Go back\n[1] View students by first name\n[2] View students by ID\n[3] View students by last then first name\nChoose option: ");
        int selection = sc.nextInt(); // Read the teacher's choice

        if (selection != 0) {
            // Sort students based on the selected option
            Pair[] students;
            if (selection == 1) {
                students = selectedDatabase.getPersons().sortStudentsByName();
            } else if (selection == 2) {
                students = selectedDatabase.getPersons().sortStudentsByID();
            } else {
                students = selectedDatabase.getPersons().sortStudentsByLastAndFirstName();
            }

            int size = students.length, choice;
            Person person;

            // Display the list of students or a message if no students are found
            System.out.println("[0] Go back");
            if (size > 0) {
                for (int i = 0; i < size; i++) {
                    person = students[i].getPerson();
                    System.out.printf("[%d] %-20s%d\n", i + 1, person.getName(), person.getId());
                }
            } else {
                System.out.println("No students in the database!");
            }

            // Allow the teacher to choose a student to add to the classroom
            System.out.print("Choose student: ");
            choice = sc.nextInt();
            if (choice != 0) {
                classroom.addStudent((Student) students[choice - 1].getPerson());
                System.out.println();
            }
        }

        // Redirect the teacher to the class choices menu
        teacherClassChoices(classroom, teacher);
    }

    /**
     * Allows a teacher to mark tasks in the classroom.
     *
     * @param classroom The classroom in which tasks are marked.
     * @param teacher   The teacher performing the action.
     */
    public static void markTasks(Classroom classroom, Teacher teacher) {
        // Get the list of archived tasks for the selected classroom
        ArrayList<Task> tasks = classroom.getArchivedTasks().getTasks();
        int size = tasks.size(), selection;
        Task task;

        // Display the menu of tasks or a message if no tasks are found
        System.out.println("[0] Go back");
        if (size > 0) {
            for (int i = 0; i < size; i++) {
                System.out.printf("[%d] %s\n", i + 1, tasks.get(i).getOutline().getName());
            }

            // Allow the teacher to select a task to mark
            System.out.print("Select task: ");
            selection = sc.nextInt();
            System.out.println();
            if (selection != 0) {
                // Get the selected task and mark it using the task's submission box
                task = tasks.get(selection - 1);
                task.getSubmissionBox().mark();
            }
        } else {
            System.out.println("No tasks!\n");
        }

        // Redirect the teacher to the class choices menu
        teacherClassChoices(classroom, teacher);
    }

    /**
     * Allows a teacher to create a task in the classroom.
     *
     * @param classroom The classroom in which the task is created.
     * @param teacher   The teacher performing the action.
     */
    public static void createTask(Classroom classroom, Teacher teacher) {
        String due;
        int selection, type;
        double weight;
        Outline outline = null;
        Task task;
        boolean back;

        // Allow the teacher to choose between using an existing outline or creating a new one
        System.out.print("[0] Go back\n[1] Use an existing outline\n[2] Create and use a new outline\nSelect option: ");
        selection = sc.nextInt();
        System.out.println();
        back = selection == 0;

        if (!back) {
            if (selection == 1) {
                // Display existing outlines sorted by name
                Pair[] pairs = selectedDatabase.getOutlines().sortOutlinesByName(teacher.getSubjectType());
                int size = pairs.length, choice;
                System.out.println("[0] Go back");

                if (size == 0) {
                    System.out.println("No existing outlines!");
                    createTask(classroom, teacher);
                } else {
                    for (int i = 0; i < size; i++) {
                        System.out.printf("[%d] %s\n", i + 1, pairs[i].getOutline().getName());
                    }
                }

                // Allow the teacher to select an existing outline
                System.out.print("Select option: ");
                choice = sc.nextInt();
                System.out.println();
                back = choice == 0;

                if (!back) {
                    outline = pairs[choice - 1].getOutline();
                } else {
                    createTask(classroom, teacher);
                }
            } else {
                double time;
                String name, desc;

                // Create a new outline with specified details
                System.out.print("Name: ");
                sc.nextLine();
                name = sc.nextLine();
                System.out.print("Description: ");
                desc = sc.nextLine();
                System.out.print("Time estimate: ");
                time = sc.nextDouble();
                outline = new Outline(name, desc, teacher.getSubjectType(), time);
                selectedDatabase.getOutlines().addOutline(outline);
            }

            if (!back) {
                // Specify due date, weight, and type for the new task
                System.out.print("Due date in format \"January 1 2024\": ");
                sc.nextLine();
                due = sc.nextLine();
                System.out.print("Weight where 0 < weight < 1: ");
                weight = sc.nextDouble();
                System.out.print("[1] Assignment\n[2] Evaluation\nSelect type: ");
                type = sc.nextInt();
                System.out.println();

                // Create a new task based on the specified details and add it to the active tasks
                LocalDate today = LocalDate.now(ZoneId.of("-05:00"));
                if (type == 1) {
                    task = new Assignment(new Date(due), new Date(today), weight, outline, new SubmissionBox());
                } else {
                    task = new Evaluation(new Date(due), new Date(today), weight, outline, new SubmissionBox());
                }
                task.getSubmissionBox().setTask(task);
                classroom.getActiveTasks().addTask(task);
            }
        }

        // Redirect the teacher to the class choices menu
        teacherClassChoices(classroom, teacher);
    }

    /**
     * Allows a teacher to delete an active task from a classroom.
     * @param classroom The classroom from which to delete the task.
     * @param teacher The teacher performing the action.
     */
    public static void deleteTask(Classroom classroom, Teacher teacher) {
        System.out.println("[0] Go back");
        ArrayList<Task> tasks = classroom.getActiveTasks().getTasks();
        Task task;
        int size = tasks.size(), selection;

        if (size == 0) {
            System.out.println("No active tasks!");
        } else {
            // Display the list of active tasks for the teacher to choose from
            for (int i = 0; i < size; i++) {
                System.out.printf("[%d] %s\n", i + 1, tasks.get(i).getOutline().getName());
            }

            // Allow the teacher to select a task to delete
            System.out.print("Select task: ");
            selection = sc.nextInt();
            System.out.println();

            if (selection > 0) {
                // Remove the selected task from the active tasks
                task = tasks.get(selection - 1);
                tasks.remove(task);
            }
        }

        // Redirect the teacher to the class choices menu
        teacherClassChoices(classroom, teacher);
    }

    /**
     * Displays the marks for all students in the classroom.
     *
     * @param classroom The classroom for which to display marks.
     * @param teacher   The teacher viewing the marks.
     */
    public static void viewMarks(Classroom classroom, Teacher teacher) {
        // Iterate over each student in the classroom
        for (Student student : classroom.getStudents()) {
            // Display the student's name and ID
            System.out.printf("%-20s%d:\n", student.getName(), student.getId());

            // View the marks for the student
            viewMarks(classroom, student, false);
        }

        // Redirect the teacher to the class choices menu
        teacherClassChoices(classroom, teacher);
    }

    /**
     * Handles various actions a student can take in a classroom.
     *
     * @param classroom The classroom in which the actions are performed.
     * @param student   The student performing the actions.
     */
    public static void studentClassChoices(Classroom classroom, Student student) {
        // Display the menu options for the student
        System.out.print("[0] Go back\n[1] View tasks\n[2] View marks\nSelect option: ");
        int selection = sc.nextInt();
        System.out.println();

        // Process the student's choice
        if (selection == 1) {
            // View active tasks for the student
            selectActiveTask(classroom, student);
        } else if (selection == 2){
            // View marks for the student
            viewMarks(classroom, student, true);
        }
        // Go back to the previous menu
        selectClass(student);
    }

    /**
     * Displays the marks for a student in a classroom.
     *
     * @param classroom     The classroom for which to display marks.
     * @param student       The student for whom to display marks.
     * @param studentAccess A flag indicating whether the student has access to this view.
     */
    public static void viewMarks(Classroom classroom, Student student, boolean studentAccess) {
        // Initialize variables to calculate total weight and total mark
        double totalWeight = 0, totalMark = 0;

        // Iterate through the task copies of the student
        for (TaskCopy taskCopy : student.getTaskCopies()) {
            // Check if the task has been marked
            if (taskCopy.getMark() != -1) {
                // Display the task name and its mark
                System.out.printf("%-20s%.1f%%\n", taskCopy.getTask().getOutline().getName()+":", taskCopy.getMark());

                // Update total weight and total mark
                totalWeight += taskCopy.getTask().getWeight();
                totalMark += taskCopy.getMark() * taskCopy.getTask().getWeight();
            }
        }

        // Check if nothing has been marked yet
        if (totalWeight == 0) {
            System.out.println("Nothing has been marked yet!");
        } else {
            // Display the overall grade average
            System.out.printf("%-20s%.1f%%\n","Grade Average:", totalMark / totalWeight);
        }

        // Add a newline for better readability
        System.out.println();

        // If the student has access, provide further choices
        if (studentAccess) {
            studentClassChoices(classroom, student);
        }
    }

    /**
     * Allows a student to select an active task in the classroom.
     *
     * @param classroom The classroom in which to select an active task.
     * @param student   The student performing the action.
     */
    public static void selectActiveTask(Classroom classroom, Student student) {
        // Get the list of active tasks from the classroom
        ArrayList<Task> tasks = classroom.getActiveTasks().getTasks();
        int size = tasks.size();

        // Display options to go back or select a task
        System.out.println("[0] Go back");

        // Display the list of active tasks
        if (size > 0) {
            for (int i = 0; i < size; i++) {
                System.out.printf("[%d] %s\n", i + 1, tasks.get(i).getOutline().getName());
            }
        }

        // Prompt the user to select a task
        System.out.print("Select task: ");
        int selection = sc.nextInt();
        System.out.println();

        // Check if the user wants to go back
        if (selection == 0) {
            studentClassChoices(classroom, student);
        } else {
            // Retrieve the selected task and proceed to task choices
            Task selectedTask = tasks.get(selection - 1);
            taskChoices(classroom, student, selectedTask);
        }
    }

    /**
     * Handles various actions a student can take on a specific task.
     *
     * @param classroom The classroom in which the task is located.
     * @param student   The student performing the actions.
     * @param task      The task on which the actions are performed.
     */
    public static void taskChoices(Classroom classroom, Student student, Task task) {
        // Retrieve the task copy associated with the student
        TaskCopy taskCopy = student.getCopyOfTask(task);
        int selection = -1;

        // Check if the student has not started the task yet
        if (taskCopy == null) {
            // Display options to go back or start the task
            System.out.print("[0] Go back\n[1] Start on task\nSelect choice: ");
            selection = sc.nextInt();
            System.out.println();

            // Check the user's choice
            if (selection == 1) {
                // Start the task
                student.startTask(task);
            } else if (selection == 0) {
                // Go back to the list of active tasks
                selectActiveTask(classroom, student);
            }
        }

        // Check if the user did not choose to go back
        if (selection != 0) {
            // Retrieve the task copy again
            taskCopy = student.getCopyOfTask(task);

            // Display options to go back or work on the task
            System.out.print("[0] Go back\n[1] Work on task (" + taskCopy.getPercentComplete() + "% complete) ");
            selection = sc.nextInt();
            System.out.println();

            // Check the user's choice
            if (selection == 1) {
                // Prompt the user for progress on the task
                System.out.print("How much progress?: ");
                taskCopy.work(sc.nextInt());
                System.out.println();
            }

            // Go back to the list of active tasks
            selectActiveTask(classroom, student);
        }
    }
}