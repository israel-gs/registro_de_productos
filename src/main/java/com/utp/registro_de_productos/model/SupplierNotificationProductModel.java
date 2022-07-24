package com.utp.registro_de_productos.model;

public class SupplierNotificationProductModel {

    private String id;
    private ProductModel product;
    private Integer quantity;

    public SupplierNotificationProductModel() {
    }

    public SupplierNotificationProductModel(String id, ProductModel product, Integer quantity) {
        this.id = id;
        this.product = product;
        this.quantity = quantity;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ProductModel getProduct() {
        return product;
    }

    public void setProduct(ProductModel product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

}
