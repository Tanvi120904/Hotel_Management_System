package com.example.hotel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static Connection databaseLink;

    public static Connection getConnection() {
        String url = "jdbc:mysql://localhost:3306/hotelreservationsystem"; // replace with your database details
        String username = "root";
        String password = "oracle";

        try {
            if (databaseLink == null || databaseLink.isClosed()) {
                Class.forName("com.mysql.cj.jdbc.Driver");
                databaseLink = DriverManager.getConnection(url, username, password);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return databaseLink;
    }
}
