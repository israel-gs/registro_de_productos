package com.utp.registro_de_productos.provider;

import com.utp.registro_de_productos.core.MySqlConnection;
import com.utp.registro_de_productos.core.Query;
import com.utp.registro_de_productos.model.EmployeeModel;

import io.vavr.control.Either;
import java.sql.ResultSet;
import java.util.ArrayList;

public class EmployeeProvider {

    Query query = new Query();

    public Either<String, ArrayList<EmployeeModel>> getEmployees() {
        ArrayList<EmployeeModel> employees = new ArrayList<>();
        try {
            ResultSet rs = query.query(""
                    + "select id,"
                    + "name,"
                    + "lastname,"
                    + "documentType,"
                    + "documentNumber,"
                    + "code,"
                    + "hasExperience,"
                    + "birthday,"
                    + "hasChildren,"
                    + "childrensNumber,"
                    + "maritalStatus "
                    + "from employee;");
            while (rs.next()) {
                EmployeeModel employeeModel = new EmployeeModel();
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
                employees.add(employeeModel);
            }
            return Either.right(employees);
        } catch (Exception e) {
            System.out.println(e);
            return Either.left("Ocurrió un error");
        }
    }

    public Either<String, String> insertEmployee(EmployeeModel employee) {
        try {
            int result = query.update(""
                    + "INSERT INTO employee (id, name, lastname, documentType, documentNumber, code, hasExperience, birthday, hasChildren, childrensNumber, maritalStatus) VALUES"
                    + "('" + employee.getId() + "', '" + employee.getName() + "', '" + employee.getLastname() + "', '" + employee.getDocumentType() + "', '" + employee.getDocumentNumber() + "', '" + employee.getCode() + "', '" + employee.getHasExperience() + "', '" + employee.getBirthday() + "', '" + employee.getHasChildren() + "', '" + employee.getChildrensNumber() + "', '" + employee.getMaritalStatus() + "');");
            return switch (result) {
                case 1 ->
                    Either.right("El empleado se registró correctamente");
                case 0 ->
                    Either.left("El empleado no se registró");
                default ->
                    Either.left("Ocurrió un error");
            };
        } catch (Exception e) {
            System.out.println(e);
            return Either.left("Ocurrió un error");
        }
    }

    public Either<String, String> updateEmployee(EmployeeModel employee) {
        try {
            int result = query.update(""
                    + "UPDATE employee t SET"
                    + " t.name = '" + employee.getName() + "',"
                    + " t.lastname = '" + employee.getLastname() + "',"
                    + " t.documentType = '" + employee.getDocumentType() + "',"
                    + " t.documentNumber = '" + employee.getDocumentNumber() + "',"
                    + " t.code = '" + employee.getCode() + "',"
                    + " t.hasExperience = '" + employee.getHasExperience() + "',"
                    + " t.birthday = '" + employee.getBirthday() + "',"
                    + " t.hasChildren = '" + employee.getHasChildren() + "',"
                    + " t.childrensNumber = '" + employee.getChildrensNumber() + "',"
                    + " t.maritalStatus = '" + employee.getMaritalStatus() + "'"
                    + " WHERE t.id LIKE '" + employee.getId() + "';");
            return switch (result) {
                case 1 ->
                    Either.right("El empleado se actualizó correctamente");
                case 0 ->
                    Either.left("El empleado no se actualizó");
                default ->
                    Either.left("Ocurrió un error");
            };
        } catch (Exception e) {
            System.out.println(e);
            return Either.left("Ocurrió un error");
        }
    }

    public Either<String, String> deleteEmployee(String id) {
        try {
            int result = query.update("DELETE FROM employee WHERE id LIKE '" + id + "';");
            return switch (result) {
                case 1 ->
                    Either.right("El empleado se eliminó correctamente");
                case 0 ->
                    Either.left("El empleado no se eliminó");
                default ->
                    Either.left("Ocurrió un error");
            };
        } catch (Exception e) {
            System.out.println(e);
            return Either.left("Ocurrió un error");
        }
    }

}
