/*
Class: Classroom
Author: Vedant Joshi
Date: Jan 20 2023
School: AY Jackson SS
Purpose: The Classroom class represents a classroom with a teacher, students, and tasks.
It includes methods for managing the classroom, such as adding students, changing the teacher, and retrieving task information.
 */

import java.util.ArrayList;

public class Classroom {
    private Teacher teacher;
    private String name;
    private ArrayList<Student> students = new ArrayList<Student>();
    private int id;
    private ClassroomSave classroomSave;
    private ActiveTasks activeTasks;
    private ArchivedTasks archivedTasks;

    public Classroom(String database, Teacher teacher, String name, int id, Outlines outlines, Persons persons) {
        this.teacher = teacher;
        this.name = name;
        this.id = id;
        classroomSave = new ClassroomSave(database+"/Classrooms/Classroom"+id, this, outlines, persons);
        archivedTasks = classroomSave.getArchivedTasks();
        activeTasks = classroomSave.getActiveTasks();
        classroomSave.save();
        teacher.getClassrooms().add(this);
    }

    public Classroom(String save, Outlines outlines, Persons persons) {
        classroomSave = new ClassroomSave(save, this, outlines, persons);
        classroomSave.load();
        activeTasks = classroomSave.getActiveTasks();
        archivedTasks = classroomSave.getArchivedTasks();
    }

    public ArchivedTasks getArchivedTasks() {
        return archivedTasks;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ClassroomSave getClassroomSave() {
        return classroomSave;
    }

    public ActiveTasks getActiveTasks() {
        activeTasks.archiveOld();
        //Archives all overdue tasks before returning them
        return activeTasks;
    }

    public void addStudent(Student student) {
        students.add(student);
    }
}