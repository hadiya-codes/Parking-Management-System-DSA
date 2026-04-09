package com.parking.models;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Invoice {

    private String invoiceID;
    private Ticket ticket;
    private double totalAmount;
    private Recipient recipient; // optional
    private LocalDateTime dateTime;

    public Invoice(Ticket ticket, double totalAmount) {
        this.ticket = ticket;
        this.totalAmount = totalAmount;
        this.invoiceID = generateInvoiceID(ticket);
        this.dateTime = LocalDateTime.now();
    }

    public Invoice(Ticket ticket, double totalAmount, Recipient recipient) {
        this(ticket, totalAmount);
        this.recipient = recipient;
    }

    // ---------------- GENERATE INVOICE ID ----------------
    private String generateInvoiceID(Ticket ticket) {
        return "INV-" + ticket.getTicketID() + "-" + System.currentTimeMillis();
    }

    // ---------------- DISPLAY INVOICE ----------------
    public void displayInvoice() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        System.out.println("Invoice ID: " + invoiceID);
        System.out.println("Ticket ID: " + ticket.getTicketID());
        System.out.println("Vehicle: " + ticket.getVehicle().getNumberPlate());
        System.out.println("Customer: " + ticket.getCustomer().getName());
        System.out.println("Total Amount: Rs " + totalAmount);
        System.out.println("Date: " + dateTime.format(formatter));
        if (recipient != null) {
            System.out.println("Recipient: " + recipient.getName() + " | Type: " + recipient.getType());
        }
        System.out.println("---------------------------------");
    }

    // ---------------- GETTERS ----------------
    public String getInvoiceID() {
        return invoiceID;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public Ticket getTicket() {
        return ticket;
    }
}
