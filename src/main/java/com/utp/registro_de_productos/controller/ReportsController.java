package com.utp.registro_de_productos.controller;

import com.utp.registro_de_productos.model.CategoryModel;
import com.utp.registro_de_productos.model.EmployeeModel;
import com.utp.registro_de_productos.model.ProductModel;
import com.utp.registro_de_productos.model.SupplierModel;
import com.utp.registro_de_productos.provider.CategoryProvider;
import com.utp.registro_de_productos.provider.EmployeeProvider;
import com.utp.registro_de_productos.provider.ProductProvider;
import com.utp.registro_de_productos.provider.SupplierProvider;
import io.vavr.control.Either;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

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
    
    public void generateEmpleoyeeReport(String path) throws FileNotFoundException, IOException {       
        Either<String, ArrayList<EmployeeModel>> response = new EmployeeProvider().getEmployees();
        if (response.isRight()) {
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet("Empleados");
            
            ArrayList<EmployeeModel> employees = response.get();
            
            int rowCount = 0;
            
            Row header = sheet.createRow(0);
            Cell _cell = header.createCell(0);
            _cell.setCellValue("Id");

            _cell = header.createCell(1);
            _cell.setCellValue("Apellido");

            _cell = header.createCell(2);
            _cell.setCellValue("Nombres");

            _cell = header.createCell(3);
            _cell.setCellValue("Tipo de documento");

            _cell = header.createCell(4);
            _cell.setCellValue("Numero de documento");
 
            for (EmployeeModel employee : employees) {
                Row row = sheet.createRow(++rowCount);
                Cell cell = row.createCell(0);
                cell.setCellValue(employee.getId());

                cell = row.createCell(1);
                cell.setCellValue(employee.getLastname());

                cell = row.createCell(2);
                cell.setCellValue(employee.getName());
                
                cell = row.createCell(3);
                cell.setCellValue(employee.getDocumentType());
                
                cell = row.createCell(4);
                cell.setCellValue(employee.getDocumentNumber());
            }
            
            try (FileOutputStream outputStream = new FileOutputStream(path + "/reporte_de_empleados.xlsx")) {
                workbook.write(outputStream);
            }
            
        } else {
            JOptionPane.showMessageDialog(null, response.left().get(), "Advertencia", JOptionPane.ERROR_MESSAGE);
        }
    }  

}
