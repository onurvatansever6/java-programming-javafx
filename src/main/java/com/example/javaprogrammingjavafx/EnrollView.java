package com.example.javaprogrammingjavafx;

public class EnrollView {
    private int enrollId;
    private String courseName;
    private String studentName;
    private String studentSurname;
    private float midterm;
    private float Final;
    private String grade;

    public EnrollView(int enrollID ,String courseName, String studentName, String studentSurname, float midterm, float aFinal, String grade) {
        this.enrollId = enrollID;
        this.courseName = courseName;
        this.studentName = studentName;
        this.studentSurname = studentSurname;
        this.midterm = midterm;
        Final = aFinal;
        this.grade = grade;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentSurname() {
        return studentSurname;
    }

    public void setStudentSurname(String studentSurname) {
        this.studentSurname = studentSurname;
    }

    public float getMidterm() {
        return midterm;
    }

    public void setMidterm(float midterm) {
        this.midterm = midterm;
    }

    public float getFinal() {
        return Final;
    }

    public void setFinal(float aFinal) {
        Final = aFinal;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public int getEnrollId() {
        return enrollId;
    }

    public void setEnrollId(int enrollId) {
        this.enrollId = enrollId;
    }
}
