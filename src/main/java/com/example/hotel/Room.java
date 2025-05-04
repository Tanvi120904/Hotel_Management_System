package com.example.hotel;

public class Room {
    private String roomNumber;
    private String bedCapacity;
    private String roomType;
    private String pricePerDay;
    private String roomStatus;

    // Constructor
    public Room(String roomNumber, String bedCapacity, String roomType, String pricePerDay, String roomStatus) {
        this.roomNumber = roomNumber;
        this.bedCapacity = bedCapacity;
        this.roomType = roomType;
        this.pricePerDay = pricePerDay;
        this.roomStatus = roomStatus;
    }

    // Getters and Setters
    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getBedCapacity() {
        return bedCapacity;
    }

    public void setBedCapacity(String bedCapacity) {
        this.bedCapacity = bedCapacity;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public String getPricePerDay() {
        return pricePerDay;
    }

    public void setPricePerDay(String pricePerDay) {
        this.pricePerDay = pricePerDay;
    }

    public String getRoomStatus() {
        return roomStatus;
    }

    public void setRoomStatus(String roomStatus) {
        this.roomStatus = roomStatus;
    }
}
