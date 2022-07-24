/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.utp.registro_de_productos;

import com.formdev.flatlaf.FlatLightLaf;
import com.utp.registro_de_productos.controller.ProductController;
import com.utp.registro_de_productos.controller.SupplierNotificationController;
import com.utp.registro_de_productos.model.CategoryModel;
import com.utp.registro_de_productos.model.ProductModel;
import com.utp.registro_de_productos.model.SupplierModel;
import com.utp.registro_de_productos.model.SupplierNotificationModel;
import com.utp.registro_de_productos.model.SupplierNotificationProductModel;
import com.utp.registro_de_productos.provider.SupplierNotificationProvider;
import io.vavr.control.Either;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author israelgutierrez
 */
public class AddSupplierNotificationSreen extends javax.swing.JFrame {

    SupplierModel supplier = new SupplierModel();
    ProductModel product = new ProductModel();
    ArrayList<SupplierNotificationProductModel> supplierNotificationProducts = new ArrayList<>();

    public AddSupplierNotificationSreen() {
        initComponents();
        UUID uuid = UUID.randomUUID();
        idTextField.setText(uuid.toString());
        supplierComboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                supplier = SupplierModel.class.cast(supplierComboBox.getSelectedItem());
                loadProducts(supplier.getID());
                supplierNotificationProducts = new ArrayList<>();
                loadTable();
            }
        });
        productComboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                product = ProductModel.class.cast(productComboBox.getSelectedItem());
            }
        });
        loadSuppliers();
        loadTable();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        idTextField = new javax.swing.JTextField();
        saveButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        productComboBox = new javax.swing.JComboBox<>();
        supplierComboBox = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        supplierNotificationProductTable = new javax.swing.JTable();
        quantitySpinner = new javax.swing.JSpinner();
        jLabel9 = new javax.swing.JLabel();
        addProductButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        jLabel1.setText("Enviar notificación al proveedor");

        jLabel2.setText("ID");

        idTextField.setEnabled(false);

        saveButton.setText("Enviar");
        saveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveButtonActionPerformed(evt);
            }
        });

        cancelButton.setText("Cancelar");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        jLabel7.setText("Producto");

        jLabel8.setText("Proveedor");

        supplierNotificationProductTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(supplierNotificationProductTable);

        jLabel9.setText("Cantidad");

        addProductButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/plus.png"))); // NOI18N
        addProductButton.setText("Añadir Producto");
        addProductButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addProductButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(productComboBox, 0, 277, Short.MAX_VALUE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1)
                            .addComponent(jLabel7)
                            .addComponent(idTextField))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(supplierComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(quantitySpinner)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel9)
                                        .addGap(0, 0, Short.MAX_VALUE)))
                                .addContainerGap())))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 583, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(saveButton, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(cancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(addProductButton))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(idTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(supplierComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(productComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(quantitySpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(addProductButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(saveButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void loadSuppliers() {
        Either<String, DefaultComboBoxModel> suppliersResponse = new SupplierNotificationController().onLoadSuppliers();
        if (suppliersResponse.isRight()) {
            supplierComboBox.setModel(suppliersResponse.right().get());
            supplier = SupplierModel.class.cast(supplierComboBox.getSelectedItem());
            loadProducts(supplier.getID());
        } else {
            JOptionPane.showMessageDialog(null, suppliersResponse.left().get());
        }
    }

    private void loadProducts(String supplierId) {
        Either<String, DefaultComboBoxModel> response = new SupplierNotificationController().onLoadProducts(supplierId);
        if (response.isRight()) {
            productComboBox.setModel(response.right().get());
            product = ProductModel.class.cast(productComboBox.getSelectedItem());
        } else {
            JOptionPane.showMessageDialog(null, response.left().get());
        }
    }
    
    private void loadTable() {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Nombre");
        model.addColumn("Cantidad");
        for (SupplierNotificationProductModel supplierNotificationProduct : supplierNotificationProducts) {
            model.addRow(new Object[]{
                supplierNotificationProduct.getProduct().getName(),
                supplierNotificationProduct.getQuantity().toString()
            });
        }
        supplierNotificationProductTable.setModel(model);
    }

    private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveButtonActionPerformed
        int option = JOptionPane.showConfirmDialog(null, "¿Está seguro de envíar esta notificación?", "", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (option == JOptionPane.YES_OPTION) {
            
            SupplierNotificationModel supplierNotification = new SupplierNotificationModel();
            
            supplierNotification.setDate(new Date());
            supplierNotification.setId(idTextField.getText());
            supplierNotification.setSupplier(supplier);
            supplierNotification.setSupplierNotificationProducts(supplierNotificationProducts);
            
            try {
                new SupplierNotificationProvider().sendEmail(supplierNotification);
            } catch (Exception ex) {
                System.out.println("----->>>>>" + ex);
            }
            
            this.dispose();
        }
    }//GEN-LAST:event_saveButtonActionPerformed

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        this.dispose();
    }//GEN-LAST:event_cancelButtonActionPerformed

    private void addProductButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addProductButtonActionPerformed
        int quantity = (Integer) quantitySpinner.getValue();
        if (quantity > 0) {
            SupplierNotificationProductModel supplierNotificationProduct = new SupplierNotificationProductModel(
            idTextField.getText(), product, quantity);
            supplierNotificationProducts.add(supplierNotificationProduct);
            loadTable();
        } else {
            JOptionPane.showMessageDialog(null, "La cantidad debe ser mayor a cero", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_addProductButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (Exception ex) {
            System.err.println("Failed to initialize LaF");
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AddSupplierNotificationSreen().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addProductButton;
    private javax.swing.JButton cancelButton;
    private javax.swing.JTextField idTextField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox<CategoryModel> productComboBox;
    private javax.swing.JSpinner quantitySpinner;
    private javax.swing.JButton saveButton;
    private javax.swing.JComboBox<SupplierModel> supplierComboBox;
    private javax.swing.JTable supplierNotificationProductTable;
    // End of variables declaration//GEN-END:variables
}
