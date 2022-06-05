package com.utp.registro_de_productos.core;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqlConnection {

    public static Connection createConnection() {
        String url = "jdbc:mysql://127.0.0.1:3306/registro_de_productos";
        String user = "root";
        String password = "S0p0rt3.";
        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            return connection;
        } catch (SQLException e) {
            return null;
        }
    }
}
