package com.company;

import java.util.Date;

public class Customers {
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
}
