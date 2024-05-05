/*
Class: Teacher
Author: Vedant Joshi
Date: Jan 9 2023
School: AY Jackson SS
Purpose: The Teacher class marks and assigns tasks for the students.
 */

public class Teacher extends Person{
    public static final int TEACHER_CODE = 999;
    private String subjectType;

    public Teacher(String name, int age, int id, String password, String subjectType) {
        super(name, age, id, password);
        this.subjectType = subjectType;
    }

    public String getSubjectType() {
        return subjectType;
    }
}
