package Interfaz;

import Logica.Cliente;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;


public class Nuevo_cliente extends javax.swing.JFrame {

    public Nuevo_cliente() {
        initComponents();
        imagen();

    }
    public boolean IngresoCliente() {
        boolean boleando = false;
        Cliente cl = new Cliente();
        String tip_doc = (String) Tipo_doc.getSelectedItem();
        int valor_doc = 0;

        switch (tip_doc) {
            case "DNI" ->
                valor_doc = 1;
            case "RUC" ->
                valor_doc = 2;
            case "CARNET DE EXTRANJERIA" ->
                valor_doc = 3;
        }

        cl.setNombre(Txt_Nombre.getText());
        cl.setApellido(Txt_Apellido.getText());
        cl.setEmail(Txt_correo.getText());
        cl.setNum_doc(Txt_Dni.getText());
        cl.setTipo_doc(valor_doc);

        if (!"".equals(cl.getNombre()) && !"".equals(cl.getApellido()) && !"".equals(cl.getEmail()) && !"".equals(cl.getNum_doc())) {

            if (Cliente.TipoDoc(cl.getNum_doc()) == valor_doc) {

                if (Cliente.VerificarEmail(cl.getEmail())) {
                    boolean valor = cl.RegistrarCliente(cl);
                    System.out.println(valor);
                    if (valor) {
                        JOptionPane.showMessageDialog(null, "Se registro correctamente");
                        boleando = true;
                    }else {
                         JOptionPane.showMessageDialog(null, "No se registro correctamente");
                         
                    }

                } else {
                    JOptionPane.showMessageDialog(null, "Email erroneo");
                }

            } else {
                JOptionPane.showMessageDialog(null, "Numero de documento erroneo");
            }

        } else {
            JOptionPane.showMessageDialog(null, "Falta completar algun campo");
        }
        return boleando;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        Txt_Nombre = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        Txt_Apellido = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        Txt_correo = new javax.swing.JTextField();
        iconlimpiar = new javax.swing.JLabel();
        iconsalir = new javax.swing.JLabel();
        iconregistro = new javax.swing.JLabel();
        btn_limpiar = new javax.swing.JButton();
        btn_salir = new javax.swing.JButton();
        btn_siguiente = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel11 = new javax.swing.JLabel();
        Txt_Dni = new javax.swing.JTextField();
        jSeparator5 = new javax.swing.JSeparator();
        Tipo_doc = new javax.swing.JComboBox<>();
        iconusuario = new javax.swing.JLabel();
        iconusuario2 = new javax.swing.JLabel();
        iconn = new javax.swing.JLabel();
        iconinsignia = new javax.swing.JLabel();
        iconcorreo = new javax.swing.JLabel();
        fondocliente = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(249, 249, 249));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("DATOS DEL CLIENTE");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, -10, 200, 60));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("NOMBRE");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 40, -1, -1));

        Txt_Nombre.setBackground(new java.awt.Color(204, 204, 204));
        Txt_Nombre.setForeground(new java.awt.Color(0, 0, 0));
        Txt_Nombre.setBorder(null);
        Txt_Nombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Txt_NombreActionPerformed(evt);
            }
        });
        jPanel2.add(Txt_Nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 70, 210, 30));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("APELLIDOS");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 110, -1, -1));

        Txt_Apellido.setBackground(new java.awt.Color(204, 204, 204));
        Txt_Apellido.setForeground(new java.awt.Color(0, 0, 0));
        Txt_Apellido.setBorder(null);
        jPanel2.add(Txt_Apellido, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 140, 210, 30));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Nº del Documento");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 250, -1, -1));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("CORREO");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 330, -1, -1));

        Txt_correo.setBackground(new java.awt.Color(204, 204, 204));
        Txt_correo.setForeground(new java.awt.Color(0, 0, 0));
        Txt_correo.setBorder(null);
        jPanel2.add(Txt_correo, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 350, 210, 30));
        jPanel2.add(iconlimpiar, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 400, 60, 50));
        jPanel2.add(iconsalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 460, 60, 50));
        jPanel2.add(iconregistro, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 460, 70, 50));

        btn_limpiar.setBackground(new java.awt.Color(255, 255, 255));
        btn_limpiar.setForeground(new java.awt.Color(0, 0, 0));
        btn_limpiar.setText("      Limpiar");
        btn_limpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_limpiarActionPerformed(evt);
            }
        });
        jPanel2.add(btn_limpiar, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 400, 130, 50));

        btn_salir.setBackground(new java.awt.Color(255, 255, 255));
        btn_salir.setForeground(new java.awt.Color(0, 0, 0));
        btn_salir.setText("        Salir");
        btn_salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_salirActionPerformed(evt);
            }
        });
        jPanel2.add(btn_salir, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 460, 120, 50));

        btn_siguiente.setBackground(new java.awt.Color(255, 255, 255));
        btn_siguiente.setForeground(new java.awt.Color(0, 0, 0));
        btn_siguiente.setText("      Registrar");
        btn_siguiente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_siguienteActionPerformed(evt);
            }
        });
        jPanel2.add(btn_siguiente, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 460, 150, 50));

        jSeparator1.setForeground(new java.awt.Color(0, 0, 0));
        jPanel2.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 100, 220, -1));

        jSeparator2.setForeground(new java.awt.Color(0, 0, 0));
        jPanel2.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 170, 220, -1));

        jSeparator4.setForeground(new java.awt.Color(0, 0, 0));
        jPanel2.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 380, 220, 10));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("Tipo de Documento");
        jPanel2.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 180, -1, -1));

        Txt_Dni.setBackground(new java.awt.Color(204, 204, 204));
        Txt_Dni.setForeground(new java.awt.Color(0, 0, 0));
        Txt_Dni.setBorder(null);
        Txt_Dni.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Txt_DniActionPerformed(evt);
            }
        });
        jPanel2.add(Txt_Dni, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 280, 210, 30));

        jSeparator5.setForeground(new java.awt.Color(0, 0, 0));
        jPanel2.add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 310, 220, 10));

        Tipo_doc.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "DNI", "RUC", "CARNET DE EXTRANJERIA" }));
        Tipo_doc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Tipo_docActionPerformed(evt);
            }
        });
        jPanel2.add(Tipo_doc, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 216, 190, -1));
        jPanel2.add(iconusuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 70, 60, 30));
        jPanel2.add(iconusuario2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 140, 60, 30));
        jPanel2.add(iconn, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 200, 60, 50));
        jPanel2.add(iconinsignia, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 270, 60, 40));
        jPanel2.add(iconcorreo, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 350, 70, 50));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 420, 530));
        jPanel1.add(fondocliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 480, 550));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 481, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void Txt_NombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Txt_NombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Txt_NombreActionPerformed

    private void btn_siguienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_siguienteActionPerformed
        boolean ingresar_cliente = IngresoCliente();
        if(ingresar_cliente){
            dispose();
        }
        
    }//GEN-LAST:event_btn_siguienteActionPerformed

    private void btn_salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_salirActionPerformed
        dispose();
    }//GEN-LAST:event_btn_salirActionPerformed

    private void btn_limpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_limpiarActionPerformed
        Txt_Nombre.setText("");
        Txt_Apellido.setText("");
        Txt_Dni.setText("");
        Txt_correo.setText("");
    }//GEN-LAST:event_btn_limpiarActionPerformed

    private void Txt_DniActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Txt_DniActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Txt_DniActionPerformed

    private void Tipo_docActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Tipo_docActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Tipo_docActionPerformed

    public void imagen(){
        ImageIcon iconu = new ImageIcon("Imagenes/nombre.png");
        iconusuario.setIcon(iconu);
        ImageIcon iconu2 = new ImageIcon("Imagenes/nombre.png");
        iconusuario2.setIcon(iconu2);
        ImageIcon icond = new ImageIcon("Imagenes/navegador.png");
        iconn.setIcon(icond);
        ImageIcon iconi = new ImageIcon("Imagenes/insignia.png");
        iconinsignia.setIcon(iconi);
        ImageIcon iconc = new ImageIcon("Imagenes/correo.png");
        iconcorreo.setIcon(iconc);
        ImageIcon iconl = new ImageIcon("Imagenes/escoba.png");
        iconlimpiar.setIcon(iconl);
        ImageIcon icons = new ImageIcon("Imagenes/salir.png");
        iconsalir.setIcon(icons);
        ImageIcon iconr = new ImageIcon("Imagenes/flechaC.png");
        iconregistro.setIcon(iconr);
        ImageIcon fondoc = new ImageIcon("Imagenes/fondocliente.jpeg");
        fondocliente.setIcon(fondoc);
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
            java.util.logging.Logger.getLogger(Nuevo_cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Nuevo_cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Nuevo_cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Nuevo_cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Nuevo_cliente().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> Tipo_doc;
    private javax.swing.JTextField Txt_Apellido;
    private javax.swing.JTextField Txt_Dni;
    private javax.swing.JTextField Txt_Nombre;
    private javax.swing.JTextField Txt_correo;
    private javax.swing.JButton btn_limpiar;
    private javax.swing.JButton btn_salir;
    private javax.swing.JButton btn_siguiente;
    private javax.swing.JLabel fondocliente;
    private javax.swing.JLabel iconcorreo;
    private javax.swing.JLabel iconinsignia;
    private javax.swing.JLabel iconlimpiar;
    private javax.swing.JLabel iconn;
    private javax.swing.JLabel iconregistro;
    private javax.swing.JLabel iconsalir;
    private javax.swing.JLabel iconusuario;
    private javax.swing.JLabel iconusuario2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    // End of variables declaration//GEN-END:variables
}
