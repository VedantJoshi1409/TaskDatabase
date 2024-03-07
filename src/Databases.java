import java.io.*;
import java.util.ArrayList;

public class Databases {
    private static ArrayList<Database> databases = new ArrayList<Database>();
    private static final String SAVE_NAME = "Task Database/Saves";

    public Databases() {
        load();
    }

    public static void addDatabase(Database database) {
        databases.add(database);
    }

    public static void save() {
        String base;
        try {
            BufferedWriter out;
            for (Database database : databases) {
                base = SAVE_NAME + "/Database" + database.getId();
                new File(base).mkdir();
                out = new BufferedWriter(new FileWriter(base + "/DatabaseInfo"));
                out.write(database.getName());
                out.newLine();
                out.write(database.getId());
                out.newLine();
                database.getOutlines().save();
                database.getPersons().save();
                out.close();
                for (Classroom classroom : database.getClassrooms()) {
                    classroom.getClassroomSave().save();
                }
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public static void load() {
        Database database;
        String[] databaseNames = new File(SAVE_NAME).list(), classroomNames;
        String baseTemplate, template, databaseName;
        int id = -1;
        Outlines outlines;
        Persons persons;
        for (String name : databaseNames) {
            baseTemplate = SAVE_NAME + "/" + name;
            try {
                BufferedReader in = new BufferedReader(new FileReader(baseTemplate+"/DatabaseInfo.txt"));
                databaseName = in.readLine();
                id = Integer.parseInt(in.readLine());
            } catch (IOException e) {
                System.out.println(e);
            }
            outlines = new Outlines(name + "/Outlines.txt");
            persons = new Persons(name + "/Persons.txt");
            classroomNames = new File(baseTemplate+ "/Classrooms").list();
            database = new Database(name, id, outlines, persons);
            for (String className : classroomNames) {
                template = name + "/Classrooms/" + className;
                //Saves/Databases/Database1/Classrooms/Classroom1
                database.getClassrooms().add(new Classroom(template, outlines, persons));
            }
        }
    }

    public static void listDatabases() {
        for (int i = 0; i < databases.size(); i++) {
            System.out.printf("[%d] %s\n",i+1, databases.get(i).getName());
        }
    }

    public static ArrayList<Database> getDatabases() {
        return databases;
    }

    public static void setDatabases(ArrayList<Database> databases) {
        Databases.databases = databases;
    }
}
