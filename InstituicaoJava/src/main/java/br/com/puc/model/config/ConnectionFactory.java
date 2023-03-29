package br.com.puc.model.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    private ConnectionFactory() {
    }

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection("jdbc:mysql://localhost/InstituicaoDB",
                    "root",
                    "fera7eric");
        } catch (SQLException var1) {
            throw new RuntimeException(var1);
        }
    }
}
