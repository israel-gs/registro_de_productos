/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.utp.registro_de_productos;

import com.formdev.flatlaf.FlatLightLaf;
import com.utp.registro_de_productos.controller.CategoryController;
import com.utp.registro_de_productos.model.CategoryModel;
import com.utp.registro_de_productos.model.UserModel;
import io.vavr.control.Either;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Locale;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.TableModel;

/**
 *
 * @author israelgutierrez
 */
public class MainScreen extends javax.swing.JFrame {
    UserModel user;
    /**
     * Creates new form MainScreen
     */
    public MainScreen(UserModel user) {
        initComponents();
        this.user = user;
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
        jButton5 = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        productsPanel = new javax.swing.JPanel();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        peoplePanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        registerCategoryPanel = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        categoryTable = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        addButton = new javax.swing.JButton();
        editButton = new javax.swing.JButton();
        deleteButton = new javax.swing.JButton();
        peoplePanel3 = new javax.swing.JPanel();
        peoplePanel = new javax.swing.JPanel();
        jTabbedPane3 = new javax.swing.JTabbedPane();
        peoplePanel4 = new javax.swing.JPanel();
        peoplePanel5 = new javax.swing.JPanel();
        reportsPanel = new javax.swing.JPanel();
        configurationPanel = new javax.swing.JPanel();
        controlPanel = new javax.swing.JPanel();
        jTabbedPane4 = new javax.swing.JTabbedPane();
        peoplePanel6 = new javax.swing.JPanel();
        peoplePanel7 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Helvetica Neue", 0, 24)); // NOI18N
        jLabel1.setText("Control de Inventario");

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/utp/registro_de_productos/assets/icons/sign-out-alt.png"))); // NOI18N
        jButton5.setText("Cerrar Sesion");

        jTabbedPane1.setTabPlacement(javax.swing.JTabbedPane.LEFT);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout peoplePanel1Layout = new javax.swing.GroupLayout(peoplePanel1);
        peoplePanel1.setLayout(peoplePanel1Layout);
        peoplePanel1Layout.setHorizontalGroup(
            peoplePanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 724, Short.MAX_VALUE)
        );
        peoplePanel1Layout.setVerticalGroup(
            peoplePanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(peoplePanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 322, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Registro de productos", peoplePanel1);

        registerCategoryPanel.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentHidden(java.awt.event.ComponentEvent evt) {
                registerCategoryPanelComponentHidden(evt);
            }
            public void componentShown(java.awt.event.ComponentEvent evt) {
                registerCategoryPanelComponentShown(evt);
            }
        });

        categoryTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Id", "Nombre", "Descripcion"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        categoryTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                categoryTableMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(categoryTable);
        categoryTable.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        if (categoryTable.getColumnModel().getColumnCount() > 0) {
            categoryTable.getColumnModel().getColumn(2).setResizable(false);
        }

        addButton.setText("Añadir");

