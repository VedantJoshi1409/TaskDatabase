/*
Class: Database
Author: Vedant Joshi
Date: Jan 16 2023
School: AY Jackson SS
Purpose: The Database class represents a database containing classrooms,
outlines, persons, and provides authentication methods.
 */

import java.util.ArrayList;

public class Database {
    private String name;
    private int id;
    private Outlines outlines;
    private Persons persons;
    private ArrayList<Classroom> classrooms = new ArrayList<Classroom>();

    public Database(String name, int id, Outlines outlines, Persons persons) {
        this.name = name;
        this.id = id;
        this.outlines = outlines;
        this.persons = persons;
        // Register the database in the Databases class
        Databases.addDatabase(this);
    }

    public ArrayList<Classroom> getClassrooms() {
        return classrooms;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public Outlines getOutlines() {
        return outlines;
    }

    public Persons getPersons() {
        return persons;
    }

    /**
     * Authenticates a person with the specified ID and password.
     *
     * @param id       The ID of the person.
     * @param password The password of the person.
     * @return The authenticated person or null if login fails.
     */
    public Person logIn(int id, String password) {
        Person temp = persons.getByID(id);
        // Check if the password matches
        if (temp != null && temp.getPass().equals(password)) {
            return temp;
        }
        return null;
    }

    /**
     * Signs up a new teacher with the specified details.
     *
     * @param name        The name of the teacher.
     * @param age         The age of the teacher.
     * @param code        The teacher code for authentication.
     * @param password    The password for the teacher.
     * @param subjectType The type of subject the teacher teaches (1 for STEM, 2 for Humanities).
     * @return The newly signed-up teacher or null if signup fails.
     */
    public Teacher signUpTeacher(String name, int age, int code, String password, int subjectType) {
        Teacher teacher;
        String subject;
        if (code == Teacher.TEACHER_CODE) {
            // Determine the subject based on subjectType
            subject = (subjectType == 1) ? "Stem" : "Humanities";
            // Create a new teacher
            teacher = new Teacher(name, age, persons.getPersons().size() + 1, password, subject);
            // Add the teacher to persons and teachers lists
            persons.getPersons().add(teacher);
            persons.getTeachers().add(teacher);
            System.out.println("Your ID is: " + persons.getPersons().size());
        } else {
            // Teacher code incorrect, signup fails
            teacher = null;
            System.out.println("Teacher code incorrect!");
        }
        return teacher;
    }

    /**
     * Signs up a new student with the specified details.
     *
     * @param name     The name of the student.
     * @param age      The age of the student.
     * @param password The password for the student.
     * @return The newly signed-up student.
     */
    public Student signUpStudent(String name, int age, String password) {
        // Create a new student
        Student student = new Student(name, age, persons.getPersons().size() + 1, password);
        // Add the student to persons and students lists
        persons.getPersons().add(student);
        persons.getStudents().add(student);
        System.out.println("Your ID is: " + persons.getPersons().size());
        return student;
    }
}
