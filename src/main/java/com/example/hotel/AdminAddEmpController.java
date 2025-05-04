package com.example.hotel;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker; // Import DatePicker
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.util.regex.Pattern;

public class AdminAddEmpController {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private TextField fullNameColumn;
    @FXML
    private TextField emailColumn;
    @FXML
    private TextField phoneNoColumn;
    @FXML
    private TextField addressColumn;
    @FXML
    private TextField empIdColumn;
    @FXML
    private TextField roleColumn;
    @FXML
    private DatePicker dateOfJoiningColumn; // Change from TextField to DatePicker
    @FXML
    private Button messageText;

    public AdminAddEmpController() {
    }

    @FXML
    public void addOrUpdateEmployee() {
        // Validate if all fields are filled
        if (!this.empIdColumn.getText().isEmpty() && !this.fullNameColumn.getText().isEmpty() &&
                !this.emailColumn.getText().isEmpty() && !this.phoneNoColumn.getText().isEmpty() &&
                !this.addressColumn.getText().isEmpty() && !this.roleColumn.getText().isEmpty() &&
                this.dateOfJoiningColumn.getValue() != null) { // Check if date is selected

            // Validate employee ID
            String empIdText = this.empIdColumn.getText();
            if (EmployeeDAO.employeeIdExists(empIdText)) {
                showAlert("Error", "The Employee ID \"" + empIdText + "\" is already in use. Please use a different ID.");
                return; // Exit the method if ID is a duplicate
            }

            // Validate email format
            String email = this.emailColumn.getText();
            if (!isValidEmail(email)) {
                showAlert("Error", "Please enter a valid email address!");
                return;
            }

            // Validate phone number format (e.g., digits only)
            String phoneNo = this.phoneNoColumn.getText();
            if (!isValidPhoneNumber(phoneNo)) {
                showAlert("Error", "Please enter a valid phone number!");
                return;
            }

            try {
                Integer empId = Integer.valueOf(empIdText);
                String fullName = this.fullNameColumn.getText();
                String address = this.addressColumn.getText();
                String role = this.roleColumn.getText();
                LocalDate dateOfJoining = dateOfJoiningColumn.getValue(); // Get LocalDate from DatePicker
                Employee employee = new Employee(empId, fullName, email, phoneNo, address, role, dateOfJoining);

                // Attempt to save the employee
                if (EmployeeDAO.saveEmployee(employee)) {
                    showAlert("Success", "Employee added successfully!");
                    clearFields(); // Clear fields after successful addition
                } else {
                    showAlert("Error", "Failed to add employee!");
                }

            } catch (NumberFormatException e) {
                showAlert("Error", "Please enter a valid employee ID!");
            } catch (Exception e) {
                showAlert("Error", "An unexpected error occurred: " + e.getMessage());
            }
        } else {
            showAlert("Error", "Please fill in all fields!");
        }
    }

    private boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$"; // Basic regex for email validation
        Pattern pattern = Pattern.compile(emailRegex);
        return pattern.matcher(email).matches();
    }

    private boolean isValidPhoneNumber(String phoneNo) {
        String phoneRegex = "^[0-9]+$"; // Regex for phone numbers (digits only)
        Pattern pattern = Pattern.compile(phoneRegex);
        return pattern.matcher(phoneNo).matches();
    }

    private void clearFields() {
        empIdColumn.clear();
        fullNameColumn.clear();
        emailColumn.clear();
        phoneNoColumn.clear();
        addressColumn.clear();
        roleColumn.clear();
        dateOfJoiningColumn.setValue(null); // Clear the date picker
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    // Scene switching methods
    public void switchToAdminHome(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("AdminHome.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToAdminAddEmp(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("AdminAddEmp.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToAdminEmpInfo(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("AdminEmpInfo.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToAdminCustInfo(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("AdminCustInfo.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToAdminTotalEarn(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("AdminTotalEarn.fxml"));
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
}
