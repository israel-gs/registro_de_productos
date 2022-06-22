package com.utp.registro_de_productos.provider;

import com.utp.registro_de_productos.core.Query;
import com.utp.registro_de_productos.model.EmployeeModel;
import com.utp.registro_de_productos.model.UserModel;
import io.vavr.control.Either;
import java.sql.ResultSet;

public class LoginProvider {

    Query query = new Query();

    public Either<String, UserModel> getUserIfPasswordValid(String username, String password) {
        UserModel user = null;
        try {
            ResultSet rs = query.query("""
                            select
                                   u.id,
                                   u.password,
                                   u.username,
                                   u.isAdmin,
                                   e.id,
                                   e.name,
                                   e.lastname,
                                   e.documentType,
                                   e.documentNumber,
                                   e.code,
                                   e.hasExperience,
                                   e.birthday,
                                   e.hasChildren,
                                   e.childrensNumber,
                                   e.maritalStatus
                            from registro_de_productos.user u
                                     inner join employee e on u.employeeId = e.id
                            where u.username like '""" + username + "';");
            String message = "El usuario no existe";
            while (rs.next()) {
                String dbPassword = rs.getString("password");
                if (password.equals(dbPassword)) {
                    user = new UserModel();
                    EmployeeModel employeeModel = new EmployeeModel();
                    user.setId(rs.getString("id"));
                    user.setUsername(rs.getString("username"));
                    user.setPassword(rs.getString("password"));
                    user.setIsAdmin(rs.getBoolean("isAdmin"));
                    employeeModel.setId(rs.getString("id"));
                    employeeModel.setName(rs.getString("name"));
                    employeeModel.setLastname(rs.getString("lastname"));
                    employeeModel.setDocumentType(rs.getString("documentType"));
                    employeeModel.setDocumentNumber(rs.getString("documentNumber"));
                    employeeModel.setCode(rs.getString("code"));
                    employeeModel.setHasExperience(rs.getBoolean("hasExperience"));
                    employeeModel.setBirthday(rs.getDate("birthday"));
                    employeeModel.setHasChildren(rs.getBoolean("hasChildren"));
                    employeeModel.setChildrensNumber(rs.getInt("childrensNumber"));
                    employeeModel.setMaritalStatus(rs.getString("maritalStatus"));
                    user.setEmployee(employeeModel);
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
