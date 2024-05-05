/*
Class: ClassroomSave
Author: Vedant Joshi
Date: Jan 20 2023
School: AY Jackson SS
Purpose: The ClassroomSave class represents the saving and loading mechanism
for a Classroom, including its associated tasks, teacher, and students.
It extends the Save class and provides methods to save and load the state of a classroom.
 */

import java.io.*;

public class ClassroomSave extends Save {
    private Classroom classRoom;
    private ActiveTasks activeTasks;
    private ArchivedTasks archivedTasks;
    private Persons persons;

    public ClassroomSave(String save, Classroom classRoom, Outlines outlines, Persons persons) {
        super(save);
        this.classRoom = classRoom;
        boolean dirCreated = new File(SAVE_NAME).mkdirs(), classCreated, archiveCreated, activeCreated;
        try {
            // Create files for saving classroom, active tasks, and archived tasks
            File classroom = new File(SAVE_NAME + "/ClassroomSave.txt");
            classCreated = classroom.createNewFile();
            File archive = new File(SAVE_NAME + "/ArchivedSave.txt");
            archiveCreated = archive.createNewFile();
            File active = new File(SAVE_NAME + "/ActiveSave.txt");
            activeCreated = active.createNewFile();
        } catch (IOException e) {
            System.out.println(e);
        }
        // Initialize ArchivedTasks and ActiveTasks instances
        archivedTasks = new ArchivedTasks(save + "/ArchivedSave.txt", outlines, persons);
        activeTasks = new ActiveTasks(save + "/ActiveSave.txt", outlines, persons, archivedTasks);
        this.persons = persons;
    }
    public ActiveTasks getActiveTasks() {
        return activeTasks;
    }

    public ArchivedTasks getArchivedTasks() {
        return archivedTasks;
    }

    /**
     * Saves the state of the classroom, including its ID, teacher, name,
     * students, and associated task lists (active and archived).
     */
    public void save() {
        // Save archived tasks and active tasks
        archivedTasks.save();
        activeTasks.save();
        try {
            // Write classroom details to ClassroomSave.txt
            BufferedWriter out = new BufferedWriter(new FileWriter(SAVE_NAME + "/ClassroomSave.txt"));
            out.write("" + classRoom.getId());
            out.newLine();
            out.write("" + classRoom.getTeacher().getId());
            out.newLine();
            out.write(classRoom.getName());
            out.newLine();
            out.write("" + classRoom.getStudents().size());
            out.newLine();
            // Write each student ID to the file
            for (Student student : classRoom.getStudents()) {
                out.write("" + student.getId());
                out.newLine();
            }
            out.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    /**
     * Loads the state of the classroom from the saved data,
     * including its ID, teacher, name, and students.
     */
    public void load() {
        Person person;
        int length;
        Teacher teacher;
        try {
            // Read ClassroomSave.txt and update classRoom
            BufferedReader in = new BufferedReader(new FileReader(SAVE_NAME + "/ClassroomSave.txt"));
            String firstLine = in.readLine();
            if (firstLine != null) {
                // Update classRoom details
                classRoom.setId(Integer.parseInt(firstLine));
                teacher = (Teacher) persons.getByID(Integer.parseInt(in.readLine()));
                classRoom.setTeacher(teacher);
                teacher.getClassrooms().add(classRoom);
                classRoom.setName(in.readLine());
                length = Integer.parseInt(in.readLine());
                // Update students in classRoom
                for (int i = 0; i < length; i++) {
                    person = persons.getByID(Integer.parseInt(in.readLine()));
                    if (!classRoom.getStudents().contains((Student) person)) classRoom.addStudent((Student) person);
                    if (!person.getClassrooms().contains(classRoom)) person.getClassrooms().add(classRoom);
                }
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
