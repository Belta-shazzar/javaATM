package com.company;

import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

import static com.company.AbstClass.TEXT_BLUE;
import static com.company.AbstClass.TEXT_RED;
import static com.company.AbstClass.TEXT_RESET;

public class Main {

    public static void main(String[] args) {
        NewAccount operation1 = new NewAccount();
        CustomerValidation operation2 = new CustomerValidation();
        Scanner input = new Scanner(System.in);

        byte category = 0;
        System.out.println("Hello.");
        do {
            try {
                System.out.print("1. New customer \n2. Existing customer \nSelect: ");
                category = input.nextByte();

                if (category == 1) {
                    System.out.println(TEXT_BLUE + "Welcome to my bank of java" + TEXT_RESET);
                    operation1.openAccount();
                } else if (category == 2) {
                    operation2.validateCustomer();
                } else {
                    System.out.println("Entered number not in option.");
                }
            } catch (InputMismatchException ex) {
                System.out.println(TEXT_RED + "Invalid input! \n" + TEXT_RESET);
            }
            input.nextLine();
        } while (category == 0);

    }
}
