package Interfaz;

import Logica.Venta;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


public class Registro_Ventas extends javax.swing.JFrame {

    public Registro_Ventas() {
        initComponents();
        imagen();
    }

    @SuppressWarnings("unchecked")
    
    DefaultTableModel modelo = new DefaultTableModel();//objeto de tipo modelo
    Venta vt = new Venta();//Objeto de venta para usar sus metodos
    Object[] obj = new Object[5]; //Guarda datos de venta


    //Muestra los datos de la venta en la interfaz
    public void ListarVentas() {
        String value = txt_v_value.getText();//El parametro a buscar
        String filtro = (String) filtrador.getSelectedItem();//Campo filtro
        
        List<Venta> listar_ventas = vt.Buscar_venta(filtro, value);
        modelo = (DefaultTableModel) tabla.getModel();

        for (int i = 0; i < listar_ventas.size(); i++) {
            //i representa la fila de una venta
            obj[0] = listar_ventas.get(i).getId_venta();
            obj[1] = listar_ventas.get(i).getNombre_vendedor();
            obj[2] = listar_ventas.get(i).getNombre_cliente();
            obj[3] = listar_ventas.get(i).getTotal();
            obj[4] = listar_ventas.get(i).getFecha();
            
            modelo.addRow(obj);//Se agrega datos a la lista de objetos
        }
        
        tabla.setModel(modelo);//Se muestra datos
    }
    //Limpiar tabla
    public void LimpiarTabla(){
        int rowCount = modelo.getRowCount();//Obtenemos numero filas
        for (int i = rowCount - 1; i >= 0; i--) {//Recorre desde la ultima fila
            modelo.removeRow(i);//Elimina fila
        }
    }
    //Metodo para obtener el ID Venta
    public int ObtenerIdVenta() {
        int idVenta = 0;
        modelo = (DefaultTableModel) tabla.getModel();//Se obtiene el modelo de datos de la tabla
        int filaSeleccionada = tabla.getSelectedRow();//por defecto -1
        if (filaSeleccionada >= 0) {
            //Se obtiene el dato de la primera comuna de la tabla (ID VENTA)
            idVenta = Integer.parseInt(modelo.getValueAt(filaSeleccionada, 0).toString());
            System.out.println(idVenta);
            
        } else {
            JOptionPane.showMessageDialog(null, "Ninguna fila seleccionada.");
        }
        return idVenta;
    }
    public boolean filaSeleccionada() {
        boolean siSelecciono = false;
        modelo = (DefaultTableModel) tabla.getModel();//Se obtiene el modelo de datos de la tabla
        int filaSeleccionada = tabla.getSelectedRow();//por defecto -1
        if (filaSeleccionada >= 0) {
            siSelecciono = true;
        }
        return siSelecciono;
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        iconregistro = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        txt_datos_venta = new javax.swing.JLabel();
        aceptar_salir = new javax.swing.JButton();
        buscador_ventas = new javax.swing.JButton();
        ver_detalles = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        filtrador = new javax.swing.JComboBox<>();
        txt_v_value = new javax.swing.JTextField();
        fondoregistro = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(0, 153, 153));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 850, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 850, 40));

        jPanel3.setBackground(new java.awt.Color(0, 0, 0));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 120, 80));

        jPanel4.setBackground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 120, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 80, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 40, 120, 80));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setBackground(new java.awt.Color(0, 0, 0));
        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel1.setText("REGISTRO DE VENTAS ");
        jPanel5.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 0, 410, 80));
        jPanel5.add(iconregistro, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 0, 90, 80));

        jPanel1.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 40, 610, 80));

        jPanel6.setBackground(new java.awt.Color(0, 153, 153));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel1.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 120, 850, 40));

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));

        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID_Venta", "Empleado", "Cliente", "Total", "Fecha_Venta"
            }
        ));
        tabla.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tabla.setShowGrid(false);
        jScrollPane1.setViewportView(tabla);

        jPanel7.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 130, 640, 350));

        txt_datos_venta.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txt_datos_venta.setText("DATOS DE LAS VENTAS");
        jPanel7.add(txt_datos_venta, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 130, 40));

        aceptar_salir.setBackground(new java.awt.Color(0, 153, 153));
        aceptar_salir.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        aceptar_salir.setForeground(new java.awt.Color(255, 255, 255));
        aceptar_salir.setText("ACEPTAR/SALIR ");
        aceptar_salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aceptar_salirActionPerformed(evt);
            }
        });
        jPanel7.add(aceptar_salir, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 430, 130, 40));

        buscador_ventas.setText("Buscar");
        buscador_ventas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscador_ventasActionPerformed(evt);
            }
        });
        jPanel7.add(buscador_ventas, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 90, -1, -1));

        ver_detalles.setText("Ver mas");
        ver_detalles.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ver_detallesActionPerformed(evt);
            }
        });
        jPanel7.add(ver_detalles, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 150, 100, 30));

        jLabel4.setText("Filtrar por:");

        filtrador.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todo", "Id Venta", "Id Vendedor", "N° Cliente", "Fecha" }));

        txt_v_value.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_v_valueActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(filtrador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)
                        .addComponent(txt_v_value, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel4))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_v_value, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(filtrador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jPanel7.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 50, 290, 80));
        jPanel7.add(fondoregistro, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 840, 500));

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(24, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 160, 850, 530));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void aceptar_salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aceptar_salirActionPerformed

        Menu_Opciones salir = new Menu_Opciones();
        salir.setVisible(true);
        dispose();
    }//GEN-LAST:event_aceptar_salirActionPerformed

    private void txt_v_valueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_v_valueActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_v_valueActionPerformed

    private void buscador_ventasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscador_ventasActionPerformed
        LimpiarTabla();
        ListarVentas();
    }//GEN-LAST:event_buscador_ventasActionPerformed

    private void ver_detallesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ver_detallesActionPerformed
        int id_venta = ObtenerIdVenta();
        Detalles detalles = new Detalles(id_venta);
        if(filaSeleccionada()){
            detalles.setVisible(true);
        }else{
            JOptionPane.showMessageDialog(null, "Seleccione una venta");
        }
        
        
    }//GEN-LAST:event_ver_detallesActionPerformed

    
    public void imagen(){
        ImageIcon IconR = new ImageIcon("Imagenes/registrar.png");
        iconregistro.setIcon(IconR);    
        ImageIcon fondoR = new ImageIcon("Imagenes/fondorombo.jpg");
        fondoregistro.setIcon(fondoR);
    }

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Registro_Ventas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Registro_Ventas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Registro_Ventas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Registro_Ventas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Registro_Ventas().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton aceptar_salir;
    private javax.swing.JButton buscador_ventas;
    private javax.swing.JComboBox<String> filtrador;
    private javax.swing.JLabel fondoregistro;
    private javax.swing.JLabel iconregistro;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabla;
    private javax.swing.JLabel txt_datos_venta;
    private javax.swing.JTextField txt_v_value;
    private javax.swing.JButton ver_detalles;
    // End of variables declaration//GEN-END:variables

}
