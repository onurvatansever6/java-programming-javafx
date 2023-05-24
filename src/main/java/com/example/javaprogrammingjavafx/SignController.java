package com.example.javaprogrammingjavafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class SignController {
    private Stage stage;
    public HBox signInHBOX;
    public HBox signUpHBOX;
    public HBox signButtonsHBOX;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    protected void signInButtonClick() {
        signInHBOX.setVisible(true);
        signButtonsHBOX.setVisible(false);
    }

    @FXML
    protected void signUpButtonClick() {
        signUpHBOX.setVisible(true);
        signButtonsHBOX.setVisible(false);
    }

    @FXML
    protected void switchToMainScreen(ActionEvent event) throws IOException {
        Parent mainRoot = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("main-view.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene mainScene = new Scene(mainRoot);
        stage.setScene(mainScene);
        stage.show();
    }


}
