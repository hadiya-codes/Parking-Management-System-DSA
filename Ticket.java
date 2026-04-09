package com.parking.models;
import java.time.LocalDateTime;

public class Ticket {
    private String ticketID;
    private Vehicle vehicle;
    private Customer customer;
    private Slot slot;
    private LocalDateTime entryTime;
    private String status;

    public Ticket(Customer customer, Vehicle vehicle) {
        this.customer = customer;
        this.vehicle = vehicle;
        this.ticketID = "T-" + vehicle.getNumberPlate() + "-" + System.currentTimeMillis();
        this.entryTime = LocalDateTime.now(); // Entry waqt set ho raha hai
        this.status = "Active";
    }

    // ---------------- FIX: Added getEntryTime Method ----------------
    // Ye method Pricing.java ke error ko khatam karega
    public LocalDateTime getEntryTime() {
        return entryTime;
    }

    public void assignSlot(Slot slot) {
        this.slot = slot;
        if (slot != null) {
            slot.assignVehicle(this.vehicle);
            this.vehicle.assignSlot(slot);
        }
    }

    public void closeTicket() {
        this.status = "Closed";
        if (this.slot != null) {
            this.slot.removeVehicle();
        }
    }



    // Standard Getters
    public Customer getCustomer() { return customer; }
    public Vehicle getVehicle() { return vehicle; }
    public Slot getSlot() { return slot; }
    public String getTicketID() { return ticketID; }
    public String getStatus() { return status; }
}