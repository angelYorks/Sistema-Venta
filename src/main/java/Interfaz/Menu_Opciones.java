package Interfaz;

import Logica.ConexionSQL;
import Logica.Empleado;
import javax.swing.ImageIcon;

public class Menu_Opciones extends javax.swing.JFrame {
    public String id;
    public Menu_Opciones(String id) {
        this.id = id;
        initComponents();
        imagen();
        
    }
    public Menu_Opciones() {

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        iconv = new javax.swing.JLabel();
        iconstock = new javax.swing.JLabel();
        iconregistro = new javax.swing.JLabel();
        iconcerrar = new javax.swing.JLabel();
        BTN_REGISTRO = new javax.swing.JButton();
        BTN_INGRESO = new javax.swing.JButton();
        BTN_CERRAR = new javax.swing.JButton();
        BTN_STOCK = new javax.swing.JButton();
        fondot = new javax.swing.JLabel();
        fondoooo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 0, 51));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(0, 102, 153));
        jPanel2.setForeground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Lucida Sans", 1, 40)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("MENU DE OPCIONES");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 20, 430, 62));
        jPanel2.add(iconv, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 100, 60, 50));
        jPanel2.add(iconstock, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 160, 60, 50));
        jPanel2.add(iconregistro, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 220, 60, 50));
        jPanel2.add(iconcerrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 300, 60, 60));

        BTN_REGISTRO.setBackground(new java.awt.Color(255, 255, 255));
        BTN_REGISTRO.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        BTN_REGISTRO.setForeground(new java.awt.Color(0, 0, 0));
        BTN_REGISTRO.setText("     REGISTROS DE VENTAS ");
        BTN_REGISTRO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_REGISTROActionPerformed(evt);
            }
        });
        jPanel2.add(BTN_REGISTRO, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 220, 230, 50));

        BTN_INGRESO.setBackground(new java.awt.Color(255, 255, 255));
        BTN_INGRESO.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        BTN_INGRESO.setForeground(new java.awt.Color(0, 0, 0));
        BTN_INGRESO.setText("     INGRESO DE VENTA");
        BTN_INGRESO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_INGRESOActionPerformed(evt);
            }
        });
        jPanel2.add(BTN_INGRESO, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, 230, 50));

        BTN_CERRAR.setBackground(new java.awt.Color(255, 255, 255));
        BTN_CERRAR.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        BTN_CERRAR.setForeground(new java.awt.Color(0, 0, 0));
        BTN_CERRAR.setText("       CERRAR SESIÓN ");
        BTN_CERRAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_CERRARActionPerformed(evt);
            }
        });
        jPanel2.add(BTN_CERRAR, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 300, 200, 60));

        BTN_STOCK.setBackground(new java.awt.Color(255, 255, 255));
        BTN_STOCK.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        BTN_STOCK.setForeground(new java.awt.Color(0, 0, 0));
        BTN_STOCK.setText("      STOCKS DISPONIBLES");
        BTN_STOCK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_STOCKActionPerformed(evt);
            }
        });
        jPanel2.add(BTN_STOCK, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 160, 230, 50));

        fondot.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel2.add(fondot, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 100, 260, 260));
        jPanel2.add(fondoooo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 620, 390));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 620, 390));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 664, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 431, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void BTN_STOCKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_STOCKActionPerformed
        //Abre la ventana de stock disponible
        Stock Stock = new Stock();
        Stock.setVisible(true);
        
    }//GEN-LAST:event_BTN_STOCKActionPerformed

    private void BTN_CERRARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_CERRARActionPerformed
        //Regresa al login
        Ingreso ingreso = new Ingreso();
        ingreso.setVisible(true);
        ConexionSQL sql = new ConexionSQL();
        sql.CerrarSesionSQL(); //Cierra BD
        dispose(); //Cierra ventana
    }//GEN-LAST:event_BTN_CERRARActionPerformed

    private void BTN_INGRESOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_INGRESOActionPerformed
        //Muestra ventana ingreso Venta
        Ingresar_venta ingreso = new Ingresar_venta(id);
        ingreso.setVisible(true);
        dispose();
        
    }//GEN-LAST:event_BTN_INGRESOActionPerformed

    private void BTN_REGISTROActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_REGISTROActionPerformed
        //Muestra venta Registro Venta
        Registro_Ventas registro = new Registro_Ventas();
        registro.setVisible(true);
        dispose();
    }//GEN-LAST:event_BTN_REGISTROActionPerformed

    public void imagen() {
        ImageIcon Fondo2 = new ImageIcon("Imagenes/FondoAbstracto.jpeg");
        fondoooo.setIcon(Fondo2);
        ImageIcon Iconv = new ImageIcon("Imagenes/venta.png");
        iconv.setIcon(Iconv);
        ImageIcon Icons = new ImageIcon("Imagenes/inventario.png");
        iconstock.setIcon(Icons);
        ImageIcon Iconr = new ImageIcon("Imagenes/comprobante.png");
        iconregistro.setIcon(Iconr);
        ImageIcon Iconc = new ImageIcon("Imagenes/cerrar.png");
        iconcerrar.setIcon(Iconc);
        ImageIcon Fondot = new ImageIcon("Imagenes/Logo.jpg");
        fondot.setIcon(Fondot);
                
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
            java.util.logging.Logger.getLogger(Menu_Opciones.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Menu_Opciones.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Menu_Opciones.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Menu_Opciones.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Menu_Opciones().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BTN_CERRAR;
    private javax.swing.JButton BTN_INGRESO;
    private javax.swing.JButton BTN_REGISTRO;
    private javax.swing.JButton BTN_STOCK;
    private javax.swing.JLabel fondoooo;
    private javax.swing.JLabel fondot;
    private javax.swing.JLabel iconcerrar;
    private javax.swing.JLabel iconregistro;
    private javax.swing.JLabel iconstock;
    private javax.swing.JLabel iconv;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    // End of variables declaration//GEN-END:variables
}
