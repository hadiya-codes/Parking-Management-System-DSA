package com.parking.models;



// Simple AuditLog using SLL + Stack
public class AuditLog {

    // Node class for SLL
    private class Node {
        String action;
        Node next;

        Node(String action) {
            this.action = action;
            this.next = null;
        }
    }

    private Node head;      // Head of SLL (oldest action)
    private Node top;       // Top of stack (latest action)

    public AuditLog() {
        head = null;
        top = null;
    }

    // Add new action
    public void addAction(String action) {
        Node newNode = new Node(action);

        // Insert at end of SLL
        if (head == null) {
            head = newNode;
        } else {
            Node curr = head;
            while (curr.next != null) curr = curr.next;
            curr.next = newNode;
        }

        // Push to stack for undo
        newNode.next = top;
        top = newNode;
    }

    // Undo last action
    public String undo() {
        if (top == null) return "No actions to undo";

        String lastAction = top.action;
        top = top.next;

        // Remove last node from SLL
        if (head != null) {
            if (head.next == null) { head = null; }
            else {
                Node curr = head;
                Node prev = null;
                while (curr.next != null) {
                    prev = curr;
                    curr = curr.next;
                }
                if (prev != null) prev.next = null;
            }
        }

        return "Undone: " + lastAction;
    }

    // Display full audit log
    public void display() {
        System.out.println("---- Audit Log ----");
        Node curr = head;
        int count = 1;
        while (curr != null) {
            System.out.println(count + ". " + curr.action);
            curr = curr.next;
            count++;
        }
        System.out.println("------------------");
    }
}
