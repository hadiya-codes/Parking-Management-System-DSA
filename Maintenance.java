package com.parking.models;
public class Maintenance {

    private int id;
    private String description;
    private String status;
    private operator assignedoperator;
    private int floorNo;
    private int slotNo;

    public Maintenance(int id, String description, int floorNo, int slotNo) {
        this.id = id;
        this.description = description;
        this.floorNo = floorNo;
        this.slotNo = slotNo;
        this.status = "Pending";
    }

    public void assignoperator(operator op) {
        this.assignedoperator = op;
        this.status = "InProgress";
    }

    public void updateStatus(String status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    // ✅ DISPLAY METHOD (display() error FIX)
    public void displayMaintenanceInfo() {
        System.out.println("ID: " + id);
        System.out.println("Issue: " + description);
        System.out.println("Floor: " + floorNo + " Slot: " + slotNo);
        System.out.println("Status: " + status);
        System.out.println("Operator: " +
            (assignedoperator == null ? "Not Assigned" : assignedoperator.getUsername()));
        System.out.println("---------------------------");
    }
}
