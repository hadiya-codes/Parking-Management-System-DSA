package com.parking.models;

public class User {
    protected int id;
    protected String username;
    protected String password;
    protected String email;
    protected String role;

    // Constructor for all users
    public User(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    // Login Logic
    public boolean login(String u, String p) {
        return this.username.equals(u) && this.password.equals(p);
    }

    // Password Update Logic
    public boolean changePassword(String oldPassword, String newPassword) {
        if (this.password.equals(oldPassword)) {
            this.password = newPassword;
            return true;
        }
        return false;
    }

    // Dashbaord Method (Non-abstract taake casting mein masla na ho)
    public void showDashboard() {
        System.out.println("Displaying dashboard for: " + username + " (" + role + ")");
    }

    // Standard Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getPassword() { return password; } // Added for convenience
    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}