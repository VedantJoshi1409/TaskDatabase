import java.util.ArrayList;

public class Database {
    private String name;
    private Outlines outlines;
    private Persons persons;
    private static ArrayList<Database> databases;

    public Database(String name, String outlinesSave, String personsSave) {
        this.name = name;
        outlines = new Outlines(outlinesSave);
        persons = new Persons(personsSave);
    }

    public static void loadDatabases() {

    }

    public Person logIn(int id, String password) {
        Person temp = persons.getByID(id);
        if (temp.getPass().equals(password)) {
            return temp;
        }
        return null;
    }
}
