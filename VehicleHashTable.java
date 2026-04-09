

package com.parking.models;
class HashNode {
    String numberPlate;
    Slot slot;
    HashNode next;

    public HashNode(String numberPlate, Slot slot) {
        this.numberPlate = numberPlate;
        this.slot = slot;
        this.next = null;
    }
}

public class VehicleHashTable {
    private HashNode[] buckets;
    private int capacity;

    public VehicleHashTable(int capacity) {
        this.capacity = capacity;
        this.buckets = new HashNode[capacity];
    }

    private int getIndex(String key) {
        return Math.abs(key.hashCode()) % capacity;
    }

    public void put(String key, Slot value) {
        int index = getIndex(key);
        HashNode head = buckets[index];
        while (head != null) {
            if (head.numberPlate.equals(key)) {
                head.slot = value;
                return;
            }
            head = head.next;
        }
        HashNode newNode = new HashNode(key, value);
        newNode.next = buckets[index];
        buckets[index] = newNode;
    }

    public Slot get(String key) {
        int index = getIndex(key);
        HashNode head = buckets[index];
        while (head != null) {
            if (head.numberPlate.equals(key)) {
                return head.slot;
            }
            head = head.next;
        }
        return null;
    }

    public void remove(String key) {
        int index = getIndex(key);
        HashNode head = buckets[index];
        HashNode prev = null;
        while (head != null) {
            if (head.numberPlate.equals(key)) {
                if (prev != null) prev.next = head.next;
                else buckets[index] = head.next;
                return;
            }
            prev = head;
            head = head.next;
        }
    }
}