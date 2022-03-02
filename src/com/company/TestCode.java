package com.company;

import java.util.Scanner;

public class TestCode {
    Scanner input = new Scanner(System.in);

    public class CharacterIsNumberOrDigitTest {
        public static void main(String[] args) {
            String str = "Tutorials123";
            for(int i=0; i < str.length(); i++) {
                Boolean flag = Character.isDigit(str.charAt(i));
                if(flag) {
                    System.out.println("'"+ str.charAt(i)+"' is a number");
                }
                else {
                    System.out.println("'"+ str.charAt(i)+"' is a letter");
                }
            }
        }
    }

    public static void check() {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter number: ");
        int numb = input.nextInt();

        if (numb > 5) {
            System.out.println("Pass");
        } else {
            check();
        }
    }
    public static String numberCheck(String pNumber) {
        String number = null;
        Boolean flag = true;
        String signs = "!@#$%^&*()_+=-'\";:[]}{|?/.,><\\";
        char hasSign = '\0';
//        char hasLetter = '\0';
        for (int i = 0;i < pNumber.length(); i++) {
            for (int j = 0; j < signs.length(); j++) {
                if (pNumber.charAt(i) == signs.charAt(j)) {
                    hasSign = signs.charAt(j);
                }
            }
        }

        if ((flag == false) || (hasSign != '\0')) {
            System.out.println("Invalid entry");
        } else {
            number = pNumber;
        }
        return number;
    }

    public static void main(String[] args) {
        int number = 636394682;

        System.out.println(Integer.toString(number).length());



    }
}
