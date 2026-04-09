package com.parking.models;

public class Slot {
    private int floorNo;
    private int slotNo;
    private boolean isFree;
    private String status; // Added for status compatibility
    private Vehicle currentVehicle;

    public Slot(int floorNo, int slotNo) {
        this.floorNo = floorNo;
        this.slotNo = slotNo;
        this.isFree = true;
        this.status = "Available";
        this.currentVehicle = null;
    }

    // ---------------- FIX: Added setStatus Method ----------------
    // Ye method aapka 'cannot find symbol' error khatam karega
    public void setStatus(String status) {
        this.status = status;
        if (status.equalsIgnoreCase("Available")) {
            this.isFree = true;
            this.currentVehicle = null;
        } else {
            this.isFree = false;
        }
    }

    // ---------------- Existing Methods for JSP & Logic ----------------

    public int getSlotId() { return this.slotNo; }
    public int getSlotNumber() { return this.slotNo; }
    public int getFloorNo() { return floorNo; }
    public int getSlotNo() { return slotNo; }
    public boolean isFree() { return isFree; }

    public void assignVehicle(Vehicle vehicle) {
        this.currentVehicle = vehicle;
        this.isFree = false;
        this.status = "Occupied";
    }

    public void removeVehicle() {
        this.currentVehicle = null;
        this.isFree = true;
        this.status = "Available";
    }

    public void occupy(Vehicle vehicle) { assignVehicle(vehicle); }
    public void release() { removeVehicle(); }

    public Vehicle getCurrentVehicle() { return currentVehicle; }

    public String getStatus() {
        if (isFree) return "Available";
        return (currentVehicle != null) ? "Occupied by " + currentVehicle.getNumberPlate() : "Occupied";
    }

    public void displaySlotInfo() {
        System.out.println("Slot: " + slotNo + " | Status: " + getStatus());
    }
}