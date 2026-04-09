package com.parking.models;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    // Initialize Garage
    static Garage garage = new Garage("City Center Parking"); 
    
    // Default Users (Admin and Operator)
    static Admin admin = new Admin("admin", "admin123", garage);
    static operator op = new operator("ali", "op123", garage);

    public static void main(String[] args) {
        // Setup initial dummy data so the system isn't empty
        setupInitialData();

        while (true) {
            System.out.println("\n========================================");
            System.out.println("   PARKING SLOT MANAGEMENT SYSTEM");
            System.out.println("========================================");
            System.out.println("1. Login as ADMIN");
            System.out.println("2. Login as OPERATOR");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            
            int choice = -1;
            try {
                String input = scanner.nextLine(); 
                choice = Integer.parseInt(input);
            } catch (Exception e) {
                System.out.println("Please enter a valid number!");
                continue;
            }

            switch (choice) {
                case 1:
                    adminPanel();
                    break;
                case 2:
                    operatorPanel();
                    break;
                case 3:
                    System.out.println("Exiting system... Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid option, please try again.");
            }
        }
    }

    // ---------------- ADMIN PANEL ----------------
    public static void adminPanel() {
        System.out.print("\nAdmin Username: ");
        String u = scanner.nextLine();
        System.out.print("Admin Password: ");
        String p = scanner.nextLine();

        if (admin.login(u, p)) {
            boolean back = false;
            while (!back) {
                System.out.println("\n--- ADMIN DASHBOARD ---");
                admin.showDashboard();
                System.out.println("11. Logout");
                System.out.print("Choice: ");
                
                try {
                    int c = Integer.parseInt(scanner.nextLine());
                    switch (c) {
                        case 1: admin.listOperators(); break;
                        case 2: manageGarage(); break;
                        case 3: 
                             System.out.print("Vehicle Type (Car/Bike): ");
                             String t = scanner.nextLine();
                             System.out.print("Price: ");
                             double pr = Double.parseDouble(scanner.nextLine());
                             admin.setPrice(t, pr);
                             break;
                        case 4: admin.showSlotUsage(); break;
                        case 5: admin.getSettings().displaySettings(); break;
                        case 6: admin.showAuditLog(); break;
                        case 7: 
                            System.out.print("Enter Plate: ");
                            admin.searchVehicleLocation(scanner.nextLine());
                            break;
                        case 11: back = true; break;
                        default: System.out.println("Invalid Option");
                    }
                } catch (Exception e) {
                    System.out.println("Invalid input!");
                }
            }
        }
    }

    // ---------------- OPERATOR PANEL ----------------
    public static void operatorPanel() {
        System.out.print("\nOperator Username: ");
        String u = scanner.nextLine();
        System.out.print("Operator Password: ");
        String p = scanner.nextLine();

        if (op.login(u, p)) {
            boolean back = false;
            while (!back) {
                System.out.println("\n--- OPERATOR DASHBOARD ---");
                op.showDashboard();
                System.out.println("9. Logout");
                System.out.print("Choice: ");

                try {
                    int c = Integer.parseInt(scanner.nextLine());
                    switch (c) {
                        case 1: // Entry
                            System.out.print("Customer Name: "); String name = scanner.nextLine();
                            System.out.print("Phone: "); String phone = scanner.nextLine();
                            System.out.print("Plate No: "); String plate = scanner.nextLine();
                            System.out.print("Type (Car/Bike): "); String type = scanner.nextLine();
                            
                            Customer cust = new Customer(name, phone, "000");
                            Vehicle v = new Vehicle(plate, type, cust);
                            op.vehicleEntry(cust, v);
                            
                            if(v.getAssignedSlot() != null) garage.registerVehicle(plate, v.getAssignedSlot());
                            break;
                        case 2: // Exit
                            System.out.print("Plate No: "); String outPlate = scanner.nextLine();
                            op.vehicleExit(outPlate, new Pricing());
                            garage.unregisterVehicle(outPlate);
                            break;
                        case 3: // Search
                            System.out.print("Plate No: ");
                            op.searchVehicleLocation(scanner.nextLine());
                            break;
                        case 5: op.showTicketHistory(); break;
                        case 6: op.showWaitingQueue(); break;
                        case 9: back = true; break;
                        default: System.out.println("Invalid Option");
                    }
                } catch (Exception e) {
                    System.out.println("Invalid input!");
                }
            }
        }
    }

    public static void manageGarage() {
        System.out.println("1. Add Floor\n2. Add Slot");
        try {
            int c = Integer.parseInt(scanner.nextLine());
            if (c == 1) {
                System.out.print("Floor No: ");
                admin.addFloor(new Floor(Integer.parseInt(scanner.nextLine())));
            } else if (c == 2) {
                System.out.print("Floor No: "); int f = Integer.parseInt(scanner.nextLine());
                System.out.print("Slot No: "); int s = Integer.parseInt(scanner.nextLine());
                admin.addSlotToFloor(f, new Slot(f, s));
            }
        } catch(Exception e) { System.out.println("Error!"); }
    }

    public static void setupInitialData() {
        // Setup default floors and slots
        Floor f1 = new Floor(1);
        Floor f2 = new Floor(2);
        garage.addFloor(f1);
        garage.addFloor(f2);
        f1.addSlot(new Slot(1, 101));
        f1.addSlot(new Slot(1, 102));
        f2.addSlot(new Slot(2, 201));
        admin.addOperator(op);
        admin.setPrice("Car", 200);
        admin.setPrice("Bike", 50);
    }
}