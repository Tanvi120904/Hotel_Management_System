package com.example.hotel;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class ReceiptController {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private TextField customerNameField;
    @FXML
    private TextField contactNoField;
    @FXML
    private TextField hotelNameField;
    @FXML
    private TextField roomIdField;
    @FXML
    private TextField amountPaidField;

    @FXML
    private Button downloadButton;

    // Database connection parameters
    private final String DB_URL = "jdbc:mysql://localhost:3306/hotelreservationsystem"; // replace with your database URL
    private final String USER = "root"; // replace with your username
    private final String PASS = "oracle"; // replace with your password

    // Method to fetch data
    @FXML
    private void fetchData() {
        String roomId = roomIdField.getText();
        String customerName = customerNameField.getText();

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
            String sql = "SELECT * FROM customer_info WHERE room_id = ? OR customer_name = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, roomId);
            pstmt.setString(2, customerName);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                customerNameField.setText(rs.getString("customer_name"));
                contactNoField.setText(rs.getString("contact_no"));
                hotelNameField.setText(rs.getString("hotel_name"));
                roomIdField.setText(rs.getString("room_id"));
                amountPaidField.setText(rs.getString("amount_paid"));
            } else {
                System.out.println("No data found.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Method to download the receipt
    @FXML
    private void downloadReceipt() {
        String receiptContent = "Customer Name: " + customerNameField.getText() + "\n"
                + "Contact No: " + contactNoField.getText() + "\n"
                + "Hotel Name: " + hotelNameField.getText() + "\n"
                + "Room ID: " + roomIdField.getText() + "\n"
                + "Amount Paid: " + amountPaidField.getText() + "\n";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("receipt.txt"))) {
            writer.write(receiptContent);
            System.out.println("Receipt downloaded successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Add this method to your existing back button functionality
    @FXML
    public void switchToManagerCheckOut(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("ManagerCheckOut.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
