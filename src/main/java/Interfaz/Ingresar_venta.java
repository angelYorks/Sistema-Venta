package Interfaz;

import Logica.Cliente;
import Logica.DetalleVenta;
import Logica.Empleado;
import Logica.Producto;
import Logica.Venta;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


public class Ingresar_venta extends javax.swing.JFrame {
    String id;
    Empleado empleado = new Empleado();
    double total_pagar = 0.00;
    DefaultTableModel modelo = new DefaultTableModel();
    String vendedor_name;

    public Ingresar_venta(String empleado_id) {
        initComponents();
        imagen();
        empleado = empleado.Buscar_empleado(empleado_id);
        vendedor_name = empleado.getNombre() + " " + empleado.getApellido();
        txt_empleado.setText(vendedor_name);
        id = empleado.getID();
    }

    public Ingresar_venta() {
    }
    
    
    //**************** METODOS HERRAMIENTAS *****************
    //Metodo para eliminar fila seleccionada
    public void Eliminar_pd() {
        modelo = (DefaultTableModel) tabla.getModel();//Obtiene el modelo de la tabla
        int FilaSeleccionada = tabla.getSelectedRow();
        //Si hay una fila seleccionada
        if(FilaSeleccionada >= 0){
            modelo.removeRow(FilaSeleccionada); //Elimina fila
            Total_pagar(); //Actualiza el total a pagar
        }else{
            JOptionPane.showMessageDialog(null,"Seleccione una fila para eliminar");
        }
    }
    //Metodo para calcular total a pagar
    public void Total_pagar() {
        total_pagar = 0.00;
        int numFilas = tabla.getRowCount();//Muestra el numero de filas
        for (int i = 0; i < numFilas; i++) {//Recorre por todas las filas
            double costoProducto = (double) tabla.getModel().getValueAt(i, 7);//Busca el valor de total
            total_pagar = total_pagar + costoProducto;//Acumula el costo de cada producto
        }
        LabelTotal.setText(String.format("%.2f", total_pagar));//Se muestra en pantalla
    }
    
    public void LimpiarTabla() {
        modelo = (DefaultTableModel) tabla.getModel();//Obtiene el modelo de la tabla
        modelo.removeRow(0);//Elimina todas las filas
        LabelTotal.setText("     ");//Muestra el total en vacio
    }

    //**************** METODOS PANEL PRODUCTO *****************
    Producto pd = new Producto();
    
    //Metodo para autollenar campos de producto
    public void Busqueda() {
        String codigo = txt_pd_id.getText();//Obtiene codigo del producto

        if (!"".equals(codigo)) {//Si no esta vacio
            pd = pd.Buscar_producto(codigo);//Devuelve un objeto con los datos del Producto
        } else {
            JOptionPane.showMessageDialog(null, "Vacio,Ingrese el codigo");
        }
    }
    //Metodo para limpiar panel de productos
    public void Limpiar_pd() {
        txt_nombre_pd.setText("");
        txt_marca_pd.setText("");
        txt_modelo_pd.setText("");
        txt_stock_pd.setText("");
        txt_precio_pd.setText("");
        txt_cant_pd.requestFocus();
    }


    //**************** METODOS PANEL CLIENTES *****************
    Cliente cl = new Cliente();
    
    //Metodo para autollenar campos del cliente
    public void Busqueda_cl() {

        String codigo_cl = txt_num.getText();//Obtengo el codigo ingresado
        if (!"".equals(codigo_cl)) {//Si no esta vacio
            
            if (Cliente.Longitud(codigo_cl)) {
                cl = cl.BuscarCliente(codigo_cl);//Se adquieren los datos del cliente segun codigo
                
                txt_num.setText(cl.getNum_doc());
                txt_nom_cl.setText(cl.getNombre());
                txt_ap_cl.setText(cl.getApellido());
                //Se le asocia un nombre de documento al indice del Objeto cliente
                txt_tip.setText(Cliente.Nom_doc(cl.getTipo_doc()));
                
            }else{
                System.out.println(codigo_cl.length());
                JOptionPane.showMessageDialog(null,"No tiene la cantidad de digitos adecuados");
                Limpiar_cl();
            }
            
        } else {
            JOptionPane.showMessageDialog(null, "Ingrese el documento del cliente");
            Limpiar_cl();
        }

    }

