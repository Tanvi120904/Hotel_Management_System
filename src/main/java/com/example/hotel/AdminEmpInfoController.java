package com.example.hotel;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.format.DateTimeFormatter;

public class AdminEmpInfoController {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private TableView<Employee> employeeTableView;
    @FXML
    private TableColumn<Employee, String> empIdColumn;
    @FXML
    private TableColumn<Employee, String> empNameColumn;
    @FXML
    private TableColumn<Employee, String> emailColumn;
    @FXML
    private TableColumn<Employee, String> phoneNoColumn;
    @FXML
    private TableColumn<Employee, String> addressColumn;
    @FXML
    private TableColumn<Employee, String> roleColumn;
    @FXML
    private TableColumn<Employee, String> dateOfJoiningColumn;

    @FXML
    private TextField searchField; // Search field for filtering employees

    private ObservableList<Employee> employeeData; // To hold all employees

    @FXML
    public void initialize() {
        // Initialize the table columns to bind with Employee properties
        empIdColumn.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getEmpId())));
        empNameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFullName()));
        emailColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEmail()));
        phoneNoColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPhoneNo()));
        addressColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAddress()));
        roleColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getRole()));

        // Handling LocalDate for dateOfJoining
        dateOfJoiningColumn.setCellValueFactory(cellData -> {
            String dateOfJoining = cellData.getValue().getDateOfJoining() != null
                    ? cellData.getValue().getDateOfJoining().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) // Format LocalDate
                    : "N/A";
            return new SimpleStringProperty(dateOfJoining);
        });

        // Load employee data into the table from the database
        loadEmployeeData();
    }

    private void loadEmployeeData() {
        // Assuming EmployeeDAO.getAllEmployees() returns a list of Employee objects
        employeeData = FXCollections.observableArrayList(EmployeeDAO.getAllEmployees()); // Use observable array list
        employeeTableView.setItems(employeeData);
    }

    @FXML
    public void searchEmployee() {
        String searchText = searchField.getText().toLowerCase();

        // If the search field is empty, show all employees
        if (searchText.isEmpty()) {
            employeeTableView.setItems(employeeData); // Reset to full list
            return;
        }

        // Otherwise, filter the employees by search text
        ObservableList<Employee> filteredList = FXCollections.observableArrayList();

        for (Employee employee : employeeData) {
            if (employee.getFullName().toLowerCase().contains(searchText) ||
                    String.valueOf(employee.getEmpId()).contains(searchText) ||
                    (employee.getEmail() != null && employee.getEmail().toLowerCase().contains(searchText)) ||
                    (employee.getPhoneNo() != null && employee.getPhoneNo().contains(searchText)) ||
                    (employee.getAddress() != null && employee.getAddress().toLowerCase().contains(searchText)) ||
                    (employee.getRole() != null && employee.getRole().toLowerCase().contains(searchText)) ||
                    (employee.getDateOfJoining() != null && employee.getDateOfJoining().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")).contains(searchText))) {
                filteredList.add(employee);
            }
        }

        employeeTableView.setItems(filteredList);
    }

    // Navigation methods
    public void switchToAdminHome(ActionEvent event) throws IOException {
        loadScene(event, "AdminHome.fxml");
    }

    public void switchToAdminAddEmp(ActionEvent event) throws IOException {
        loadScene(event, "AdminAddEmp.fxml");
    }

    public void switchToAdminEmpInfo(ActionEvent event) throws IOException {
        loadScene(event, "AdminEmpInfo.fxml");
    }

    public void switchToAdminCustInfo(ActionEvent event) throws IOException {
        loadScene(event, "AdminCustInfo.fxml");
    }

    public void switchToAdminTotalEarn(ActionEvent event) throws IOException {
        loadScene(event, "AdminTotalEarn.fxml");
    }

    public void switchToMainPg(ActionEvent event) throws IOException {
        loadScene(event, "MainPage.fxml");
    }

    private void loadScene(ActionEvent event, String fxmlFile) throws IOException {
        root = FXMLLoader.load(getClass().getResource(fxmlFile));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
