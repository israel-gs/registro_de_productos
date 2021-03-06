/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.utp.registro_de_productos;

import com.formdev.flatlaf.FlatLightLaf;
import com.utp.registro_de_productos.controller.OutgoingInventoryController;
import com.utp.registro_de_productos.controller.ProductController;
import com.utp.registro_de_productos.controller.SupplierNotificationController;
import com.utp.registro_de_productos.core.TextPrompt;
import com.utp.registro_de_productos.model.CategoryModel;
import com.utp.registro_de_productos.model.OutgoingInventoryModel;
import com.utp.registro_de_productos.model.OutgoingInventoryProductModel;
import com.utp.registro_de_productos.model.ProductModel;
import com.utp.registro_de_productos.model.SupplierModel;
import com.utp.registro_de_productos.model.SupplierNotificationModel;
import com.utp.registro_de_productos.model.SupplierNotificationProductModel;
import com.utp.registro_de_productos.model.UserModel;
import com.utp.registro_de_productos.provider.OutgoingInventoryProvider;
import com.utp.registro_de_productos.provider.SupplierNotificationProvider;
import io.vavr.control.Either;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
public class AddOutgoingInventorySreen extends javax.swing.JFrame {

    SupplierModel supplier = new SupplierModel();
    ProductModel product = new ProductModel();
    ArrayList<SupplierNotificationProductModel> supplierNotificationProducts = new ArrayList<>();

    public AddOutgoingInventorySreen() {
        initComponents();
        UUID uuid = UUID.randomUUID();
        idTextField.setText(uuid.toString());
        productComboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                product = ProductModel.class.cast(productComboBox.getSelectedItem());
            }
        });
        
        TextPrompt placeholder = new TextPrompt("dd/mm/yyyy", birthdateTextField);
        placeholder.changeAlpha(0.75f);
        placeholder.changeStyle(Font.ITALIC);
        
        loadProducts();
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
        productComboBox = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        supplierNotificationProductTable = new javax.swing.JTable();
        quantitySpinner = new javax.swing.JSpinner();
        jLabel9 = new javax.swing.JLabel();
        addProductButton = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        birthdateTextField = new javax.swing.JFormattedTextField();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        origenTextArea = new javax.swing.JTextArea();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        destinoTextArea = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        jLabel1.setText("Registrar Gu??a de Remisi??n");

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
        addProductButton.setText("A??adir Producto");
        addProductButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addProductButtonActionPerformed(evt);
            }
        });

        jLabel6.setText("Fecha");

        birthdateTextField.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("dd/MM/yyy"))));
        birthdateTextField.setToolTipText("dd/mm/yyyy");
        birthdateTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                birthdateTextFieldActionPerformed(evt);
            }
        });

        jLabel3.setText("Direcci??n origen");

        origenTextArea.setColumns(20);
        origenTextArea.setRows(5);
        jScrollPane2.setViewportView(origenTextArea);

        jLabel4.setText("Direcci??n Destino");

        destinoTextArea.setColumns(20);
        destinoTextArea.setRows(5);
        jScrollPane3.setViewportView(destinoTextArea);

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
                            .addComponent(quantitySpinner)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel6))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(birthdateTextField)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 583, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(saveButton, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(cancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(addProductButton)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jScrollPane3))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(idTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(birthdateTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(saveButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void loadProducts() {
        Either<String, DefaultComboBoxModel> response = new OutgoingInventoryController().onLoadProducts();
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
        int option = JOptionPane.showConfirmDialog(null, "??Est?? seguro de registrar esta guia?", "", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (option == JOptionPane.YES_OPTION) {
            
            Date birthday = new Date();
            try {
                birthday = new SimpleDateFormat("dd/MM/yyyy").parse(birthdateTextField.getText());
            } catch (ParseException ex) {
                System.out.println("Error en la fecha");
            } 
            
            ArrayList<OutgoingInventoryProductModel> outgoingInventoryProducts = new ArrayList();
            
            for (SupplierNotificationProductModel p : supplierNotificationProducts) {
                outgoingInventoryProducts.add(new OutgoingInventoryProductModel(
                        "", idTextField.getText(), p.getQuantity(), p.getProduct().getId(), "", "", 0.0, "", "")
                );
            }

            OutgoingInventoryModel outgoingInventoryModel = new OutgoingInventoryModel(
                    idTextField.getText(), 
                    origenTextArea.getText(), 
                    destinoTextArea.getText(), 
                    birthday, 
                    new UserModel("ea880f99-e69f-4557-9b72-0133fda7ca5f", null, null, null, false), 
                    outgoingInventoryProducts);
            
            try {
                new OutgoingInventoryProvider().insertOutgoingInventory(outgoingInventoryModel);
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

    private void birthdateTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_birthdateTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_birthdateTextFieldActionPerformed

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
                new AddOutgoingInventorySreen().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addProductButton;
    private javax.swing.JFormattedTextField birthdateTextField;
    private javax.swing.JButton cancelButton;
    private javax.swing.JTextArea destinoTextArea;
    private javax.swing.JTextField idTextField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextArea origenTextArea;
    private javax.swing.JComboBox<CategoryModel> productComboBox;
    private javax.swing.JSpinner quantitySpinner;
    private javax.swing.JButton saveButton;
    private javax.swing.JTable supplierNotificationProductTable;
    // End of variables declaration//GEN-END:variables
}
