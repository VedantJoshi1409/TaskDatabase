/*
Class: Person
Author: Vedant Joshi
Date: Jan 9 2023
School: AY Jackson SS
Purpose: Represents a Person object. Parent class to Teacher and Student.
 */

import java.util.ArrayList;

public abstract class Person {
   private String name;
   private int age;
   private int id;
   private String password;
   private ArrayList<Classroom> classrooms = new ArrayList<Classroom>();

    public Person(String name, int age, int id, String password) {
        this.name = name;
        this.age = age;
        this.id = id;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public String getPass() {
        return password;
    }

    public int getId() {
        return id;
    }

    public ArrayList<Classroom> getClassrooms() {
        return classrooms;
    }
}
