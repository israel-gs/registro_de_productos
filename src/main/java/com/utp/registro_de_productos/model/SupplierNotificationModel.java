package com.utp.registro_de_productos.model;

import java.util.ArrayList;
import java.util.Date;

public class SupplierNotificationModel {

    private String id;
    private SupplierModel supplier;
    private Date date;

    private ArrayList<SupplierNotificationProductModel> supplierNotificationProducts;

    public SupplierNotificationModel() {
    }

    public SupplierNotificationModel(String id, SupplierModel supplier, Date date, ArrayList<SupplierNotificationProductModel> supplierNotificationProducts) {
        this.id = id;
        this.supplier = supplier;
        this.date = date;
        this.supplierNotificationProducts = supplierNotificationProducts;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public SupplierModel getSupplier() {
        return supplier;
    }

    public void setSupplier(SupplierModel supplier) {
        this.supplier = supplier;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public ArrayList<SupplierNotificationProductModel> getSupplierNotificationProducts() {
        return supplierNotificationProducts;
    }

    public void setSupplierNotificationProducts(ArrayList<SupplierNotificationProductModel> supplierNotificationProducts) {
        this.supplierNotificationProducts = supplierNotificationProducts;
    }

}
