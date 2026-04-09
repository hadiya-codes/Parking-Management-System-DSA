package com.parking.models;

import com.parking.utils.DSAManager;
import java.util.ArrayList;
import java.util.List;

public class operator extends User {

    private Garage assignedGarage;
    private CircularLinkedList<Ticket> waitingQueue;

    public operator(String username, String password) {
        super(username, password, "OPERATOR");
        this.waitingQueue = new CircularLinkedList<>();
    }

    public operator(String username, String password, Garage garage) {
        super(username, password, "OPERATOR");
        this.assignedGarage = garage;
        this.waitingQueue = new CircularLinkedList<>();
    }

    // ---------------- FIX: Added showWaitingQueue Method ----------------
    // Ye method 'cannot find symbol' error khatam karega
    public List<Ticket> showWaitingQueue() {
        List<Ticket> queueList = new ArrayList<>();
        // CLL se data nikal kar list mein daal rahe hain taake JSP display kar sake
        if (waitingQueue != null && !waitingQueue.isEmpty()) {
            for (int i = 0; i < waitingQueue.size(); i++) {
                queueList.add(waitingQueue.get(i));
            }
        }
        return queueList;
    }

    // Existing showTicketHistory method for compatibility
    public List<Ticket> showTicketHistory() {
        return showWaitingQueue();
    }

    public String searchVehicleLocation(String plateNumber) {
        Slot s = DSAManager.garage.searchByPlate(plateNumber);
        return (s != null) ? "Floor: " + s.getFloorNo() + ", Slot: " + s.getSlotNo() : "Not found!";
    }

    public void vehicleEntry(Customer customer, Vehicle vehicle) {
        Slot freeSlot = DSAManager.garage.findFreeSlot();
        if (freeSlot != null) {
            Ticket newTicket = new Ticket(customer, vehicle);
            waitingQueue.add(newTicket);
            freeSlot.assignVehicle(vehicle);
            DSAManager.garage.registerVehicle(vehicle.getNumberPlate(), freeSlot);
        }
    }

    public void vehicleExit(String plateNumber, Pricing pricing) {
        Slot occupiedSlot = DSAManager.garage.searchByPlate(plateNumber);
        if (occupiedSlot != null) {
            pricing.calculateFee(occupiedSlot.getCurrentVehicle());
            occupiedSlot.removeVehicle();
            DSAManager.garage.unregisterVehicle(plateNumber);
        }
    }

    @Override
    public void showDashboard() {
        System.out.println("Operator Dashboard: " + this.username);
    }

    public Garage getAssignedGarage() { return assignedGarage; }
}