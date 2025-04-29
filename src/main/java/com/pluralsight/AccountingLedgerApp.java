package com.pluralsight;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Scanner;

public class AccountingLedgerApp {

    //scanner allows user input
    static Scanner ledgerScanner = new Scanner(System.in);

    public static void main(String[] args) {

        //while loop will run until user chooses to exit the application
        boolean appRunning = true;

        while (appRunning) {

            //welcome message
            System.out.println("Welcome to Alondra's Prestigious Accounting Ledger Application! " +
                    "\nWhere you can track all your financial transactions");
            System.out.println("===================================================");

            //display Home Screen options
            System.out.println("D) Add Deposit " +
                    "\nP) Make Payment (Debit) " +
                    "\nL) Ledger " +
                    "\nX) Exit ");
            System.out.print("Make a selection: ");
            String userSelection = ledgerScanner.nextLine().trim();

            //if statement for the Home Screen based off what the user selects
            if (userSelection.equalsIgnoreCase("X")) {
                exitApp();
                appRunning = false;
            } else if (userSelection.equalsIgnoreCase("D")) {
                addDeposit();
            } else if (userSelection.equalsIgnoreCase("P")) {
                makePayment();
            } else if (userSelection.equalsIgnoreCase("L")) {
                openLedger();
            } else {
                System.out.println("Invalid selection, please make a valid selection");
            }
        }
    }

    public static void writeToCSV(Transaction transaction) {
    }

//    //METHOD FOR HOME SCREEN
//    public static void homeScreen() {}

    //method for X) Exit
    public static void exitApp() {

        //user exiting program goodbye message
        System.out.println("Thank you for choosing Alondra's Prestigious Accounting Ledger Application. " +
                "\nYou are now exiting, Have a wonderful day!");
    }

    // method for D) Add Deposit - MIGHT NEED TRY/CATCH STATEMENT TO READ/WRITE TO FILE
    public static void addDeposit() {

        //ask user for deposit info and save it in the csv file
        System.out.println("Enter description: ");
        String description = ledgerScanner.nextLine().trim();

        System.out.println("Enter vendor: ");
        String vendor = ledgerScanner.nextLine().trim();

        //amount will be positive
        System.out.println("Enter amount: ");
        Double amount = ledgerScanner.nextDouble();
        ledgerScanner.nextLine(); //eats the new line

        Transaction deposit = new Transaction(LocalDate.now(), LocalTime.now(), description, vendor, amount);
        writeToCSV(deposit);

        System.out.println("Deposit has successfully been made!");
    }

    //method for P) Make Payment (Debit) - MIGHT NEED TRY/CATCH STATEMENT TO READ/WRITE TO FILE
    public static void makePayment() {

        //ask user for payment info and save it in the csv file
        System.out.println("Enter description: ");
        String description = ledgerScanner.nextLine().trim();

        System.out.println("Enter vendor: ");
        String vendor = ledgerScanner.nextLine().trim();

        //amount will be negative?? - MIGHT NEED IF STATEMENT
        System.out.println("Enter amount: ");
        Double amount = ledgerScanner.nextDouble();
        ledgerScanner.nextLine(); //eats the new line

        Transaction payment = new Transaction(LocalDate.now(), LocalTime.now(), description, vendor, amount);
        writeToCSV(payment);

        System.out.println("Payment has successfully been made!");
    }

    //method for L) Ledger Screen
    public static void openLedger() {

        //ledger screen (all entries should show the newest entries first) - WILL NEED A WHILE LOOP?
        //A) All: display all entries
        //D) Deposits: display only the entries that are deposits into the account
        //P) Payments: display only the negative entries/payments
        //R) Reports: a new screen that allows the user to run pre-defined reports or to run a custom search
        //-1) Month To Date - WILL NEED SWITCH STATEMENT
        //-2) Previous Month
        //-3) Year To Date
        //-4) Previous Year
        //-5) Search By Vendor: prompt user for the vendor name and display all entries for that vendor
        //-0) Back: go back to Reports page
        //H) Home: go back to home screen
    }

}