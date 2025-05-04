package com.example.hotel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminCustInfoController {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private TableView<Customer> customerHistoryTable;

    @FXML
    private TableColumn<Customer, Integer> custIdColumn;

    @FXML
    private TableColumn<Customer, String> custNameColumn;

    @FXML
    private TableColumn<Customer, String> contactNoColumn;

    @FXML
    private TableColumn<Customer, Integer> roomIdColumn;

    @FXML
    private TableColumn<Customer, Double> totalExpensesColumn;

    @FXML
    private TableColumn<Customer, String> receiptColumn;

    @FXML
    private TextField searchField;

    @FXML
    private Button searchButton;

    private ObservableList<Customer> customerList = FXCollections.observableArrayList();

    // Initialize method to set up columns
    @FXML
    public void initialize() {
        custIdColumn.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        custNameColumn.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        contactNoColumn.setCellValueFactory(new PropertyValueFactory<>("contactNo"));
        roomIdColumn.setCellValueFactory(new PropertyValueFactory<>("roomId"));
        totalExpensesColumn.setCellValueFactory(new PropertyValueFactory<>("totalExpenses"));
        receiptColumn.setCellValueFactory(new PropertyValueFactory<>("receipt"));

        loadCustomerData();
    }

    // Method to load all customer data from the database
    private void loadCustomerData() {
        customerList.clear();
        try {
            Connection conn = DatabaseConnection.getConnection();
            String query = "SELECT * FROM customer_info"; // Updated table name
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                customerList.add(new Customer(
                        rs.getInt("customer_id"),
                        rs.getString("customer_name"),
                        rs.getString("contact_no"),
                        rs.getString("room_id"),
                        rs.getDouble("total_expenses"),
                        rs.getString("receipt")
                ));
            }

            customerHistoryTable.setItems(customerList);
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Search customer by ID
    @FXML
    private void searchCustomer() {
        String customerId = searchField.getText();
        customerList.clear();
        try {
            Connection conn = DatabaseConnection.getConnection();
            String query = "SELECT * FROM customer_info WHERE customer_id = ?"; // Updated table name
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, customerId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                customerList.add(new Customer(
                        rs.getInt("customer_id"),
                        rs.getString("customer_name"),
                        rs.getString("contact_no"),
                        rs.getString("room_id"),
                        rs.getDouble("total_expenses"),
                        rs.getString("receipt")
                ));
            }

            customerHistoryTable.setItems(customerList);
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Button handler methods to switch to other screens
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
}
