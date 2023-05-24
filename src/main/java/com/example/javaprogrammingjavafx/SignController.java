package com.example.javaprogrammingjavafx;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class SignInController {

    public HBox signInButtonsHBOX;
    @FXML
    private HBox signInHBOX;
    @FXML
    private HBox signUpHBOX;

    @FXML
    protected void signInButtonClick() {
        signInHBOX.setVisible(true);
    }
    @FXML
    protected void signUpButtonClick() {
        signUpHBOX.setVisible(true);
    }
}