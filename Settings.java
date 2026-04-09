package com.parking.models;

public class Settings {

    private double defaultBillingRate; // default rate if no pricing set
    private int defaultMembershipDiscount; // default % discount
    private int defaultMembershipValidity; // in days
    private int maxSlotsPerFloor; // limit for slots per floor

    public Settings() {
        // Default values
        this.defaultBillingRate = 100.0;  // Rs 100
        this.defaultMembershipDiscount = 10; // 10%
        this.defaultMembershipValidity = 30; // 30 days
        this.maxSlotsPerFloor = 50; // max 50 slots per floor
    }

    // ---------------- GETTERS ----------------
    public double getDefaultBillingRate() {
        return defaultBillingRate;
    }

    public int getDefaultMembershipDiscount() {
        return defaultMembershipDiscount;
    }

    public int getDefaultMembershipValidity() {
        return defaultMembershipValidity;
    }

    public int getMaxSlotsPerFloor() {
        return maxSlotsPerFloor;
    }

    // ---------------- SETTERS ----------------
    public void setDefaultBillingRate(double rate) {
        this.defaultBillingRate = rate;
        System.out.println("Default billing rate updated to: " + rate);
    }

    public void setDefaultMembershipDiscount(int discount) {
        this.defaultMembershipDiscount = discount;
        System.out.println("Default membership discount updated to: " + discount + "%");
    }

    public void setDefaultMembershipValidity(int days) {
        this.defaultMembershipValidity = days;
        System.out.println("Default membership validity updated to: " + days + " days");
    }

    public void setMaxSlotsPerFloor(int maxSlots) {
        this.maxSlotsPerFloor = maxSlots;
        System.out.println("Max slots per floor updated to: " + maxSlots);
    }

    // ---------------- DISPLAY ----------------
    public void displaySettings() {
        System.out.println("System Settings:");
        System.out.println("- Default Billing Rate: Rs " + defaultBillingRate);
        System.out.println("- Default Membership Discount: " + defaultMembershipDiscount + "%");
        System.out.println("- Default Membership Validity: " + defaultMembershipValidity + " days");
        System.out.println("- Max Slots per Floor: " + maxSlotsPerFloor);
    }
}
