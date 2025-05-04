package com.example.hotel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO {
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/hotelreservationsystem"; // Update with your database URL
    private static final String DATABASE_USER = "root"; // Update with your database user
    private static final String DATABASE_PASSWORD = "oracle"; // Update with your database password

    // Method to save an employee to the database
    public static boolean saveEmployee(Employee employee) {
        String sql = "INSERT INTO employee (emp_id, full_name, email, phone_no, address, role, date_of_joining) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, employee.getEmpId());
            statement.setString(2, employee.getFullName());
            statement.setString(3, employee.getEmail());
            statement.setString(4, employee.getPhoneNo());
            statement.setString(5, employee.getAddress());
            statement.setString(6, employee.getRole());
            statement.setDate(7, java.sql.Date.valueOf(employee.getDateOfJoining())); // Convert LocalDate to java.sql.Date

            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0; // Returns true if the employee was added successfully

        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Returns false in case of an error
        }
    }

    // Method to retrieve all employees from the database
    public static List<Employee> getAllEmployees() { // Fixed the method name here
        List<Employee> employeeList = new ArrayList<>();
        String sql = "SELECT * FROM employee";

        try (Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                int empId = resultSet.getInt("emp_id");
                String fullName = resultSet.getString("full_name");
                String email = resultSet.getString("email");
                String phoneNo = resultSet.getString("phone_no");
                String address = resultSet.getString("address");
                String role = resultSet.getString("role");

                // Convert the SQL Date to LocalDate
                LocalDate dateOfJoining = resultSet.getDate("date_of_joining") != null
                        ? resultSet.getDate("date_of_joining").toLocalDate()
                        : null;

                // Create an Employee object and add it to the list
                Employee employee = new Employee(empId, fullName, email, phoneNo, address, role, dateOfJoining);
                employeeList.add(employee); // Add the employee to the list
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return employeeList; // Return the list of employees
    }


    // Method to update employee details in the database
    public static boolean updateEmployee(Employee employee) {
        String sql = "UPDATE employee SET full_name = ?, email = ?, phone_no = ?, address = ?, role = ?, date_of_joining = ? WHERE emp_id = ?";
        try (Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, employee.getFullName());
            statement.setString(2, employee.getEmail());
            statement.setString(3, employee.getPhoneNo());
            statement.setString(4, employee.getAddress());
            statement.setString(5, employee.getRole());
            statement.setDate(6, java.sql.Date.valueOf(employee.getDateOfJoining())); // Convert LocalDate to java.sql.Date
            statement.setInt(7, employee.getEmpId());

            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0; // Returns true if the employee was updated successfully

        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Returns false in case of an error
        }
    }

    // Method to check if an employee ID exists in the database
    public static boolean employeeIdExists(String empId) {
        String query = "SELECT COUNT(*) FROM employee WHERE emp_id = ?";
        try (Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, empId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1) > 0; // Return true if count is greater than 0
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; // Return false if an error occurs or no ID is found
    }

    // Optionally, you can add methods for deleting an employee or finding an employee by ID if needed.
}
