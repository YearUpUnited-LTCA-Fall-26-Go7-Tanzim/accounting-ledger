package com.pluralsight;

import java.util.Scanner;

import java.sql.SQLOutput;

public class AccountingLedger {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        homeScreen();


    }

    public static void homeScreen() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("===== Account Home =====");
        System.out.println("\nAdd Deposit (D)");
        System.out.println("Make payment (P)");
        System.out.println("Ledger (L)");
        System.out.println("Exit(X)");
        String input = scanner.nextLine().trim();

        if (input.equalsIgnoreCase("D")) {
            makeADeposit();
        } else if (input.equalsIgnoreCase("P")) {
            makeAPayment();

        } else if (input.equalsIgnoreCase("L")) {
            System.out.println("This is your ledger");

        } else if (input.equalsIgnoreCase("X")) {
            System.out.println("You have exited");

        } else {
            System.out.println("Invalid input");
        }


    }

    public static void makeADeposit() {

            System.out.println("Make a deposit");

    }

    public static void makeAPayment() {

        System.out.println("Make a payment");

    }
}
