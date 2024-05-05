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
        Databases.addDatabase(this);
    }

    public ArrayList<Classroom> getClassrooms() {
        return classrooms;
    }

    public void addClass(Classroom classroom) {
        classrooms.add(classroom);
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

    public void setId(int id) {
        this.id = id;
    }

    public Outlines getOutlines() {
        return outlines;
    }

    public void setOutlines(Outlines outlines) {
        this.outlines = outlines;
    }

    public Persons getPersons() {
        return persons;
    }

    public void setPersons(Persons persons) {
        this.persons = persons;
    }

    public Person logIn(int id, String password) {
        Person temp = persons.getByID(id);
        if (temp.getPass().equals(password)) {
            return temp;
        }
        return null;
    }
}
