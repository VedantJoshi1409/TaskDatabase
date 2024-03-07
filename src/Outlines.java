import java.io.*;
import java.util.ArrayList;

public class Outlines extends Save{
    private ArrayList<Outline> outlines = new ArrayList<Outline>();

    public Outlines(String save) {
        super(save);
        load();
    }

    public void save() {
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(SAVE_NAME));
            out.write(outlines.size());
            out.newLine();
            for (Outline outline : outlines) {
                out.write(outline.getName());
                out.newLine();
                out.write(outline.getDescription());
                out.newLine();
                out.write(outline.getSubjectType());
                out.newLine();
                out.write(""+outline.getTimeEstimate());
            }
            out.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public void load() {
        String name, desc, type, temp;
        double time;
        int size;
        try {
            BufferedReader in = new BufferedReader(new FileReader(SAVE_NAME));
            temp = in.readLine();
            if (temp != null) {
                size = Integer.parseInt(temp);
                for (int i = 0; i < size; i++) {
                    name = in.readLine();
                    desc = in.readLine();
                    type = in.readLine();
                    time = Double.parseDouble(in.readLine());
                    outlines.add(new Outline(name, desc, type, time));
                }
            }
            in.close();
        } catch (IOException e) {
            System.out.println(e);
        }
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
