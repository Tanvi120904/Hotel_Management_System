package com.example.hotel;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

import static javafx.fxml.FXMLLoader.load;

public class MainPageController {
    private Stage stage;
    private Scene scene;
    private Parent root;

    public void switchToAdminLogIn(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("LoginPageAdmin.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToManagerLogin(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("LoginPageManager.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToFirstPage(ActionEvent event) throws IOException {
        root = load(Objects.requireNonNull(getClass().getResource("FirstPage.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
