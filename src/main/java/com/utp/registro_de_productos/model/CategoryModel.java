package com.utp.registro_de_productos.model;

public class CategoryModel {

    private String id;
    private String name;
    private String description;

    public CategoryModel() {
    }

    public CategoryModel(String id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String value) {
        this.description = value;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
