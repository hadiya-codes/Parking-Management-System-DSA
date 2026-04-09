package com.parking.models;

import java.util.ArrayList;
import java.util.List;

public class Garage {

    private String name;
    private Floor root; // BST Root for floors
    private VehicleHashTable searchMap; // Hash Table for vehicles

    public Garage(String name) {
        this.name = name;
        this.searchMap = new VehicleHashTable(200);
    }

    // ---------------- FIX: Added displaySlots Method ----------------
    // Ye method console par saare floors aur unke slots dikhayega
    public void displaySlots() {
        if (root == null) {
            System.out.println("Garage is empty (No floors).");
            return;
        }
        System.out.println("\n--- Garage: " + name + " Layout ---");
        List<Floor> floors = getAllFloors(); // BST In-order traversal
        for (Floor f : floors) {
            f.displayFloorInfo(); // Floor class ka display method
        }
    }

    // ---------------- BST SEARCH FLOOR ----------------
    public Floor getFloor(int floorNo) {
        return searchRecursive(root, floorNo);
    }

    private Floor searchRecursive(Floor current, int floorNo) {
        if (current == null || current.getFloorNo() == floorNo) return current;
        if (floorNo < current.getFloorNo()) return searchRecursive(current.getLeft(), floorNo);
        return searchRecursive(current.getRight(), floorNo);
    }

    // ---------------- BST REMOVE FLOOR ----------------
    public void removeFloor(int floorNo) {
        root = deleteRecursive(root, floorNo);
    }

    private Floor deleteRecursive(Floor current, int floorNo) {
        if (current == null) return null;
        if (floorNo < current.getFloorNo()) {
            current.setLeft(deleteRecursive(current.getLeft(), floorNo));
        } else if (floorNo > current.getFloorNo()) {
            current.setRight(deleteRecursive(current.getRight(), floorNo));
        } else {
            if (current.getLeft() == null && current.getRight() == null) return null;
            if (current.getLeft() == null) return current.getRight();
            if (current.getRight() == null) return current.getLeft();
            int smallestValue = findSmallestValue(current.getRight());
            Floor replacement = new Floor(smallestValue);
            replacement.setLeft(current.getLeft());
            replacement.setRight(deleteRecursive(current.getRight(), smallestValue));
            return replacement;
        }
        return current;
    }

    private int findSmallestValue(Floor root) {
        return root.getLeft() == null ? root.getFloorNo() : findSmallestValue(root.getLeft());
    }

    // ---------------- HASHING & BST UTILS ----------------
    public void registerVehicle(String plate, Slot slot) { searchMap.put(plate, slot); }
    public void unregisterVehicle(String plate) { searchMap.remove(plate); }
    public Slot searchByPlate(String plate) { return searchMap.get(plate); }

    public void addFloor(Floor floor) { root = insert(root, floor); }
    private Floor insert(Floor curr, Floor f) {
        if (curr == null) return f;
        if (f.getFloorNo() < curr.getFloorNo()) curr.setLeft(insert(curr.getLeft(), f));
        else curr.setRight(insert(curr.getRight(), f));
        return curr;
    }

    public List<Floor> getAllFloors() {
        List<Floor> floors = new ArrayList<>();
        inOrder(root, floors);
        return floors;
    }

    private void inOrder(Floor node, List<Floor> list) {
        if (node == null) return;
        inOrder(node.getLeft(), list);
        list.add(node);
        inOrder(node.getRight(), list);
    }

    public Slot findFreeSlot() { return dfsSlot(root); }
    private Slot dfsSlot(Floor node) {
        if (node == null) return null;
        Slot s = node.findFreeSlot();
        if (s != null) return s;
        s = dfsSlot(node.getLeft());
        return (s != null) ? s : dfsSlot(node.getRight());
    }

    public int getFloorCount() { return getAllFloors().size(); }
}