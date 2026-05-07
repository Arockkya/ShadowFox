package com.shadowfox.contact;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        ArrayList<Contact> contacts = new ArrayList<>();

        int choice = 0;

        do {

            System.out.println("\n===== CONTACT MANAGEMENT SYSTEM =====");

            System.out.println("1. Add Contact");
            System.out.println("2. View Contacts");
            System.out.println("3. Update Contact");
            System.out.println("4. Delete Contact");
            System.out.println("5. Exit");

            System.out.print("Enter your choice: ");

            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:

                    System.out.print("Enter Name: ");
                    String name = sc.nextLine();

                    if (name.isEmpty()) {

                        System.out.println("Name cannot be empty!");
                        break;

                    }

                    if (!name.matches("[a-zA-Z ]+")) {

                        System.out.println("Name should contain letters only!");
                        break;

                    }

                    System.out.print("Enter Phone Number: ");
                    String phone = sc.nextLine();

                    if (phone.isEmpty()) {

                        System.out.println("Phone number cannot be empty!");
                        break;

                    }

                    if (!phone.matches("\\d{10}")) {

                        System.out.println("Phone number must contain exactly 10 digits!");
                        break;

                    }

                    boolean exists = false;

                    for (Contact c : contacts) {

                        if (c.getPhoneNumber().equals(phone)) {

                            exists = true;
                            break;

                        }
                    }

                    if (exists) {

                        System.out.println("Phone number already exists!");
                        break;

                    }

                    Contact contact = new Contact(name, phone);

                    contacts.add(contact);

                    System.out.println("Contact Added Successfully!");
                    break;

                case 2:

                    if (contacts.isEmpty()) {

                        System.out.println("No Contacts Found!");

                    } else {

                        System.out.println("\n--- Contact List ---");

                        for (int i = 0; i < contacts.size(); i++) {

                            System.out.println((i + 1) + ". " + contacts.get(i));

                        }
                    }

                    break;

                case 3:

                    System.out.print("Enter Contact Number to Update: ");
                    int updateIndex = sc.nextInt() - 1;
                    sc.nextLine();

                    if (updateIndex >= 0 && updateIndex < contacts.size()) {

                        System.out.print("Enter New Name: ");
                        String newName = sc.nextLine();

                        if (!newName.matches("[a-zA-Z ]+")) {

                            System.out.println("Name should contain letters only!");
                            break;

                        }

                        System.out.print("Enter New Phone Number: ");
                        String newPhone = sc.nextLine();

                        if (!newPhone.matches("\\d{10}")) {

                            System.out.println("Phone number must contain exactly 10 digits!");
                            break;

                        }

                        boolean duplicate = false;

                        for (int i = 0; i < contacts.size(); i++) {

                            if (i != updateIndex &&
                                    contacts.get(i).getPhoneNumber().equals(newPhone)) {

                                duplicate = true;
                                break;

                            }
                        }

                        if (duplicate) {

                            System.out.println("Phone number already exists!");
                            break;

                        }

                        contacts.get(updateIndex).setName(newName);
                        contacts.get(updateIndex).setPhoneNumber(newPhone);

                        System.out.println("Contact Updated Successfully!");

                    } else {

                        System.out.println("Invalid Contact Number!");
                    }

                    break;

                case 4:

                    System.out.print("Enter Contact Number to Delete: ");
                    int deleteIndex = sc.nextInt() - 1;

                    if (deleteIndex >= 0 && deleteIndex < contacts.size()) {

                        contacts.remove(deleteIndex);

                        System.out.println("Contact Deleted Successfully!");

                    } else {

                        System.out.println("Invalid Contact Number!");
                    }

                    break;

                case 5:

                    System.out.println("Application Closed");
                    break;

                default:

                    System.out.println("Invalid Choice!");

            }

        } while (choice != 5);

        sc.close();
    }
}