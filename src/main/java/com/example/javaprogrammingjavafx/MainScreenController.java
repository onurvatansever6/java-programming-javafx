package com.example.javaprogrammingjavafx;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.FloatStringConverter;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MainScreenController {

    public TextField surnameField;
    public TextField nameField;
    public Button studentButton;
    public ComboBox studentComboBox;
    public ComboBox courseComboBox;

    public Button enrollButton;
    public ComboBox courseComboBox2;
    public Button viewEnroll;
    public TableView enrollTable;
    public TableColumn<EnrollView,String> studentSurname;
    public TableColumn<EnrollView,String> courseName;
    public TableColumn<EnrollView,String> studentName;
    public TableColumn<EnrollView, Float> midterm;
    public TableColumn<EnrollView,Float> Final;
    public TableColumn<EnrollView,String> grade;
    private Connection connection;
    public String url = "jdbc:sqlserver://DESKTOP-MSF109I\\KRYJERATH;databaseName=JavaProject;encrypt=true;trustServerCertificate=true;";
    public  String username = "javaProject";
    public  String password = "123java.";
    @FXML
    public  TableView<Courses> courseTable;

    @FXML
    public TableColumn<Courses, Integer> Id;

    @FXML
    public TableColumn<Courses, String> Name;

    public MainScreenController() {
        try {
            // Load the JDBC driver
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    @FXML
    private void handleCoursesTabSelection() {
        // Fetch course data from the database and populate the TableView
        ObservableList();
    }

    public void  ObservableList() {

        try {
            connection = DriverManager.getConnection(url, username, password);
            System.out.println("Connected to the database.");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Courses");
            ObservableList<Courses> courseData = FXCollections.observableArrayList();
            while (resultSet.next()) {
                int Id = resultSet.getInt("Id");
                String Name = resultSet.getString("Name");

                Courses course = new Courses(Id , Name);
                courseData.add(course);
            }
            courseTable.setItems(courseData);


        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    @FXML
    private void handleSubmit(ActionEvent event) {
        String name = nameField.getText();
        String surname = surnameField.getText();

        // Create a new Student object with the entered values
        Student student = new Student(name, surname);

        // Save the student to the database
        // Assuming you have a method to save the student in your database connection class
        saveStudent(student);

        // Clear the text fields after submission
        nameField.clear();
        surnameField.clear();
    }

    @FXML
    private void submitEnroll(ActionEvent event) {

        try {
            connection = DriverManager.getConnection(url, username, password);
            System.out.println("Connected to the database.");
            Courses selectedCourse = (Courses) courseComboBox.getSelectionModel().getSelectedItem();
            StudentView selectedStudent = (StudentView) studentComboBox.getSelectionModel().getSelectedItem();
            String sql = "INSERT INTO Enrolls (StudentId, CourseId) VALUES (?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);



            // Create a new enrollment record with the selected course and student
            Enroll enrollment = new Enroll(selectedStudent.getId(), selectedCourse.getId());

            statement.setInt(2, enrollment.getStudentId());
            statement.setInt(1, enrollment.getCourseId());

            // Execute the statement
            statement.executeUpdate();

            // Close the statement
            statement.close();


        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void listEnrolls(ActionEvent event) {

        try {
            connection = DriverManager.getConnection(url, username, password);
            System.out.println("Connected to the database.");
            Courses selectedCourse = (Courses) courseComboBox2.getSelectionModel().getSelectedItem();
            String sql ="\n" +
                    "\n" +
                    "SELECT E.Id,ESC.courseName,ES.Name,ES.Surname, E.Midterm,E.Final,E.Grade from Enrolls as E\n" +
                    "CROSS APPLY( select S.Name,S.Surname from Students as S\n" +
                    " where S.Id = E.StudentId) ES\n" +
                    " CROSS APPLY (\n" +
                    "\tselect C.Name as courseName from Courses as C\n" +
                    "\twhere C.Id = E.CourseId and C.Id = ?) ESC\n" +
                    " ";
             int getcourseId = selectedCourse.getId();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, getcourseId);
            ResultSet resultSet = statement.executeQuery();

            ObservableList<EnrollView> enrollViews = FXCollections.observableArrayList();
            while (resultSet.next()) {
                int enrollId = resultSet.getInt("Id");
                String courseName = resultSet.getString("courseName");
                String studentName = resultSet.getString("Name");
                String studentSurname = resultSet.getString("Surname");
                int midterm = resultSet.getInt("Midterm");
                int finalExam = resultSet.getInt("Final");
                String grade = resultSet.getString("Grade");

                // Create an EnrollView object for each row of data
                EnrollView enrollView = new EnrollView(enrollId,courseName, studentName, studentSurname, midterm, finalExam, grade);
                enrollViews.add(enrollView);

            }
            enrollTable.setItems(enrollViews);
            // Close the statement
            statement.close();


        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void saveStudent(Student student) {
        try {
            connection = DriverManager.getConnection(url, username, password);
            System.out.println("Connected to the database.");

            // Prepare the SQL statement
            String query = "INSERT INTO Students (Name, Surname) VALUES (?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);

            // Set the values for the parameters
            statement.setString(1, student.getName());
            statement.setString(2, student.getSurname());

            // Execute the statement
            statement.executeUpdate();

            // Close the statement
            statement.close();
        } catch (SQLException e) {
            // Handle any errors that occur during the database operation
            e.printStackTrace();
        }
    }

    public  List<StudentView> getAllStudents() {
        List<StudentView> students = new ArrayList<>();

        try {
            connection = DriverManager.getConnection(url, username, password);
            System.out.println("Connected to the database.");
            // Prepare the SQL statement
            String sql = "SELECT * FROM Students";
            PreparedStatement statement = connection.prepareStatement(sql);

            // Execute the query
            ResultSet resultSet = statement.executeQuery();

            // Process the results
            while (resultSet.next()) {
                int id = resultSet.getInt("Id");
                String name = resultSet.getString("Name");
                String surname = resultSet.getString("Surname");

                // Create a new Student object and add it to the list
                StudentView student = new StudentView(id, name, surname);
                students.add(student);
            }

            // Close the result set and statement
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            // Handle any errors that occur during the database operation
            e.printStackTrace();
        }

        return students;
    }

    public  List<Courses> getAllCourses() {
        List<Courses> courses = new ArrayList<>();

        try {
            connection = DriverManager.getConnection(url, username, password);
            System.out.println("Connected to the database.");
            // Prepare the SQL statement
            String sql = "SELECT * FROM Courses";
            PreparedStatement statement = connection.prepareStatement(sql);

            // Execute the query
            ResultSet resultSet = statement.executeQuery();

            // Process the results
            while (resultSet.next()) {
                int id = resultSet.getInt("Id");
                String name = resultSet.getString("Name");


                // Create a new Student object and add it to the list
               Courses course = new Courses(id, name);
                courses.add(course);
            }

            // Close the result set and statement
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            // Handle any errors that occur during the database operation
            e.printStackTrace();
        }

        return courses;
    }
    public void updateEnroll(EnrollView enrollView) {
        String sql = "UPDATE Enrolls SET Midterm = ?, Final= ?,Grade= ? WHERE Id = ?";
        try (

                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setFloat(1, enrollView.getMidterm());
            statement.setFloat(2,enrollView.getFinal());
            statement.setString(3,enrollView.getGrade());
            statement.setInt(4, enrollView.getEnrollId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }}


    @FXML
    public void initialize() {

        studentName.setCellValueFactory(data -> new SimpleObjectProperty<>(data.getValue().getStudentName()));
        studentSurname.setCellValueFactory(data -> new SimpleObjectProperty<>(data.getValue().getStudentSurname()));
        courseName.setCellValueFactory(data -> new SimpleObjectProperty<>(data.getValue().getCourseName()));
        midterm.setCellValueFactory(data -> new SimpleObjectProperty<>(data.getValue().getMidterm()));
        midterm.setCellFactory(TextFieldTableCell.<EnrollView, Float>forTableColumn(new FloatStringConverter()));
        Final.setCellFactory(TextFieldTableCell.<EnrollView, Float>forTableColumn(new FloatStringConverter()));
        grade.setCellFactory(TextFieldTableCell.<EnrollView>forTableColumn());
        Final.setCellValueFactory(data -> new SimpleObjectProperty<>(data.getValue().getFinal()));
        grade.setCellValueFactory(data -> new SimpleObjectProperty<>(data.getValue().getGrade()));
        Id.setCellValueFactory(data -> new SimpleObjectProperty<>(data.getValue().getId()));
        Name.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getName()));
        midterm.setOnEditCommit(event -> {
            Float newMidterm = event.getNewValue();
            EnrollView enrollView = event.getRowValue();
            enrollView.setMidterm(newMidterm);
            updateEnroll(enrollView);
        });
        Final.setOnEditCommit(event -> {
            Float newFinal = event.getNewValue();
            EnrollView enrollView = event.getRowValue();
            enrollView.setFinal(newFinal);
            updateEnroll(enrollView);
        });
        grade.setOnEditCommit(event -> {
            String newGrade = event.getNewValue();
            EnrollView enrollView = event.getRowValue();
            enrollView.setGrade(newGrade);
            updateEnroll(enrollView);
        });
        // Populate the courseComboBox with names of courses
        List<Courses> courses = getAllCourses();
        courseComboBox.setItems(FXCollections.observableArrayList(courses));

        // Populate the studentComboBox with names+surnames of students
        List<StudentView> students = getAllStudents();
        studentComboBox.setItems(FXCollections.observableArrayList(students));
        courseComboBox2.setItems(FXCollections.observableArrayList(courses));
    }

}




