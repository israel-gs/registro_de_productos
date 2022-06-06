package com.utp.registro_de_productos.provider;

import com.utp.registro_de_productos.core.Query;
import com.utp.registro_de_productos.model.CategoryModel;
import io.vavr.control.Either;
import java.sql.ResultSet;
import java.util.ArrayList;

public class CategoryProvider {

    Query query = new Query();

    public Either<String, ArrayList<CategoryModel>> getCategories() {
        ArrayList<CategoryModel> categories = new ArrayList<>();
        try {
            ResultSet rs = query.query(""
                    + "select id, "
                    + "       name, "
                    + "       description "
                    + "from "
                    + "    registro_de_productos.category;");
            while (rs.next()) {
                CategoryModel categoryModel = new CategoryModel();
                categoryModel.setID(rs.getString("id"));
                categoryModel.setName(rs.getString("name"));
                categoryModel.setDescription(rs.getString("description"));
                categories.add(categoryModel);
            }
            return Either.right(categories);
        } catch (Exception e) {
            System.out.println(e);
            return Either.left("Ocurrió un error");
        }
    }

    public Either<String, String> insertCategory(CategoryModel category) {
        try {
            int result = query.update("INSERT INTO registro_de_productos.category (id, name, description) VALUES ('" + category.getID() + "', '" + category.getName() + "', '" + category.getDescription() + "');");
            return switch (result) {
                case 1 ->
                    Either.right("La categoria se registro correctamente");
                case 0 ->
                    Either.left("La categoria no se registro");
                default ->
                    Either.left("Ocurrió un error");
            };
        } catch (Exception e) {
            System.out.println(e);
            return Either.left("Ocurrió un error");
        }
    }

    public Either<String, String> updateCategory(CategoryModel category) {
        try {
            int result = query.update("UPDATE registro_de_productos.category t SET t.name = '" + category.getName() + "', t.description = '" + category.getDescription() + "' WHERE t.id LIKE '" + category.getID() + "';");
            return switch (result) {
                case 1 ->
                    Either.right("La categoria se actualizo correctamente");
                case 0 ->
                    Either.left("La categoria no se actualizo");
                default ->
                    Either.left("Ocurrió un error");
            };
        } catch (Exception e) {
            System.out.println(e);
            return Either.left("Ocurrió un error");
        }
    }

    public Either<String, String> deleteCategory(String id) {
        try {
            int result = query.update("DELETE FROM registro_de_productos.category WHERE id LIKE '" + id + "';");
            return switch (result) {
                case 1 ->
                    Either.right("La categoria se elimino correctamente");
                case 0 ->
                    Either.left("La categoria no se elimino");
                default ->
                    Either.left("Ocurrió un error");
            };
        } catch (Exception e) {
            System.out.println(e);
            return Either.left("Ocurrió un error");
        }
    }

}
