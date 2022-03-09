package com.company;

import java.sql.*;
import java.util.Date;

public class Customers extends AbstClass {
//    Right here is an instance of the customer class representing the fund receiving customer
    CustomerTransaction recipient;

    private String name;
    private String phoneNumber;
    private String dob;
    private String email;
    private long accountNumber;
    private int accountBal;
    private short password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public int getAccountBal() {
        return accountBal;
    }

    public void setAccountBal(int accountBal) {
        this.accountBal = accountBal;
    }

    public short getPassword() {
        return password;
    }

    public void setPassword(short password) {
        this.password = password;
    }

    public Customers(String name, String phoneNumber, String dob, String email, short password, Long accountNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.dob = dob;
        this.email = email;
        this.password = password;
        this.accountNumber = accountNumber;
        this.accountBal = 0;
    }

    @Override
    public String toString() {
        return ", name = " + name + '\n' +
                ", phoneNumber = " + phoneNumber + '\n' +
                ", dob = " + dob + '\n' +
                ", email = " + email + '\n' +
                ", accountNumber = " + accountNumber + '\n' +
                ", accountBal = " + accountBal + '\n' +
                ", password = " + password + "\n";
    }

    @Override
    public void errorMsg() {

    }

//    createConnection here fetches fund receiving customer's details.
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

                recipient = new CustomerTransaction(accName, phoneNum, dob, email, password, accNumb);
                recipient.setAccountBal(accBal);
//                System.out.println(activeCustomer.activeCustomerArray);

            }
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
        return accName;
    }
}
