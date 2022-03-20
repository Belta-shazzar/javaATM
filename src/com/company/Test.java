package com.company;

import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.company.AbstClass.*;
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

    public String setgmail() {
        String gmail;
        do {
            System.out.print("Enter gmail: ");
            gmail = this.input.nextLine();

            Pattern pat = Pattern.compile("[a-z][a-z0-9]*@gmail.com");
            Matcher mat = pat.matcher(gmail);

            if (!(mat.find() && mat.group().equals(gmail))) {
                System.out.println(TEXT_RED + "Invalid gmail!" + TEXT_RESET);
                gmail = null;
            }

        } while (gmail == null);

        return gmail;
    }

    public String setUpNumber() {
        String phoneNumber;
        do {
            System.out.print("Enter phone number: 234");
            String enteredPhoneNumb = this.input.nextLine();

            Pattern pat = Pattern.compile("(0)?[7-9](0|1)[0-9]{8}");
            Matcher mat = pat.matcher(enteredPhoneNumb);

            if (mat.find() && mat.group().equals(enteredPhoneNumb)) {
                if (enteredPhoneNumb.startsWith("0")) {
                    phoneNumber = "+234" + enteredPhoneNumb.substring(1);
                } else {
                    phoneNumber = "+234" + enteredPhoneNumb;
                }
            } else {
                System.out.println(TEXT_RED + "Invalid phone number!" + TEXT_RESET);
                phoneNumber = null;
            }

        } while (phoneNumber == null);

        return phoneNumber;
    }

    public short setPass() {
        short pin = 0;
        do {
            try {
                System.out.print(TEXT_RED + "* pin must be digits of exactly length of 4." + TEXT_RESET +
                        "\nEnter pin: ");
                short enteredPin = this.input.nextShort();
//                String passwordCheck = Integer.toString(password);
                if (Integer.toString(enteredPin).length() != 4) {
//                    errorMsg();
                    System.out.println("Error message");
                    System.out.println(TEXT_RED + "Pin must be in 4 digits." + TEXT_RESET);
                    pin = 0;
                } else {
                    System.out.print("Re-enter pin: ");
                    short confirmPin = this.input.nextShort();
                    if (enteredPin == confirmPin) {
                        pin = enteredPin;
                    } else {
                        System.out.println(TEXT_RED + "Pin doesn't match" + TEXT_RESET);

                        pin = 0;
                    }
                }
            } catch (InputMismatchException ex) {
//                errorMsg();
                System.out.println("Error message");
            }
            input.nextLine();
        } while (pin == 0);
        return pin;
    }

//  trying out generic
    public String alertMsg(T... text) {
        String message = TEXT_BLUE + text + ": Alert type: " + text + text + text + "\n " + TEXT_RESET;
        return message;
    }

    public static void main(String[] args) throws ParseException {
        Test op = new Test();

//        System.out.println(op.alertMsg(1, 2, "Sam", "Ade"));

        System.out.println(op.setUpNumber());
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
