import java.io.*;

public class ClassroomSave extends Save {
    private Classroom classRoom;
    private ActiveTasks activeTasks;
    private ArchivedTasks archivedTasks;
    private Persons persons;

    public ClassroomSave(String save, Classroom classRoom, Outlines outlines, Persons persons) {
        super(save);
        this.classRoom = classRoom;
        new File(save).mkdir();
        new File(SAVE_NAME+"/ClassroomSave.txt");
        activeTasks = new ActiveTasks(save+"/activeSave.txt", outlines, persons);
        archivedTasks = new ArchivedTasks(save+"/archivedSave.txt", outlines, persons);
        this.persons = persons;
    }

    public ActiveTasks getActiveTasks() {
        return activeTasks;
    }

    public void setActiveTasks(ActiveTasks activeTasks) {
        this.activeTasks = activeTasks;
    }

    public void save() {
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(SAVE_NAME+"/ClassroomSave.txt"));
            out.write(classRoom.getId());
            out.newLine();
            out.write(classRoom.getTeacher().getId());
            out.newLine();
            out.write(classRoom.getName());
            out.newLine();
            out.write(classRoom.getStudents().size());
            out.newLine();
            for (Student student : classRoom.getStudents()) {
                out.write(student.getId());
                out.newLine();
            }
            out.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public void load() {
        Person person;
        int classId, teacherId, length;
        try {
            BufferedReader in = new BufferedReader(new FileReader(SAVE_NAME+"/ClassroomSave.txt"));
            classRoom.setId(Integer.parseInt(in.readLine()));
            classRoom.setTeacher((Teacher)persons.getByID(Integer.parseInt(in.readLine())));
            classRoom.setName(in.readLine());
            length = Integer.parseInt(in.readLine());
            for (int i = 0; i < length; i++) {
                person = persons.getByID(Integer.parseInt(in.readLine()));
                classRoom.addStudent((Student) person);
                ((Student) person).getClassrooms().add(classRoom);
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
