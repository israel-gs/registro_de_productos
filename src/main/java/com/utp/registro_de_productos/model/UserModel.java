package com.utp.registro_de_productos.model;

public class UserModel {

    private String dni;
    private String name;
    private String password;
    private boolean isAdmin;

    public String getDni() {
        return dni;
    }

    public void setDni(String value) {
        this.dni = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String value) {
        this.name = value;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String value) {
        this.password = value;
    }

    public boolean getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(boolean value) {
        this.isAdmin = value;
    }
}
