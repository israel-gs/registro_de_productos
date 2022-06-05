package com.utp.registro_de_productos.provider;

import com.utp.registro_de_productos.core.Query;
import com.utp.registro_de_productos.model.UserModel;
import java.sql.ResultSet;

public class LoginProvider {

    Query query = new Query();

    public UserModel getUserIfPasswordValid(String username, String password) {
        UserModel user = new UserModel();
        try {
            ResultSet rs = query.query(""
                    + "select dni, "
                    + "       name, "
                    + "       password, "
                    + "       username, "
                    + "       isAdmin "
                    + "from "
                    + "    registro_de_productos.user "
                    + "where "
                    + "    dni = '" + username + "' and "
                    + "    password = '" + password + "';");
            while (rs.next()) {
                user.setDni(rs.getString("dni"));
                user.setName(rs.getString("name"));
                user.setPassword(rs.getString("password"));
                user.setIsAdmin(rs.getBoolean("isAdmin"));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return user;
    }

}
