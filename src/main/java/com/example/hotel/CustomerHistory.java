package com.example.hotel;

public class CustomerHistory {
    private String customerId;
    private String customerName;
    private String contactNo;
    private String roomId;
    private String totalExpenses;
    private String receipt;
    private String dateOfStay; // New attribute added

    // Constructor
    public CustomerHistory(String customerId, String customerName, String contactNo, String roomId, String totalExpenses, String receipt, String dateOfStay) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.contactNo = contactNo;
        this.roomId = roomId;
        this.totalExpenses = totalExpenses;
        this.receipt = receipt;
        this.dateOfStay = dateOfStay; // Initialize new attribute
    }

    // Getters and Setters
    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getTotalExpenses() {
        return totalExpenses;
    }

    public void setTotalExpenses(String totalExpenses) {
        this.totalExpenses = totalExpenses;
    }

    public String getReceipt() {
        return receipt;
    }

    public void setReceipt(String receipt) {
        this.receipt = receipt;
    }

    public String getDateOfStay() { // Getter for new attribute
        return dateOfStay;
    }

    public void setDateOfStay(String dateOfStay) { // Setter for new attribute
        this.dateOfStay = dateOfStay;
    }
}
