package Logica;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class DetalleVenta {
    //Atributos de la tabla detalle venta
    private int id_detalle;
    private int id_venta;
    private String id_producto;
    private String nom_pd;
    private int cantidad;
    private double precio_u;
    private double total = cantidad * precio_u;
    
    //Constructor vacio
    public DetalleVenta() {

    }

    Connection cx;
    ConexionSQL csql = new ConexionSQL();

    
    //Metodo para registrar los datos de un objeto DetalleVenta
    public void RegistroDetalle(DetalleVenta d) {
        //Insercion de detalle venta
        String query = """
                       INSERT INTO Detalle_venta (id_venta, id_producto, cantidad, precioUnit,total) VALUES
                       (?, ?, ?, ?, ?)
                       """;
        try {
            cx = csql.establecerConexion();
            PreparedStatement ps = cx.prepareStatement(query);//Prepara y almacena la consulta
            //Se establece la posición de los parametros ingresados en el objeto
            ps.setInt(1, d.getId_venta());
            ps.setString(2, d.getId_producto());
            ps.setInt(3, d.getCantidad());
            ps.setDouble(4, d.getPrecio_u());
            ps.setDouble(5, d.getTotal());
            ps.execute();//Se ejecuta la consulta
            
            csql.CerrarSesionSQL();//Cerramos BD
            
        } catch (SQLException e) {
            System.out.println(e.toString());

        }
    }
    
    //Metodo para obtener detalle de la venta en una lista
    public List<DetalleVenta> obtenerDetallesVenta(int idVenta) {
        List<DetalleVenta> detalles = new ArrayList<>();//Creación de objeto tipo List
        //Consulta para mostrar datos de detalle venta segun su id venta
            String query = """
                           select dv.id_venta,dv.id_producto,p.nombreProducto,dv.cantidad,dv.precioUnit
                           from Detalle_venta dv
                           inner join Producto p ON dv.id_producto = p.id_producto where id_venta= ?""";

        try {
            cx = csql.establecerConexion();
            PreparedStatement ps = cx.prepareStatement(query); //Redundante
            ps.setInt(1, idVenta);//Asignación de posicion de parametro
            ResultSet rs = ps.executeQuery();//Almacena el resultado de la consulta

            while (rs.next()) {//Si se encuentra se ejecuta
                DetalleVenta detalle = new DetalleVenta(); //objeto del tipo DetalleVenta
                //Se inserta los datos en los atributos segun las columnas de la BD
                detalle.setId_venta(rs.getInt("id_venta"));
                detalle.setId_producto(rs.getString("id_producto"));
                detalle.setCantidad(rs.getInt("cantidad"));
                detalle.setPrecio_u(rs.getDouble("precioUnit"));
                detalle.setNom_pd(rs.getString("nombreProducto"));
                
                total = detalle.getCantidad() * detalle.getPrecio_u();
                
                detalle.setTotal(total);
                detalles.add(detalle); //Se agrega el objeto a la variable tipo List
            }
            csql.CerrarSesionSQL();

        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return detalles;//Devuelve una lista de objetos detalle
    }
   
    //Getters and Setters
    public int getId_detalle() {
        return id_detalle;
    }
    public void setId_detalle(int id_detalle) {
        this.id_detalle = id_detalle;
    }
    public int getId_venta() {
        return id_venta;
    }
    public void setId_venta(int id_venta) {
        this.id_venta = id_venta;
    }
    public String getId_producto() {
        return id_producto;
    }
    public void setId_producto(String id_producto) {
        this.id_producto = id_producto;
    }
    public int getCantidad() {
        return cantidad;
    }
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    public double getPrecio_u() {
        return precio_u;
    }
    public void setPrecio_u(double precio_u) {
        this.precio_u = precio_u;
    }
    public double getTotal() {
        return total;
    }
    public void setTotal(double total) {
        this.total = total;
    }
    public String getNom_pd() {
        return nom_pd;
    }
    public void setNom_pd(String nom_pd) {
        this.nom_pd = nom_pd;
    }
    public String detalleToString() {
        return "Producto: " + id_producto + ", Cantidad: " + cantidad + ", Precio Unitario: " + precio_u + ", Total: " + total;
    }
}
