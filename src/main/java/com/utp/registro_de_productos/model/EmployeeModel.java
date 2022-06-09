package com.utp.registro_de_productos.model;

import java.util.Date;

public class EmployeeModel {

    private String id;
    private String name;
    private String lastname;
    private String documentType;
    private String documentNumber;
    private String code;
    private Boolean hasExperience;
    private Date birthday;
    private Boolean hasChildren;
    private Integer childrensNumber;
    private String maritalStatus;

    public EmployeeModel() {
    }

    public EmployeeModel(String id, String name, String lastname, String documentType, String documentNumber, String code, Boolean hasExperience, Date birthday, Boolean hasChildren, Integer childrensNumber, String maritalStatus) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.documentType = documentType;
        this.documentNumber = documentNumber;
        this.code = code;
        this.hasExperience = hasExperience;
        this.birthday = birthday;
        this.hasChildren = hasChildren;
        this.childrensNumber = childrensNumber;
        this.maritalStatus = maritalStatus;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getDocumentType() {
        return documentType;
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Boolean getHasExperience() {
        return hasExperience;
    }

    public void setHasExperience(Boolean hasExperience) {
        this.hasExperience = hasExperience;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Boolean getHasChildren() {
        return hasChildren;
    }

    public void setHasChildren(Boolean hasChildren) {
        this.hasChildren = hasChildren;
    }

    public Integer getChildrensNumber() {
        return childrensNumber;
    }

    public void setChildrensNumber(Integer childrensNumber) {
        this.childrensNumber = childrensNumber;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

}