        editButton.setText("Editar");
        editButton.setEnabled(false);
        editButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editButtonActionPerformed(evt);
            }
        });

        deleteButton.setText("Eliminar");
        deleteButton.setEnabled(false);
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(addButton)
                .addGap(18, 18, 18)
                .addComponent(editButton)
                .addGap(18, 18, 18)
                .addComponent(deleteButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(addButton, javax.swing.GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE)
                    .addComponent(deleteButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(editButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout registerCategoryPanelLayout = new javax.swing.GroupLayout(registerCategoryPanel);
        registerCategoryPanel.setLayout(registerCategoryPanelLayout);
        registerCategoryPanelLayout.setHorizontalGroup(
            registerCategoryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 724, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        registerCategoryPanelLayout.setVerticalGroup(
            registerCategoryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, registerCategoryPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Registro de categorias", registerCategoryPanel);

        javax.swing.GroupLayout peoplePanel3Layout = new javax.swing.GroupLayout(peoplePanel3);
        peoplePanel3.setLayout(peoplePanel3Layout);
        peoplePanel3Layout.setHorizontalGroup(
            peoplePanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 724, Short.MAX_VALUE)
        );
        peoplePanel3Layout.setVerticalGroup(
            peoplePanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 321, Short.MAX_VALUE)
        );

        jTabbedPane2.addTab("Registro de proveedores", peoplePanel3);

        javax.swing.GroupLayout productsPanelLayout = new javax.swing.GroupLayout(productsPanel);
        productsPanel.setLayout(productsPanelLayout);
        productsPanelLayout.setHorizontalGroup(
            productsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, productsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane2))
        );
        productsPanelLayout.setVerticalGroup(
            productsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(productsPanelLayout.createSequentialGroup()
                .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 354, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 2, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Productos", productsPanel);

        javax.swing.GroupLayout peoplePanel4Layout = new javax.swing.GroupLayout(peoplePanel4);
        peoplePanel4.setLayout(peoplePanel4Layout);
        peoplePanel4Layout.setHorizontalGroup(
            peoplePanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 724, Short.MAX_VALUE)
        );
        peoplePanel4Layout.setVerticalGroup(
            peoplePanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 323, Short.MAX_VALUE)
        );

        jTabbedPane3.addTab("Registro de empleados", peoplePanel4);

        javax.swing.GroupLayout peoplePanel5Layout = new javax.swing.GroupLayout(peoplePanel5);
        peoplePanel5.setLayout(peoplePanel5Layout);
        peoplePanel5Layout.setHorizontalGroup(
            peoplePanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 724, Short.MAX_VALUE)
        );
        peoplePanel5Layout.setVerticalGroup(
            peoplePanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 323, Short.MAX_VALUE)
        );

        jTabbedPane3.addTab("Registro de usuarios", peoplePanel5);

        javax.swing.GroupLayout peoplePanelLayout = new javax.swing.GroupLayout(peoplePanel);
        peoplePanel.setLayout(peoplePanelLayout);
        peoplePanelLayout.setHorizontalGroup(
            peoplePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, peoplePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane3))
        );
        peoplePanelLayout.setVerticalGroup(
            peoplePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane3)
        );

        jTabbedPane1.addTab("Personas", peoplePanel);

        javax.swing.GroupLayout reportsPanelLayout = new javax.swing.GroupLayout(reportsPanel);
        reportsPanel.setLayout(reportsPanelLayout);
        reportsPanelLayout.setHorizontalGroup(
            reportsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 730, Short.MAX_VALUE)
        );
        reportsPanelLayout.setVerticalGroup(
            reportsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 356, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Reportes", reportsPanel);

        javax.swing.GroupLayout configurationPanelLayout = new javax.swing.GroupLayout(configurationPanel);
        configurationPanel.setLayout(configurationPanelLayout);
        configurationPanelLayout.setHorizontalGroup(
            configurationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 730, Short.MAX_VALUE)
        );
        configurationPanelLayout.setVerticalGroup(
            configurationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 356, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Configuración", configurationPanel);

        javax.swing.GroupLayout peoplePanel6Layout = new javax.swing.GroupLayout(peoplePanel6);
        peoplePanel6.setLayout(peoplePanel6Layout);
        peoplePanel6Layout.setHorizontalGroup(
            peoplePanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 724, Short.MAX_VALUE)
        );
        peoplePanel6Layout.setVerticalGroup(
            peoplePanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 323, Short.MAX_VALUE)
        );

        jTabbedPane4.addTab("Control de productos", peoplePanel6);

        javax.swing.GroupLayout peoplePanel7Layout = new javax.swing.GroupLayout(peoplePanel7);
        peoplePanel7.setLayout(peoplePanel7Layout);
        peoplePanel7Layout.setHorizontalGroup(
            peoplePanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 724, Short.MAX_VALUE)
        );
        peoplePanel7Layout.setVerticalGroup(
            peoplePanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 323, Short.MAX_VALUE)
        );

        jTabbedPane4.addTab("Control de empleados", peoplePanel7);

        javax.swing.GroupLayout controlPanelLayout = new javax.swing.GroupLayout(controlPanel);
        controlPanel.setLayout(controlPanelLayout);
        controlPanelLayout.setHorizontalGroup(
            controlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, controlPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane4))
        );
        controlPanelLayout.setVerticalGroup(
            controlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane4)
        );

        jTabbedPane1.addTab("Control", controlPanel);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(243, 243, 243))
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 356, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void loadCategoryTable() {
        categoryTable.setVisible(false);
        Either<String, TableModel> response = new CategoryController().onLoad();
        if (response.isRight()) {
            categoryTable.setModel(response.right().get());
        }
        if (response.isLeft()) {
            JOptionPane.showMessageDialog(null, response.left().get(), "Advertencia", JOptionPane.ERROR_MESSAGE);
        }
        categoryTable.setVisible(true);
    }
    
    private void registerCategoryPanelComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_registerCategoryPanelComponentShown
        this.loadCategoryTable();
    }//GEN-LAST:event_registerCategoryPanelComponentShown

    private void categoryTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_categoryTableMouseClicked
        System.out.println(evt.getClickCount());
        if (evt.getClickCount() == 1) {
            editButton.setEnabled(true);
            deleteButton.setEnabled(true);
            // JTable target = (JTable) evt.getSource();
            // int row = target.getSelectedRow();
            // System.out.println(row);
        }
    }//GEN-LAST:event_categoryTableMouseClicked

    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButtonActionPerformed
        // TODO add your handling code here:
        int option = JOptionPane.showConfirmDialog(null, "¿Está seguro de eliminar esta categoría?", "", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (option == JOptionPane.YES_OPTION) {
            int selectedRow = categoryTable.getSelectedRow();
            String categoryId = categoryTable.getValueAt(selectedRow, 0).toString();
            Either<String, String> response = new CategoryController().onDelete(categoryId);
            if (response.isRight()) {
                JOptionPane.showMessageDialog(null, response.right().get());
                this.loadCategoryTable();
            } else {
                JOptionPane.showMessageDialog(null, response.left().get());
            }
        }
    }//GEN-LAST:event_deleteButtonActionPerformed

    private void registerCategoryPanelComponentHidden(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_registerCategoryPanelComponentHidden
        // TODO add your handling code here:
        editButton.setEnabled(false);
        deleteButton.setEnabled(false);
    }//GEN-LAST:event_registerCategoryPanelComponentHidden

    private void editButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editButtonActionPerformed
        // TODO add your handling code here:
        CategoryModel categoryModel = new CategoryModel();
        int selectedRow = categoryTable.getSelectedRow();
        categoryModel.setID(categoryTable.getValueAt(selectedRow, 0).toString());
        categoryModel.setName(categoryTable.getValueAt(selectedRow, 1).toString());
        categoryModel.setDescription(categoryTable.getValueAt(selectedRow, 2).toString());
        EditCategorySreen editCategorySreen = new EditCategorySreen(categoryModel);
        editCategorySreen.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                loadCategoryTable();
                editButton.setEnabled(false);
                deleteButton.setEnabled(false);
            }
        });
        editCategorySreen.pack();
        editCategorySreen.setResizable(false);
        editCategorySreen.setLocationRelativeTo(null);
        editCategorySreen.setVisible(true);
    }//GEN-LAST:event_editButtonActionPerformed

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
            UIManager.setLookAndFeel( new FlatLightLaf() );
        } catch( Exception ex ) {
            System.err.println( "Failed to initialize LaF" );
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                UserModel user = new UserModel();
                JFrame frame = new MainScreen(user);
                frame.setResizable(false);
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addButton;
    private javax.swing.JTable categoryTable;
    private javax.swing.JPanel configurationPanel;
    private javax.swing.JPanel controlPanel;
    private javax.swing.JButton deleteButton;
    private javax.swing.JButton editButton;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTabbedPane jTabbedPane3;
    private javax.swing.JTabbedPane jTabbedPane4;
    private javax.swing.JTable jTable1;
    private javax.swing.JPanel peoplePanel;
    private javax.swing.JPanel peoplePanel1;
    private javax.swing.JPanel peoplePanel3;
    private javax.swing.JPanel peoplePanel4;
    private javax.swing.JPanel peoplePanel5;
    private javax.swing.JPanel peoplePanel6;
    private javax.swing.JPanel peoplePanel7;
    private javax.swing.JPanel productsPanel;
    private javax.swing.JPanel registerCategoryPanel;
    private javax.swing.JPanel reportsPanel;
    // End of variables declaration//GEN-END:variables
}
