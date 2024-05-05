/*
Class: Teacher
Author: Vedant Joshi
Date: Jan 9 2023
School: AY Jackson SS
Purpose: The Teacher class marks and assigns tasks for the students.
 */

public class Teacher extends Person{
    private String subjectType;

    // Constructor
    // Initializes a Teacher object with specified attributes, including subject type.
    // Parameters:
    //   - name: The name of the teacher.
    //   - age: The age of the teacher.
    //   - subjectType: The subject type taught by the teacher.
    public Teacher(String name, int age, int id, String password, String subjectType) {
        super(name, age, id, password);
        this.subjectType = subjectType;
    }

    // Getter for subjectType field.
    // Return value:
    //   - The subject type taught by the teacher.
    public String getSubjectType() {
        return subjectType;
    }

    // Setter for subjectType field.
    // Parameters:
    //   - subjectType: The new subject type to set.
    public void setSubjectType(String subjectType) {
        this.subjectType = subjectType;
    }
}