    //Metodo para limpiar panel de cliente
    public void Limpiar_cl() {
        txt_num.setText(" ");
        txt_tip.setText(" ");
        txt_nom_cl.setText(" ");
        txt_ap_cl.setText(" ");
    }
    
    //**************** METODO PARA AGREGAR PRODUCTO A TABLA *****************
    Object[] obj = new Object[8]; //Lista de obj con 8 campos
    int item = 1; //Indice para producto de cada fila
    
    //Metodo para agregar un producto a la tabla
    public void Agregar_pd() {
        
        int cantidad = (int) txt_cant_pd.getValue();
        boolean productoExistente = false;
        if (cantidad <= pd.getStock()) {//Si cantidad no supera al stock
          
            Limpiar_pd();
            txt_pd_id.setText("");
            // SI EL PRODUCTO YA ESTA EN LA TABLA (ACTUALIZAR CANTIDAD VENTA)
            for (int i = 0; i < tabla.getRowCount(); i++) {//Recorre filas tabla
                //Compara el ID del producto de la tabla con el ingresado
                if (tabla.getValueAt(i, 1).equals(pd.getIdProducto())) {
                    productoExistente = true;
                    // El producto ya está registrado, se actualiza la cantidad y el total
                    int cantidadActual = (int) tabla.getValueAt(i, 5);//Cantidad actual
                    int nuevaCantidad = cantidadActual + cantidad; //Se le aumenta la cantidad ingresada
                    double nuevoTotal = pd.getPrecio() * nuevaCantidad;//Se calcula el nuevo total
                    //Se actualiza la cantidad y total
                    tabla.setValueAt(nuevaCantidad, i, 5);
                    tabla.setValueAt(nuevoTotal, i, 7);
                    JOptionPane.showMessageDialog(null, "El producto ya está registrado. Se ha actualizado la cantidad.");
                    break;
                }
            }
            //Si el producto no esta registrado en la tabla
            if (!productoExistente) {
                double total = pd.getPrecio() * cantidad;
                modelo = (DefaultTableModel) tabla.getModel();
                //Define los datos del nuevo producto en un arreglo de objetos
                obj[0] = item;
                obj[1] = pd.getIdProducto();
                obj[2] = pd.getDescripcion();
                obj[3] = pd.getMarca();
                obj[4] = pd.getModelo();
                obj[5] = cantidad;
                obj[6] = pd.getPrecio();
                obj[7] = total;

                modelo.addRow(obj);//Agregar una nueva fila al modelo con datos de Obj
                tabla.setModel(modelo);//Manda el modelo a la tabla (para visualizarlo)
                item++; //Pasa al siguiente indice
            }

            Total_pagar(); //Calcula el total a pagar

        } else {
            JOptionPane.showMessageDialog(null, "No hay el stock suficiente");
        }
    }

    //**************** METODOS REGISTRAR LA VENTA *****************
    Venta venta = new Venta();
    Empleado lg = new Empleado();
    
    public void RegistrarVenta() {        
        String vendedor = empleado.getID();
        String cliente = cl.getID();
        double monto = Double.parseDouble(LabelTotal.getText());
        //System.out.println(lg.getIdVendedor());
        venta.setId_vendedor(vendedor);
        venta.setId_cliente(cliente);
        venta.setTotal(monto);
        venta.RegistrarVentaProducto(venta);//<--------------
        int id_venta = venta.getId_venta();//Se guarda el id_venta

        System.out.println(id_venta);
        
        if (id_venta > 0) {
            RegistrarDetalle(id_venta);
            JOptionPane.showMessageDialog(null, "Se procesó la venta con éxito");
            
        } else {
            JOptionPane.showMessageDialog(null, "Error al registrar la venta");
        }

    }

    DetalleVenta dv = new DetalleVenta();

