package com.company;

import java.sql.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class CustomerValidation extends AbstClass{
    CustomerTransaction activeCustomer;
    Scanner input;
    public CustomerValidation() {
        this.input = new Scanner(System.in);
    }

    @Override
    public void errorMsg() {
        System.out.println(TEXT_RED + "Invalid input! \n" + TEXT_RESET);
    }

    @Override
    public String createConnection(long enteredAccNumb) {
        String accName = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/java_bank", "shazzar",
                    "Las-jefa5");
            Statement statement = con.createStatement();
            ResultSet resultForAccNumb =
                    statement.executeQuery("SELECT * FROM customers WHERE account_number = " + enteredAccNumb + ";");
            while (resultForAccNumb.next()) {

                accName = resultForAccNumb.getString("account_name");
                String phoneNum = resultForAccNumb.getString("phone_number");
                String dob = resultForAccNumb.getString("dob");
                String email = resultForAccNumb.getString("email");
                long accNumb = resultForAccNumb.getLong("account_number");
                int accBal = resultForAccNumb.getInt("account_balance");
                short password = resultForAccNumb.getShort("password");

                activeCustomer = new CustomerTransaction(accName, phoneNum, dob, email, password, accNumb);
                activeCustomer.setAccountBal(accBal);
//                activeCustomer.activeCustomerArray.add(activeCustomer);
//                System.out.println(activeCustomer.activeCustomerArray);

            }
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
        return accName;
    }


    public void validateCustomer() {
        try {
            System.out.print("Enter account number: ");
            long accNumb = this.input.nextLong();

            if (Long.toString(accNumb).length() == 9) {
//                Parse the account number to the createConnection method for check and returns the account name if
//                such account number exists on the database
                String accName = createConnection(accNumb);
                if (accName != null) {
                    System.out.println(accName);
//                    user authentication (2 times trial password)
                    short accPass = 0;
                    byte trial = 0;
                    for (; trial < 2; trial++) {
                        System.out.print("Enter password: ");
                        accPass = this.input.nextShort();
                        if ((Short.toString(accPass).length() == 4) && (accPass == activeCustomer.getPassword())) {
                            transactionOptions();
                            break;
                        } else {
                            System.out.println(TEXT_RED + "Incorrect password" + TEXT_RESET);
                        }
                    }
                    if (accPass == 0) {
                        System.out.println(TEXT_RED + "Transaction terminated" + TEXT_RESET);
                    }
                } else {
                    System.out.println("Account number does not exist");
                }
            } else {
                System.out.println(TEXT_RED + "Not a valid account number!" + TEXT_RESET);
            }
        } catch (InputMismatchException ex) {
            errorMsg();
            ex.printStackTrace();
        }
    }

    public void transactionOptions() {
        Scanner input = new Scanner(System.in);
        System.out.println("Good day. What transaction would you like to perform: ");
        String transactions ="1. Withdraw \n2. Check balance \n3. Transfer fund \n4. Deposit \n5. End transaction" +
                "\nTransaction no: ";

        byte perform = 0;
        do {
            try {
                System.out.print(transactions);
                perform = input.nextByte();
                switch (perform) {
                    case 1:
//                        System.out.println("Withdraw successful");
                        activeCustomer.withdrawal();
                        break;
                    case 2:
//                        System.out.println("Balance check successful");
                        activeCustomer.checkBal();
                        break;
                    case 3:
//                        System.out.println("Transfer successful");
                        activeCustomer.transferFunds();
                        break;
                    case 4:
//                        System.out.println("Deposit successful");
                        activeCustomer.deposit();
                        break;
                    case 5:
                        System.out.println("Thank you for banking with us.");
                        break;
                    default: {
                        System.out.println(TEXT_RED + "Number not in option" + TEXT_RESET);
                        break;
                    }
                }
            } catch (InputMismatchException ex) {
                errorMsg();
                ex.printStackTrace();
            }
            input.nextLine();
        } while (!(perform >= 1 || perform <= 5));
    }
}
//290375474



/*java.util.Map map = con.getTypeMap();
            map.put("mySchemaName.ATHLETES", Class.forName("Athletes"));
            con.setTypeMap(map);*/