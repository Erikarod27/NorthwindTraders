package com.pluralsight;

import java.sql.*;
import java.util.Scanner;
import javax.sql.DataSource;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String url = "jdbc:mysql://127.0.0.1:3306/northwind";
        String username = "root";
        String password = args[0];

        System.out.println("What do you want to do?");
        System.out.println("1) Display all products");
        System.out.println("2) Display all customers");
        System.out.println("0) Exit");
        int choice = scanner.nextInt();

        try {
            Connection connection;
            connection = DriverManager.getConnection(url, username, password);

            Statement statement = connection.createStatement();

            if (choice == 1) {
                String query = "SELECT ProductID, ProductName, UnitPrice, UnitsInStock FROM Products";

                ResultSet results = statement.executeQuery(query);

                while(results.next()) {
                    System.out.println("Product ID: " + results.getInt(1));
                    System.out.println("Name: " + results.getString(2));
                    System.out.println("Price: " + results.getDouble(3));
                    System.out.println("Stock: " + results.getInt(4));
                    System.out.println("------------------");
                }
            } else if (choice == 2) {
                String query = "SELECT ContactName, CompanyName, City, Country, Phone FROM Customers";

                ResultSet results = statement.executeQuery(query);
                while(results.next()) {
                    System.out.println("Contact ID: " + results.getInt(1));
                    System.out.println("Name: " + results.getString(2));
                    System.out.println("Company: " + results.getString(3));
                    System.out.println("City: " + results.getString(4));
                    System.out.println("Country: " + results.getString(5));
                    System.out.println("Phone Number: " + results.getString(6));
                    System.out.println("------------------");
                }
            } else if (choice == 0) {
                System.out.println("Exiting...");
                System.exit(0);
            } else {
                System.out.println("Wrong choice. Try again.");
                return;
            }

            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
