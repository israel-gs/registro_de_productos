package com.utp.registro_de_productos.controller;

import com.utp.registro_de_productos.model.EmployeeModel;
import com.utp.registro_de_productos.model.UserModel;
import com.utp.registro_de_productos.provider.EmployeeProvider;
import com.utp.registro_de_productos.provider.UserProvider;
import io.vavr.control.Either;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class UserController {

    public Either<String, DefaultComboBoxModel<EmployeeModel>> onLoadEmployee() {
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        Either<String, ArrayList<EmployeeModel>> response = new EmployeeProvider().getEmployees();
        if (response.isRight()) {
            ArrayList<EmployeeModel> employees = response.right().get();
            employees.forEach((e) -> {
                model.addElement(e);
            });
            return Either.right(model);
        } else {
            return Either.left("Ocurrio un error");
        }
    }
    
    public Either<String, TableModel> onLoad() {
        DefaultTableModel model = new DefaultTableModel();
        Either<String, ArrayList<UserModel>> response = new UserProvider().getUsers();
        if (response.isRight()) {
            ArrayList<UserModel> users = response.right().get();
            model.addColumn("Id");
            model.addColumn("Contraseña");
            model.addColumn("Nombre de usuario");
            model.addColumn("Es administrador");
            model.addColumn("Id Empleado");
            users.forEach((user) -> {
                Object[] data = {
                    user.getId(),
                    user.getPassword(),
                    user.getUsername(),
                    user.getIsAdmin(),
                    user.getEmployee().getId()
                };
                model.addRow(data);
            });
            return Either.right(model);
        } else {
            return Either.left("Ocurrio un error");
        }
    }

    public Either<String, String> onDelete(String id) {
        Either<String, String> response = new UserProvider().deleteUser(id);
        return response;
    }

    public Either<String, String> onEdit(UserModel user) {
        Either<String, String> response = new UserProvider().updateUser(user);
        return response;
    }

    public Either<String, String> onAdd(UserModel user) {
        Either<String, String> response = new UserProvider().insertUser(user);
        return response;
    }

}
