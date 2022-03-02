package com.company;

public class ProjectStructure {
    /*
     * OOP CONCEPTS
     *
     *Abstraction:
     *Encapsulation
     *Polymorphism
     *Inheritance
     */
    /*Customers variable*/
    /*
     * Account name   - Universal
     * Account number - For funds transfer
     * Customer id    - Account access
     * Account balance
     * DOB
     * Customer phone number - Update text
     * email
     * password     - Account access
     */


    /* Program structure*/
    /*
     * On run, the user is asked to - 1. New customer
     *                                2. Existing customer
     * If (1 is selected)
     *   Output text "Welcome to my bank of java"
     *   Ask user for the following inputs
     *       String: "Enter name: "
     *       String: "Enter phone number: "
     *       DOB
     *       Email
     *   Auto-generates
     *       Customer id: Auto generate;
     *       Account number: Auto generate;
     *
     *   Output
     *       "Dear " + name + ", Your account opening is still in progress...";
     *  us"
     *       "Your account number is " + accountNumber;
     *   Asks input:
     *       String: "Enter password: ";
     *       String: "Re-enter password: ";
     *
     *   If(Enter password == Re-enter password)
     *       All collected details should be organized and added to a DB or an array.
     *       Output: "Account creation successful! \n
     *       "Your ID is " + usedId;
     *   Else:
     *       Output: "Password doesn't match.
     *       re-enter password.
     *
     *
     * If(Selected == 2)
     *       Ask user for input: "Enter id: ";
     *       Check the DB for matching id
     *       if (DB does not contain id)
     *           Output: "Entered id is not recognized"
     *               "Enter 1 to open an account with us, enter 2 to exit program."
     *               if(input is 1)
     *                   call the create account method.
     *               else {
     *                   exit program.
     *               }
     *       if (DB contains entered id)
     *           get the account name matching the id
     *           Output: "Dear (account name)"
     *           Ask user: "Enter password: "
     *           if (password doesn't match password of the id)
     *               Output: "Wrong password, try again";
     *               //if the password is wrong after the second try, end program.
     *
     *           if (password matches the id password)
     *               Output: Select transaction...
     *               Ask user:   1. Withdraw
     *                           2. Check balance
     *                           3. Transfer fund
     *                           4. Deposit
     *                           5. Recharge
     *
     *               Switch (input) {
     *                   case 1:
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     * */

}
