package com.example.hotel;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;
import java.util.regex.Pattern;

import static javafx.fxml.FXMLLoader.load;

public class CreateAccController {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private TextField mobileNoField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField usernameField;

    @FXML
    private TextField passwordField;


    private UserDatabase userDatabase;

    public CreateAccController() {
        userDatabase = new UserDatabase();
    }

    // Method to handle user signup
    public void signupUser(ActionEvent event) {
        String mobileNo = mobileNoField.getText().trim();
        String email = emailField.getText().trim();
        String username = usernameField.getText().trim();
        String password = passwordField.getText().trim();

        // Validate input fields
        if (mobileNo.isEmpty() || email.isEmpty() || username.isEmpty() || password.isEmpty()) {
            showAlert("Input Error", "All fields must be filled out.");
            return;
        }

        // Email validation regex
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        if (!Pattern.matches(emailRegex, email)) {
            showAlert("Input Error", "Please enter a valid email address.");
            return;
        }

        // Check if the email already exists
        if (userDatabase.isEmailRegistered(email)) {
            showAlert("Signup Error", "The email is already registered. Please use another email.");
            return;
        }

        // Proceed with signup
        boolean isSuccess = userDatabase.signupUser(mobileNo, email, username, password);
        if (isSuccess) {
            showAlert("Signup Successful", "Account created successfully!");
        } else {
            showAlert("Signup Failed", "Error creating account.");
        }
    }

    // Method to show alert dialog
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(title);
        alert.setContentText(message);
        alert.show();
    }

    // Method to switch to first page
    public void switchToFirstPage(ActionEvent event) throws IOException {
        root = load(Objects.requireNonNull(getClass().getResource("FirstPage.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
