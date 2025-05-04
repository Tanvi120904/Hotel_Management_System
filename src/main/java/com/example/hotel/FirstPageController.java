package com.example.hotel;

import java.io.IOException;
import java.util.Objects;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import static javafx.fxml.FXMLLoader.load;

public class FirstPageController {
    private Stage stage;
    private Scene scene;
    private Parent root;




    public void switchToCreateAccount(ActionEvent event) throws IOException {
        root = load(Objects.requireNonNull(getClass().getResource("CreateAccPage.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToLogin(ActionEvent event) throws IOException {
        root = load(Objects.requireNonNull(getClass().getResource("MainPage.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}