package com.example.hotel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDatabase {
    private DatabaseConnection databaseConnection;

    public UserDatabase() {
        databaseConnection = new DatabaseConnection();
    }

    // Method to sign up a new user
    public boolean signupUser(String mobileNo, String email, String username, String password) {
        // First, check if the email is already registered
        if (isEmailRegistered(email)) {
            return false; // Return false if the email is already registered
        }

        String insertUser = "INSERT INTO Users (mobile_no, email, username, password) VALUES (?, ?, ?, ?)";
        try (Connection connectDB = databaseConnection.getConnection();
             PreparedStatement preparedStatement = connectDB.prepareStatement(insertUser)) {

            preparedStatement.setString(1, mobileNo);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, username);
            preparedStatement.setString(4, password);

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0; // Return true if signup was successful

        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Return false if there was an error
        }
    }

    // Method to log in a user
    public boolean loginUser(String username, String password) {
        String verifyLogin = "SELECT * FROM Users WHERE username = ? AND password = ?";
        try (Connection connectDB = databaseConnection.getConnection();
             PreparedStatement preparedStatement = connectDB.prepareStatement(verifyLogin)) {

            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();

            return resultSet.next(); // Return true if login is successful

        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Return false if there was an error
        }
    }

    // Method to check if an email is already registered
    public boolean isEmailRegistered(String email) {
        String checkEmailQuery = "SELECT * FROM Users WHERE email = ?";
        try (Connection connectDB = databaseConnection.getConnection();
             PreparedStatement preparedStatement = connectDB.prepareStatement(checkEmailQuery)) {

            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();

            return resultSet.next(); // Return true if the email is already in use

        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Return false if there was an error
        }
    }
}
