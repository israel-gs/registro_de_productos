package com.utp.registro_de_productos.controller;

import com.utp.registro_de_productos.model.CategoryModel;
import com.utp.registro_de_productos.provider.CategoryProvider;
import io.vavr.control.Either;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class CategoryController {

    public Either<String, TableModel> onLoad() {
        DefaultTableModel model = new DefaultTableModel();
        Either<String, ArrayList<CategoryModel>> response = new CategoryProvider().getCategories();
        if (response.isRight()) {
            ArrayList<CategoryModel> categories = response.right().get();
            model.addColumn("Id");
            model.addColumn("Nombre");
            model.addColumn("Descripcion");
            categories.forEach((category) -> {
                Object[] data = {category.getID(), category.getName(), category.getDescription()};
                model.addRow(data);
            });
            return Either.right(model);
        } else {
            return Either.left("Ocurrio un error");
        }
    }
    
    public Either<String, String> onDelete(String id) {
        Either<String, String> response = new CategoryProvider().deleteCategory(id);
        return response;
    }
    
    public Either<String, String> onEdit(CategoryModel category) {
        Either<String, String> response = new CategoryProvider().updateCategory(category);
        return response;
    }

}
