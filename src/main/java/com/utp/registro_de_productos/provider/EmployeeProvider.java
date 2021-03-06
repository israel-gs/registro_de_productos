package com.utp.registro_de_productos.provider;

import com.utp.registro_de_productos.core.MySqlConnection;
import com.utp.registro_de_productos.core.Query;
import com.utp.registro_de_productos.model.EmployeeModel;

import io.vavr.control.Either;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
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
            return Either.left("Ocurri?? un error");
        }
    }

    public Either<String, String> insertEmployee(EmployeeModel employee) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            String formatedDate = formatter.format(employee.getBirthday());
            System.out.println(formatedDate);
            int result = query.update(""
                    + "INSERT INTO employee (id, name, lastname, documentType, documentNumber, code, hasExperience, birthday, hasChildren, childrensNumber, maritalStatus) VALUES"
                    + "('" + employee.getId() + "', '" + employee.getName() + "', '" + employee.getLastname() + "', '" + employee.getDocumentType() + "', '" + employee.getDocumentNumber() + "', '" + employee.getCode() + "', '" + (employee.getHasExperience() ? 1 : 0) + "', '" + formatedDate + "', '" + (employee.getHasChildren() ? 1 : 0) + "', '" + employee.getChildrensNumber() + "', '" + employee.getMaritalStatus() + "');");
            return switch (result) {
                case 1 ->
                    Either.right("El empleado se registr?? correctamente");
                case 0 ->
                    Either.left("El empleado no se registr??");
                default ->
                    Either.left("Ocurri?? un error");
            };
        } catch (Exception e) {
            System.out.println(e);
            return Either.left("Ocurri?? un error");
        }
    }

    public Either<String, String> updateEmployee(EmployeeModel employee) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            String formatedDate = formatter.format(employee.getBirthday());
            System.out.println(formatedDate);
            int result = query.update(""
                    + "UPDATE employee t SET"
                    + " t.name = '" + employee.getName() + "',"
                    + " t.lastname = '" + employee.getLastname() + "',"
                    + " t.documentType = '" + employee.getDocumentType() + "',"
                    + " t.documentNumber = '" + employee.getDocumentNumber() + "',"
                    + " t.code = '" + employee.getCode() + "',"
                    + " t.hasExperience = '" + (employee.getHasExperience() ? 1 : 0) + "',"
                    + " t.birthday = '" + formatedDate + "',"
                    + " t.hasChildren = '" + (employee.getHasChildren() ? 1 : 0) + "',"
                    + " t.childrensNumber = '" + employee.getChildrensNumber() + "',"
                    + " t.maritalStatus = '" + employee.getMaritalStatus() + "'"
                    + " WHERE t.id LIKE '" + employee.getId() + "';");
            return switch (result) {
                case 1 ->
                    Either.right("El empleado se actualiz?? correctamente");
                case 0 ->
                    Either.left("El empleado no se actualiz??");
                default ->
                    Either.left("Ocurri?? un error");
            };
        } catch (Exception e) {
            System.out.println(e);
            return Either.left("Ocurri?? un error");
        }
    }

    public Either<String, String> deleteEmployee(String id) {
        try {
            int result = query.update("DELETE FROM employee WHERE id LIKE '" + id + "';");
            return switch (result) {
                case 1 ->
                    Either.right("El empleado se elimin?? correctamente");
                case 0 ->
                    Either.left("El empleado no se elimin??");
                default ->
                    Either.left("Ocurri?? un error");
            };
        } catch (Exception e) {
            System.out.println(e);
            return Either.left("Ocurri?? un error");
        }
    }

}
