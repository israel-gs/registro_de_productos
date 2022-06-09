package com.utp.registro_de_productos.controller;

import com.utp.registro_de_productos.model.CategoryModel;
import com.utp.registro_de_productos.model.ProductModel;
import com.utp.registro_de_productos.model.SupplierModel;
import com.utp.registro_de_productos.provider.CategoryProvider;
import com.utp.registro_de_productos.provider.ProductProvider;
import com.utp.registro_de_productos.provider.SupplierProvider;
import io.vavr.control.Either;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

public class ReportsController {

    public void generateProductsReport() {
        Either<String, ArrayList<ProductModel>> response = new ProductProvider().getProducts();
        if (response.isRight()) {
            try {
                JasperReport reportes = (JasperReport) JRLoader.loadObject(getClass().getResource("/reports/product.jasper"));
                JasperPrint print = JasperFillManager.fillReport(reportes, null, new JRBeanCollectionDataSource(response.right().get()));
                JasperViewer visualiza = new JasperViewer(print, false);
                visualiza.setTitle("Reporte de Productos");
                visualiza.setVisible(true);
            } catch (JRException e) {
                JOptionPane.showMessageDialog(null, "Ocurrió un error", "Advertencia", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, response.left().get(), "Advertencia", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void generateSupplierReport() {
        Either<String, ArrayList<SupplierModel>> response = new SupplierProvider().getSuppliers();
        if (response.isRight()) {
            try {
                JasperReport reportes = (JasperReport) JRLoader.loadObject(getClass().getResource("/reports/supplier.jasper"));
                JasperPrint print = JasperFillManager.fillReport(reportes, null, new JRBeanCollectionDataSource(response.right().get()));
                JasperViewer visualiza = new JasperViewer(print, false);
                visualiza.setTitle("Reporte de Proveedores");
                visualiza.setVisible(true);
            } catch (JRException e) {
                System.out.println(e);
                JOptionPane.showMessageDialog(null, "Ocurrió un error", "Advertencia", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, response.left().get(), "Advertencia", JOptionPane.ERROR_MESSAGE);
        }
    }

}
