package com.pluralsight;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileWriter;
import java.io.BufferedWriter;


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
                LocalDate date = LocalDate.parse(parts[0]);
                LocalTime time = LocalTime.parse(parts[1]);
                double amount = Double.parseDouble(parts[4]);
                Transaction transaction = new Transaction(date, time, parts[2], parts[3], amount);
                transactions.add(transaction);

            }
        } catch (IOException e) {
            System.err.println("An error occurred while reading the file.");
            e.printStackTrace();
        }

        return transactions;
    }

    public static void homeScreen() {

        while (true) {
            System.out.println("\n===== Account Home =====");
            System.out.println("\nAdd Deposit (D)");
            System.out.println("Make payment (P)");
            System.out.println("Ledger (L)");
            System.out.println("Exit(X)");
            String input = Console.promptForString("Enter your choice: ");

            if (input.equalsIgnoreCase("D")) {
                makeADeposit();
            } else if (input.equalsIgnoreCase("P")) {
                makeAPayment();

            } else if (input.equalsIgnoreCase("L")) {
                yourLedger();

            } else if (input.equalsIgnoreCase("X")) {
                exit();
                return;

            } else {
                System.out.println("Invalid input");
            }

        }




    }

    public static void makeADeposit() {

            System.out.println("\n===Make a deposit===");
            LocalDate date = Console.promptForDate();
            LocalTime time = Console.promptForTime();
            String description = Console.promptForString("Description: ");
            String vendor = Console.promptForString("Vendor: ");
            double amount = Console.promptForAmount();


            Transaction deposit = new Transaction(date, time, description, vendor, amount);
            transactions.add(deposit);
            saveTransaction(deposit);




    }

    public static void makeAPayment() {

            System.out.println("\n===Make a payment===");

            LocalDate date = Console.promptForDate();
            LocalTime time = Console.promptForTime();
            String description = Console.promptForString("Description: ");
            String vendor = Console.promptForString("Vendor: ");
           double amount = Console.promptForAmount();
            amount = -Math.abs(amount);


            Transaction payment = new Transaction(date, time, description, vendor, amount);
            transactions.add(payment);
            saveTransaction(payment);


    }

    public static void yourLedger() {

        while (true) {
            System.out.println("\n===This is your ledger===");
            System.out.println("\nAll entries (A)");
            System.out.println("Deposits (D)");
            System.out.println("Payments (P)");
            System.out.println("Reports (R)");
            System.out.println("Back to home (H)");
            String input = Console.promptForString("Enter your choice: ");

            if (input.equalsIgnoreCase("A")) {
                showAllEntries();
            } else if (input.equalsIgnoreCase("D")) {
                showDeposits();

            } else if (input.equalsIgnoreCase("P")) {
                showPayments();

            } else if (input.equalsIgnoreCase("R")) {
                reportsScreen();

            } else if (input.equalsIgnoreCase("H")) {
                return;
            } else {
                System.out.println("Invalid input");
            }

        }




    }

    public static void showAllEntries() {
        System.out.println("\n==== All your entries ====");
        for (Transaction t : transactions) {
            System.out.println("\n" + t.getDate() + "|" + t.getTime() + "|" + t.getDescription() + "|" + t.getVendor() + "|" + t.getAmount());

        }
    }

    public static void showDeposits() {
        System.out.println("\n==== All your deposits ====");
        for (Transaction t : transactions) {
            if (t.getAmount() > 0) {
                System.out.println("\n" + t.getDate() + "|" + t.getTime() + "|" + t.getDescription() + "|" + t.getVendor() + "|" + t.getAmount());
            }
        }

    }

    public static void showPayments() {
        System.out.println("\n==== All your payments ====");
        for (Transaction t : transactions) {
            if (t.getAmount() < 0) {
                System.out.println("\n" + t.getDate() + "|" + t.getTime() + "|" + t.getDescription() + "|" + t.getVendor() + "|" + t.getAmount());
            }
        }
    }

    public static void reportsScreen() {

        while (true) {
            System.out.println("\n==== Your reports ====");
            System.out.println("\nMonth To Date (1)");
            System.out.println("Previous Month (2)");
            System.out.println("Year To Date (3)");
            System.out.println("Previous Year (4)");
            System.out.println("Search by Vendor (5)");
            System.out.println("Go back (0)");
            String input = Console.promptForString("Enter your choice: ").trim();

            if (input.equals("1")) {
                showMonthToDate();

            } else if (input.equals("2")) {
               showPreviousMonth();

            } else if (input.equals("3")) {
               showYearToDate();

            } else if (input.equals("4")) {
                showPreviousYear();

            } else if (input.equals("5")) {
                searchByVendor();

            } else if (input.equals("0")) {
                return;
            } else {
                System.out.println("\nInvalid input. Try again.");
            }


        }
    }

    public static void showMonthToDate() {
        LocalDate today = LocalDate.now();
        LocalDate startOfMonth = today.withDayOfMonth(1);
        for (Transaction t : transactions) {
            if (!t.getDate().isBefore(startOfMonth) && !t.getDate().isAfter(today)) {
                System.out.println("\n" + t.getDate() + "|" + t.getTime() + "|" + t.getDescription() + "|" + t.getVendor() + "|" + t.getAmount());
            }
        }
    }

    public static void showPreviousMonth() {
        LocalDate today = LocalDate.now();
        LocalDate firstDay = today.minusMonths(1).withDayOfMonth(1);
        LocalDate lastDay = today.withDayOfMonth(1).minusDays(1);
        for (Transaction t : transactions) {
            if (!t.getDate().isBefore(firstDay) && !t.getDate().isAfter(lastDay)) {
                System.out.println("\n" + t.getDate() + "|" + t.getTime() + "|" + t.getDescription() + "|" + t.getVendor() + "|" + t.getAmount());
            }


        }
    }

    public static void showYearToDate() {
        LocalDate today = LocalDate.now();
        LocalDate startOfYear = today.withDayOfYear(1);
        for (Transaction t : transactions) {
            if (!t.getDate().isBefore(startOfYear) && !t.getDate().isAfter(today)) {
                System.out.println("\n" + t.getDate() + "|" + t.getTime() + "|" + t.getDescription() + "|" + t.getVendor() + "|" + t.getAmount());
            }
        }
    }

    public static void showPreviousYear() {
        LocalDate today = LocalDate.now();
        LocalDate startOfYear = today.minusYears(1).withDayOfYear(1);
        LocalDate endOfYear = today.withDayOfYear(1).minusDays(1);
        for (Transaction t : transactions) {
            if (!t.getDate().isBefore(startOfYear) && !t.getDate().isAfter(endOfYear)) {
                System.out.println("\n" + t.getDate() + "|" + t.getTime() + "|" + t.getDescription() + "|" + t.getVendor() + "|" + t.getAmount());
            }
        }
    }

    public static void searchByVendor() {
        String vendorSearch = Console.promptForString("Enter vendor name: ");
        for (Transaction t : transactions) {
            if (t.getVendor().equalsIgnoreCase(vendorSearch)) {
                System.out.println("\n" + t.getDate() + "|" + t.getTime() + "|" + t.getDescription() + "|" + t.getVendor() + "|" + t.getAmount());
            }
        }
    }

    public static void exit() {

        System.out.println("You have exited");

    }

    public static void saveTransaction(Transaction transaction) {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("transactions.csv", true))) {

            writer.write(transaction.getDate() + "|" + transaction.getTime() + "|" + transaction.getDescription() + "|" + transaction.getVendor() + "|" + transaction.getAmount());
            writer.newLine();


        } catch (IOException e) {
            System.err.println("Error saving transaction.");
            e.printStackTrace();
        }
    }


}
