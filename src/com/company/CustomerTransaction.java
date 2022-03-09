package com.company;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

import static com.company.AbstClass.TEXT_BLUE;
import static com.company.AbstClass.TEXT_RESET;

public class CustomerTransaction extends Customers {
    public CustomerTransaction(String name, String phoneNumber, String dob, String email, short password, Long accountNumber) {
        super(name, phoneNumber, dob, email, password, accountNumber);
    }

    CustomerValidation repeat = new CustomerValidation();
    Scanner input = new Scanner(System.in);

    public String alertMsg(String phoneNumb, String alertType, String transactionType, int amount, int balance) {
        return TEXT_BLUE + phoneNumb + "\nAlert type: " + alertType +
                "\nTransaction type: " + transactionType + "\n"
                + transactionType + " amount: " + amount +
                "\nAccount balance: " + balance + "\n" + TEXT_RESET;
    }

//    Delete later, if not necessary
    public void newTransaction() {
        try {
            byte option;
            do {
                System.out.print("Would you like to perform another transaction \n1. yes \n2. No \nselect: ");
                option = this.input.nextByte();

                if (option == 1) {
                    repeat.validateCustomer();
                } else if (option == 2) {
                    System.out.println(TEXT_BLUE + "Thank you for banking with us." + TEXT_RESET);
                } else {
                    System.out.println("Invalid number entered");
                }
            } while (!(option != 1 || option != 2));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void withdrawal() {
        try {
            int amount;
            do {
                System.out.print("Enter withdrawal amount: ");
                amount = input.nextInt();
                if (amount % 500 != 0) {
                    System.out.println(TEXT_BLUE + "Enter amount in #500 denominations" + TEXT_RESET);
                }
            } while (amount % 500 != 0);
            if (getAccountBal() < 500) {
                System.out.println(TEXT_BLUE + "Insufficient funds" + TEXT_RESET);
            } else {
                int newBal = getAccountBal() - amount;
                setAccountBal(newBal);
                System.out.println("Please take your cash" + TEXT_BLUE + "\nWithdraw successful" + TEXT_RESET);
                System.out.println(alertMsg(getPhoneNumber(), "Debit", "Withdrawal", amount, getAccountBal()));
                createConnectionUpdate(newBal, getAccountNumber());
                newTransaction();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void checkBal() {
        float accBal = getAccountBal();
        System.out.println(TEXT_BLUE + "Balance: " + accBal + TEXT_RESET);
        newTransaction();
    }

    public void transferFunds() {
        try {
            System.out.print("Enter recipient's account number: ");
            long accNumb = this.input.nextLong();
            if (Long.toString(accNumb).length() == 9) {
                createConnection(accNumb);

                System.out.print("Enter transfer amount: ");
                int transferAmt = this.input.nextInt();

                if (transferAmt < getAccountBal()) {
                    byte toContinue = 0;
                    do {
                        System.out.print(TEXT_BLUE + "Amount of " + transferAmt + " to be transfered to \n" +
                                recipient.getName() + "\n" + recipient.getAccountNumber() + "\n1. Continue \n2. Cancel \n" +
                                " Select: " + TEXT_RESET);
                        toContinue = this.input.nextByte();

                        if (toContinue == 1) {
                            int newBal = getAccountBal() - transferAmt;
                            int recipientNewBal = recipient.getAccountBal() + transferAmt;

                            setAccountBal(newBal);
                            recipient.setAccountBal(recipientNewBal);

//                            Active customr's alert message
                            System.out.println(alertMsg(getPhoneNumber(), "Debit", "Transfer", transferAmt,
                                    getAccountBal()));

//                            Recipient's alert message
                            System.out.println(alertMsg(recipient.getPhoneNumber(), "Credit", "Transfer", transferAmt,
                                    recipient.getAccountBal()));

                            createConnectionUpdate(newBal, getAccountNumber());
                            createConnectionUpdate(recipientNewBal, recipient.getAccountNumber());

                            newTransaction();
                        } else if (toContinue == 2) {
                            System.out.println("Transaction terminated.");
                        } else {
                            System.out.println("Invalid entry");
                        }
                    } while (!(toContinue == 1 || toContinue == 2));

                } else {
                    System.out.println(TEXT_BLUE + "Insufficient funds" + TEXT_RESET);
                }
            } else {
                System.out.println(TEXT_RED + "Not a valid account number!" + TEXT_RESET);
            }

//        System.out.println(accName);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public void deposit() {
        try {
            int depositAmount = 0;
            do {
                System.out.print("Enter amount to be deposited: ");
                depositAmount = this.input.nextInt();

                if ((depositAmount % 10 == 0) && (depositAmount > 0)) {
                    int newBal = getAccountBal() + depositAmount;
                    setAccountBal(newBal);
                    createConnectionUpdate(getAccountBal(), getAccountNumber());
                    System.out.println(TEXT_BLUE + "Deposit successful." + TEXT_RESET);

                    newTransaction();
                } else {
                    System.out.println(TEXT_RED + "Invalid" + TEXT_RESET);
                }
            } while (depositAmount == 0);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void createConnectionUpdate(int currentBal, long accNumber) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/java_bank", "shazzar", "Las" +
                    "-jefa5");
            Statement statement = con.createStatement();

//            UPDATE `java_bank`.`customers` SET `account_balance` = '100000' WHERE (`id` = '13');
            statement.executeUpdate("UPDATE java_bank.customers SET account_balance = " + currentBal + " WHERE " +
                    "account_number = " + accNumber + ";");
            con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
//        System.out.println("Database insertion success!");
//        System.out.println(TEXT_BLUE + "Database insertion successful!" + TEXT_RESET);

    }
}
