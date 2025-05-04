package com.example.hotel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;

public class Database {
    // Database connection details
    private static final String URL = "jdbc:mysql://localhost:3306/hotelreservationsystem"; // Change to your database URL
    private static final String USER = "root"; // Change to your database username
    private static final String PASSWORD = "oracle"; // Change to your database password

    // Method to establish a connection to the database
    public static Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.err.println("Connection failed: " + e.getMessage());
        }
        return conn;
    }

    // Method to get all employees from the employee table
    public static ResultSet getAllEmployees() {
        ResultSet resultSet = null;
        String query = "SELECT * FROM employee"; // Adjust table name if needed

        try {
            Connection conn = connect(); // Establish connection
            Statement stmt = conn.createStatement();
            resultSet = stmt.executeQuery(query); // Execute query to get all employees
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resultSet; // Return the ResultSet with employee data
    }

    // Method to check if an employee ID already exists
    public static boolean isEmployeeIdExists(String empId) {
        String query = "SELECT empId FROM employee WHERE empId = ?";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, empId); // Set employee ID to check
            ResultSet rs = pstmt.executeQuery();
            return rs.next(); // Returns true if employee ID exists
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Method to insert an employee into the employee table
    public static boolean insertEmployee(String empId, String fullName, String email, String phoneNo, String address) {
        if (isEmployeeIdExists(empId)) {
            System.err.println("Error: Employee ID already exists.");
            return false;
        }

        String query = "INSERT INTO employee (empId, full_name, email, phone_no, address) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, empId);        // Set employee ID
            pstmt.setString(2, fullName);     // Set full name
            pstmt.setString(3, email);        // Set email
            pstmt.setString(4, phoneNo);      // Set phone number
            pstmt.setString(5, address);      // Set address
            pstmt.executeUpdate();            // Execute the insert query
            return true;                      // Return true if insert is successful
        } catch (SQLException e) {
            e.printStackTrace();
            return false;                     // Return false if insert fails
        }
    }

    // Method to update employee details
    public static boolean updateEmployee(String empId, String fullName, String email, String phoneNo, String address) {
        String query = "UPDATE employee SET full_name = ?, email = ?, phone_no = ?, address = ? WHERE empId = ?";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, fullName);     // Set new full name
            pstmt.setString(2, email);        // Set new email
            pstmt.setString(3, phoneNo);      // Set new phone number
            pstmt.setString(4, address);      // Set new address
            pstmt.setString(5, empId);        // Set the employee ID for where clause
            pstmt.executeUpdate();            // Execute the update query
            return true;                      // Return true if update is successful
        } catch (SQLException e) {
            e.printStackTrace();
            return false;                     // Return false if update fails
        }
    }

    // Method to delete an employee from the employee table
    public static boolean deleteEmployee(String empId) {
        String query = "DELETE FROM employee WHERE empId = ?";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, empId);        // Set employee ID for deletion
            pstmt.executeUpdate();            // Execute the delete query
            return true;                      // Return true if delete is successful
        } catch (SQLException e) {
            e.printStackTrace();
            return false;                     // Return false if delete fails
        }
    }

    // Additional utility methods if needed
}
