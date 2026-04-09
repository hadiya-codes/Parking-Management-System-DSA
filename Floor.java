package com.parking.models;

import java.util.ArrayList;
import java.util.List;

// Node for Slot Linked List
class SlotNode {
    Slot slot;
    SlotNode next;

    public SlotNode(Slot slot) {
        this.slot = slot;
        this.next = null;
    }
}

public class Floor {

    private int floorNo;
    private SlotNode head; // Linked list of Slots
    private Floor left;    // BST Left Child
    private Floor right;   // BST Right Child

    public Floor(int floorNo) {
        this.floorNo = floorNo;
        this.head = null;
        this.left = null;
        this.right = null;
    }

    // ---------------- FIX: Added removeSlot Method ----------------
    // Ye method specific slot number ko Linked List se remove karega
    public void removeSlot(int slotNo) {
        if (head == null) {
            System.out.println("No slots to remove!");
            return;
        }

        // Agar head node hi wo slot ho
        if (head.slot.getSlotNo() == slotNo) {
            head = head.next;
            System.out.println("Slot " + slotNo + " removed from Floor " + floorNo);
            return;
        }

        // Linked List traverse karke dhoondna
        SlotNode temp = head;
        while (temp.next != null && temp.next.slot.getSlotNo() != slotNo) {
            temp = temp.next;
        }

        if (temp.next != null) {
            temp.next = temp.next.next;
            System.out.println("Slot " + slotNo + " removed from Floor " + floorNo);
        } else {
            System.out.println("Slot " + slotNo + " not found on Floor " + floorNo);
        }
    }

    public void displayFloorInfo() {
        System.out.println("Floor No: " + floorNo);
        SlotNode temp = head;
        if (temp == null) {
            System.out.println("   (No slots on this floor)");
            return;
        }
        while (temp != null) {
            temp.slot.displaySlotInfo();
            temp = temp.next;
        }
    }

    public List<Slot> getSlotList() {
        List<Slot> list = new ArrayList<>();
        SlotNode temp = head;
        while (temp != null) {
            list.add(temp.slot);
            temp = temp.next;
        }
        return list;
    }

    public void addSlot(Slot slot) {
        SlotNode node = new SlotNode(slot);
        node.next = head;
        head = node;
        System.out.println("Slot " + slot.getSlotNo() + " added to Floor " + floorNo);
    }

    public Slot findFreeSlot() {
        SlotNode temp = head;
        while (temp != null) {
            if (temp.slot.isFree()) {
                return temp.slot;
            }
            temp = temp.next;
        }
        return null;
    }

    public int getFloorNo() { return floorNo; }
    public Floor getLeft() { return left; }
    public void setLeft(Floor left) { this.left = left; }
    public Floor getRight() { return right; }
    public void setRight(Floor right) { this.right = right; }
}