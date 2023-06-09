package com.example.javaprogrammingjavafx;

public class StudentView {
    private int Id;
    private String Name;
    private  String Surname;


    public StudentView(int id, String name, String surname) {
        Id = id;
        Name = name;
        Surname = surname;
    }

    @Override
    public String toString() {
        return getName() + getSurname(); // Replace with the appropriate fields that represent the name and surname of the student
    }
    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getSurname() {
        return Surname;
    }

    public void setSurname(String surname) {
        Surname = surname;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }
}
