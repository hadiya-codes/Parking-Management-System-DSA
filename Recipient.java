package com.parking.models;
public class Recipient {

    private String name;          // Customer name or department
    private String type;          // "Customer", "Finance", "Other"
    private String contactInfo;   // phone, email, etc. (optional)

    public Recipient(String name, String type, String contactInfo) {
        this.name = name;
        this.type = type;
        this.contactInfo = contactInfo;
    }

    // ---------------- GETTERS ----------------
    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    // ---------------- SETTERS ----------------
    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    // ---------------- DISPLAY ----------------
    public void displayRecipient() {
        System.out.println("Recipient Info:");
        System.out.println("- Name: " + name);
        System.out.println("- Type: " + type);
        if (contactInfo != null && !contactInfo.isEmpty()) {
            System.out.println("- Contact: " + contactInfo);
        }
    }
}

