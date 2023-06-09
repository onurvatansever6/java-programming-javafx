package com.example.javaprogrammingjavafx;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class SqlConnection {
    private Connection connection;
    public String url = "jdbc:sqlserver://DESKTOP-MSF109I\\KRYJERATH;databaseName=JavaProject;encrypt=true;trustServerCertificate=true;";
    public  String username = "javaProject";
    public  String password = "123java.";
    public SqlConnection() {
        try {
            // Load the JDBC driver
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void connect() {


        try {
            connection = DriverManager.getConnection(url, username, password);
            System.out.println("Connected to the database.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void getRecords(){
        try {
            connection = DriverManager.getConnection(url, username, password);
            System.out.println("Connected to the database.");
            String query ="select * from Courses";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while(resultSet.next()){
                int id=resultSet.getInt("Id");
                String name = resultSet.getString("Name");
                //String Surname = resultSet.getString("Surname");

                System.out.println("Id:" +id+"Name:"+name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
