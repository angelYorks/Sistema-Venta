package Interfaz;

import Logica.Empleado;
import javax.swing.ImageIcon;//Para Manejar iconos
import javax.swing.JOptionPane;//Para manejar cuadros de dialogo

public class Ingreso extends javax.swing.JFrame {//Crea una ventana
    //Declaración de instancia de Empleado
    Empleado empleado = new Empleado();
    Empleado login = new Empleado();
    
    //Constructor vacio
    public Ingreso() {
        initComponents();//Inicia los componentes graficos
        imagen();//Ejecuta las imagenes
    }
    //Valida usuario
    public void validacion() {
        String usuario_empl = txt_usuario.getText();
        String pass = String.valueOf(txt_contraseña.getPassword());

        if (!"".equals(usuario_empl) && !"".equals(pass)) {//Campos no vacios

            empleado = login.log(usuario_empl, pass);//Autentifica la existencia del empleado en BD
                                               //Retorna un objeto empleado
            String id = empleado.getID();
            if (empleado.getUsuario() != null && empleado.getPass() != null) {
                Menu_Opciones mo = new Menu_Opciones(id);//Objeto de clase Menu_Opciones
                mo.setVisible(true);//Muestra interfaz menu
                System.out.println(id);
                dispose();//Cierra ventana
            }else{
                JOptionPane.showMessageDialog(null, "Usuario o contraseña erronea","INCORRECTO",JOptionPane.WARNING_MESSAGE);
            }

        }else{
            JOptionPane.showMessageDialog(null, "Falta completar cuadros");
        }
    }
    //Aquí esta el metodo initComponents
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        fimg = new javax.swing.JLabel();
        Icono = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        Rb_MostrarContraseña = new javax.swing.JRadioButton();
        txt_usuario = new javax.swing.JTextField();
        btn_ingresar = new javax.swing.JButton();
        lblcontraseña = new javax.swing.JLabel();
        txt_contraseña = new javax.swing.JPasswordField();
        lblusuario = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        iconkey = new javax.swing.JLabel();
        iconlogin = new javax.swing.JLabel();
        lblbienvenido = new javax.swing.JLabel();
        fondoo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(0, 153, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        fimg.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(fimg, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 90, 80));

        Icono.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(Icono, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 120, 160, 130));

        jPanel2.setBackground(new java.awt.Color(51, 51, 51));
        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Rb_MostrarContraseña.setForeground(new java.awt.Color(51, 204, 255));
        Rb_MostrarContraseña.setText("Mostrar Contraseña");
        Rb_MostrarContraseña.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Rb_MostrarContraseñaActionPerformed(evt);
            }
        });
        jPanel2.add(Rb_MostrarContraseña, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 170, -1, 20));

        txt_usuario.setBackground(new java.awt.Color(51, 51, 51));
        txt_usuario.setForeground(new java.awt.Color(255, 255, 255));
        txt_usuario.setBorder(null);
        txt_usuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_usuarioActionPerformed(evt);
            }
        });
        jPanel2.add(txt_usuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 50, 220, 20));

        btn_ingresar.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btn_ingresar.setText("INGRESAR");
        btn_ingresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ingresarActionPerformed(evt);
            }
        });
        jPanel2.add(btn_ingresar, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 230, 140, 40));

        lblcontraseña.setFont(new java.awt.Font("Lucida Sans", 1, 14)); // NOI18N
        lblcontraseña.setForeground(new java.awt.Color(255, 255, 255));
        lblcontraseña.setText("CONTRASEÑA");
        jPanel2.add(lblcontraseña, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 80, -1, 40));

        txt_contraseña.setBackground(new java.awt.Color(51, 51, 51));
        txt_contraseña.setForeground(new java.awt.Color(255, 255, 255));
        txt_contraseña.setBorder(null);
        txt_contraseña.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_contraseñaActionPerformed(evt);
            }
        });
        jPanel2.add(txt_contraseña, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 120, 220, 20));

        lblusuario.setBackground(new java.awt.Color(255, 255, 255));
        lblusuario.setFont(new java.awt.Font("Lucida Sans", 1, 14)); // NOI18N
        lblusuario.setForeground(new java.awt.Color(255, 255, 255));
        lblusuario.setText("USUARIO");
        jPanel2.add(lblusuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 20, 70, 20));

        jTextField1.setBackground(new java.awt.Color(51, 51, 51));
        jTextField1.setBorder(null);
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        jPanel2.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 150, 220, 10));

        jSeparator1.setBackground(new java.awt.Color(0, 0, 0));
        jPanel2.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 140, 220, 10));

        jSeparator2.setBackground(new java.awt.Color(0, 0, 0));
        jPanel2.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 70, 220, 10));
        jPanel2.add(iconkey, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 120, 20, 20));
        jPanel2.add(iconlogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, 20, 20));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 230, 330, 300));

        lblbienvenido.setBackground(new java.awt.Color(255, 255, 255));
        lblbienvenido.setFont(new java.awt.Font("Hack Nerd Font", 1, 36)); // NOI18N
        lblbienvenido.setForeground(new java.awt.Color(204, 204, 204));
        lblbienvenido.setText("¡Bienvenido!");
        jPanel1.add(lblbienvenido, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 50, 280, 50));

        fondoo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(fondoo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 560, 580));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 556, 580));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txt_usuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_usuarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_usuarioActionPerformed

    private void txt_contraseñaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_contraseñaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_contraseñaActionPerformed

    private void btn_ingresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ingresarActionPerformed
       validacion();//Ejecuta validación
    }//GEN-LAST:event_btn_ingresarActionPerformed

    private void Rb_MostrarContraseñaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Rb_MostrarContraseñaActionPerformed
        if (!Rb_MostrarContraseña.isSelected()){//Si el JRadioButton esta seleccionado o no
            txt_contraseña.setEchoChar('\u25cf');//Muestra puntos si no esta seleccionado
        }
        else{
            txt_contraseña.setEchoChar((char)0);//Muestra la contraseña
        }
    }//GEN-LAST:event_Rb_MostrarContraseñaActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    //Metodo para configurar imagenes de interfaz
    public void imagen() {
        ImageIcon Fondo = new ImageIcon("Imagenes/Fondo1.jpeg");
        fondoo.setIcon(Fondo);
        
        ImageIcon img = new ImageIcon("Imagenes/logochico2.jpg");
        fimg.setIcon(img);
        
        ImageIcon Icon = new ImageIcon("Imagenes/Icono.png");
        Icono.setIcon(Icon);
        
        ImageIcon Iconl = new ImageIcon("Imagenes/userLabel.png");
        iconlogin.setIcon(Iconl);
        
        ImageIcon Iconk = new ImageIcon("Imagenes/key.png");
        iconkey.setIcon(Iconk);            
    } 
    
    //Inicia la ejecución
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
            java.util.logging.Logger.getLogger(Ingreso.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Ingreso.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Ingreso.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Ingreso.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Ingreso().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Icono;
    private javax.swing.JRadioButton Rb_MostrarContraseña;
    private javax.swing.JButton btn_ingresar;
    private javax.swing.JLabel fimg;
    private javax.swing.JLabel fondoo;
    private javax.swing.JLabel iconkey;
    private javax.swing.JLabel iconlogin;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel lblbienvenido;
    private javax.swing.JLabel lblcontraseña;
    private javax.swing.JLabel lblusuario;
    private javax.swing.JPasswordField txt_contraseña;
    private javax.swing.JTextField txt_usuario;
    // End of variables declaration//GEN-END:variables
}
