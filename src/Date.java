/*
Class: Date
Author: Vedant Joshi
Date: Jan 9 2023
School: AY Jackson SS
Purpose: Represents a date with day, month, and year, providing methods for manipulation and comparison.
 */

public class Date {
    public final String[] MONTHS = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
    private int day;
    private int month;
    private int year;

    // Constructor to initialize a Date object with specified day, month, and year.
    // Parameters:
    //   - day: The day of the date.
    //   - month: The month of the date (1 for January, 2 for February, ..., 12 for December).
    //   - year: The year of the date.
    public Date(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public Date(String date) {
        String[] values = date.split(" ");
        for (int i = 0; i < MONTHS.length; i++) {
            if (values[0].equals(MONTHS[i])) {
                month = i+1;
            }
        }
        day = Integer.parseInt(values[1]);
        year = Integer.parseInt(values[2]);
    }

    // Returns the day of the date.
    // Return value:
    //   - The day of the date.
    public int getDay() {
        return day;
    }

    // Returns the month of the date (1 for January, 2 for February, ..., 12 for December).
    // Return value:
    //   - The month of the date.
    public int getMonth() {
        return month;
    }

    // Returns the year of the date.
    // Return value:
    //   - The year of the date.
    public int getYear() {
        return year;
    }

    // Sets the day of the date.
    // Parameters:
    //   - day: The new day to set.
    public void setDay(int day) {
        this.day = day;
    }

    // Sets the month of the date (1 for January, 2 for February, ..., 12 for December).
    // Parameters:
    //   - month: The new month to set.
    public void setMonth(int month) {
        this.month = month;
    }

    // Sets the year of the date.
    // Parameters:
    //   - year: The new year to set.
    public void setYear(int year) {
        this.year = year;
    }

    // Determines if the current date is larger (later) than another date.
    // Parameters:
    //   - other: The Date object to compare against.
    // Return value:
    //   - true if the current date is larger than the provided date; false otherwise.
    public boolean largerThan(Date other) {
        // Compare years
        if (year > other.getYear()) {
            return true;
        } else if (year < other.getYear()) {
            return false;
        } else {
            // If years are equal, compare months
            if (month > other.getMonth()) {
                return true;
            } else if (month < other.getMonth()) {
                return false;
            } else {
                // If years and months are equal, compare days
                return day > other.getDay();
            }
        }
    }

    // Returns a formatted string representation of the date.
    // Format: "{Month} {Day} {Year}" (e.g., "January 1 2022").
    // Return value:
    //   - A string representing the date.
    public String toString() {
        return (MONTHS[month - 1] + " " + day + " " + year);
    }
}
