import java.util.ArrayList;

public class Classroom {
    private Teacher teacher;
    private String name;
    private ArrayList<Student> students = new ArrayList<Student>();
    private int id;
    private ClassroomSave classroomSave;
    private ActiveTasks activeTasks;

    public Classroom(Teacher teacher, String name, int id, Outlines outlines, Persons persons) {
        this.teacher = teacher;
        this.name = name;
        this.id = id;
        classroomSave = new ClassroomSave("Classrooms/Classroom"+id, this, outlines, persons);
        activeTasks = classroomSave.getActiveTasks();
    }

    public Classroom(String save, Outlines outlines, Persons persons) {
        classroomSave = new ClassroomSave(save, this, outlines, persons);
        classroomSave.load();
        activeTasks = classroomSave.getActiveTasks();
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

    public void setStudents(ArrayList<Student> students) {
        this.students = students;
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

    public void setClassroomSave(ClassroomSave classroomSave) {
        this.classroomSave = classroomSave;
    }

    public ActiveTasks getActiveTasks() {
        return activeTasks;
    }

    public void setActiveTasks(ActiveTasks activeTasks) {
        this.activeTasks = activeTasks;
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public void assignAssignment(Date due, Date assigned, double weight, Outline outline, SubmissionBox submissionBox) {
        activeTasks.addTask(new Assignment(due, assigned, weight, outline, submissionBox));
    }

    public void assignEvaluation(Date due, Date assigned, double weight, Outline outline, SubmissionBox submissionBox) {
        activeTasks.addTask(new Evaluation(due, assigned, weight, outline, submissionBox));
    }

    public void submitWork(TaskCopy taskCopy) {
        taskCopy.getTask().getSubmissionBox().upload(taskCopy);
    }


}