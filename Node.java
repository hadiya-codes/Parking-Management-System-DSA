package com.parking.models;
import java.util.ArrayList;

public class Node {

    private String name; // e.g., "EntryGate", "ExitGate", "Floor1", "Slot5"
    private ArrayList<Edge> edges; // connections to other nodes

    public Node(String name) {
        this.name = name;
        this.edges = new ArrayList<>();
    }

    // ---------------- ADD EDGE ----------------
    public void addEdge(Node targetNode, double weight) {
        // Ab ye line error nahi degi kyunke humne Edge.java bana li hai
        Edge edge = new Edge(this, targetNode, weight);
        edges.add(edge);
    }

    // ---------------- GETTERS ----------------
    public String getName() {
        return name;
    }

    public ArrayList<Edge> getEdges() {
        return edges;
    }

    // ---------------- DISPLAY ----------------
    public void displayNode() {
        System.out.println("Node: " + name);
        if (edges.size() > 0) {
            System.out.println("Connected to:");
            for (Edge e : edges) {
                // Ab getTarget() aur getWeight() methods Edge class se mil jayenge
                System.out.println(" -> " + e.getTarget().getName() + " (weight: " + e.getWeight() + ")");
            }
        } else {
            System.out.println("No connections yet.");
        }
    }
}