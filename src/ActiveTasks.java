public class ActiveTasks extends TaskSaves{
    public ActiveTasks(String save, Outlines outlines, Persons persons) {
        super(save, outlines, persons);
    }

    public void addTask(Task task) {
        super.getTasks().add(task);
        save();
    }
}
