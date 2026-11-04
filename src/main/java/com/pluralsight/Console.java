package com.pluralsight;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;

public class Console {

    private static Scanner scanner = new Scanner(System.in);

    public static LocalDate promptForDate() {
        while (true) {
            try {
                System.out.print("Date (YYYY-MM-DD): ");
                return LocalDate.parse(scanner.nextLine().trim());

            } catch (DateTimeException e) {
                System.out.println("Invalid date. Please try again.");
            }
        }
    }

    public static LocalTime promptForTime() {
        while (true) {
            try {
                System.out.print("Time (HH:MM:SS): ");
                return LocalTime.parse(scanner.nextLine().trim());
            } catch (DateTimeException e) {
                System.out.println("Invalid time. Please try again.");
            }
        }
    }

    public static double promptForAmount() {
        while (true) {
            try {
                System.out.print("Amount: ");
                return Double.parseDouble(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("invalid amount. Please try again.");
            }
        }
    }

}
