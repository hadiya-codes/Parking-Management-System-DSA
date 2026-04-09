package com.parking.models;
public class Customer {

    private String name;
    private String phoneNumber;
    private String cnic;
    private MemberShip membership; // optional, membership discount

    public Customer(String name, String phoneNumber, String cnic) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.cnic = cnic;
        this.membership = null; // default no membership
    }

    // ---------------- SET MEMBERSHIP ----------------
    public void setMembership(MemberShip membership) {
        this.membership = membership;
    }

    public MemberShip getMembership() {
        return membership;
    }
     public boolean hasActiveMembership() {
        return membership != null && membership.isActive();
    }


    // ---------------- GETTER METHODS ----------------
    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getCnic() {
        return cnic;
    }

    // ---------------- DISPLAY ----------------
    public void displayCustomerInfo() {
        System.out.println("Customer Name: " + name);
        System.out.println("Phone Number: " + phoneNumber);
        System.out.println("CNIC: " + cnic);
        if (membership != null) {
            System.out.println("Membership: " + membership.getType() + 
                               ", Discount: " + membership.getDiscount() + "%");
        } else {
            System.out.println("No membership");
        }
    }
   
}
