/*
Class: Person
Author: Vedant Joshi
Date: Jan 9 2023
School: AY Jackson SS
Purpose: Represents a Person object. Parent class to Teacher and Student.
 */

public abstract class Person {
   private String name;
   private int age;
   private int id;
   private String password;

    // Constructor
    // Initializes a Person object with specified attributes.
    // Parameters:
    //   - name: The name of the person.
    //   - age: The age of the person.
    public Person(String name, int age, int id, String password) {
        this.name = name;
        this.age = age;
        this.id = id;
        this.password = password;
    }

    // Getter for name field.
    // Return value:
    //   - The name of the person.
    public String getName() {
        return name;
    }

    // Setter for name field.
    // Parameters:
    //   - name: The new name to set.
    public void setName(String name) {
        this.name = name;
    }

    // Getter for age field.
    // Return value:
    //   - The age of the person.
    public int getAge() {
        return age;
    }

    public String getPass() {
        return password;
    }

    // Setter for age field.
    // Parameters:
    //   - age: The new age to set.
    public void setAge(int age) {
        this.age = age;
    }

    public int getId() {
        return id;
    }
}
