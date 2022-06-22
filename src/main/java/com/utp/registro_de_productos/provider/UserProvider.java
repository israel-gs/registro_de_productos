package com.utp.registro_de_productos.provider;

import com.utp.registro_de_productos.core.Query;
import com.utp.registro_de_productos.model.EmployeeModel;
import com.utp.registro_de_productos.model.UserModel;
import io.vavr.control.Either;
import java.sql.ResultSet;
import java.util.ArrayList;

public class UserProvider {

    Query query = new Query();

    public Either<String, ArrayList<UserModel>> getUsers() {
        ArrayList<UserModel> users = new ArrayList<>();
        try {
            ResultSet rs = query.query("""
                            select
                                   u.id,
                                   u.password,
                                   u.username,
                                   u.isAdmin,
                                   e.id employeeId,
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
                            inner join employee e on u.employeeId = e.id;""");
            while (rs.next()) {
                UserModel user = null;
                user = new UserModel();
                EmployeeModel employeeModel = new EmployeeModel();
                user.setId(rs.getString("id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setIsAdmin(rs.getBoolean("isAdmin"));
                employeeModel.setId(rs.getString("employeeId"));
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
                users.add(user);
            }
            return Either.right(users);
        } catch (Exception e) {
            System.out.println(e);
            return Either.left("Ocurrió un error");
        }
    }

    public Either<String, String> insertUser(UserModel user) {
        try {
            UserModel userExist = new UserModel();
            ResultSet rs = query.query(""
                    + "select id, password, username, isAdmin, employeeId "
                    + "from user "
                    + "where user.employeeId like '" + user.getEmployee().getId() + "';");

            while (rs.next()) {
                userExist.setId(rs.getString("id"));
            }

            if (userExist.getId() == null) {
                int result = query.update(""
                        + "insert into registro_de_productos.user (id, password, username, isAdmin, employeeId) "
                        + "values ("
                        + "'" + user.getId() + "', "
                        + "'" + user.getPassword() + "', "
                        + "'" + user.getUsername() + "', "
                        + "'" + (user.getIsAdmin() ? "1" : "0") + "', "
                        + "'" + user.getEmployee().getId() + "');");
                return switch (result) {
                    case 1 ->
                        Either.right("El usuario se registró correctamente");
                    case 0 ->
                        Either.left("El usuario no se registró");
                    default ->
                        Either.left("Ocurrió un error");
                };
            } else {
                return Either.left("Ya existe un usuario para ese empleado.");
            }
        } catch (Exception e) {
            System.out.println(e);
            return Either.left("Ocurrió un error");
        }
    }

    public Either<String, String> updateUser(UserModel user) {
        try {
            int result = query.update(""
                    + "update registro_de_productos.user "
                    + "set "
                    + "password = '" + user.getPassword() + "', "
                    + "username = '" + user.getUsername() + "', "
                    + "isAdmin = '" + (user.getIsAdmin() ? "1" : "0") + "', "
                    + "employeeId = '" + user.getEmployee().getId() + "' "
                    + "where id like '" + user.getId() + "';");
            return switch (result) {
                case 1 ->
                    Either.right("El usuario se actualizó correctamente");
                case 0 ->
                    Either.left("El usuario no se actualizó");
                default ->
                    Either.left("Ocurrió un error");
            };
        } catch (Exception e) {
            System.out.println(e);
            return Either.left("Ocurrió un error");
        }
    }

    public Either<String, String> deleteUser(String id) {
        try {
            int result = query.update("DELETE FROM user WHERE id LIKE '" + id + "';");
            return switch (result) {
                case 1 ->
                    Either.right("El usuario se eliminó correctamente");
                case 0 ->
                    Either.left("El usuario no se eliminó");
                default ->
                    Either.left("Ocurrió un error");
            };
        } catch (Exception e) {
            System.out.println(e);
            return Either.left("Ocurrió un error");
        }
    }

}
