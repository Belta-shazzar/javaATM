package com.company;

import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

import static com.company.NewAccount.TEXT_BLUE;
import static com.company.Test.TEXT_RED;
import static com.company.Test.TEXT_RESET;

public class Main {

    public static void main(String[] args) {
        NewAccount operation = new NewAccount();
        Scanner input = new Scanner(System.in);

        byte category = 0;
        System.out.println("Hello.");
        do {
            try {
                System.out.print("1. New customer \n2. Existing customer \nSelect: ");
                category = input.nextByte();
            } catch (InputMismatchException ex) {
                System.out.println(TEXT_RED + "Invalid input! \n" + TEXT_RESET);
            }
            input.nextLine();
        } while (category == 0);

        if (category == 1) {
            System.out.println(TEXT_BLUE + "Welcome to my bank of java" + TEXT_RESET);
            operation.openAccount();
        } else if (category == 2) {
            System.out.println("Dear customer, what transaction will you like to perform?");
        } else {
            System.out.println("Entered number not in option.");
        }
    }
}