    public void RegistrarDetalle(int id_venta) {
        for (int i = 0; i < tabla.getRowCount(); i++) {//Recorre filas
            //Obtenemos dato de la tabla
            String cod_prod = tabla.getValueAt(i, 1).toString();//Codigo producto
            int cantidad = Integer.parseInt(tabla.getValueAt(i, 5).toString());//Cantidad producto
            double precio_u = Double.parseDouble(tabla.getValueAt(i, 6).toString());//Precio Unitario
            double total_v = Double.parseDouble(tabla.getValueAt(i, 7).toString());//Total
            //Ingresa datos a un objeto detalle venta
            dv.setId_venta(venta.getId_venta());
            dv.setId_producto(cod_prod);
            dv.setCantidad(cantidad);
            dv.setPrecio_u(precio_u);
            dv.setTotal(total_v);

            dv.RegistroDetalle(dv);
            
        }
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jPanel1 = new javax.swing.JPanel();
        Logo = new javax.swing.JPanel();
        fondologov = new javax.swing.JLabel();
        Datos_generales = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txt_empleado = new javax.swing.JLabel();
        Fecha_txt = new javax.swing.JLabel();
        Hora_txt = new javax.swing.JLabel();
        iconventa = new javax.swing.JLabel();
        P_Cliente = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        txt_num = new javax.swing.JTextField();
        Buscar_cliente = new javax.swing.JButton();
        btnnuevo = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        txt_tip = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txt_nom_cl = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txt_ap_cl = new javax.swing.JTextField();
        Productor = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        txt_pd_id = new javax.swing.JTextField();
        buscar_pd = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        txt_marca_pd = new javax.swing.JTextField();
        txt_nombre_pd = new javax.swing.JTextField();
        txt_stock_pd = new javax.swing.JTextField();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        txt_modelo_pd = new javax.swing.JTextField();
        txt_precio_pd = new javax.swing.JTextField();
        txt_cant_pd = new javax.swing.JSpinner();
        Venta = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        total = new javax.swing.JLabel();
        txt_elim_pd = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        LabelTotal = new javax.swing.JLabel();

        jList1.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane2.setViewportView(jList1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 153, 204));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Logo.setBackground(new java.awt.Color(255, 255, 255));

