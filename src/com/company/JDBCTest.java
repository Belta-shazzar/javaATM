package com.company;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JDBCTest {
    public static void main(String[] args) throws SQLException {
        JDBCTest pro = new JDBCTest();
        pro.createConnection();
    }
    void createConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/java_bank", "shazzar", "Las" +
                    "-jefa5");
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM customers;");
            while (resultSet.next()) {
                String name = resultSet.getString("account_name");
//                String name = resultSet.getString("last_name") + " " + resultSet.getString("first_name");
                System.out.println(name);
            }
            System.out.println("Database creation success.");
        } catch (ClassNotFoundException | SQLException e) {
            Logger.getLogger(JDBCTest.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}
