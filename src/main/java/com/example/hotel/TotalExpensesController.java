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
import java.sql.ResultSet;

import static com.example.hotel.CheckInDAO.showAlert;

public class TotalExpensesController {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private TextField customerIdField; // TextField to enter Customer ID
    @FXML
    private TextField roomChargesField;
    @FXML
    private TextField foodChargesField;
    @FXML
    private TextField appliancesChargesField;
    @FXML
    private TextField otherTaxesField;
    @FXML
    private TextField totalAmountField; // TextField to display total amount

    // Database URL, username, and password
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/hotelreservationsystem"; // Change to your database
    private static final String USERNAME = "root"; // Change to your database username
    private static final String PASSWORD = "oracle"; // Change to your database password

    // Method to fetch data
    public void fetchData(String customerId) {
        String query = "SELECT room_charges, food_charges, appliances_charges, other_taxes FROM customer_info WHERE customer_id = ?";

        try (Connection connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, customerId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                // Populate TextFields with fetched data
                roomChargesField.setText(String.valueOf(resultSet.getDouble("room_charges")));
                foodChargesField.setText(String.valueOf(resultSet.getDouble("food_charges")));
                appliancesChargesField.setText(String.valueOf(resultSet.getDouble("appliances_charges")));
                otherTaxesField.setText(String.valueOf(resultSet.getDouble("other_taxes")));

                // Calculate total amount
                double totalAmount = resultSet.getDouble("room_charges") +
                        resultSet.getDouble("food_charges") +
                        resultSet.getDouble("appliances_charges") +
                        resultSet.getDouble("other_taxes");
                totalAmountField.setText(String.valueOf(totalAmount));
            } else {
                // Handle case where no data is found
                roomChargesField.setText("0");
                foodChargesField.setText("0");
                appliancesChargesField.setText("0");
                otherTaxesField.setText("0");
                totalAmountField.setText("0");
            }
        } catch (Exception e) {
            e.printStackTrace(); // Handle exceptions (e.g., show an alert)
        }
    }

    // Call this method to fetch data when needed (e.g., on button click or when loading the scene)
    public void loadCustomerData() {
        String customerId = customerIdField.getText(); // Get customer ID from input field
        if (!customerId.isEmpty()) {
            fetchData(customerId); // Fetch and display data
        }
    }

    public void switchToManagerCheckOut(ActionEvent event) throws IOException {
        loadScene(event, "ManagerCheckOut.fxml");
    }

    private void loadScene(ActionEvent event, String fxmlFile) {
        try {
            root = FXMLLoader.load(getClass().getResource(fxmlFile));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            showAlert(Alert.AlertType.ERROR, "Error", "Could not load the next page: " + e.getMessage());
        }
    }
}
