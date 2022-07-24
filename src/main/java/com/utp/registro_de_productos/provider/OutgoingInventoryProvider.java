package com.utp.registro_de_productos.provider;

import com.utp.registro_de_productos.core.Query;
import com.utp.registro_de_productos.model.CategoryModel;
import com.utp.registro_de_productos.model.EmployeeModel;
import com.utp.registro_de_productos.model.OutgoingInventoryModel;
import com.utp.registro_de_productos.model.OutgoingInventoryProductModel;
import com.utp.registro_de_productos.model.ProductModel;
import com.utp.registro_de_productos.model.SupplierModel;
import com.utp.registro_de_productos.model.UserModel;
import io.vavr.control.Either;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class OutgoingInventoryProvider {

    Query query = new Query();

    public Either<String, ArrayList<OutgoingInventoryModel>> getOutgoingInventories() {
        ArrayList<OutgoingInventoryModel> OutgoingInventories = new ArrayList<>();
        try {
            ResultSet rs = query.query("""
            select oi.id  as oi_id,
                   oi.originAddress,
                   oi.destinationAddress,
                   oi.userId,
                   oi.date,
                   u.id   as u_id,
                   u.password,
                   u.username,
                   u.isAdmin,
                   e.id as e_id,
                   e.name as e_name,
                   e.lastname,
                   e.documentType,
                   e.documentNumber,
                   e.code,
                   e.hasExperience,
                   e.birthday,
                   e.hasChildren,
                   e.childrensNumber,
                   e.maritalStatus
            from outgoing_inventory oi
                     join user u on oi.userId = u.id
                     join employee e on u.employeeId = e.id;""");
            while (rs.next()) {
                EmployeeModel employee = new EmployeeModel();
                employee.setId(rs.getString("e_id"));
                employee.setName(rs.getString("e_name"));
                employee.setLastname(rs.getString("lastname"));
                employee.setDocumentType(rs.getString("documentType"));
                employee.setDocumentNumber(rs.getString("documentNumber"));
                employee.setCode(rs.getString("code"));
                employee.setHasExperience(rs.getBoolean("hasExperience"));
                employee.setBirthday(rs.getDate("birthday"));
                employee.setHasChildren(rs.getBoolean("hasChildren"));
                employee.setChildrensNumber(rs.getInt("childrensNumber"));
                employee.setMaritalStatus(rs.getString("maritalStatus"));

                UserModel user = new UserModel(
                        rs.getString("u_id"),
                        employee,
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getBoolean("isAdmin")
                );
                
                ArrayList<OutgoingInventoryProductModel> outgoingInventoryProducts = new ArrayList<>();
                        
                Either<String, ArrayList<OutgoingInventoryProductModel>> response = getOutgoingInventoryProducts(rs.getString("oi_id"));

                if (response.isRight()) {
                    outgoingInventoryProducts = response.right().get();
                }
                
                OutgoingInventoryModel outgoingInventory = new OutgoingInventoryModel(
                        rs.getString("oi_id"),
                        rs.getString("originAddress"),
                        rs.getString("destinationAddress"),
                        rs.getDate("date"),
                        user,
                        outgoingInventoryProducts
                );
                OutgoingInventories.add(outgoingInventory);
            }
            return Either.right(OutgoingInventories);
        } catch (Exception e) {
            System.out.println(e);
            return Either.left("Ocurrió un error");
        }
    }
    
    public Either<String, OutgoingInventoryModel> getOutgoingInventory(String id) {
        ArrayList<OutgoingInventoryModel> OutgoingInventories = new ArrayList<>();
        try {
            String script = String.format("""
            select oi.id  as oi_id,
                   oi.originAddress,
                   oi.destinationAddress,
                   oi.userId,
                   oi.date,
                   u.id   as u_id,
                   u.password,
                   u.username,
                   u.isAdmin,
                   e.id as e_id,
                   e.name as e_name,
                   e.lastname,
                   e.documentType,
                   e.documentNumber,
                   e.code,
                   e.hasExperience,
                   e.birthday,
                   e.hasChildren,
                   e.childrensNumber,
                   e.maritalStatus
            from outgoing_inventory oi
                     join user u on oi.userId = u.id
                     join employee e on u.employeeId = e.id
            where oi.id = '%s';""", id);
            ResultSet rs = query.query(script);
            while (rs.next()) {
                EmployeeModel employee = new EmployeeModel();
                employee.setId(rs.getString("e_id"));
                employee.setName(rs.getString("e_name"));
                employee.setLastname(rs.getString("lastname"));
                employee.setDocumentType(rs.getString("documentType"));
                employee.setDocumentNumber(rs.getString("documentNumber"));
                employee.setCode(rs.getString("code"));
                employee.setHasExperience(rs.getBoolean("hasExperience"));
                employee.setBirthday(rs.getDate("birthday"));
                employee.setHasChildren(rs.getBoolean("hasChildren"));
                employee.setChildrensNumber(rs.getInt("childrensNumber"));
                employee.setMaritalStatus(rs.getString("maritalStatus"));

                UserModel user = new UserModel(
                        rs.getString("u_id"),
                        employee,
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getBoolean("isAdmin")
                );
                
                ArrayList<OutgoingInventoryProductModel> outgoingInventoryProducts = new ArrayList<>();
                        
                Either<String, ArrayList<OutgoingInventoryProductModel>> response = getOutgoingInventoryProducts(rs.getString("oi_id"));

                if (response.isRight()) {
                    outgoingInventoryProducts = response.right().get();
                }
                
                OutgoingInventories.add(new OutgoingInventoryModel(
                        rs.getString("oi_id"),
                        rs.getString("originAddress"),
                        rs.getString("destinationAddress"),
                        rs.getDate("date"),
                        user,
                        outgoingInventoryProducts
                ));
            }
            return Either.right(OutgoingInventories.get(0));
        } catch (Exception e) {
            System.out.println(e);
            return Either.left("Ocurrió un error");
        }
    }

    public Either<String, ArrayList<OutgoingInventoryProductModel>> getOutgoingInventoryProducts(String id) {
        ArrayList<OutgoingInventoryProductModel> outgoingInventoryProducts = new ArrayList<>();
        try {
            String script = String.format("""
            select oip.id as oipId,
                   oip.outgoingInventoryId,
                   oip.quantity as oipQuantity,
                   p.id   as p_id,
                   p.name as p_name,
                   p.description,
                   p.price,
                   p.quantity as p_quantity,
                   c.id   as categoryId,
                   c.name as categoryName,
                   c.description as categoryDescription,
                   s.id   as supplierId,
                   s.name as supplierName,
                   s.ruc as supplierRuc,
                   s.phone as supplierPhone,
                   s.email as supplierEmail 
            from `outgoing_inventory-product` oip
                     join product p on p.id = oip.productId
                     join category c on c.id = p.categoryId
                     join supplier s on p.supplierId = s.id
            where oip.outgoingInventoryId = '%s';""", id);

            ResultSet rs = query.query(script);

            while (rs.next()) {
                ProductModel product = new ProductModel();
                product.setId(rs.getString("p_id"));
                product.setName(rs.getString("p_name"));
                product.setDescription(rs.getString("description"));
                product.setPrice(rs.getDouble("price"));
                product.setQuantity(rs.getInt("p_quantity"));
                product.setCategory(new CategoryModel(rs.getString("categoryId"), rs.getString("categoryName"), rs.getString("categoryDescription")));
                product.setSupplier(new SupplierModel(rs.getString("supplierId"), rs.getString("supplierName"), rs.getString("supplierRuc"), rs.getString("supplierPhone"), rs.getString("supplierEmail")));

                OutgoingInventoryProductModel outgoingInventoryProduct = new OutgoingInventoryProductModel(
                        rs.getString("oipId"),
                        rs.getString("outgoingInventoryId"),
                        rs.getInt("oipQuantity"),
                        product.getId(),
                        product.getName(),
                        product.getDescription(),
                        product.getPrice(),
                        product.getCategory().getName(),
                        product.getSupplier().getName()
                );
                outgoingInventoryProducts.add(outgoingInventoryProduct);
            }
            return Either.right(outgoingInventoryProducts);
        } catch (Exception e) {
            System.out.println(e);
            return Either.left("Ocurrió un error");
        }
    }

    public Either<String, String> insertOutgoingInventory(OutgoingInventoryModel value) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            String formatedDate = formatter.format(value.getDate());
            int result = query.update(""
                    + "insert into outgoing_inventory (id, originAddress, destinationAddress, userId, date) "
                    + "values ("
                    + "'" + value.getId() + "', "
                    + "'" + value.getOriginAddress() + "', "
                    + "'" + value.getDestinationAddress() + "', "
                    + "'" + value.getUser().getId() + "', "
                    + "'" + formatedDate + "' "
                    + ");");
            switch (result) {
                case 1:
                    value.getOutgoingInventoryProducts().forEach((outgoingInventoryProduct) -> insertOutgoingInventoryProduct(outgoingInventoryProduct));
                    return Either.right("Se registró correctamente");
                case 0:
                    return Either.left("No se registró");
                default:
                    return Either.left("Ocurrió un error");
            }
        } catch (Exception e) {
            System.out.println(e);
            return Either.left("Ocurrió un error");
        }
    }

    public Either<String, String> insertOutgoingInventoryProduct(OutgoingInventoryProductModel value) {
        try {
            int result = query.update(""
                    + "insert into `outgoing_inventory-product` (outgoingInventoryId, productId, quantity) "
                    + "values ("
                    + "'" + value.getOutgoingInventoryId() + "', "
                    + "'" + value.getProductId() + "', "
                    + "" + value.getQuantity() + " "
                    + ");");
            return switch (result) {
                case 1 ->
                    Either.right("Se registró correctamente");
                case 0 ->
                    Either.left("No se registró");
                default ->
                    Either.left("Ocurrió un error");
            };
        } catch (Exception e) {
            System.out.println(e);
            return Either.left("Ocurrió un error");
        }
    }

    public Either<String, String> deleteOutgoingInventory(String id) {
        try {
            int result = query.update(""
                    + "delete from outgoing_inventory "
                    + "where id = '" + id + "';");
            switch (result) {
                case 1:
                    return deleteOutgoingInventoryProduct(id);
                case 0:
                    return Either.left("No se eliminó");
                default:
                    return Either.left("Ocurrió un error");
            }
        } catch (Exception e) {
            System.out.println(e);
            return Either.left("Ocurrió un error");
        }
    }

    public Either<String, String> deleteOutgoingInventoryProduct(String id) {
        try {
            int result = query.update(""
                    + "delete from `outgoing_inventory-product` "
                    + "where outgoingInventoryId = '" + id + "';");
            switch (result) {
                case 1:
                    return Either.right("Se eliminó correctamente");
                case 0:
                    return Either.left("No se eliminó");
                default:
                    return Either.left("Ocurrió un error");
            }
        } catch (Exception e) {
            System.out.println(e);
            return Either.left("Ocurrió un error");
        }
    }
}
