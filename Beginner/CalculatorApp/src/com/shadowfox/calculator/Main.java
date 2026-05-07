package com.shadowfox.calculator;

import java.util.Scanner;
import java.util.InputMismatchException;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        Calculator calc = new Calculator();

        int choice=0;

        do {

            System.out.println("\n===== SHADOWFOX CALCULATOR =====");

            System.out.println("1. Addition");
            System.out.println("2. Subtraction");
            System.out.println("3. Multiplication");
            System.out.println("4. Division");
            System.out.println("5. Exit");

            System.out.print("Enter your choice: ");

            try {

                choice = sc.nextInt();

                if (choice >= 1 && choice <= 4) {

                    System.out.print("Enter first number: ");
                    double num1 = sc.nextDouble();

                    System.out.print("Enter second number: ");
                    double num2 = sc.nextDouble();

                    double result = switch (choice) {
                        case 1 -> calc.add(num1, num2);
                        case 2 -> calc.subtract(num1, num2);
                        case 3 -> calc.multiply(num1, num2);
                        case 4 -> calc.divide(num1, num2);
                        default -> 0;
                    };

                    System.out.println("Result = " + result);

                }

                else if (choice == 5) {

                    System.out.println("Calculator Closed");

                }

                else {

                    System.out.println("Invalid Choice");

                }

            }

            catch (ArithmeticException e) {

                System.out.println("Error: " + e.getMessage());

            }

            catch (InputMismatchException e) {

                System.out.println("Invalid Input! Please enter numbers only.");

                sc.nextLine();

                choice = 0;

            }

        } while (choice != 5);

        sc.close();
    }
}