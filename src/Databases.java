/*
Class: Database
Author: Vedant Joshi
Date: Jan 20 2023
School: AY Jackson SS
Purpose: The Databases class manages a collection of databases, providing methods for saving and loading data.
 */

import java.io.*;
import java.util.ArrayList;

public class Databases {
    // Collection of databases
    private static ArrayList<Database> databases = new ArrayList<>();
    // Path to the directory where databases are saved
    private static final String SAVE_NAME = "Task Database/Saves";

    /**
     * Adds a database to the collection.
     *
     * @param database The database to be added.
     */
    public static void addDatabase(Database database) {
        databases.add(database);
    }

    /**
     * Saves the databases, including outlines, persons, and classrooms, to files.
     */
    public static void save() {
        String base;
        try {
            BufferedWriter out;
            // Loop through each database
            for (Database database : databases) {
                base = SAVE_NAME + "/Database" + database.getId();
                // Create a directory for the database
                new File(base).mkdir();
                // Write database information to a file
                out = new BufferedWriter(new FileWriter(base + "/DatabaseInfo.txt"));
                out.write(database.getName());
                out.newLine();
                out.write("" + database.getId());
                out.newLine();
                // Save outlines and persons
                database.getOutlines().save();
                database.getPersons().save();
                out.close();
                // Save each classroom within the database
                for (Classroom classroom : database.getClassrooms()) {
                    classroom.getClassroomSave().save();
                }
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    /**
     * Loads databases, including outlines, persons, and classrooms, from saved files.
     */
    public static void load() {
        Database database;
        String[] databaseNames = new File(SAVE_NAME).list(), classroomNames;
        String baseTemplate, template, databaseName = "";
        int id = -1;
        Outlines outlines;
        Persons persons;
        // Loop through each saved database
        for (String name : databaseNames) {
            // Database1
            baseTemplate = SAVE_NAME + "/" + name;
            // Task Database/Saves/Database1
            try {
                BufferedReader in = new BufferedReader(new FileReader(baseTemplate + "/DatabaseInfo.txt"));
                // Read database information from a file
                databaseName = in.readLine();
                id = Integer.parseInt(in.readLine());
            } catch (IOException e) {
                System.out.println(e);
            }
            // Create outlines and persons instances for the database
            outlines = new Outlines(name + "/Outlines.txt");
            persons = new Persons(name + "/Persons.txt");
            // List of saved classrooms for the database
            classroomNames = new File(baseTemplate + "/Classrooms").list();
            // Create a new database instance
            database = new Database(databaseName, id, outlines, persons);
            // Loop through each saved classroom and add it to the database
            for (String className : classroomNames) {
                template = name + "/Classrooms/" + className;
                // Database1/Classrooms/Classroom1
                database.getClassrooms().add(new Classroom(template, outlines, persons));
            }
        }
    }

    /**
     * Lists the names of the existing databases.
     */
    public static void listDatabases() {
        for (int i = 0; i < databases.size(); i++) {
            System.out.printf("[%d] %s\n", i + 1, databases.get(i).getName());
        }
    }

    public static ArrayList<Database> getDatabases() {
        return databases;
    }

    public static void setDatabases(ArrayList<Database> databases) {
        Databases.databases = databases;
    }
}