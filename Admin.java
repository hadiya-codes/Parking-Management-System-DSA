package com.parking.models;

import java.util.Scanner;

public class Admin extends User {
    private CircularLinkedList<operator> operators;
    private Garage garage;
    private Pricing pricing;
    private Settings settings;
    private AuditLog auditLog;
    private SingleLinkedList<Maintenance> maintenanceList;
    private SingleLinkedList<MemberShip> memberships;

    public Admin(String username, String password, Garage garage) {
        super(username, password, "ADMIN");
        this.operators = new CircularLinkedList<>();

        // Null pointer fix for garage
        if (garage == null) {
            this.garage = new Garage("Main Garage");
        } else {
            this.garage = garage;
        }

        this.pricing = new Pricing();
        this.settings = new Settings();
        this.auditLog = new AuditLog();
        this.maintenanceList = new SingleLinkedList<>();
        this.memberships = new SingleLinkedList<>();
    }

    // ---------------- FIX: Pricing (Function 3) ----------------
    // Symbol Error Fix: Exact parameters (String, double)
    public void setPrice(String vehicleType, double price) {
        if (pricing != null) {
            pricing.setPrice(vehicleType, price); // Pricing class link
            auditLog.addAction("Set Price for " + vehicleType + ": " + price);
            System.out.println("Price set for " + vehicleType + ": " + price);
        }
    }

    // ---------------- FIX: Search Vehicle (String return type) ----------------
    public String searchVehicleLocation(String numberPlate) {
        if (this.garage == null) return "❌ Garage not initialized!";

        Slot foundSlot = garage.searchByPlate(numberPlate);
        if (foundSlot != null && foundSlot.getCurrentVehicle() != null) {
            String loc = "Floor " + foundSlot.getFloorNo() + ", Slot " + foundSlot.getSlotNo();
            auditLog.addAction("Searched vehicle: " + numberPlate + " (Found)");
            return "✅ Found at " + loc;
        }
        return "❌ Vehicle NOT found.";
    }

    // ---------------- Baki Methods ----------------
    public void addOperator(operator op) { operators.add(op); auditLog.addAction("Added Operator: " + op.getUsername()); }
    public void listOperators() { operators.display(); }
    public void addFloor(Floor floor) { if (garage != null) garage.addFloor(floor); }
    public void addSlotToFloor(int floorNo, Slot slot) {
        Floor f = (garage != null) ? garage.getFloor(floorNo) : null;
        if (f != null) f.addSlot(slot);
    }
    public void addMaintenance(Maintenance m) { maintenanceList.add(m); }
    public Settings getSettings() { return settings; }
    public String getUsername() { return username; }
}