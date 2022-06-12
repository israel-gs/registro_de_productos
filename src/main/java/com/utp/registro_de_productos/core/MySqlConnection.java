package com.utp.registro_de_productos.core;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqlConnection {
    
    public static String db = "registro_de_productos";

    public static Connection createConnection() {
        String url = "jdbc:mysql://127.0.0.1:3306/registro_de_productos";
        // String url = "jdbc:mysql://root:iOYBf3sIDzm5Md007CN5@containers-us-west-70.railway.app:7210/railway";
        String user = "root";
        // String password = "iOYBf3sIDzm5Md007CN5";
        String password = "S0p0rt3.";
        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            return connection;
        } catch (SQLException e) {
            return null;
        }
    }
}
