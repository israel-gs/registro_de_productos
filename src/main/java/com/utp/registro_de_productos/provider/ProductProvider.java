package com.utp.registro_de_productos.provider;

import com.utp.registro_de_productos.core.MySqlConnection;
import com.utp.registro_de_productos.core.Query;
import com.utp.registro_de_productos.model.CategoryModel;
import com.utp.registro_de_productos.model.ProductModel;
import com.utp.registro_de_productos.model.SupplierModel;
import io.vavr.control.Either;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ProductProvider {

    Query query = new Query();

    public Either<String, ArrayList<ProductModel>> getProducts() {
        ArrayList<ProductModel> products = new ArrayList<>();
        try {
            ResultSet rs = query.query("""
                                       select p.id          as producId,
                                              p.name        as productName,
                                              p.description as productDescription,
                                              p.price       as productPrice,
                                              p.quantity    as productQuantity,
                                              p.categoryId  as categoryId,
                                              c.name        as categoryName,
                                              c.description as categoryDescription,
                                              p.supplierId  as supplierId,
                                              s.name        as supplierName,
                                              s.ruc         as supplierRuc,
                                              s.phone       as supplierPhone,
                                              s.email       as supplierEmail
                                       from product p
                                                inner join category c on c.id = p.categoryId
                                                inner join supplier s on p.supplierId = s.id;""");
            while (rs.next()) {
                ProductModel product = new ProductModel();
                product.setId(rs.getString("producId"));
                product.setName(rs.getString("productName"));
                product.setDescription(rs.getString("productDescription"));
                product.setPrice(rs.getDouble("productPrice"));
                product.setQuantity(rs.getInt("productQuantity"));
                product.setCategory(new CategoryModel(rs.getString("categoryId"), rs.getString("categoryName"), rs.getString("categoryDescription")));
                product.setSupplier(new SupplierModel(rs.getString("supplierId"), rs.getString("supplierName"), rs.getString("supplierRuc"), rs.getString("supplierPhone"), rs.getString("supplierEmail")));
                products.add(product);
            }
            return Either.right(products);
        } catch (Exception e) {
            System.out.println(e);
            return Either.left("Ocurrió un error");
        }
    }

    public Either<String, ArrayList<ProductModel>> getProductsBySupplierId(String supplierId) {
        ArrayList<ProductModel> products = new ArrayList<>();
        try {
            String script = String.format("""
                                       select p.id          as producId,
                                              p.name        as productName,
                                              p.description as productDescription,
                                              p.price       as productPrice,
                                              p.quantity    as productQuantity,
                                              p.categoryId  as categoryId,
                                              c.name        as categoryName,
                                              c.description as categoryDescription,
                                              p.supplierId  as supplierId,
                                              s.name        as supplierName,
                                              s.ruc         as supplierRuc,
                                              s.phone       as supplierPhone,
                                              s.email       as supplierEmail
                                       from product p
                                                inner join category c on c.id = p.categoryId
                                                inner join supplier s on p.supplierId = s.id
                                       where p.supplierId = '%s'
                                       ;""", supplierId);
            ResultSet rs = query.query(script);
            while (rs.next()) {
                ProductModel product = new ProductModel();
                product.setId(rs.getString("producId"));
                product.setName(rs.getString("productName"));
                product.setDescription(rs.getString("productDescription"));
                product.setPrice(rs.getDouble("productPrice"));
                product.setQuantity(rs.getInt("productQuantity"));
                product.setCategory(new CategoryModel(rs.getString("categoryId"), rs.getString("categoryName"), rs.getString("categoryDescription")));
                product.setSupplier(new SupplierModel(rs.getString("supplierId"), rs.getString("supplierName"), rs.getString("supplierRuc"), rs.getString("supplierPhone"), rs.getString("supplierEmail")));
                products.add(product);
            }
            return Either.right(products);
        } catch (Exception e) {
            System.out.println(e);
            return Either.left("Ocurrió un error");
        }
    }

    public Either<String, String> insertProduct(ProductModel product) {
        try {
            int result = query.update("INSERT INTO product (id, name, description, price, quantity, categoryId, supplierId) VALUES ('" + product.getId() + "', '" + product.getName() + "', '" + product.getDescription() + "', '" + product.getPrice() + "', '" + product.getQuantity() + "', '" + product.getCategory().getID() + "', '" + product.getSupplier().getID() + "');");
            return switch (result) {
                case 1 ->
                    Either.right("El producto se registró correctamente");
                case 0 ->
                    Either.left("El producto no se registró");
                default ->
                    Either.left("Ocurrió un error");
            };
        } catch (Exception e) {
            System.out.println(e);
            return Either.left("Ocurrió un error");
        }
    }

    public Either<String, String> updateProduct(ProductModel product) {
        try {
            int result = query.update("UPDATE product t "
                    + "SET "
                    + "t.name = '" + product.getName() + "', "
                    + "t.description = '" + product.getDescription() + "', "
                    + "t.price = '" + product.getPrice() + "', "
                    + "t.quantity = '" + product.getQuantity() + "', "
                    + "t.categoryId = '" + product.getCategory().getID() + "', "
                    + "t.supplierId = '" + product.getSupplier().getID() + "' "
                    + "WHERE t.id LIKE '" + product.getId() + "';");
            return switch (result) {
                case 1 ->
                    Either.right("El producto se actualizó correctamente");
                case 0 ->
                    Either.left("El producto no se actualizó");
                default ->
                    Either.left("Ocurrió un error");
            };
        } catch (Exception e) {
            System.out.println(e);
            return Either.left("Ocurrió un error");
        }
    }

    public Either<String, String> deleteProduct(String id) {
        try {
            int result = query.update("DELETE FROM product WHERE id LIKE '" + id + "';");
            return switch (result) {
                case 1 ->
                    Either.right("El producto se eliminó correctamente");
                case 0 ->
                    Either.left("El producto no se eliminó");
                default ->
                    Either.left("Ocurrió un error");
            };
        } catch (Exception e) {
            System.out.println(e);
            return Either.left("Ocurrió un error");
        }
    }

}
