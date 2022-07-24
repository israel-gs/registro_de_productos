package com.utp.registro_de_productos.controller;

import com.utp.registro_de_productos.model.SupplierModel;
import com.utp.registro_de_productos.provider.SupplierProvider;
import io.vavr.control.Either;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class SupplierController {

    public Either<String, TableModel> onLoad() {
        DefaultTableModel model = new DefaultTableModel();
        Either<String, ArrayList<SupplierModel>> response = new SupplierProvider().getSuppliers();
        if (response.isRight()) {
            ArrayList<SupplierModel> suppliers = response.right().get();
            model.addColumn("Id");
            model.addColumn("Nombre");
            model.addColumn("Ruc");
            model.addColumn("Teléfono");
            model.addColumn("Email");
            suppliers.forEach((supplier) -> {
                Object[] data = {supplier.getID(), supplier.getName(), supplier.getRuc(), supplier.getPhone(), supplier.getEmail()};
                model.addRow(data);
            });
            return Either.right(model);
        } else {
            return Either.left("Ocurrio un error");
        }
    }
    
    public Either<String, String> onDelete(String id) {
        Either<String, String> response = new SupplierProvider().deleteSupplier(id);
        return response;
    }
    
    public Either<String, String> onEdit(SupplierModel supplier) {
        Either<String, String> response = new SupplierProvider().updateSupplier(supplier);
        return response;
    }
    
    public Either<String, String> onAdd(SupplierModel supplier) {
        Either<String, String> response = new SupplierProvider().insertSupplier(supplier);
        return response;
    }

}
