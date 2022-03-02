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
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo", "shazzar", "Las-jefa5");
            Statement statement = con.createStatement();

           /* statement.executeUpdate("INSERT INTO employees(id, last_name, first_name, email, department, salary) " +
                    "VALUES(14, 'Bim', 'Shasha', 'shasha.bim@f00.com', 'Acting', 90000);");
            con.close();*/
//            ResultSet put = statement.executeQuery("INSERT INTO users(name) \nVALUES('Chinasa'), ('Joshua'), " +
//                    "('Dominic');");
            ResultSet resultSet = statement.executeQuery("SELECT * FROM employees;");
            while (resultSet.next()) {
                String name = resultSet.getString("last_name") + " " + resultSet.getString("first_name");
                System.out.println(name);
            }
            System.out.println("Database creation success.");
        } catch (ClassNotFoundException | SQLException e) {
            Logger.getLogger(JDBCTest.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}
