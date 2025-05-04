package com.example.hotel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Date; // Keep this import for SQL Date
import javafx.scene.control.Alert;

public class CheckInDAO {
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/hotelreservationsystem";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "oracle";

    public void checkInCustomer(String customerId, String roomNo, String contactNo, String customerName, String emailId, String roomType, String address, String pricePerDay, Date checkInDate, Date checkOutDate) {
        String query = "INSERT INTO customer_info (customer_id, room_id, contact_no, customer_name, email_id, room_type, address, price_per_day, check_in_date, check_out_date) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, customerId);
            preparedStatement.setString(2, roomNo);
            preparedStatement.setString(3, contactNo);
            preparedStatement.setString(4, customerName);
            preparedStatement.setString(5, emailId);
            preparedStatement.setString(6, roomType);
            preparedStatement.setString(7, address);
            preparedStatement.setString(8, pricePerDay);
            preparedStatement.setDate(9, checkInDate);
            preparedStatement.setDate(10, checkOutDate);

            preparedStatement.executeUpdate();
            showAlert(Alert.AlertType.INFORMATION, "Success", "Customer checked in successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Error", "Failed to check in customer: " + e.getMessage());
        }
    }

    public static void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
