package com.parking.models;
import java.util.function.Predicate;
import java.util.function.Consumer;

// Node for Single Linked List
class SLLNode<T> {
    T data;
    SLLNode<T> next;

    public SLLNode(T data) {
        this.data = data;
        this.next = null;
    }
}

// Single Linked List
public class SingleLinkedList<T> {
    private SLLNode<T> head;

    public SingleLinkedList() {
        head = null;
    }

    // ---------------- ADD ----------------
    public void add(T data) {
        SLLNode<T> node = new SLLNode<>(data);
        node.next = head;
        head = node;
    }

    // ---------------- REMOVE ----------------
    public void remove(T data) {
        if (head == null) return;
        if (head.data.equals(data)) {
            head = head.next;
            return;
        }
        SLLNode<T> temp = head;
        while (temp.next != null && !temp.next.data.equals(data)) {
            temp = temp.next;
        }
        if (temp.next != null) temp.next = temp.next.next;
    }

    // ---------------- SEARCH WITH PREDICATE ----------------
    public T search(Predicate<T> condition) {
        SLLNode<T> temp = head;
        while (temp != null) {
            if (condition.test(temp.data)) return temp.data;
            temp = temp.next;
        }
        return null;
    }

    // ---------------- TRAVERSE ----------------
    public void traverse(Consumer<T> action) {
        SLLNode<T> temp = head;
        while (temp != null) {
            action.accept(temp.data);
            temp = temp.next;
        }
    }

    // ---------------- CHECK EMPTY ----------------
    public boolean isEmpty() {
        return head == null;
    }

    // ---------------- DISPLAY ----------------
    public void display() {
        if (head == null) {
            System.out.println("List is empty.");
            return;
        }
        SLLNode<T> temp = head;
        while (temp != null) {
            System.out.println(temp.data); // Calls Ticket/Maintenance toString()
            temp = temp.next;
        }
    }

    // ---------------- HELPER FOR TICKET ----------------
    // Only works if T = Ticket
    public T searchByVehicleNumber(String vehicleNo) {
        if (head == null) return null;

        if (!(head.data instanceof Ticket)) {
            throw new RuntimeException("searchByVehicleNumber() can only be used with Ticket type");
        }

        return search(t -> ((Ticket) t).getVehicle().getNumberPlate().equals(vehicleNo));
    }

    // ---------------- HELPER FOR MAINTENANCE ----------------
    public Maintenance searchById(int id) {
        SLLNode<T> temp = head;
        while (temp != null) {
            if (temp.data instanceof Maintenance) {
                Maintenance m = (Maintenance) temp.data;
                if (m.getId() == id)
                    return m;
            }
            temp = temp.next;
        }
        return null;
    }

    public void displayAll() {
        SLLNode<T> temp = head;
        while (temp != null) {
            if (temp.data instanceof Maintenance) {
                ((Maintenance) temp.data).displayMaintenanceInfo();
                System.out.println("------------------------");
            }
            temp = temp.next;
        }
    }
}
