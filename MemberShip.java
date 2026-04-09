package com.parking.models;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class MemberShip {

    private String type;        // e.g., "Gold", "Silver", "Platinum"
    private int discount;       // discount % (e.g., 10, 20)
    private int validityDays;   // MemberShip validity in days
    private LocalDate startDate;
    private LocalDate endDate;
    SingleLinkedList<MemberShip> memberships;


    public MemberShip(String type, int discount, int validityDays) {
        this.type = type;
        this.discount = discount;
        this.validityDays = validityDays;
        this.startDate = LocalDate.now();
        this.endDate = startDate.plusDays(validityDays);
    }

    // ---------------- GETTERS ----------------
    public String getType() {
        return type;
    }

    public int getDiscount() {
        return discount;
    }

    public int getValidityDays() {
        return validityDays;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    // ---------------- SETTERS ----------------
    public void setType(String type) {
        this.type = type;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public void setValidityDays(int days) {
        this.validityDays = days;
        this.endDate = startDate.plusDays(days);
    }

    // ---------------- CHECK VALIDITY ----------------
    public boolean isActive() {
        LocalDate today = LocalDate.now();
        return today.isBefore(endDate) || today.isEqual(endDate);
    }

    // ---------------- DISPLAY ----------------
    public void displayMemberShip() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        System.out.println("MemberShip Type: " + type);
        System.out.println("Discount: " + discount + "%");
        System.out.println("Validity: " + startDate.format(formatter) + " to " + endDate.format(formatter));
        System.out.println("Status: " + (isActive() ? "Active" : "Expired"));
    }
}

