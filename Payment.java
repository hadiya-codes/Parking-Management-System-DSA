package com.parking.models;
public class Payment {

    private Ticket ticket;
    private double amountPaid;
    private String paymentMethod; // e.g., "Cash", "Card", "Mobile"
    private boolean isPaid;

    public Payment(Ticket ticket) {
        this.ticket = ticket;
        this.amountPaid = 0.0;
        this.paymentMethod = "";
        this.isPaid = false;
    }

    // ---------------- CALCULATE AMOUNT ----------------
    public double calculateAmount(Pricing pricing) {
        double total = pricing.calculate(ticket);
        System.out.println("Total amount to pay for Ticket " + ticket.getTicketID() + ": Rs " + total);
        return total;
    }

    // ---------------- MAKE PAYMENT ----------------
    public void makePayment(double amount, String method) {
        this.amountPaid = amount;
        this.paymentMethod = method;
        this.isPaid = true;
        System.out.println("Payment of Rs " + amount + " received via " + method + " for Ticket " + ticket.getTicketID());
        ticket.closeTicket(); // close ticket after payment
    }

    // ---------------- GETTERS ----------------
    public double getAmountPaid() {
        return amountPaid;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public Ticket getTicket() {
        return ticket;
    }

    // ---------------- DISPLAY PAYMENT INFO ----------------
    public void displayPaymentInfo() {
        System.out.println("Payment Info for Ticket " + ticket.getTicketID());
        System.out.println("- Vehicle: " + ticket.getVehicle().getNumberPlate());
        System.out.println("- Customer: " + ticket.getCustomer().getName());
        System.out.println("- Amount Paid: Rs " + amountPaid);
        System.out.println("- Payment Method: " + paymentMethod);
        System.out.println("- Status: " + (isPaid ? "Paid" : "Pending"));
    }
}
