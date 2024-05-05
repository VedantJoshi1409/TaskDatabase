/*
Class: Date
Author: Vedant Joshi
Date: Jan 9 2023
School: AY Jackson SS
Purpose: Represents a date with day, month, and year, providing methods for manipulation and comparison.
 */

import java.time.LocalDate;

public class Date {
    // Array to map month numbers to month names
    public final String[] MONTHS = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};

    // Day, month, and year components of the date
    private int day;
    private int month;
    private int year;

    public Date(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    /**
     * Constructs a Date object from a string representation of the date.
     * The string should be in the format "Month Day Year".
     *
     * @param date The string representation of the date.
     */
    public Date(String date) {
        String[] values = date.split(" ");
        for (int i = 0; i < MONTHS.length; i++) {
            if (values[0].equals(MONTHS[i])) {
                month = i + 1;
            }
        }
        day = Integer.parseInt(values[1]);
        year = Integer.parseInt(values[2]);
    }

    /**
     * Constructs a Date object from a LocalDate object.
     *
     * @param date The LocalDate object.
     */
    public Date(LocalDate date) {
        year = date.getYear();
        month = date.getMonthValue();
        day = date.getDayOfMonth();
    }

    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    /**
     * Checks if the current date is later than another date.
     *
     * @param other The other date to compare with.
     * @return True if the current date is later than the other date; otherwise, false.
     */
    public boolean laterThan(Date other) {
        if (year > other.getYear()) {
            return true;
        } else if (year < other.getYear()) {
            return false;
        } else {
            if (month > other.getMonth()) {
                return true;
            } else if (month < other.getMonth()) {
                return false;
            } else {
                return day > other.getDay();
            }
        }
    }

    /**
     * Returns a string representation of the date.
     *
     * @return The string representation of the date.
     */
    public String toString() {
        return (MONTHS[month - 1] + " " + day + " " + year);
    }
}