//package com.pluralsight;
//
//public class HoldExtraCode {

//try {
//
//          //create a file writer and set append to true so it adds to the file and not override its contents
//          FileWriter outPutFile = new FileWriter("src/main/resources/transactions.csv", true);
//
//        //create the buffered writer to write to file
//        BufferedWriter bufWriter = new BufferedWriter(outPutFile);
//
//        //create a date and time
//        LocalDateTime timeStamp = LocalDateTime.now();
//
//           //create the line to write to the file by concating the timestamp in the correct format a space and the action
//            bufWriter.write(timeStamp.format(timeStampFormatter) + " " + userSelection);
//
//        //make sure we have a new line in our file
//                bufWriter.newLine();
//
//        //close the buffered writer so it actually writes to the file
//                bufWriter.close();
//
//            } catch (Exception e) {
//
//        //if we run into an issue writing to the file, display this instead
//        System.out.println("Error writing to the file: " + e.getMessage());
//        }
////}
