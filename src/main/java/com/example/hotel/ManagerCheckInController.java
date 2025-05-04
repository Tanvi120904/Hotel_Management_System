package com.example.hotel;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class ManagerCheckInController {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private TextField customerIdField;
    @FXML
    private TextField roomNoField;
    @FXML
    private TextField contactNoField;
    @FXML
    private TextField customerNameField;
    @FXML
    private TextField emailIdField;
    @FXML
    private TextField roomTypeField;
    @FXML
    private TextField addressField;
    @FXML
    private TextField pricePerDayField;
    @FXML
    private DatePicker checkInDateField;
    @FXML
    private DatePicker checkOutDateField;

    // Switch between pages
    public void switchToManagerHome(ActionEvent event) throws IOException {
        loadScene(event, "ManagerHome.fxml");
    }

    private void loadScene(ActionEvent event, String fxmlFile) throws IOException {
        root = FXMLLoader.load(getClass().getResource(fxmlFile));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToMainPg(ActionEvent event) throws IOException {
        loadScene(event, "MainPage.fxml");
    }

    public void switchToManagerCustHistory(ActionEvent event) throws IOException {
        loadScene(event, "ManagerCustomerHistory.fxml");
    }

    public void switchToManagerCustExp(ActionEvent event) throws IOException {
        loadScene(event, "ManagerCustomerReceipt.fxml");
    }

    public void switchToManagerCheckOut(ActionEvent event) throws IOException {
        loadScene(event, "ManagerCheckOut.fxml");
    }

    public void switchToManagerCheckIn(ActionEvent event) throws IOException {
        loadScene(event, "ManagerCheckIn.fxml");
    }

    @FXML
    private void handleCheckIn(ActionEvent event) {
        try {
            // Retrieve input data
            String customerId = customerIdField.getText();
            String roomNo = roomNoField.getText();
            String contactNo = contactNoField.getText();
            String customerName = customerNameField.getText();
            String emailId = emailIdField.getText();
            String roomType = roomTypeField.getText();
            String address = addressField.getText();
            String pricePerDay = pricePerDayField.getText();

            // Default to current date if check-in date is not provided
            LocalDate checkInDate = (checkInDateField.getValue() != null) ? checkInDateField.getValue() : LocalDate.now();
            LocalDate checkOutDate = checkOutDateField.getValue();

            // Convert LocalDate to java.sql.Date
            Date sqlCheckInDate = convertToSqlDate(checkInDate);
            Date sqlCheckOutDate = convertToSqlDate(checkOutDate);

            // Validate input fields
            if (validateEntries(customerId, roomNo, contactNo, customerName, emailId, pricePerDay, sqlCheckInDate, sqlCheckOutDate)) {
                // Call DAO method to save check-in details to the `customer_info` table
                CheckInDAO checkInDAO = new CheckInDAO();
                checkInDAO.checkInCustomer(customerId, roomNo, contactNo, customerName, emailId, roomType, address, pricePerDay, sqlCheckInDate, sqlCheckOutDate);

                // Show success message
                Platform.runLater(() -> showAlert(AlertType.INFORMATION, "Check-In Successful", "Customer information has been saved successfully!"));

                // Clear fields after successful entry
                clearFields();
            } else {
                // Show alert for invalid entries
                Platform.runLater(() -> showAlert(AlertType.ERROR, "Invalid Input", "Please fill all the fields with valid data."));
            }

        } catch (Exception e) {
            // Display error message
            Platform.runLater(() -> showAlert(AlertType.ERROR, "Error", "Invalid data. Please check your input."));
        }
    }


    private Date convertToSqlDate(LocalDate localDate) {
        return (localDate == null) ? null : Date.valueOf(localDate);
    }

    @FXML
    private void handleCancel(ActionEvent event) {
        clearFields();
    }

    // Validate entries
    private boolean validateEntries(String customerId, String roomNo, String contactNo, String customerName, String emailId, String pricePerDay, Date checkInDate, Date checkOutDate) {
        if (customerId.isEmpty() || roomNo.isEmpty() || contactNo.isEmpty() || customerName.isEmpty() || emailId.isEmpty() || pricePerDay.isEmpty() || checkInDate == null || checkOutDate == null) {
            return false;
        }
        return true;
    }

    // Show alert
    private void showAlert(AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    // Clear all fields
    private void clearFields() {
        customerIdField.clear();
        roomNoField.clear();
        contactNoField.clear();
        customerNameField.clear();
        emailIdField.clear();
        roomTypeField.clear();
        addressField.clear();
        pricePerDayField.clear();
        checkInDateField.setValue(null);
        checkOutDateField.setValue(null);
    }
}
