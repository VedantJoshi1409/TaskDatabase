/*
Class: Pair
Author: Vedant Joshi
Date: Jan 20 2023
School: AY Jackson SS
Purpose: The Pair class represents a pair of values, associating an Object value with either a Person or an Outline.
 */

public class Pair {
    private Object value;
    private Person person;
    private Outline outline;

    public Pair(Object value, Person person) {
        this.value = value;
        this.person = person;
    }

    public Pair(Object value, Outline outline) {
        this.value = value;
        this.outline = outline;
    }

    public Object getValue() {
        return value;
    }

    public Person getPerson() {
        return person;
    }

    public Outline getOutline() {
        return outline;
    }

    // Compare two Pair objects
    public boolean compareTo(Pair other) {
        if (getValue() instanceof String) {
            // If the value is a String, use String comparison
            return ((String) value).compareTo((String) other.getValue()) > 0;
        } else {
            // If the value is an integer, use integer comparison
            return ((int) value) > ((int) other.value);
        }
    }

    // Compare a Pair's value with an integer
    public boolean compareTo(int other) {
        // If the value is an integer, use integer comparison
        return ((int) value) > (other);
    }
}