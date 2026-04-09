package com.parking.models;

import java.time.LocalDateTime;
import java.time.Duration;

public class Pricing {
    private double ratePerHour;

    // Default Constructor
    public Pricing() {
        this.ratePerHour = 50.0;
    }

    // Parameterized Constructor
    public Pricing(double ratePerHour) {
        this.ratePerHour = ratePerHour;
    }

    // ---------------- FIX: Added getPrice Method ----------------
    // Ye method 'cannot find symbol getPrice' error ko khatam karega
    public double getPrice(String type) {
        // Filhal hum generic ratePerHour return kar rahe hain
        // Aap isay vehicle type (e.g. Car, Bike) ke mutabiq expand kar sakte hain
        return this.ratePerHour;
    }

    // ---------------- FIX: setPrice Method ----------------
    public void setPrice(String type, double price) {
        this.ratePerHour = price;
    }

    // Ticket ke liye calculation
    public double calculate(Ticket ticket) {
        if (ticket == null || ticket.getEntryTime() == null) {
            return 0.0;
        }
        return calculateDurationFee(ticket.getEntryTime());
    }

    // Vehicle ke liye calculation
    public double calculateFee(Vehicle vehicle) {
        if (vehicle == null || vehicle.getEntryTime() == null) {
            return 0.0;
        }
        return calculateDurationFee(vehicle.getEntryTime());
    }

    // Helper logic for duration
    private double calculateDurationFee(LocalDateTime entryTime) {
        LocalDateTime exitTime = LocalDateTime.now();
        Duration duration = Duration.between(entryTime, exitTime);
        long hours = duration.toHours();

        if (hours < 1) hours = 1; // Minimum 1 hour charge

        return hours * ratePerHour;
    }

    public double getRatePerHour() {
        return ratePerHour;
    }

    public void setRatePerHour(double ratePerHour) {
        this.ratePerHour = ratePerHour;
    }
}