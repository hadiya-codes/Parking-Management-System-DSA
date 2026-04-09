package com.parking.models;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Complaint {

    private String complaintID;
    private Customer customer;
    private String description;
    private String status; // "Pending", "Resolved"
    private LocalDateTime dateTime;

    public Complaint(Customer customer, String description) {
        this.customer = customer;
        this.description = description;
        this.status = "Pending";
        this.dateTime = LocalDateTime.now();
        this.complaintID = generateComplaintID();
    }

    private String generateComplaintID() {
        return "C-" + System.currentTimeMillis();
    }

    // ---------------- RESOLVE COMPLAINT ----------------
    public void resolve() {
        this.status = "Resolved";
        System.out.println("Complaint " + complaintID + " resolved.");
    }

    // ---------------- DISPLAY ----------------
    public void displayComplaint() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        System.out.println("Complaint ID: " + complaintID);
        System.out.println("Customer: " + customer.getName());
        System.out.println("Description: " + description);
        System.out.println("Status: " + status);
        System.out.println("Date: " + dateTime.format(formatter));
        System.out.println("--------------------------");
    }

    // ---------------- GETTERS ----------------
    public String getStatus() { return status; }
    public String getComplaintID() { return complaintID; }
}
