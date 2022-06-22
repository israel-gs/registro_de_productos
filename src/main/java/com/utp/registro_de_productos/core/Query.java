package com.utp.registro_de_productos.core;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Query {

    private Connection connection;

    public int update(String query, String metodo) throws Exception {
        int resp = 0;
        System.out.println(metodo + "----->" + query);
        try {
            connection = MySqlConnection.createConnection();
            Statement st = connection.createStatement();
            resp = st.executeUpdate(query);
        } finally {
            connection.close();
        }
        return resp;
    }

    public int update(String query) throws Exception {
        int resp = 0;
        resp = update(query, "");
        return resp;
    }

    public ResultSet query(String query, String metodo) throws Exception {
        ResultSet rs;
        connection = MySqlConnection.createConnection();
        Statement st = connection.createStatement();
        rs = st.executeQuery(query);
        System.out.println(metodo + "----->" + query);
        return rs;
    }

    public ResultSet query(String query) throws Exception {
        ResultSet rs;
        rs = query(query, "");
        return rs;
    }
}
