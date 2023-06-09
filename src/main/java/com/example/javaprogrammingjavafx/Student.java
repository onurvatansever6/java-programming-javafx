package com.example.javaprogrammingjavafx;

public class Student {
    private String Name;
    private  String Surname;


    public Student( String name, String surname) {


        Name = name;
        Surname = surname;
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


}
