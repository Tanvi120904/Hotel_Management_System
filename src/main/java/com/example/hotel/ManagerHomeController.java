package com.example.hotel;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import static javafx.fxml.FXMLLoader.load;

public class ManagerHomeController {
    private Stage stage;
    private Scene scene;
    private Parent root;

    public void switchToManagerHome(ActionEvent event) throws IOException {
        root = load(getClass().getResource("ManagerHome.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToMainPg(ActionEvent event) throws IOException {
        root = load(getClass().getResource("MainPage.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }



    public void switchToManagerCustHistory(ActionEvent event) throws IOException {
        root = load(getClass().getResource("ManagerCustomerHistory.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToManagerCustExp(ActionEvent event) throws IOException {
        root = load(getClass().getResource("ManagerCustomerReceipt.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToManagerCheckOut(ActionEvent event) throws IOException {
        root = load(getClass().getResource("ManagerCheckOut.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToManagerCheckIn(ActionEvent event) throws IOException {
        root = load(getClass().getResource("ManagerCheckIn.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


}