/*
Class: Persons
Author: Vedant Joshi
Date: Jan 12 2023
School: AY Jackson SS
Purpose: The Persons class represents a collection of Person objects, which can include both students and teachers.
It extends the Save class to provide functionality for saving and loading the data.
 */

import java.io.*;
import java.util.ArrayList;

public class Persons extends Save {
    // ArrayLists to store different types of persons
    private ArrayList<Person> persons = new ArrayList<Person>();
    private ArrayList<Student> students = new ArrayList<Student>();
    private ArrayList<Teacher> teachers = new ArrayList<Teacher>();

    public Persons(String save) {
        super(save);
        load();
        separatePersonTypes();
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public ArrayList<Teacher> getTeachers() {
        return teachers;
    }

    public ArrayList<Person> getPersons() {
        return persons;
    }

    /**
     * Saves the persons' data to the specified save file.
     */
    public void save() {
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(SAVE_NAME));

            // Writing the number of persons to the file
            out.write("" + persons.size());
            out.newLine();

            // Writing individual person data to the file
            for (Person person : persons) {
                out.write(person.getName());
                out.newLine();
                out.write("" + person.getAge());
                out.newLine();
                out.write("" + person.getId());
                out.newLine();
                out.write(person.getPass());
                out.newLine();

                // Determining if the person is a teacher or student
                if (person instanceof Teacher) {
                    out.write("Teacher");
                    out.newLine();
                    out.write(((Teacher) person).getSubjectType());
                } else {
                    out.write("Student");
                }
                out.newLine();
            }

            out.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    /**
     * Loads persons' data from the save file.
     */
    public void load() {
        int length, age, id;
        String name, pass, type, subjectType, temp;
        Person person;

        try {
            BufferedReader in = new BufferedReader(new FileReader(SAVE_NAME));

            // Reading the number of persons from the file
            temp = in.readLine();
            if (temp != null) {
                length = Integer.parseInt(temp);

                // Looping through each person in the file
                for (int i = 0; i < length; i++) {
                    // Reading individual person data from the file
                    name = in.readLine();
                    age = Integer.parseInt(in.readLine());
                    id = Integer.parseInt(in.readLine());
                    pass = in.readLine();
                    type = in.readLine();

                    // Creating either a teacher or a student based on the type
                    if (type.equals("Teacher")) {
                        subjectType = in.readLine();
                        person = new Teacher(name, age, id, pass, subjectType);
                        if (!persons.contains(person)) persons.add(person);
                    } else {
                        person = new Student(name, age, id, pass);
                        if (!persons.contains(person)) persons.add(person);
                    }
                }
            }

            in.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    /**
     * Retrieves a person by their ID using binary search.
     *
     * @param id The ID of the person to retrieve.
     * @return The person with the specified ID.
     */
    public Person getByID(int id) {
        return SortSearch.binarySearch(persons, id);
    }

    /**
     * Sorts students by their names and returns an array of pairs containing the sorted data.
     *
     * @return An array of pairs containing the sorted student data.
     */
    public Pair[] sortStudentsByName() {
        Student student;
        Pair[] pairs = new Pair[students.size()];

        // Looping through each student
        for (int i = 0; i < students.size(); i++) {
            student = students.get(i);
            pairs[i] = new Pair(student.getName(), student);
        }

        // Sorting the array of pairs
        SortSearch.bubbleSort(pairs);
        return pairs;
    }

    /**
     * Sorts students by their last and first names and returns an array of pairs containing the sorted data.
     *
     * @return An array of pairs containing the sorted student data.
     */
    public Pair[] sortStudentsByLastAndFirstName() {
        Student student;
        String[] names;
        String name;
        Pair[] pairs = new Pair[students.size()];

        // Looping through each student
        for (int i = 0; i < students.size(); i++) {
            student = students.get(i);
            names = student.getName().split(" ");

            // Constructing a name for sorting based on last and first names
            if (names.length > 1) {
                name = names[names.length - 1] + names[0];
            } else {
                name = names[0];
            }

            pairs[i] = new Pair(name, student);
        }

        // Sorting the array of pairs
        SortSearch.bubbleSort(pairs);
        return pairs;
    }

    /**
     * Sorts students by their IDs and returns an array of pairs containing the sorted data.
     *
     * @return An array of pairs containing the sorted student data.
     */
    public Pair[] sortStudentsByID() {
        Student student;
        Pair[] pairs = new Pair[students.size()];

        // Looping through each student
        for (int i = 0; i < students.size(); i++) {
            student = students.get(i);
            pairs[i] = new Pair(student.getId(), student);
        }

        // Sorting the array of pairs
        SortSearch.bubbleSort(pairs);
        return pairs;
    }

    /**
     * Separates persons into students and teachers based on their types.
     */
    private void separatePersonTypes() {
        // Looping through each person
        for (Person person : persons) {
            // Checking if the person is a student or a teacher and adding them to the respective lists
            if (person instanceof Student) {
                students.add((Student) person);
            } else {
                teachers.add((Teacher) person);
            }
        }
    }
}