package com.example.hotel;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AdminTotalEarnController {
    private Stage stage;
    private Scene scene;
    private Parent root;

    // Input fields for entering data
    @FXML
    private TextField totalBookingsField;  // For entering total bookings
    @FXML
    private TextField totalExpensesField;  // For entering total expenses
    @FXML
    private TextField totalRevenueField;  // Display total revenue (calculated)

    // Database connection details
    private final String DB_URL = "jdbc:mysql://localhost:3306/hotelreservationsystem"; // Change to your DB URL
    private final String DB_USER = "root"; // Change to your DB username
    private final String DB_PASSWORD = "oracle"; // Change to your DB password

    // Method to calculate total revenue and display it
    public void calculateTotalRevenue(ActionEvent event) {
        try {
            // Get the values from the input fields
            double totalBookings = Double.parseDouble(totalBookingsField.getText());
            double totalExpenses = Double.parseDouble(totalExpensesField.getText());

            // Calculate total revenue
            double totalRevenue = totalBookings - totalExpenses;

            // Display total revenue in the revenue field
            totalRevenueField.setText(String.valueOf(totalRevenue));

        } catch (NumberFormatException e) {
            showAlert("Invalid Input", "Please enter valid numbers for total bookings and expenses.");
        }
    }

    // Method to handle saving the entered data to the database
    public void saveData(ActionEvent event) {
        try {
            // Get the values from the input fields
            double totalBookings = Double.parseDouble(totalBookingsField.getText());
            double totalExpenses = Double.parseDouble(totalExpensesField.getText());
            double totalRevenue = Double.parseDouble(totalRevenueField.getText());  // Use the already displayed value

            // Database connection and insertion
            String insertQuery = "INSERT INTO earnings (total_bookings, total_expenses, total_revenue) VALUES (?, ?, ?)";

            try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                 PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {

                // Set parameters for the SQL query
                preparedStatement.setDouble(1, totalBookings);
                preparedStatement.setDouble(2, totalExpenses);
                preparedStatement.setDouble(3, totalRevenue);  // Insert calculated total revenue

                // Execute the update (insert data)
                int rowsAffected = preparedStatement.executeUpdate();
                System.out.println(rowsAffected + " row(s) inserted.");

                // Clear the fields after saving
                totalBookingsField.clear();
                totalExpensesField.clear();
                totalRevenueField.clear();  // You can choose to keep this value displayed

            } catch (SQLException e) {
                e.printStackTrace();
            }

        } catch (NumberFormatException e) {
            showAlert("Invalid Input", "Please enter valid numbers for total bookings and expenses.");
        }
    }

    // Method to show alert dialogs
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    // Scene switching methods
    public void switchToAdminHome(ActionEvent event) throws IOException {
        loadScene(event, "AdminHome.fxml");
    }

    public void switchToAdminAddEmp(ActionEvent event) throws IOException {
        loadScene(event, "AdminAddEmp.fxml");
    }

    public void switchToAdminEmpInfo(ActionEvent event) throws IOException {
        loadScene(event, "AdminEmpInfo.fxml");
    }

    public void switchToAdminCustInfo(ActionEvent event) throws IOException {
        loadScene(event, "AdminCustInfo.fxml");
    }

    public void switchToAdminTotalEarn(ActionEvent event) throws IOException {
        loadScene(event, "AdminTotalEarn.fxml");
    }

    public void switchToMainPg(ActionEvent event) throws IOException {
        loadScene(event, "MainPage.fxml");
    }

    private void loadScene(ActionEvent event, String fxmlFile) throws IOException {
        root = FXMLLoader.load(getClass().getResource(fxmlFile));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
