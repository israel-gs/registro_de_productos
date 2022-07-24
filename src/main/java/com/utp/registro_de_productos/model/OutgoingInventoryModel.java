package com.utp.registro_de_productos.model;

import java.util.ArrayList;
import java.util.Date;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class OutgoingInventoryModel {

    private String id;
    private String originAddress;
    private String destinationAddress;
    private Date date;
    private UserModel user;
    private ArrayList<OutgoingInventoryProductModel> outgoingInventoryProducts;
    private JRBeanCollectionDataSource coursedataSource;

    public OutgoingInventoryModel() {
    }

    public OutgoingInventoryModel(String id, String originAddress, String destinationAddress, Date date, UserModel user, ArrayList<OutgoingInventoryProductModel> outgoingInventoryProducts) {
        this.id = id;
        this.originAddress = originAddress;
        this.destinationAddress = destinationAddress;
        this.date = date;
        this.user = user;
        this.outgoingInventoryProducts = outgoingInventoryProducts;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOriginAddress() {
        return originAddress;
    }

    public void setOriginAddress(String originAddress) {
        this.originAddress = originAddress;
    }

    public String getDestinationAddress() {
        return destinationAddress;
    }

    public void setDestinationAddress(String destinationAddress) {
        this.destinationAddress = destinationAddress;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    public ArrayList<OutgoingInventoryProductModel> getOutgoingInventoryProducts() {
        return outgoingInventoryProducts;
    }

    public void setOutgoingInventoryProducts(ArrayList<OutgoingInventoryProductModel> outgoingInventoryProducts) {
        this.outgoingInventoryProducts = outgoingInventoryProducts;
    }

    public JRBeanCollectionDataSource getCoursedataSource() {
        return new JRBeanCollectionDataSource(outgoingInventoryProducts, false);
    }
}
