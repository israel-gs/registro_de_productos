package com.utp.registro_de_productos.provider;

import com.utp.registro_de_productos.core.Query;
import com.utp.registro_de_productos.model.UserModel;
import io.vavr.control.Either;
import java.sql.ResultSet;

public class LoginProvider {

    Query query = new Query();

    public Either<String, UserModel> getUserIfPasswordValid(String username, String password) {
        UserModel user = null;
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
                    + "    dni = '" + username + "';");
            String message = "El usuario no existe";
            while (rs.next()) {
                String dbPassword = rs.getString("password");
                if (password.equals(dbPassword)) {
                    user = new UserModel();
                    user.setDni(rs.getString("dni"));
                    user.setName(rs.getString("name"));
                    user.setPassword(dbPassword);
                    user.setIsAdmin(rs.getBoolean("isAdmin"));
                } else {
                    message = "Contraseña incorrecta";
                }
            }
            if (user != null) {
                return Either.right(user);
            } else {
                return Either.left(message);
            }
        } catch (Exception e) {
            System.out.println(e);
            return Either.left("Ocurrió un error");
        }
    }

}
