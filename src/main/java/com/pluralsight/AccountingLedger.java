package com.pluralsight;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class AccountingLedger {

    private static ArrayList<Transaction> transactions;

    public static void main(String[] args) {
        System.out.println(new java.io.File(".").getAbsolutePath());
        transactions = readCSV();

        homeScreen();


    }

    public static ArrayList<Transaction> readCSV() {

        ArrayList<Transaction> transactions = new ArrayList<>();


        String fileName = "transactions.csv";

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
              //  System.out.println(line);
                String[] parts = line.split("\\|");
                double amount = Double.parseDouble(parts[4]);
                Transaction transaction = new Transaction(parts[0], parts[1], parts[2], parts[3], Double.parseDouble(parts[4]));
                transactions.add(transaction);

            }
        } catch (IOException e) {
            System.err.println("An error occurred while reading the file.");
            e.printStackTrace();
        }

        return transactions;
    }

    public static void homeScreen() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n===== Account Home =====");
        System.out.println("\nAdd Deposit (D)");
        System.out.println("Make payment (P)");
        System.out.println("Ledger (L)");
        System.out.println("Exit(X)");
        System.out.print("Enter your choice: ");
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
        Scanner scanner = new Scanner(System.in);

            System.out.println("\n===Make a deposit===");
        System.out.print("Date YYYY-MM-DD: ");
        String date = scanner.nextLine().trim();
        System.out.print("Time HH:MM:SS: ");
        String time = scanner.nextLine().trim();
        System.out.print("Description: ");
        String description = scanner.nextLine();
        System.out.print("Vendor: ");
        String vendor = scanner.nextLine();
        System.out.print("Amount: ");
        double amount = Double.parseDouble(scanner.nextLine().trim());


        Transaction deposit = new Transaction(date, time, description, vendor, amount);
        transactions.add(deposit);
        for (Transaction t : transactions ) {
            System.out.println(t.getAmount());
        }
        //write new transaction to the csv
        // make a payment similar to the deposit
        // ledger screen 
    }

    public static void makeAPayment() {

        System.out.println("Make a payment");

    }

    public static void yourLedger() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n===This is your ledger===");
        System.out.println("\nAll entries (A)");
        System.out.println("Deposits (D)");
        System.out.println("Payments (P)");
        System.out.println("Reports (R)");
        System.out.println("Back to home (H)");
        System.out.print("Enter your choice: ");
        String Input = scanner.nextLine().trim();

        if (Input.equalsIgnoreCase("A")) {
            System.out.println("All your entries");
        } else if (Input.equalsIgnoreCase("D")) {
            System.out.println("Your deposits");

        } else if (Input.equalsIgnoreCase("P")) {
            System.out.println("Your payments");

        } else if (Input.equalsIgnoreCase("R")) {
            System.out.println("Your reports");

        } else if (Input.equalsIgnoreCase("H")) {
            homeScreen();
        } else {
            System.out.println("Invalid input");
        }



    }

    public static void exit() {

        System.out.println("You have exited");

    }


    // populate your csv file with some data
    // create a class to be the "thing" represented by 1 line of file.
    // write the logic to read from the file and create the things, and put them someplace.
    // creating transaction object for every new transaction and storing it in a array list.
}
