package Interfaz;

import Logica.ConexionSQL;
import Logica.DetalleVenta;
import java.util.List;
import javax.swing.table.DefaultTableModel;

public final class Detalles extends javax.swing.JFrame {
    
    public int id_venta;

    DefaultTableModel modelo = new DefaultTableModel();
    DetalleVenta dv = new DetalleVenta();

    public Detalles(int id_venta) {
        this.id_venta = id_venta;
        System.out.println(id_venta);
 
        initComponents();
        DefaultTableModel modelo = new DefaultTableModel();
        modelo = (DefaultTableModel) tabla.getModel();
        ListarDetalles();
        imagen();
        
    }
    public Detalles(){
    }
    
    // Método para obtener los detalles de la venta desde la base de datos
    public void ListarDetalles() {
        
        List<DetalleVenta> listar_venta = dv.obtenerDetallesVenta(id_venta);
        modelo = (DefaultTableModel) tabla.getModel();

        Object[] obj = new Object[5];

        for (int i = 0; i < listar_venta.size(); i++) {
            
            obj[0] = listar_venta.get(i).getId_producto();
            obj[1] = listar_venta.get(i).getNom_pd();
            obj[2] = listar_venta.get(i).getCantidad();
            obj[3] = listar_venta.get(i).getPrecio_u();
            obj[4] = listar_venta.get(i).getTotal();

            
            modelo.addRow(obj);
        }
        
        tabla.setModel(modelo);

    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lblbienvenido = new javax.swing.JLabel();
        btn_ingresar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(0, 153, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblbienvenido.setBackground(new java.awt.Color(255, 255, 255));
        lblbienvenido.setFont(new java.awt.Font("Hack Nerd Font", 1, 36)); // NOI18N
        lblbienvenido.setForeground(new java.awt.Color(204, 204, 204));
        lblbienvenido.setText("Detalles de la venta");
        jPanel1.add(lblbienvenido, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 20, 460, 50));

        btn_ingresar.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btn_ingresar.setText("Cerrar");
        btn_ingresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ingresarActionPerformed(evt);
            }
        });
        jPanel1.add(btn_ingresar, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 440, 100, 30));

        tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id Producto", "Producto", "Cantidad", "Precio U.", "Total"
            }
        ));
        jScrollPane1.setViewportView(tabla);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, 530, 350));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 590, 480));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_ingresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ingresarActionPerformed
        dispose();
    }//GEN-LAST:event_btn_ingresarActionPerformed

    public void imagen() {
    
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
            java.util.logging.Logger.getLogger(Detalles.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Detalles.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Detalles.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Detalles.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                 new Detalles().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_ingresar;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblbienvenido;
    private javax.swing.JTable tabla;
    // End of variables declaration//GEN-END:variables
}
