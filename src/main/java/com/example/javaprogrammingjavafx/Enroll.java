package com.example.javaprogrammingjavafx;

public class Enroll {

    private int CourseId;
    private int StudentId;

    public Enroll(int courseId, int studentId) {
        CourseId = courseId;
        StudentId = studentId;
    }

    public int getCourseId() {
        return CourseId;
    }

    public void setCourseId(int courseId) {
        CourseId = courseId;
    }

    public int getStudentId() {
        return StudentId;
    }

    public void setStudentId(int studentId) {
        StudentId = studentId;
    }
}
