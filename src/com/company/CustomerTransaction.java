package com.company;

import java.util.ArrayList;

public class CustomerTransaction extends Customers {
    ArrayList<Customers> activeCustomerArray = new ArrayList<>();

    public CustomerTransaction(String name, String phoneNumber, String dob, String email, short password, Long accountNumber) {
        super(name, phoneNumber, dob, email, password, accountNumber);
    }
}
