public abstract class Save {
    public final String SAVE_NAME;

    public Save(String save) {
        this.SAVE_NAME = "Task Database/Saves/"+save;
    }

    public abstract void save();

    public abstract void load();
}
