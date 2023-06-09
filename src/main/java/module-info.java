module com.example.javaprogrammingjavafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mssql.jdbc;


    opens com.example.javaprogrammingjavafx to javafx.fxml;
    exports com.example.javaprogrammingjavafx;

}