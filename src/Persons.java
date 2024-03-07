import java.io.*;
import java.util.ArrayList;

public class Persons extends Save{
    ArrayList<Person> persons = new ArrayList<Person>();

    public Persons(String save) {
        super(save);
        load();
    }

    public void save() {
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(SAVE_NAME));
            out.write(persons.size());
            out.newLine();
            for (Person person : persons) {
                out.write(person.getName());
                out.newLine();
                out.write(person.getAge());
                out.newLine();
                out.write(person.getId());
                out.newLine();
                out.write(person.getPass());
                out.newLine();
                if (person instanceof Teacher) {
                    out.write("Teacher");
                    out.newLine();
                    out.write(((Teacher) person).getSubjectType());
                } else {
                    out.write("Student");
                    out.newLine();
                    out.write(((Student)person).getStemProficiency());
                    out.newLine();
                    out.write(((Student)person).getHumanitiesProficiency());
                    out.newLine();
                    out.write(((Student)person).getBurstLength());
                    out.newLine();
                    out.write(((Student)person).getDailyLength());
                }
                out.newLine();
            }
            out.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public void load() {
        int length, age, id, stem, humanities, burst, daily;
        String name, pass, type, subjectType, temp;
        try {
            BufferedReader in = new BufferedReader(new FileReader(SAVE_NAME));
            temp = in.readLine();
            if (temp != null) {
                length = Integer.parseInt(temp);
                for (int i = 0; i < length; i++) {
                    name = in.readLine();
                    age = Integer.parseInt(in.readLine());
                    id = Integer.parseInt(in.readLine());
                    pass = in.readLine();
                    type = in.readLine();
                    if (type.equals("Teacher")) {
                        subjectType = in.readLine();
                        persons.add(new Teacher(name, age, id, pass, subjectType));
                    } else {
                        stem = Integer.parseInt(in.readLine());
                        humanities = Integer.parseInt(in.readLine());
                        burst = Integer.parseInt(in.readLine());
                        daily = Integer.parseInt(in.readLine());
                        persons.add(new Student(name, age, id, pass, stem, humanities, burst, daily));
                    }
                }
            }
            in.close();
        } catch (IOException e) {
            System.out.println(e);
        }

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
