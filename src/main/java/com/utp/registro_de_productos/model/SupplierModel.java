package com.utp.registro_de_productos.model;

public class SupplierModel {

    private String id;
    private String name;
    private String ruc;
    private String phone;

    public SupplierModel() {
    }

    public SupplierModel(String id, String name, String ruc, String phone) {
        this.id = id;
        this.name = name;
        this.ruc = ruc;
        this.phone = phone;
    }

    public String getID() {
        return id;
    }

    public void setID(String value) {
        this.id = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String value) {
        this.name = value;
    }

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String value) {
        this.ruc = value;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String value) {
        this.phone = value;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
