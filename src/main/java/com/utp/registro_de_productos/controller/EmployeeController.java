package com.utp.registro_de_productos.controller;

import com.utp.registro_de_productos.model.EmployeeModel;
import com.utp.registro_de_productos.provider.EmployeeProvider;

import io.vavr.control.Either;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class EmployeeController {

    public Either<String, TableModel> onLoad() {
        DefaultTableModel model = new DefaultTableModel();
        Either<String, ArrayList<EmployeeModel>> response = new EmployeeProvider().getEmployees();
        if (response.isRight()) {
            ArrayList<EmployeeModel> employeeModels = response.right().get();
            model.addColumn("ID");
            model.addColumn("Nombre");
            model.addColumn("Apellido");
            model.addColumn("Tipo de documento");
            model.addColumn("Número de documento");
            model.addColumn("Código");
            model.addColumn("Experiencia");
            model.addColumn("Fecha de nacimiento");
            model.addColumn("Tiene hijos");
            model.addColumn("Número de hijos");
            model.addColumn("Estado civil");
            for (EmployeeModel employeeModel : employeeModels) {
                model.addRow(new Object[]{employeeModel.getId(), employeeModel.getName(), employeeModel.getLastname(), employeeModel.getDocumentType(), employeeModel.getDocumentNumber(), employeeModel.getCode(), employeeModel.getHasExperience(), employeeModel.getBirthday(), employeeModel.getHasChildren(), employeeModel.getChildrensNumber(), employeeModel.getMaritalStatus()});
            }
            return Either.right(model);
        } else {
            return Either.left("Ocurrio un error");
        }
    }

    public Either<String, String> onDelete(String id) {
        Either<String, String> response = new EmployeeProvider().deleteEmployee(id);
        return response;
    }

    public Either<String, String> onEdit(EmployeeModel employee) {
        Either<String, String> response = new EmployeeProvider().updateEmployee(employee);
        return response;
    }

    public Either<String, String> onAdd(EmployeeModel employee) {
        Either<String, String> response = new EmployeeProvider().insertEmployee(employee);
        return response;
    }

}
