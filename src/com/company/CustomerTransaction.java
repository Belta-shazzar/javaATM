package com.company;

import java.util.ArrayList;
import java.util.Scanner;

import static com.company.AbstClass.TEXT_BLUE;
import static com.company.AbstClass.TEXT_RESET;

public class CustomerTransaction extends Customers {
    ArrayList<Customers> activeCustomerArray = new ArrayList<>();
    Scanner input = new Scanner(System.in);

    public void withdrawal() {
        try {
            System.out.print("Enter withdrawal amount: ");
            int amount = input.nextInt();
            do {
                if (amount % 500 != 0) {
                    System.out.println(TEXT_BLUE + "Enter amount in #500 denominations" + TEXT_RESET);
                }
            } while (amount % 500 != 0);
            if (getAccountBal() < 500) {
                System.out.println(TEXT_BLUE + "Insufficient funds" + TEXT_RESET);
            } else {
                System.out.println("Please take your cash");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public CustomerTransaction(String name, String phoneNumber, String dob, String email, short password, Long accountNumber) {
        super(name, phoneNumber, dob, email, password, accountNumber);
    }
}
