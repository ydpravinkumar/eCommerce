package com.example.supplychainback.model;

import java.sql.Connection;
import java.sql.DriverManager;


/**
 * Connect to my PostGreSQL data base manually using the sql.connection
 */

public class PostGreDatabase {
    public static void main(String args[]) {
    Connection connection = null;
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/supplychain", "postgres", "postgres");
        } catch (Exception exception) {
            exception.printStackTrace();
            System.err.println(exception.getClass().getName() + ": " + exception.getMessage());
            System.exit(0);
        }

        System.out.println("Opened database successfully");
    }
}



