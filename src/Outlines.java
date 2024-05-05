import java.util.ArrayList;

public class Outlines {
    private final String SAVE_NAME;
    private ArrayList<Outline> outlines = new ArrayList<Outline>();

    public Outlines(String save) {
        SAVE_NAME = save;
        load();
    }

    public void save() {

    }

    public void load() {

    }

    public Outline getOutline(String name) {
        for (Outline outline : outlines) {
            if (outline.getName().equals(name)) {
                return outline;
            }
        }
        return null;
    }
}
