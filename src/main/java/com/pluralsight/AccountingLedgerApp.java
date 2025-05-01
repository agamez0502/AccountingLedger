package com.pluralsight;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AccountingLedgerApp {

    //scanner allows user input
    static Scanner ledgerScanner = new Scanner(System.in);

    public static void main(String[] args) {

        //while loop will run until user chooses to exit the application
        boolean appRunning = true;

        while (appRunning) {

            //welcome message
            System.out.println("Welcome to Alondra's Prestigious Accounting Ledger Application!");
            System.out.println("===============================================================");

            //display Home Screen options
            System.out.println("--- Welcome to the Home Screen ---");
            System.out.println("D) Add Deposit");
            System.out.println("P) Make Payment (Debit)");
            System.out.println("L) Ledger");
            System.out.println("X) Exit");
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

    //method for reading file and returns an ArrayList
    public static ArrayList<Transaction> readFromCSV() {
        ArrayList<Transaction> transaction = new ArrayList<>();

        //read the file line by line
        try {
            //create a file reader to read the file
            FileReader readFile = new FileReader("src/main/resources/transactions.csv");

            //create the buffered reader to read the file
            BufferedReader bufRead = new BufferedReader(readFile);

            String line;

            //while loop
            while ((line = bufRead.readLine()) != null) {

                //split the line into different pieces
                String[] pieces = line.split("\\|");

                //if statement for pieces of the line
                if (pieces.length == 5) {

                    //parse the pieces of the line to be for the user to read
                    LocalDate date = LocalDate.parse(pieces[0].trim());
                    LocalTime time = LocalTime.parse(pieces[1].trim());
                    String description = pieces[2].trim();
                    String vendor = pieces[3].trim();
                    Double amount = Double.parseDouble(pieces[4].trim());

                    //create a new transaction
                    Transaction t = new Transaction(date, time, description, vendor, amount);

                    //add the product to our transaction ArrayList
                    transaction.add(t);
                }
            }
            //close the buffered reader so it can read the file
            bufRead.close();

        } catch (Exception e) {

            //if we run into an issue reading the file, display this instead
            System.out.println("Error reading the file " + e.getMessage());
        }

//            //sort transactions from
//            Collections.reverseArrayList;

        //return the transaction
        return transaction;
    }

    //method for writing to file
    public static void writeToCSV(Transaction transaction) {
        try {

            //create a file writer and set append to true so it adds to the file and not override its contents
            FileWriter csvFile = new FileWriter("src/main/resources/transactions.csv", true);

            //create the buffered writer to write to file
            BufferedWriter bufWriter = new BufferedWriter(csvFile);

            //create a date and time
            LocalDateTime time = LocalDateTime.now();

            //format time
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm:ss");
            String formatTime = time.format(formatter);

            //format the buffered writer will put the transaction info in
            bufWriter.write(transaction.getDate() + " | " +
                    formatTime + " | " +
                    transaction.getDescription() + " | " +
                    transaction.getVendor() + " | " +
                    transaction.getAmount());

            //make sure we have a new line in our file
            bufWriter.newLine();

            //close the buffered writer so it actually writes to the file
            bufWriter.close();

        } catch (Exception e) {

            //if we run into an issue writing to the file, display this instead
            System.out.println("Error writing to the file: " + e.getMessage());
        }
    }

//METHOD FOR HOME SCREEN
//    public static void homeScreen() {}

    //method for X) Exit
    public static void exitApp() {

        //user exiting program goodbye message
        System.out.println("Thank you for choosing Alondra's Prestigious Accounting Ledger Application. " +
                "\n--- You are now exiting, Have a wonderful day! ---");
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

        //if statement for making sure amount is negative for a payment
        if (amount > 0) {
            amount = -amount;
        }

        ledgerScanner.nextLine(); //eats the new line

        Transaction payment = new Transaction(LocalDate.now(), LocalTime.now(), description, vendor, amount);
        writeToCSV(payment);

        System.out.println("Payment has successfully been made!");
    }

    //method for L) Ledger Screen
    public static void openLedger() {

        boolean viewingLedger = true;

        //while loop for viewing ledger (all entries should show the newest entries first)
        while (viewingLedger) {

            //display Ledger Screen Submenu options
            System.out.println("--- Welcome to the Ledger Screen ---");
            System.out.println("A) All Transactions");
            System.out.println("D) Deposits");
            System.out.println("P) Payments");
            System.out.println("R) Reports");
            System.out.println("H) Home");
            System.out.print("Choose an option: ");
            String userChoice = ledgerScanner.nextLine().trim();

            ArrayList<Transaction> allTransactions = readFromCSV();

            //switch statement for the Ledger Screen based off what the user chooses
            switch (userChoice.toUpperCase()) {
                case "A":
                    //display all entries
                    for (Transaction t : allTransactions) {
                        System.out.println(t);
                    }
                    break;
                case "D":
                    //display only the entries that are deposits into the account
                    for (Transaction t : allTransactions) {
                        if (t.getAmount() > 0) {
                            System.out.println(t);
                        }
                    }
                    break;
                case "P":
                    //display only the negative entries/payments
                    for (Transaction t : allTransactions) {
                        if (t.getAmount() < 0) {
                            System.out.println(t);
                        }
                    }
                    break;
                case "R":
                    //a new screen that allows the user to run pre-defined reports or to run a custom search
                    openReports();
                    break;
                case "H":
                    //go back to Home Screen
                    viewingLedger = false;
                    break;
            }
        }
    }
    public static void openReports(){
        //-1) Month To Date: display transactions from this month WILL NEED SWITCH STATEMENT
        //-2) Previous Month: display transactions from last month
        //-3) Year To Date: display transactions from this year
        //-4) Previous Year: display transactions from last year
        //-5) Search By Vendor: prompt user for the vendor name and display all entries for that vendor
        //-0) Back: go back to Ledger page
    }
}
