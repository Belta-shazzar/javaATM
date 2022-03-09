package com.company;

import java.sql.*;

public abstract class AbstClass {
    public static final String TEXT_RESET = "\u001B[0m";
    public static final String TEXT_RED = "\u001B[31m";
    public static final String TEXT_BLUE = "\u001B[34m";
//    public abstract long generateDigit();
    public abstract void errorMsg();

    public abstract String createConnection(long enteredAccNumb);

//    public abstract T createConnection(T parameter);

//    public abstract void createConnection(long enteredAccNumb);
}
