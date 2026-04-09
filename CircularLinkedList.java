package com.parking.models;

import javax.servlet.jsp.JspWriter;
import java.io.IOException;

public class CircularLinkedList<T> {
    private Node<T> head = null;
    private Node<T> tail = null;
    private int size = 0;

    private static class Node<T> {
        T data;
        Node<T> next;
        Node(T data) { this.data = data; }
    }

    // ---------------- FIX: Added display Method ----------------
    // Ye method console par list print karega aur error khatam karega
    public void display() {
        if (head == null) {
            System.out.println("List is empty.");
            return;
        }
        Node<T> current = head;
        do {
            System.out.print(current.data + " -> ");
            current = current.next;
        } while (current != head);
        System.out.println("(Head)");
    }

    // Authentication ke liye
    public T findByUsername(String username) {
        if (head == null) return null;
        Node<T> current = head;
        do {
            if (current.data instanceof operator) {
                operator op = (operator) current.data;
                if (op.getUsername().equalsIgnoreCase(username)) return current.data;
            }
            current = current.next;
        } while (current != head);
        return null;
    }

    // Operator delete karne ke liye
    public boolean removeByUsername(String username) {
        if (head == null) return false;
        Node<T> current = head;
        Node<T> prev = tail;
        do {
            if (current.data instanceof operator) {
                operator op = (operator) current.data;
                if (op.getUsername().equals(username)) {
                    if (size == 1) { head = null; tail = null; }
                    else {
                        prev.next = current.next;
                        if (current == head) head = head.next;
                        if (current == tail) tail = prev;
                    }
                    size--;
                    return true;
                }
            }
            prev = current;
            current = current.next;
        } while (current != head);
        return false;
    }

    // Queue se data nikalne ke liye
    public T get(int index) {
        if (index < 0 || index >= size) return null;
        Node<T> temp = head;
        for (int i = 0; i < index; i++) temp = temp.next;
        return temp.data;
    }

    // Web dashboard par dikhane ke liye
    public void displayInJSP(JspWriter out) throws IOException {
        if (head == null) {
            out.println("<tr><td colspan='3'>No Operators Found</td></tr>");
            return;
        }
        Node<T> current = head;
        do {
            if (current.data instanceof operator) {
                operator op = (operator) current.data;
                out.println("<tr><td>" + op.getUsername() + "</td>");
                out.println("<td>" + op.getRole() + "</td></tr>");
            }
            current = current.next;
        } while (current != head);
    }

    public void add(T data) {
        Node<T> newNode = new Node<>(data);
        if (head == null) { head = newNode; tail = newNode; newNode.next = head; }
        else { tail.next = newNode; tail = newNode; tail.next = head; }
        size++;
    }

    public boolean isEmpty() { return size == 0; }
    public int size() { return size; }
}