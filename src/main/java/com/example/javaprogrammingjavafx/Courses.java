package com.example.javaprogrammingjavafx;

public class Courses {
 private  int Id;
 private String Name;


    public Courses(int id, String name) {
        Id = id;
        Name = name;
    }

    @Override
    public String toString() {
        return getName(); // Replace with the appropriate field that represents the name of the course
    }
    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}

