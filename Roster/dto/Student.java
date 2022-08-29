package Roster.dto;

import java.util.Objects;

public class Student {

    //these methods will be invoked in the VIEW class

    private String firstName;
    private String lastName;
    private String studentId;
    private String cohort; // programming language [space] month [space] year

    public Student(String studentId) {
        this.studentId = studentId;
    }

    /*
    Both messages below are auto-generated for the sake of comparing objects of class STUDENT.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student)) return false;
        Student student = (Student) o;
        return Objects.equals(getFirstName(), student.getFirstName())
                &&
                Objects.equals(getLastName(), student.getLastName())
                &&
                Objects.equals(getStudentId(), student.getStudentId())
                &&
                Objects.equals(getCohort(), student.getCohort());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFirstName(), getLastName(), getStudentId(), getCohort());
    }

    /*
    It is also recommended to add an overridden version of the Java in-built toString() method
    to our Student class to help with test failure messages.

    This toString() method is added mostly for convenience.
    Often in error messages, JUnit will print out information about the object that failed a test.
    It helps if that information is readable to us, but the default toString() method
    only really serializes the object's class name and hashcode - both interesting information,
    but not often particularly useable.

    Overriding this method can allow us to print out all of the object's property values instead,
    which can allow for much faster insight into issues when reading test logs!
     */

    @Override
    public String toString() {
        return "Student{" + "firstName=" + firstName + ", lastName=" + lastName + ", studentId=" + studentId + ", cohort=" + cohort + '}';
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getCohort() {
        return cohort;
    }

    public void setCohort(String cohort) {
        this.cohort = cohort;
    }
}

