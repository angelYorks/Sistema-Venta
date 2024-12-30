package Logica;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class Venta {

    private int id_venta;
    private String id_cliente;
    private String nombre_cliente;
    private String id_vendedor;
    private String nombre_vendedor;
    private String id_producto;
    private double total;
    private boolean existVenta;
    private int cantidad;
    private double precio_u;
    private Date fecha;

    Connection conx;
    PreparedStatement ps;
    ResultSet rs;
    //ConexionSQL csql = new ConexionSQL();
    ConexionSQL cn = new ConexionSQL();
    int r;
    
    //Constructor vacio
    public Venta() {
    }
    //Constructor completo
    public Venta(int id_venta, String cliente, String Vendedor, String id_producto, double total, boolean existVenta, int cantidad, double precio_u, Date fecha) {
        this.id_venta = id_venta;
        this.nombre_cliente = cliente;
        this.nombre_vendedor = Vendedor;
        this.id_producto = id_producto;
        this.total = total;
        this.existVenta = existVenta;
        this.cantidad = cantidad;
        this.precio_u = precio_u;
        this.fecha = fecha;
    }
    
//Metodo con retorno de lista de objetos ventas
    public List Buscar_venta(String filtro, String value) {

        String buscador = "";//Variable para asignar nombre del filtro 

        List<Venta> ventas = new ArrayList<>(); //Creamos una lista tipo Venta

        try {
            switch (filtro) {
                case "Id Venta" ->
                    buscador = "id_venta";
                case "Id Vendedor" ->
                    buscador = "e.id_empleado";
                case "N° Cliente" ->
                    buscador = "p_cli.num_documento";
                case "Fecha" ->
                    buscador = "fecha_venta";
                case "Todo" -> {
                    buscador = "Todo";
                    existVenta = true;
                }
            }

            conx = cn.establecerConexion();
            
            //Muestra los datos de la venta
            String query = """
                        SELECT v.id_venta,p_emp.nombre + ' ' + p_emp.apellido AS Empleado,p_cli.nombre + ' ' + p_cli.apellido AS Cliente,
                        v.totalAPagar,v.fecha_venta FROM Venta v
                        INNER JOIN  Empleado e ON v.id_empleado = e.id_empleado
                        INNER JOIN Personas p_emp ON e.id_persona = p_emp.id_persona
                        INNER JOIN Cliente cl ON v.id_cliente = cl.id_cliente
                        INNER JOIN Personas p_cli ON cl.id_persona = p_cli.id_persona
                        """;

            if ("Todo".equals(buscador)) {
                ps = conx.prepareStatement(query);
            } else { //Filtrar segun parametro
                query = query + " WHERE " + buscador + " = ?";//Concatenamos el filtro
                ps = conx.prepareStatement(query);
                ps.setString(1, value);
            }

            rs = ps.executeQuery();//Se ejecuta consulta

            while (rs.next()) {
                Venta vnt = new Venta(); //Instancia de venta

                //vnt.setId_venta(Integer.parseInt(rs.getString("id_venta")));
                vnt.setId_venta(rs.getInt("id_venta"));
                vnt.setNombre_vendedor(rs.getString("Empleado"));
                vnt.setNombre_cliente(rs.getString("Cliente"));
                vnt.setTotal(rs.getDouble("totalAPagar"));
                vnt.setFecha(rs.getDate("fecha_venta"));
                existVenta = true;
                ventas.add(vnt); //Se agrega objeto vnt a lista

            }
            //Revisar redundancia de esto
            if (!existVenta) {
                JOptionPane.showMessageDialog(null, "No se encontro el registro de la venta");
                System.out.println("No hay ");
            }

            cn.CerrarSesionSQL();

        } catch (SQLException e) {
            System.out.println(e.toString());
        }

        return ventas; //Retorna lista de objetos venta
    }



    public int RegistrarVentaProducto(Venta v) {

        String sql = """
                     INSERT INTO Venta (id_empleado, id_cliente, totalAPagar) VALUES
                     (?,?,?)
                     """;

        try {
            conx = cn.establecerConexion();
            //Se prepara declaracion e indica que se quiere obtener los id autoincrementales
            ps = conx.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);//id venta

            ps.setString(1, v.getId_vendedor());
            ps.setString(2, v.getId_cliente());
            ps.setDouble(3, v.getTotal());

            ps.executeUpdate();

            rs = ps.getGeneratedKeys(); //Se obtiene el id venta generado en BD
            
            if (rs.next()) {//Si existe una clave se ingresa al objeto V
                v.setId_venta(rs.getInt(1));//Devuelve el valor de la primera columna de rs

            }
            cn.CerrarSesionSQL();

        }catch (SQLException e){
            System.out.println(e.toString());
        }
        return v.getId_venta(); //Se regresa el valor del id venta
    }
    
    //Getters and Setters
    public int getId_venta() {
        return id_venta;
    }

    public void setId_venta(int id_venta) {
        this.id_venta = id_venta;
    }

    public String getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(String id_cliente) {
        this.id_cliente = id_cliente;
    }

    public String getNombre_cliente() {
        return nombre_cliente;
    }

    public void setNombre_cliente(String nombre_cliente) {
        this.nombre_cliente = nombre_cliente;
    }

    public String getId_vendedor() {
        return id_vendedor;
    }

    public void setId_vendedor(String id_vendedor) {
        this.id_vendedor = id_vendedor;
    }

    public String getNombre_vendedor() {
        return nombre_vendedor;
    }

    public void setNombre_vendedor(String nombre_vendedor) {
        this.nombre_vendedor = nombre_vendedor;
    }

    public String getId_producto() {
        return id_producto;
    }

    public void setId_producto(String id_producto) {
        this.id_producto = id_producto;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public boolean isExistVenta() {
        return existVenta;
    }

    public void setExistVenta(boolean existVenta) {
        this.existVenta = existVenta;
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

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    
    /////////CREO QUE INNECESARIO//////////////7
    public Connection getConx() {
        return conx;
    }
    public void setConx(Connection conx) {
        this.conx = conx;
    }
    public PreparedStatement getPs() {
        return ps;
    }
    public void setPs(PreparedStatement ps) {
        this.ps = ps;
    }
    public ResultSet getRs() {
        return rs;
    }
    public void setRs(ResultSet rs) {
        this.rs = rs;
    }
    public ConexionSQL getCn() {
        return cn;
    }
    public void setCn(ConexionSQL cn) {
        this.cn = cn;
    }
    public int getR() {
        return r;
    }
    public void setR(int r) {
        this.r = r;
    }

}
