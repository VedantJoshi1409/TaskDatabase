/*
Class: Save
Author: Vedant Joshi
Date: Jan 16 2023
School: AY Jackson SS
Purpose: The Save class is an abstract class that provides a common structure for saving and loading data.
 Classes extending Save must implement the save and load methods based on their specific requirements.
 */

public abstract class Save {
    public final String PATH = "Task Database/Saves/";
    /**
     * The name of the save file.
     */
    public final String SAVE_NAME;

    public Save(String save) {
        this.SAVE_NAME = PATH + save;
    }

    /**
     * Abstract method to save data. Subclasses must implement this method based on their specific requirements.
     */
    public abstract void save();

    /**
     * Abstract method to load data. Subclasses must implement this method based on their specific requirements.
     */
    public abstract void load();
}