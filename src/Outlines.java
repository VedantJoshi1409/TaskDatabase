/*
Class: Outlines
Author: Vedant Joshi
Date: Jan 12 2023
School: AY Jackson SS
Purpose: The Outlines class represents a collection of outlines for tasks.
It provides methods to save and load outlines, as well as manage and access them.
 */

import java.io.*;
import java.util.ArrayList;

public class Outlines extends Save {
    // Constants representing subject types
    public static final String STEM = "Stem";
    public static final String HUMANITIES = "HUMANITIES";

    // Lists to store all outlines, STEM outlines, and Humanities outlines
    private ArrayList<Outline> outlines = new ArrayList<>();
    private ArrayList<Outline> stemOutlines = new ArrayList<>();
    private ArrayList<Outline> humanitiesOutlines = new ArrayList<>();

    public Outlines(String save) {
        super(save);
        load();
        separateOutlineTypes();
    }

    /**
     * Saves the outlines to a file.
     */
    public void save() {
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(SAVE_NAME));
            out.write("" + outlines.size());
            out.newLine();
            for (Outline outline : outlines) {
                out.write(outline.getName());
                out.newLine();
                out.write(outline.getDescription());
                out.newLine();
                out.write(outline.getSubjectType());
                out.newLine();
                out.write("" + outline.getTimeEstimate());
                out.newLine();
            }
            out.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    /**
     * Loads outlines from a file.
     */
    public void load() {
        String name, desc, type, temp;
        double time;
        int size;
        Outline outline;
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
                    outline = new Outline(name, desc, type, time);
                    if (!outlines.contains(outline)) outlines.add(outline);
                }
            }
            in.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    /**
     * Gets the outline with the specified name.
     *
     * @param name The name of the outline to retrieve.
     * @return The outline with the specified name, or null if not found.
     */
    public Outline getOutline(String name) {
        for (Outline outline : outlines) {
            if (outline.getName().equals(name)) {
                return outline;
            }
        }
        return null;
    }

    /**
     * Adds an outline to the collection.
     *
     * @param outline The outline to add.
     */
    public void addOutline(Outline outline) {
        outlines.add(outline);
        if (outline.getSubjectType().equals(STEM)) {
            stemOutlines.add(outline);
        } else {
            humanitiesOutlines.add(outline);
        }
    }

    /**
     * Sorts outlines by name based on the specified subject type.
     *
     * @param type The subject type (STEM or HUMANITIES) for sorting.
     * @return An array of pairs containing names and corresponding outlines, sorted by name.
     */
    public Pair[] sortOutlinesByName(String type) {
        Outline outline;
        ArrayList<Outline> outlines;

        // Determine the list of outlines based on the subject type
        if (type.equals(STEM)) {
            outlines = stemOutlines;
        } else {
            outlines = humanitiesOutlines;
        }

        // Create an array of pairs to store names and corresponding outlines
        Pair[] pairs = new Pair[outlines.size()];

        // Populate the array with pairs (name, outline)
        for (int i = 0; i < outlines.size(); i++) {
            outline = outlines.get(i);
            pairs[i] = new Pair(outline.getName(), outline);
        }

        // Use the selection sort algorithm to sort the array by names
        SortSearch.selectionSort(pairs);

        // Return the sorted array of pairs
        return pairs;
    }

    /**
     * Separates outlines into STEM and Humanities types.
     */
    private void separateOutlineTypes() {
        for (Outline outline : outlines) {
            if (outline.getSubjectType().equals(STEM)) {
                stemOutlines.add(outline);
            } else {
                humanitiesOutlines.add(outline);
            }
        }
    }
}