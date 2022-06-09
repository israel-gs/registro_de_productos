package com.utp.registro_de_productos.controller;

import com.utp.registro_de_productos.model.CategoryModel;
import com.utp.registro_de_productos.model.ProductModel;
import com.utp.registro_de_productos.model.SupplierModel;
import com.utp.registro_de_productos.provider.CategoryProvider;
import com.utp.registro_de_productos.provider.ProductProvider;
import com.utp.registro_de_productos.provider.SupplierProvider;
import io.vavr.control.Either;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class ProductController {

    public Either<String, DefaultComboBoxModel<CategoryModel>> onLoadCategories() {
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        Either<String, ArrayList<CategoryModel>> response = new CategoryProvider().getCategories();
        if (response.isRight()) {
            ArrayList<CategoryModel> categories = response.right().get();
            categories.forEach((category) -> {
                model.addElement(category);
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
    
    public Either<String, TableModel> onLoad() {
        DefaultTableModel model = new DefaultTableModel();
        Either<String, ArrayList<ProductModel>> response = new ProductProvider().getProducts();
        if (response.isRight()) {
            ArrayList<ProductModel> products = response.right().get();
            model.addColumn("Id");
            model.addColumn("Nombre");
            model.addColumn("Descripcion");
            model.addColumn("Precio");
            model.addColumn("Cantidad");
            model.addColumn("Categoria");
            model.addColumn("Proveedor");
            products.forEach((product) -> {
                Object[] data = {
                    product.getId(), 
                    product.getName(), 
                    product.getDescription(), 
                    product.getPrice(), 
                    product.getQuantity(),
                    product.getCategory().getName(),
                    product.getSupplier().getName(),
                };
                model.addRow(data);
            });
            return Either.right(model);
        } else {
            return Either.left("Ocurrio un error");
        }
    }
    
     public Either<String, String> onDelete(String id) {
        Either<String, String> response = new ProductProvider().deleteProduct(id);
        return response;
    }
    
    public Either<String, String> onEdit(ProductModel product) {
        Either<String, String> response = new ProductProvider().updateProduct(product);
        return response;
    }
    
    public Either<String, String> onAdd(ProductModel producto) {
        Either<String, String> response = new ProductProvider().insertProduct(producto);
        return response;
    }

}
