package com.pluralsight;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class AccountingLedger {
    public static void main(String[] args) {
        System.out.println(new java.io.File(".").getAbsolutePath());
        readCSV();
        homeScreen();


    }

    public static void readCSV() {

        ArrayList<Transaction> transactions = new ArrayList<>();


        String fileName = "transactions.csv";

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
                String[] parts = line.split("\\|");
                double amount = Double.parseDouble(parts[4]);
                Transaction transaction = new Transaction(parts[0], parts[1], parts[2], parts[3], Double.parseDouble(parts[4]));
                transactions.add(transaction);
                
            }
        } catch (IOException e) {
            System.err.println("An error occurred while reading the file.");
            e.printStackTrace();
        }
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
            yourLedger();

        } else if (input.equalsIgnoreCase("X")) {
           exit();

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

    public static void yourLedger() {

        System.out.println("This is your ledger");

    }

    public static void exit() {

        System.out.println("You have exited");

    }


    // populate your csv file with some data
    // create a class to be the "thing" represented by 1 line of file.
    // write the logic to read from the file and create the things, and put them someplace.
    // creating transaction object for every new transaction and storing it in a array list.
}
