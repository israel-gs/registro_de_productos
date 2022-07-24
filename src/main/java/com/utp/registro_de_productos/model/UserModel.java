package com.utp.registro_de_productos.model;

public class UserModel {

    private String id;
    private EmployeeModel employee;
    private String usermane;
    private String password;
    private boolean isAdmin;

    public UserModel() {
    }

    public UserModel(String id, EmployeeModel employee, String usermane, String password, boolean isAdmin) {
        this.id = id;
        this.employee = employee;
        this.usermane = usermane;
        this.password = password;
        this.isAdmin = isAdmin;
    }

    public String getId() {
        return id;
    }

    public void setId(String value) {
        this.id = value;
    }

    public EmployeeModel getEmployee() {
        return employee;
    }

    public void setEmployee(EmployeeModel value) {
        this.employee = value;
    }
    
    public String getUsername() {
        return usermane;
    }

    public void setUsername(String value) {
        this.usermane = value;
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
