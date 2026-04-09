package com.parking.models;class QueueNode {
    Vehicle vehicle;
    QueueNode next;

    public QueueNode(Vehicle vehicle) {
        this.vehicle = vehicle;
        this.next = null;
    }
}

public class WaitingQueue {

    private QueueNode tail; // points to last node
    private int size;

    public WaitingQueue() {
        tail = null;
        size = 0;
    }

    // ---------------- ENQUEUE ----------------
    public void enqueue(Vehicle vehicle) {
        QueueNode node = new QueueNode(vehicle);
        if (tail == null) {
            tail = node;
            tail.next = tail; // circular
        } else {
            node.next = tail.next; // new node points to head
            tail.next = node;      // old tail points to new node
            tail = node;           // new node becomes new tail
        }
        size++;
        System.out.println("Vehicle " + vehicle.getNumberPlate() + " added to waiting queue.");
    }

    // ---------------- DEQUEUE ----------------
    public Vehicle dequeue() {
        if (tail == null) {
            System.out.println("Queue is empty!");
            return null;
        }

        QueueNode head = tail.next;
        if (head == tail) { // only one node
            tail = null;
        } else {
            tail.next = head.next;
        }
        size--;
        System.out.println("Vehicle " + head.vehicle.getNumberPlate() + " removed from waiting queue.");
        return head.vehicle;
    }

    // ---------------- PEEK ----------------
    public Vehicle peek() {
        if (tail == null) return null;
        return tail.next.vehicle; // head vehicle
    }

    // ---------------- IS EMPTY ----------------
    public boolean isEmpty() {
        return tail == null;
    }

    // ---------------- SIZE ----------------
    public int getSize() {
        return size;
    }

    // ---------------- DISPLAY ----------------
    public void displayQueue() {
        if (tail == null) {
            System.out.println("Waiting queue is empty.");
            return;
        }

        System.out.println("Waiting Queue:");
        QueueNode current = tail.next;
        do {
            System.out.println("- " + current.vehicle.getNumberPlate());
            current = current.next;
        } while (current != tail.next);
    }
}
