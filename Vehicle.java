package com.parking.models;

import java.time.LocalDateTime;

public class Vehicle {
    private String numberPlate;
    private String type;
    private LocalDateTime entryTime;
    private Slot assignedSlot;
    private Customer owner;

    // Standard Constructor (2 arguments)
    public Vehicle(String numberPlate, String type) {
        this.numberPlate = numberPlate;
        this.type = type;
        this.entryTime = LocalDateTime.now();
    }


    // ---------------- FIX: Added 3-argument Constructor ----------------
    // Ye method aapka 'cannot be applied to given types' error khatam karega
    public Vehicle(String numberPlate, String type, Customer owner) {
        this.numberPlate = numberPlate;
        this.type = type;
        this.owner = owner;
        this.entryTime = LocalDateTime.now();
    }

    // Getters and Setters
    public String getVehicleType() { return type; }
    public String getType() { return type; }
    public String getNumberPlate() { return numberPlate; }

    public Customer getOwner() { return owner; }
    public void setOwner(Customer owner) { this.owner = owner; }

    public void assignSlot(Slot slot) { this.assignedSlot = slot; }
    public Slot getAssignedSlot() { return assignedSlot; }
    public LocalDateTime getEntryTime() { return entryTime; }
}