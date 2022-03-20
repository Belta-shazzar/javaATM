package com.company;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.company.AbstClass.TEXT_RED;
import static com.company.AbstClass.TEXT_BLUE;
import static com.company.AbstClass.TEXT_RESET;

public class NewAccount {
    Scanner input;
    ArrayList<Customers> customersList;
    Customers customer;

    public NewAccount() {
        this.input = new Scanner(System.in);
        this.customersList = new ArrayList<>();
    }

    public void errorMsg() {
        System.out.println(TEXT_RED + "Invalid input!" + TEXT_RESET);
    }

    public String phoneNumber() {
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

    public Date forDob() throws ParseException {
        Scanner input = new Scanner(System.in);
        Date date = null;
        do {
            try {
                System.out.print("Enter birth year: ");
                short year = this.input.nextShort();
                System.out.print("Enter birth month: ");
                byte month = this.input.nextByte();
                System.out.print("Enter birth day: ");
                byte day = this.input.nextByte();

//                get present year
                short presentYear = (short) Calendar.getInstance().get(Calendar.YEAR);
                if ((month < 1) || (month > 12)) { //Check if month entered exists on the calendar
                    errorMsg();
                } else {
//                    check for months with 31 days
                    if (((month == 01) || (month == 03) || (month == 5) || (month == 7) || (month == 8) || (month == 10) || (month == 12)) && ((day < 1) || (day > 31)) ){
                            errorMsg();
                    } else if (((month == 04) || (month == 06) || (month == 9) || (month == 11)) && ((day < 1) || (day > 30))) { //check for month with 30 days
                        errorMsg();
                    } else if ((month == 02) && ((day < 1) || (day > 28))) { //check for month with 28 days
                        errorMsg();
                    } else {
                        if (year < 1900) {
                            System.out.println("Sorry, the year entered cannot be accepted.");
                            date = null;
                        } else if (year >= (presentYear - 18)) { //Calculates to check if the user is eligible for an
                            // account
                            System.out.println(TEXT_RED + "Sorry, to have an account with us, you must be above the " +
                                    "age of " +
                                    "17." + TEXT_RESET);
                            date = null;
                        } else {
                            String dob = day + "/" + month + "/" + year;
                            date = new SimpleDateFormat("dd/MM/yyyy").parse(dob);
//                        new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(date);
                        }
                    }
                }
            } catch (InputMismatchException ex) {
                System.out.println("Invalid entry!");
                errorMsg();
            }
            input.nextLine();
        } while (date == null);
        return date;
    }

    public String forGmail() {
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

    public long generateAccountNumber() {
        // It will generate 6 digit random Number.
        // from 0 to 999999
        long number;
        do {
            Random rnd = new Random();
            number = rnd.nextInt((int) 9999999999l);
        } while (Long.toString(number).length() != 9);
        // this will convert any number sequence into 6 character.
//        return String.format("%09d", number);
        return number;
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
                    errorMsg();
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
                errorMsg();
            }
            input.nextLine();
        } while (pin == 0);
        return pin;
    }

    public void openAccount() {
        System.out.print("Enter Name: ");
        String accName = this.input.nextLine();

        String pNumber = phoneNumber();

        String email = forGmail();

        SimpleDateFormat DateFor = new SimpleDateFormat("dd-MM-yyyy");
        String stringDate= null;
        try {
            stringDate = DateFor.format(forDob());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String dob = stringDate;

        long accNumb = generateAccountNumber();

        System.out.println(TEXT_BLUE + "\nDear " + accName + ", Your account opening is still in progress..." + TEXT_RESET);
        System.out.println(TEXT_BLUE + "Your account number is " + accNumb + TEXT_RESET);

        short password = setPass();

        customer = new Customers(accName, pNumber, dob, email, password, accNumb);
        createConnection(customer.getName(), customer.getPhoneNumber(), customer.getDob(),
                customer.getEmail(), customer.getAccountNumber(), customer.getAccountBal(), customer.getPassword());
        this.customersList.add(customer);
        System.out.println(customersList);
        System.out.println(TEXT_BLUE + "Account open, successful!" + TEXT_RESET);
    }

    void createConnection(String accName, String phoneNumber, String dob, String email, long accNumber,
                          int accBal, short password) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/java_bank", "shazzar", "Las" +
                    "-jefa5");
            Statement statement = con.createStatement();

            statement.executeUpdate("INSERT INTO customers(account_name, phone_number, dob, email, " +
                    "account_number, " +
                    "account_balance, password) " +
                    "VALUES('" + accName + "', '" + phoneNumber + "', '" + dob + "', '" + email + "', " +
                    accNumber + ", " + accBal + ", " + password + ");");
            con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
//        System.out.println("Database insertion success!");
        System.out.println(TEXT_BLUE + "Database insertion successful!" + TEXT_RESET);

    }
}