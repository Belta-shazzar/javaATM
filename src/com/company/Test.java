package com.company;

import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.company.AbstClass.TEXT_BLUE;
import static com.company.AbstClass.TEXT_RESET;

public class Test<T> {
    Scanner input = new Scanner(System.in);
    public static final String TEXT_RESET = "\u001B[0m";
    public static final String TEXT_BLACK = "\u001B[30m";
    public static final String TEXT_RED = "\u001B[31m";
    public static final String TEXT_GREEN = "\u001B[32m";
    public static final String TEXT_YELLOW = "\u001B[33m";
    public static final String TEXT_BLUE = "\u001B[34m";
    public static final String TEXT_PURPLE = "\u001B[35m";
    public static final String TEXT_CYAN = "\u001B[36m";
    public static final String TEXT_WHITE = "\u001B[37m";

    //    Account number generation method.
    public long generateAccountNumber() {
        // It will generate 6 digit random Number.
        // from 0 to 999999
        Random rnd = new Random();
        long number = rnd.nextInt(999999999);

        // this will convert any number sequence into 6 character.
//        return String.format("%09d", number);
        return number;
    }

    //    DOB method.
    public static Date forDob() throws ParseException {
        Scanner input = new Scanner(System.in);
        Date date = null;
        do {
            try {
                System.out.print("Enter birth year: ");
                short year = input.nextShort();
                System.out.print("Enter birth month: ");
                byte month = input.nextByte();
                System.out.print("Enter birth day: ");
                byte day = input.nextByte();

                int presentYear = Calendar.getInstance().get(Calendar.YEAR);
                if ((month < 1) || (month > 12) || (day < 1) || (day > 31)) {
                    System.out.println("Invalid entry!");
                } else {
                    if (year < 1900) {
                        System.out.println("Sorry, the year entered cannot be accepted.");
                        date = null;
                    } else if (year >= (presentYear - 18)) {
                        System.out.println("Sorry, to have an account with us, you must be above the age of 17.");
                        date = null;
                    } else {
                        String dob = day + "/" + month + "/" + year;
                        date = new SimpleDateFormat("dd/MM/yyyy").parse(dob);
                        new SimpleDateFormat("dd-MM-yyyy").format(date);
                    }
                }
            } catch (InputMismatchException ex) {
                System.out.println("Invalid entry!");
            }
            input.nextLine();
        } while (date == null);
        return date;
    }

    //    Email method
    public  String forEmail() {
        Scanner input = new Scanner(System.in);
        String email;
        do {
            System.out.print("Enter email: ");
            String enteredEmail = input.nextLine();
            email = enteredEmail.toLowerCase();
            if (!((email.contains("@") && email.endsWith(".com")))) {
                System.out.println("Email is invalid!");
                email = null;
            }
        } while (email == null);
        return email;
    }

//  trying out generic
    public String alertMsg(T... text) {
        String message = TEXT_BLUE + text + ": Alert type: " + text + text + text + "\n " + TEXT_RESET;
        return message;
    }

    public static void main(String[] args) throws ParseException {
        Test op = new Test();

        System.out.println(op.alertMsg(1, 2, "Sam", "Ade"));

// Implementation
//        System.out.println(TEXT_RED + "This text is red!" + TEXT_RESET);
//        System.out.println("This text is red ends here!");
//        System.out.println(TEXT_RED + "This text is red!");
//        System.out.println("This text is red suppose end for here sha!");

//        Scanner input = new Scanner(System.in);
//        System.out.print("Enter password: ");
//        int pass = input.nextInt();
//
//        TextField password = new TextField(4);
//        password.setEchoChar('*');




//        String password = PasswordField.readPassword("Enter password: ");
//        System.out.println("The password entered is: "+password);




    }
}