        fondologov.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout LogoLayout = new javax.swing.GroupLayout(Logo);
        Logo.setLayout(LogoLayout);
        LogoLayout.setHorizontalGroup(
            LogoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(fondologov, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
        );
        LogoLayout.setVerticalGroup(
            LogoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(fondologov, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
        );

        jPanel1.add(Logo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 150, 110));

        Datos_generales.setBackground(new java.awt.Color(0, 0, 0));
        Datos_generales.setForeground(new java.awt.Color(0, 153, 255));
        Datos_generales.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("INGRESO DE VENTA");
        Datos_generales.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 0, -1, 60));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Vendedor:");
        Datos_generales.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 60, -1, -1));

        txt_empleado.setBackground(new java.awt.Color(255, 255, 255));
        txt_empleado.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txt_empleado.setForeground(new java.awt.Color(255, 255, 255));
        txt_empleado.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                txt_empleadoComponentShown(evt);
            }
        });
        Datos_generales.add(txt_empleado, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 60, 160, 30));
        Datos_generales.add(Fecha_txt, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 66, -1, -1));
        Datos_generales.add(Hora_txt, new org.netbeans.lib.awtextra.AbsoluteConstraints(621, 66, -1, -1));
        Datos_generales.add(iconventa, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 0, 80, 70));

        jPanel1.add(Datos_generales, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 0, 700, 110));

        P_Cliente.setBackground(new java.awt.Color(204, 204, 204));
        P_Cliente.setForeground(new java.awt.Color(153, 153, 153));
        P_Cliente.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel6.setBackground(new java.awt.Color(255, 255, 255));
        jLabel6.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        jLabel6.setText("ID");
        P_Cliente.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(13, 14, 38, -1));

        txt_num.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_numActionPerformed(evt);
            }
        });
        P_Cliente.add(txt_num, new org.netbeans.lib.awtextra.AbsoluteConstraints(57, 15, 126, 28));

        Buscar_cliente.setBackground(new java.awt.Color(0, 153, 153));
        Buscar_cliente.setForeground(new java.awt.Color(255, 255, 255));
        Buscar_cliente.setText("Buscar");
        Buscar_cliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Buscar_clienteActionPerformed(evt);
            }
        });
        P_Cliente.add(Buscar_cliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 20, -1, -1));

        btnnuevo.setBackground(new java.awt.Color(0, 153, 153));
        btnnuevo.setForeground(new java.awt.Color(255, 255, 255));
        btnnuevo.setText("Nuevo");
        btnnuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnnuevoActionPerformed(evt);
            }
        });
        P_Cliente.add(btnnuevo, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 18, -1, -1));

        jLabel8.setBackground(new java.awt.Color(255, 255, 255));
        jLabel8.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        jLabel8.setText("Tipo de doc");
        P_Cliente.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 80, -1));

        txt_tip.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_tipActionPerformed(evt);
            }
        });
        P_Cliente.add(txt_tip, new org.netbeans.lib.awtextra.AbsoluteConstraints(105, 61, 141, 28));

        jLabel9.setBackground(new java.awt.Color(255, 255, 255));
        jLabel9.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        jLabel9.setText("Nombres:");
        P_Cliente.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(24, 95, 69, 29));

        txt_nom_cl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_nom_clActionPerformed(evt);
            }
        });
        P_Cliente.add(txt_nom_cl, new org.netbeans.lib.awtextra.AbsoluteConstraints(105, 96, 141, 28));

        jLabel10.setBackground(new java.awt.Color(255, 255, 255));
        jLabel10.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        jLabel10.setText("Apellidos:");
        P_Cliente.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(24, 133, -1, -1));

        txt_ap_cl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_ap_clActionPerformed(evt);
            }
        });
        P_Cliente.add(txt_ap_cl, new org.netbeans.lib.awtextra.AbsoluteConstraints(105, 130, 141, 28));

        jPanel1.add(P_Cliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 110, 380, 210));

        Productor.setBackground(new java.awt.Color(204, 204, 204));
        Productor.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel11.setText("COD. PRODUCT:");
        Productor.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(21, 16, -1, -1));

        txt_pd_id.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_pd_idActionPerformed(evt);
            }
        });
        Productor.add(txt_pd_id, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 13, 116, -1));

        buscar_pd.setBackground(new java.awt.Color(0, 153, 153));
        buscar_pd.setForeground(new java.awt.Color(255, 255, 255));
        buscar_pd.setText("Buscar");
        buscar_pd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscar_pdActionPerformed(evt);
            }
        });
        Productor.add(buscar_pd, new org.netbeans.lib.awtextra.AbsoluteConstraints(254, 13, -1, -1));

        jLabel12.setText("CANTIDAD");
        Productor.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(259, 48, -1, 22));

        jLabel13.setText("PRODUCTO:");
        Productor.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(21, 51, -1, -1));

        jLabel14.setText("PRECIO");
        Productor.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(21, 150, -1, -1));

        jLabel15.setText("STOCK");
        Productor.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(259, 85, -1, -1));

        jLabel16.setText("MARCA");
        Productor.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(21, 85, -1, 19));

        jLabel17.setText("MODELO");
        Productor.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(21, 116, -1, 22));

        txt_marca_pd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_marca_pdActionPerformed(evt);
            }
        });
        Productor.add(txt_marca_pd, new org.netbeans.lib.awtextra.AbsoluteConstraints(98, 82, 140, -1));

        txt_nombre_pd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_nombre_pdActionPerformed(evt);
            }
        });
        Productor.add(txt_nombre_pd, new org.netbeans.lib.awtextra.AbsoluteConstraints(98, 48, 140, -1));

        txt_stock_pd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_stock_pdActionPerformed(evt);
            }
        });
        Productor.add(txt_stock_pd, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 80, 42, -1));

        jButton5.setBackground(new java.awt.Color(0, 153, 153));
        jButton5.setForeground(new java.awt.Color(255, 255, 255));
        jButton5.setText("Agregar Producto");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        Productor.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 140, -1, 37));

        jButton6.setBackground(new java.awt.Color(0, 153, 153));
        jButton6.setForeground(new java.awt.Color(255, 255, 255));
        jButton6.setText("Stock");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        Productor.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(338, 13, -1, -1));

        txt_modelo_pd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_modelo_pdActionPerformed(evt);
            }
        });
        Productor.add(txt_modelo_pd, new org.netbeans.lib.awtextra.AbsoluteConstraints(98, 116, 140, -1));

        txt_precio_pd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_precio_pdActionPerformed(evt);
            }
        });
        Productor.add(txt_precio_pd, new org.netbeans.lib.awtextra.AbsoluteConstraints(98, 150, 140, -1));
        Productor.add(txt_cant_pd, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 50, 70, -1));

        jPanel1.add(Productor, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 110, 460, 210));

        Venta.setBackground(new java.awt.Color(0, 204, 0));

        tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Item", "Cod Prod", "Descripcion", "Marca", "Modelo", "Cant", "C/U", "Total"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tabla);
        if (tabla.getColumnModel().getColumnCount() > 0) {
            tabla.getColumnModel().getColumn(0).setMinWidth(10);
            tabla.getColumnModel().getColumn(0).setMaxWidth(50);
            tabla.getColumnModel().getColumn(1).setMinWidth(80);
            tabla.getColumnModel().getColumn(1).setPreferredWidth(50);
            tabla.getColumnModel().getColumn(1).setMaxWidth(100);
            tabla.getColumnModel().getColumn(2).setPreferredWidth(150);
            tabla.getColumnModel().getColumn(3).setPreferredWidth(120);
            tabla.getColumnModel().getColumn(4).setPreferredWidth(120);
            tabla.getColumnModel().getColumn(5).setPreferredWidth(30);
            tabla.getColumnModel().getColumn(6).setPreferredWidth(30);
            tabla.getColumnModel().getColumn(7).setPreferredWidth(70);
        }

        javax.swing.GroupLayout VentaLayout = new javax.swing.GroupLayout(Venta);
        Venta.setLayout(VentaLayout);
        VentaLayout.setHorizontalGroup(
            VentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 710, Short.MAX_VALUE)
        );
        VentaLayout.setVerticalGroup(
            VentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(VentaLayout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 351, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel1.add(Venta, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 340, 710, 350));

        total.setBackground(new java.awt.Color(255, 255, 255));
        total.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        total.setForeground(new java.awt.Color(255, 255, 255));
        total.setText("Total");
        jPanel1.add(total, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 520, 50, -1));

        txt_elim_pd.setBackground(new java.awt.Color(153, 153, 153));
        txt_elim_pd.setText("ELIMINAR");
        txt_elim_pd.setToolTipText("");
        txt_elim_pd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_elim_pdActionPerformed(evt);
            }
        });
        jPanel1.add(txt_elim_pd, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 340, 90, 30));

        jButton7.setBackground(new java.awt.Color(153, 153, 153));
        jButton7.setText("Menu");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 660, 90, 30));

        jButton4.setBackground(new java.awt.Color(153, 153, 153));
        jButton4.setText("LIMPIAR");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 380, 90, 30));

        jButton8.setBackground(new java.awt.Color(153, 153, 153));
        jButton8.setText("PROCESAR");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton8, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 610, -1, 30));

        LabelTotal.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        LabelTotal.setForeground(new java.awt.Color(204, 204, 204));
        jPanel1.add(LabelTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 560, 90, 40));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 720, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txt_pd_idActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_pd_idActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_pd_idActionPerformed

    private void buscar_pdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscar_pdActionPerformed
        Limpiar_pd();
        Busqueda();
        txt_nombre_pd.setText(pd.getDescripcion());
        txt_marca_pd.setText(pd.getMarca());
        txt_modelo_pd.setText(pd.getModelo());
        txt_stock_pd.setText(String.valueOf(pd.getStock()));
        txt_precio_pd.setText(String.valueOf(pd.getPrecio()));
    }//GEN-LAST:event_buscar_pdActionPerformed

    private void txt_marca_pdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_marca_pdActionPerformed

    }//GEN-LAST:event_txt_marca_pdActionPerformed

    private void txt_nombre_pdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_nombre_pdActionPerformed

    }//GEN-LAST:event_txt_nombre_pdActionPerformed

    private void txt_stock_pdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_stock_pdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_stock_pdActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        Agregar_pd();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        Stock stock = new Stock();
        stock.setVisible(true);

    }//GEN-LAST:event_jButton6ActionPerformed

    private void txt_elim_pdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_elim_pdActionPerformed
        Eliminar_pd();
    }//GEN-LAST:event_txt_elim_pdActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        Menu_Opciones volver = new Menu_Opciones(id);
        volver.setVisible(true);
        dispose();
    }//GEN-LAST:event_jButton7ActionPerformed

    private void txt_modelo_pdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_modelo_pdActionPerformed

    }//GEN-LAST:event_txt_modelo_pdActionPerformed

    private void txt_precio_pdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_precio_pdActionPerformed

    }//GEN-LAST:event_txt_precio_pdActionPerformed

    private void txt_ap_clActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_ap_clActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_ap_clActionPerformed

    private void txt_nom_clActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_nom_clActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_nom_clActionPerformed

    private void txt_tipActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_tipActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_tipActionPerformed

    private void btnnuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnnuevoActionPerformed
        
        Nuevo_cliente nuevo = new Nuevo_cliente();
        nuevo.setVisible(true);
    }//GEN-LAST:event_btnnuevoActionPerformed

    private void Buscar_clienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Buscar_clienteActionPerformed

        Busqueda_cl();
    }//GEN-LAST:event_Buscar_clienteActionPerformed

    private void txt_numActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_numActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_numActionPerformed

    private void txt_empleadoComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_txt_empleadoComponentShown

        txt_empleado.setText(lg.getNombre());
    }//GEN-LAST:event_txt_empleadoComponentShown

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        
        LimpiarTabla();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        RegistrarVenta();
        pd.Actualizar_pd(venta.getId_venta());
    }//GEN-LAST:event_jButton8ActionPerformed

    public void imagen() {
        ImageIcon Fondolv = new ImageIcon("Imagenes/logoventa.jpg");
        fondologov.setIcon(Fondolv);
        ImageIcon iconvent = new ImageIcon("Imagenes/ventas2.png");
        iconventa.setIcon(iconvent);
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
            java.util.logging.Logger.getLogger(Ingresar_venta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Ingresar_venta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Ingresar_venta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Ingresar_venta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Ingresar_venta().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Buscar_cliente;
    private javax.swing.JPanel Datos_generales;
    private javax.swing.JLabel Fecha_txt;
    private javax.swing.JLabel Hora_txt;
    private javax.swing.JLabel LabelTotal;
    private javax.swing.JPanel Logo;
    private javax.swing.JPanel P_Cliente;
    private javax.swing.JPanel Productor;
    private javax.swing.JPanel Venta;
    private javax.swing.JButton btnnuevo;
    private javax.swing.JButton buscar_pd;
    private javax.swing.JLabel fondologov;
    private javax.swing.JLabel iconventa;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JList<String> jList1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tabla;
    private javax.swing.JLabel total;
    private javax.swing.JTextField txt_ap_cl;
    private javax.swing.JSpinner txt_cant_pd;
    private javax.swing.JButton txt_elim_pd;
    private javax.swing.JLabel txt_empleado;
    private javax.swing.JTextField txt_marca_pd;
    private javax.swing.JTextField txt_modelo_pd;
    private javax.swing.JTextField txt_nom_cl;
    private javax.swing.JTextField txt_nombre_pd;
    private javax.swing.JTextField txt_num;
    private javax.swing.JTextField txt_pd_id;
    private javax.swing.JTextField txt_precio_pd;
    private javax.swing.JTextField txt_stock_pd;
    private javax.swing.JTextField txt_tip;
    // End of variables declaration//GEN-END:variables
}
