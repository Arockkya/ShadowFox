package com.shadowfox.contact;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        ArrayList<Contact> contacts = new ArrayList<>();

        int choice;

        do {

            System.out.println("\n===== CONTACT MANAGEMENT SYSTEM =====");
            System.out.println("1. Add Contact");
            System.out.println("2. View Contacts");
            System.out.println("3. Update Contact");
            System.out.println("4. Delete Contact");
            System.out.println("5. Search Contact");
            System.out.println("6. Exit");

            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                // ================= ADD =================
                case 1 -> {

                    System.out.print("Enter Name: ");
                    String name = sc.nextLine().trim();

                    if (!name.matches("[a-zA-Z ]+")) {
                        System.out.println("Invalid name!");
                        break;
                    }

                    System.out.print("Enter Phone Number: ");
                    String phone = sc.nextLine().trim();

                    if (!phone.matches("\\d{10}")) {
                        System.out.println("Phone must be 10 digits!");
                        break;
                    }

                    boolean exists = contacts.stream()
                            .anyMatch(c -> c.getPhoneNumber().equals(phone));

                    if (exists) {
                        System.out.println("Phone already exists!");
                        break;
                    }

                    contacts.add(new Contact(name, phone));
                    System.out.println("Contact Added Successfully!");
                }

                // ================= VIEW =================
                case 2 -> {

                    if (contacts.isEmpty()) {
                        System.out.println("No contacts found!");
                    } else {
                        System.out.println("\n--- CONTACT LIST ---");
                        for (int i = 0; i < contacts.size(); i++) {
                            System.out.println((i + 1) + ". " + contacts.get(i));
                        }
                    }
                }

                // ================= UPDATE =================
                case 3 -> {

                    System.out.print("Enter Contact Number to Update: ");
                    int index = sc.nextInt() - 1;
                    sc.nextLine();

                    if (index < 0 || index >= contacts.size()) {
                        System.out.println("Invalid contact number!");
                        break;
                    }

                    System.out.print("Enter New Name: ");
                    String newName = sc.nextLine().trim();

                    if (!newName.matches("[a-zA-Z ]+")) {
                        System.out.println("Invalid name!");
                        break;
                    }

                    System.out.print("Enter New Phone Number: ");
                    String newPhone = sc.nextLine().trim();

                    if (!newPhone.matches("\\d{10}")) {
                        System.out.println("Phone must be 10 digits!");
                        break;
                    }

                    boolean duplicate = false;

                    for (int i = 0; i < contacts.size(); i++) {
                        if (i != index &&
                                contacts.get(i).getPhoneNumber().equals(newPhone)) {
                            duplicate = true;
                            break;
                        }
                    }

                    if (duplicate) {
                        System.out.println("Phone already exists!");
                        break;
                    }

                    contacts.get(index).setName(newName);
                    contacts.get(index).setPhoneNumber(newPhone);

                    System.out.println("Contact Updated!");
                }

                // ================= DELETE =================
                case 4 -> {

                    System.out.print("Enter Contact Number to Delete: ");
                    int index = sc.nextInt() - 1;

                    if (index >= 0 && index < contacts.size()) {
                        contacts.remove(index);
                        System.out.println("Deleted Successfully!");
                    } else {
                        System.out.println("Invalid index!");
                    }
                }

                // ================= SEARCH (NEW FEATURE) =================
                case 5 -> {

                    System.out.println("Search by:");
                    System.out.println("1. Name");
                    System.out.println("2. Phone");
                    System.out.print("Choice: ");

                    int type = sc.nextInt();
                    sc.nextLine();

                    if (type == 1) {

                        System.out.print("Enter Name: ");
                        String searchName = sc.nextLine().toLowerCase();

                        boolean found = false;

                        for (Contact c : contacts) {
                            if (c.getName().toLowerCase().contains(searchName)) {
                                System.out.println(c);
                                found = true;
                            }
                        }

                        if (!found) {
                            System.out.println("No contact found!");
                        }

                    } else if (type == 2) {

                        System.out.print("Enter Phone: ");
                        String searchPhone = sc.nextLine();

                        boolean found = false;

                        for (Contact c : contacts) {
                            if (c.getPhoneNumber().equals(searchPhone)) {
                                System.out.println(c);
                                found = true;
                                break;
                            }
                        }

                        if (!found) {
                            System.out.println("No contact found!");
                        }

                    } else {
                        System.out.println("Invalid choice!");
                    }
                }

                // ================= EXIT =================
                case 6 -> System.out.println("Application Closed!");

                default -> System.out.println("Invalid choice!");

            }

        } while (choice != 6);

        sc.close();
    }
}