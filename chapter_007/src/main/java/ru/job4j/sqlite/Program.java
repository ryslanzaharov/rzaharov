package ru.job4j.sqlite;

import java.sql.*;
import java.util.Scanner;

public class Program {

    Connection co;

    public static void main(String ... args) {
        Program program = new Program();
        program.open();
        program.insert();
        program.close();

    }

    public void open() {
        try {
        //    Class.forName("org.sqlite.JDBC");
            co = DriverManager.getConnection(
                    "jdbc:sqlite:C:\\sqlite\\users.db"
            );
            System.out.println("Connected");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void insert() {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter field:");
            Integer name = Integer.parseInt(scanner.nextLine());

            String query = "INSERT INTO TEST(field) values('"+ name +"')";
            Statement st = co.createStatement();
            st.executeUpdate(query);

            System.out.println("zbs");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void close() {
        try {
            co.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
