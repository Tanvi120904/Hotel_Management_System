package com.example.hotel;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ManagerCheckOutController {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private TextField customerNameField;

    @FXML
    private TextField contactNoField;

    @FXML
    private TextField roomIdField;

    @FXML
    private TextField customerIdField;


    @FXML
    private Hyperlink customerReceiptLink;

    @FXML
    private Hyperlink totalExpensesLink;

    @FXML
    private Button loadCustomerInfoButton;

    @FXML
    private Button checkOutButton;

    @FXML
    private Button cancelButton;

    @FXML
    private void initialize() {
        // Optional: Set default values or clear fields on initialization if necessary
    }

    @FXML
    private void loadCustomerInfo() {
        String roomId = roomIdField.getText();
        if (roomId.isEmpty()) {
            showAlert("Input Error", "Please enter a Room ID to load customer information.");
            return;
        }

        String query = "SELECT customer_name, contact_no, customer_id FROM customer_info WHERE room_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, roomId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                customerNameField.setText(rs.getString("customer_name"));
                contactNoField.setText(rs.getString("contact_no"));
            } else {
                showAlert("No Customer Found", "No customer data found for the given Room ID.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Error", "An error occurred while loading customer information.");
        }
    }

    @FXML
    private void handleCheckOut() {
        // Logic to handle checkout
        showAlert("Checkout Successful", "The customer has been successfully checked out.");

        // Optionally, clear the fields after checkout
        clearFields();
    }

    public void switchToManagerHome(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("ManagerHome.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToMainPg(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("MainPage.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToManagerCustHistory(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("ManagerCustomerHistory.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }



    public void switchToManagerCheckOut(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("ManagerCheckOut.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToManagerCheckIn(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("ManagerCheckIn.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void handleCustomerReceiptLink() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ManagerCustomerReceipt.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Customer Receipt");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Error", "Could not load the Customer Receipt page.");
        }
    }

    @FXML
    private void handleTotalExpensesLink() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ManagerTotalExpenses.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Total Expenses");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Error", "Could not load the Total Expenses page.");
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void clearFields() {
        customerNameField.clear();
        contactNoField.clear();
        roomIdField.clear();
    }
}
