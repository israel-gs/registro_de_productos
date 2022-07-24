package com.utp.registro_de_productos.controller;

import com.utp.registro_de_productos.model.OutgoingInventoryModel;
import com.utp.registro_de_productos.model.OutgoingInventoryProductModel;
import com.utp.registro_de_productos.model.ProductModel;
import com.utp.registro_de_productos.provider.OutgoingInventoryProvider;
import com.utp.registro_de_productos.provider.ProductProvider;
import io.vavr.control.Either;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

public class OutgoingInventoryController {

    public Either<String, TableModel> onLoad() {
        DefaultTableModel model = new DefaultTableModel();
        Either<String, ArrayList<OutgoingInventoryModel>> response = new OutgoingInventoryProvider().getOutgoingInventories();
        if (response.isRight()) {
            ArrayList<OutgoingInventoryModel> outgoingInventories = response.right().get();
            model.addColumn("ID");
            model.addColumn("Direccion Origen");
            model.addColumn("Direccion Destino");
            model.addColumn("Fecha");
            model.addColumn("Registro");
            model.addColumn("Cantidad de productos");
            for (OutgoingInventoryModel outgoingInventory : outgoingInventories) {
                model.addRow(new Object[]{
                    outgoingInventory.getId(),
                    outgoingInventory.getOriginAddress(),
                    outgoingInventory.getDestinationAddress(),
                    outgoingInventory.getDate(),
                    outgoingInventory.getUser().getEmployee().getName() + ' ' + outgoingInventory.getUser().getEmployee().getLastname(),
                    outgoingInventory.getOutgoingInventoryProducts().size()
                });
            }
            return Either.right(model);
        } else {
            return Either.left("Ocurrio un error");
        }
    }

    public void generatePdf(String id) {
        Either<String, OutgoingInventoryModel> response = new OutgoingInventoryProvider().getOutgoingInventory(id);
        if (response.isRight()) {
            try {
                OutgoingInventoryModel outgoingInventoryModel = response.right().get();
                
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                String formatedDate = formatter.format(outgoingInventoryModel.getDate());
                
                ArrayList<OutgoingInventoryProductModel> products = outgoingInventoryModel.getOutgoingInventoryProducts();
                
                Double total = 0.0;
                
                for (OutgoingInventoryProductModel p: products) {
                    total += p.getPrice() * p.getQuantity();
                }
                
                Map parameters = new HashMap();
                parameters.put("OI_ID", outgoingInventoryModel.getId());
                parameters.put("OI_PARTIDA", outgoingInventoryModel.getOriginAddress());
                parameters.put("OI_LLEGADA", outgoingInventoryModel.getDestinationAddress());
                parameters.put("OI_FECHA", formatedDate);
                parameters.put("OI_TOTAL", total);
                
                JasperReport reportes = (JasperReport) JRLoader.loadObject(getClass().getResource("/reports/outgoingInventory.jasper"));
                JasperPrint print = JasperFillManager.fillReport(reportes, parameters, new JRBeanCollectionDataSource(outgoingInventoryModel.getOutgoingInventoryProducts()));
                JasperViewer visualiza = new JasperViewer(print, false);
                visualiza.setTitle("Reporte de Productos");
                visualiza.setVisible(true);
            } catch (JRException e) {
                System.out.println(e);
                JOptionPane.showMessageDialog(null, "Ocurrió un error", "Advertencia", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, response.left().get(), "Advertencia", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public Either<String, DefaultComboBoxModel> onLoadProducts() {
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        Either<String, ArrayList<ProductModel>> response = new ProductProvider().getProducts();
        if (response.isRight()) {
            ArrayList<ProductModel> products = response.right().get();
            products.forEach((product) -> {
                model.addElement(product);
            });
            return Either.right(model);
        } else {
            return Either.left("Ocurrio un error");
        }
    }

}
