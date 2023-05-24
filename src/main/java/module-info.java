module com.example.javaprogrammingjavafx {
    requires javafx.controls;
    requires javafx.fxml;
            
                            
    opens com.example.javaprogrammingjavafx to javafx.fxml;
    exports com.example.javaprogrammingjavafx;
}