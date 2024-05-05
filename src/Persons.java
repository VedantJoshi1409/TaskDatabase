import java.util.ArrayList;

public class Persons {
    public final String PERSONS_SAVE;
    ArrayList<Person> persons = new ArrayList<Person>();

    public Persons(String save) {
        PERSONS_SAVE = save;
        load();
    }

    public void save() {

    }

    public void load() {

    }

    public Person getByID(int id) {
        for (Person person : persons) {
            if (person.getId() == id) {
                return person;
            }
        }
        return null;
    }
}
