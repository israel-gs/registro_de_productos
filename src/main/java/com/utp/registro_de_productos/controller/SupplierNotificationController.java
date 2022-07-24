package com.utp.registro_de_productos.controller;

import com.utp.registro_de_productos.model.ProductModel;
import com.utp.registro_de_productos.model.SupplierModel;
import com.utp.registro_de_productos.model.SupplierNotificationModel;
import com.utp.registro_de_productos.provider.ProductProvider;
import com.utp.registro_de_productos.provider.SupplierNotificationProvider;
import com.utp.registro_de_productos.provider.SupplierProvider;
import io.vavr.control.Either;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class SupplierNotificationController {
    
    public Either<String, TableModel> onLoad() {
        DefaultTableModel model = new DefaultTableModel();
        Either<String, ArrayList<SupplierNotificationModel>> response = new SupplierNotificationProvider().getSupplierNotifications();
        if (response.isRight()) {
            ArrayList<SupplierNotificationModel> a = response.right().get();
            model.addColumn("Id");
            model.addColumn("Proveedor");
            model.addColumn("Productos");
            a.forEach((item) -> {
                Object[] data = {
                    item.getId(),
                    item.getSupplier().getName(),
                    item.getSupplierNotificationProducts().size()
                };
                model.addRow(data);
            });
            return Either.right(model);
        } else {
            return Either.left("Ocurrio un error");
        }
    }

    public Either<String, DefaultComboBoxModel> onLoadSuppliers() {
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        Either<String, ArrayList<SupplierModel>> response = new SupplierProvider().getSuppliers();
        if (response.isRight()) {
            ArrayList<SupplierModel> suppliers = response.right().get();
            suppliers.forEach((supplier) -> {
                model.addElement(supplier);
            });
            return Either.right(model);
        } else {
            return Either.left("Ocurrio un error");
        }
    }
    
    public Either<String, DefaultComboBoxModel> onLoadProducts(String supplierId) {
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        Either<String, ArrayList<ProductModel>> response = new ProductProvider().getProductsBySupplierId(supplierId);
        if (response.isRight()) {
            ArrayList<ProductModel> products = response.right().get();
            products.forEach((product) -> {
                model.addElement(product);
            });
            return Either.right(model);
        } else {
            return Either.left("Ocurrio un error");
        }
    }

}
