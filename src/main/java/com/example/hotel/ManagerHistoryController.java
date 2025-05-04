package com.example.hotel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static javafx.fxml.FXMLLoader.load;

public class ManagerHistoryController {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private TableView<CustomerHistory> historyTable;

    @FXML
    private TableColumn<CustomerHistory, String> custIdColumn;

    @FXML
    private TableColumn<CustomerHistory, String> custNameColumn;

    @FXML
    private TableColumn<CustomerHistory, String> contactNoColumn;

    @FXML
    private TableColumn<CustomerHistory, String> roomIdColumn;

    @FXML
    private TableColumn<CustomerHistory, String> totalExpensesColumn;

    @FXML
    private TableColumn<CustomerHistory, String> receiptColumn;

    private ObservableList<CustomerHistory> customerHistoryList;

    @FXML
    public void initialize() {
        // Initializing the table columns
        custIdColumn.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        custNameColumn.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        contactNoColumn.setCellValueFactory(new PropertyValueFactory<>("contactNo"));
        roomIdColumn.setCellValueFactory(new PropertyValueFactory<>("roomId"));
        totalExpensesColumn.setCellValueFactory(new PropertyValueFactory<>("totalExpenses"));
        receiptColumn.setCellValueFactory(new PropertyValueFactory<>("receipt"));

        // Fetch data from the database in a background thread
        fetchDataFromDatabase();
    }

    // Method to fetch data from the database
    private void fetchDataFromDatabase() {
        Task<ObservableList<CustomerHistory>> task = new Task<ObservableList<CustomerHistory>>() {
            @Override
            protected ObservableList<CustomerHistory> call() throws Exception {
                return loadCustomerHistoryFromDatabase();
            }

            @Override
            protected void succeeded() {
                super.succeeded();
                // Set the data into the table view
                historyTable.setItems(getValue());
            }

            @Override
            protected void failed() {
                super.failed();
                // Handle failure (could show an alert or log error)
                getException().printStackTrace();
            }
        };

        // Start the task in a background thread
        new Thread(task).start();
    }

    // Method to load customer history from the database
    private ObservableList<CustomerHistory> loadCustomerHistoryFromDatabase() throws SQLException {
        ObservableList<CustomerHistory> customerHistoryList = FXCollections.observableArrayList();
        String query = "SELECT customer_id, customer_name, contact_no, room_id, total_expenses, receipt FROM customer_info"; // Update this if your table name is different

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                String customerId = rs.getString("customer_id");
                String customerName = rs.getString("customer_name");
                String contactNo = rs.getString("contact_no");
                String roomId = rs.getString("room_id");
                String totalExpenses = rs.getString("total_expenses");
                String receipt = rs.getString("receipt");

                // Add the record to the observable list
                customerHistoryList.add(new CustomerHistory(customerId, roomId, contactNo, customerName, "", totalExpenses, receipt));
                // Assuming the empty string is for the emailId; you may need to adjust this as necessary
            }
        }

        return customerHistoryList;
    }

    // Method to switch back to Manager Home
    public void switchToManagerHome(ActionEvent event) throws IOException {
        root = load(getClass().getResource("ManagerHome.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    // Other switch methods
    public void switchToMainPg(ActionEvent event) throws IOException {
        root = load(getClass().getResource("MainPage.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToManagerCustHistory(ActionEvent event) throws IOException {
        root = load(getClass().getResource("ManagerCustomerHistory.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToManagerCustExp(ActionEvent event) throws IOException {
        root = load(getClass().getResource("ManagerCustomerReceipt.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToManagerCheckOut(ActionEvent event) throws IOException {
        root = load(getClass().getResource("ManagerCheckOut.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToManagerCheckIn(ActionEvent event) throws IOException {
        root = load(getClass().getResource("ManagerCheckIn.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
