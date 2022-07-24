package com.utp.registro_de_productos.model;

public class OutgoingInventoryProductModel {

    private String id;
    private String outgoingInventoryId;
    private Integer quantity;
    
    private String productId;
    private String name;
    private String description;
    private Double price;
    private String category;
    private String supplier;

    public OutgoingInventoryProductModel() {
    }

    public OutgoingInventoryProductModel(String id, String outgoingInventoryId, Integer quantity, String productId, String name, String description, Double price, String category, String supplier) {
        this.id = id;
        this.outgoingInventoryId = outgoingInventoryId;
        this.quantity = quantity;
        this.productId = productId;
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
        this.supplier = supplier;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOutgoingInventoryId() {
        return outgoingInventoryId;
    }

    public void setOutgoingInventoryId(String outgoingInventoryId) {
        this.outgoingInventoryId = outgoingInventoryId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
    
    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

}

