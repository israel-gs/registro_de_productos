package com.utp.registro_de_productos.provider;

import com.utp.registro_de_productos.core.Query;
import com.utp.registro_de_productos.model.SupplierModel;
import io.vavr.control.Either;
import java.sql.ResultSet;
import java.util.ArrayList;

public class SupplierProvider {

    Query query = new Query();

    public Either<String, ArrayList<SupplierModel>> getSuppliers() {
        ArrayList<SupplierModel> suppliers = new ArrayList<>();
        try {
            ResultSet rs = query.query(""
                    + "select id, "
                    + "       name, "
                    + "       ruc,"
                    + "       phone "
                    + "from "
                    + "    supplier;");
            while (rs.next()) {
                SupplierModel supplierModel = new SupplierModel();
                supplierModel.setID(rs.getString("id"));
                supplierModel.setName(rs.getString("name"));
                supplierModel.setRuc(rs.getString("ruc"));
                supplierModel.setPhone(rs.getString("phone"));
                suppliers.add(supplierModel);
            }
            return Either.right(suppliers);
        } catch (Exception e) {
            System.out.println(e);
            return Either.left("Ocurrió un error");
        }
    }

    public Either<String, String> insertSupplier(SupplierModel supplier) {
        try {
            int result = query.update("INSERT INTO supplier (id, name, ruc, phone) VALUES ('" + supplier.getID() + "', '" + supplier.getName() + "', '" + supplier.getRuc()+ "', '" + supplier.getPhone()+ "');");
            return switch (result) {
                case 1 ->
                    Either.right("El proveedor se registró correctamente");
                case 0 ->
                    Either.left("El proveedor no se registró");
                default ->
                    Either.left("Ocurrió un error");
            };
        } catch (Exception e) {
            System.out.println(e);
            return Either.left("Ocurrió un error");
        }
    }

    public Either<String, String> updateSupplier(SupplierModel supplier) {
        try {
            int result = query.update("UPDATE supplier t SET t.name = '" + supplier.getName() + "', t.ruc = '" + supplier.getRuc()+ "', t.phone = '" + supplier.getPhone()+ "' WHERE t.id LIKE '" + supplier.getID() + "';");
            return switch (result) {
                case 1 ->
                    Either.right("El proveedor se actualizó correctamente");
                case 0 ->
                    Either.left("El proveedor no se actualizó");
                default ->
                    Either.left("Ocurrió un error");
            };
        } catch (Exception e) {
            System.out.println(e);
            return Either.left("Ocurrió un error");
        }
    }

    public Either<String, String> deleteSupplier(String id) {
        try {
            int result = query.update("DELETE FROM supplier WHERE id LIKE '" + id + "';");
            return switch (result) {
                case 1 ->
                    Either.right("El proveedor se eliminó correctamente");
                case 0 ->
                    Either.left("El proveedor no se eliminó");
                default ->
                    Either.left("Ocurrió un error");
            };
        } catch (Exception e) {
            System.out.println(e);
            return Either.left("Ocurrió un error");
        }
    }

}
