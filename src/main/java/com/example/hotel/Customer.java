package com.example.hotel;

public class Customer {
    private int customerId;
    private String customerName;
    private String contactNo;
    private String roomId;
    private double totalExpenses;
    private String receipt;

    public Customer(int customerId, String customerName, String contactNo,  String roomId, double totalExpenses, String receipt) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.contactNo = contactNo;
        this.roomId = roomId;
        this.totalExpenses = totalExpenses;
        this.receipt = receipt;
    }

    // Getters and setters
    public String getCustomerId() {
        return String.valueOf(customerId);
    }

    public void setCustomerId(int customerId) {
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

    public double getTotalExpenses() {
        return totalExpenses;
    }

    public void setTotalExpenses(double totalExpenses) {
        this.totalExpenses = totalExpenses;
    }

    public String getReceipt() {
        return receipt;
    }

    public void setReceipt(String receipt) {
        this.receipt = receipt;
    }
}
