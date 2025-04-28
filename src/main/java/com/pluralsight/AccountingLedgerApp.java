package com.pluralsight;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.util.Scanner;

public class AccountingLedgerApp {

    //scanner allows user input
    static Scanner ledgerScanner = new Scanner(System.in);

    public static void main(String[] args) {

        //while loop will run until user chooses to exit the application
        boolean appRunning = true;

        while (appRunning) {
            //home screen (runs until user chooses to exit the application)
            System.out.println("Welcome to Alondra's Prestigious Accounting Ledger Application! " +
                    "\nWhere you can track all your financial transactions");
            System.out.println("===================================================");
            //D) Add Deposit: prompt user for the debit information and save it in the csv file
            //P) Make Payment (Debit): prompt user for the debit information and to save it in the csv file
            //L) Ledger: display the ledger screen
            //X) Exit: exit the application
            //display user selection options
            System.out.println("D) Add Deposit " +
                    "\nP) Make Payment (Debit) " +
                    "\nL) Ledger " +
                    "\nX) Exit ");
            System.out.print("Make a selection: ");
            String userSelection = ledgerScanner.nextLine().trim(); //trims extra characters

            //if statement for if the user wants to exit the application
            if (userSelection.equalsIgnoreCase("X")) {
                System.out.println("You are now exiting the application, " +
                        "\nThank you for choosing Alondra's Prestigious Accounting Ledger Application. " +
                        "\nHave a wonderful day!");
                appRunning = false;
            }
//            //else statement if they don't want to exit
//            else {
//
//            }
            //ledger screen (all entries should show the newest entries first)
            //A) All: display all entries
            //D) Deposits: display only the entries that are deposits into the account
            //P) Payments: display only the negative entries/payments
            //R) Reports: a new screen that allows the user to run pre-defined reports or to run a custom search
            //-1) Month To Date
            //-2) Previous Month
            //-3) Year To Date
            //-4) Previous Year
            //-5) Search By Vendor: prompt user for the vendor name and display all entries for that vendor
            //-0) Back: go back to Reports page
            //H) Home: go back to home screen
            try {

                //create a file writer and set append to true so it adds to the file
                //and not override its contents
                FileWriter outPutFile = new FileWriter("src/main/resources/transactions.csv", true);

                //create the buffered writer to write to the log file
                BufferedWriter bufWriter = new BufferedWriter(outPutFile);

                //create a date and time
                LocalDateTime timeStamp = LocalDateTime.now();

//            //create the line to write to the log by concating the timestamp in the correct format a space and the action
//            bufWriter.write(timeStamp.format(timeStampFormatter) + " " + theAction);

                //make sure we have a new line in our file
                bufWriter.newLine();

                //close the buffered writer so it actually writes to the file
                bufWriter.close();

            } catch (Exception e) {

                //if we run into an issue writing to the file, display this instead
                System.out.println("Error writing to the file: " + e.getMessage());
            }
        }
    }
}

