package Logica;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.JOptionPane;

///////////////// CLASE PARA BUSCAR PRODUCTOS ///////////////
public class Producto {
    //Atributos de tabla producto
    private String id;
    private String marca;
    private String modelo;
    private String descripcion;
    private double precio;
    private int stock;
    private boolean existProduct; //Por defecto False
    
    Connection conx;
    PreparedStatement ps;
    ResultSet rs;
    ConexionSQL cn = new ConexionSQL();
    
    //Metodo con retorno de lista de objetos producto
    public List Buscar_producto(String filtro, String value) {
        String buscador = ""; //Variable para asignar nombre del filtro 

        List<Producto> productos = new ArrayList<>(); //Lista de objetos Producto

        try {
            //Se define parametro de busqueda de acuerdo a opciones de lista desplegable
            switch (filtro) {
                case "Codigo" ->
                    buscador = "id_producto";
                case "Marca" ->
                    buscador = "marca";
                case "Modelo" ->
                    buscador = "modelo";
                case "Descripcion" ->
                    buscador = "nombreProducto";
                case "Todo" -> {
                    buscador = "Todo";
                    existProduct = true;
                }
            }

            conx = cn.establecerConexion();

            String query;
            
            //Se escoge consulta de acuerdo a buscador
            if ("Todo".equals(buscador)) {
                query = "select * from Producto";//Muestra todos los productos
                ps = conx.prepareStatement(query);
            } else {
                query = "select * from Producto where " + buscador + " = ?";
                ps = conx.prepareStatement(query);
                ps.setString(1, value);
            }

            rs = ps.executeQuery();//Ejecuto consulta

            while (rs.next()) {//Si existe
                Producto pd = new Producto(); //Objeto de producto
                //Se ingresa los datos de la BD al objeto
                pd.setIdProducto(rs.getString("id_producto"));
                pd.setDescripcion(rs.getString("nombreProducto"));
                pd.setMarca(rs.getString("marca"));
                pd.setModelo(rs.getString("modelo"));
                pd.setStock(rs.getInt("stock"));
                pd.setPrecio(rs.getDouble("precioUnit"));
                existProduct = true;
                productos.add(pd); //Se agrega el objeto a la lista

            }
            
            //Parece que esto esta de mas
            if (existProduct == false) {//Si no existe producto
                JOptionPane.showMessageDialog(null, "No se encontro el producto");
                System.out.println("No hay ");
            }

            cn.CerrarSesionSQL();

        } catch (SQLException e) {
            System.out.println(e.toString());
        }

        return productos;//Se devuelve lista de objetos
    }

    //Metodo para buscar producto por codigo
    public Producto Buscar_producto(String valor) {//Parametro id producto(string)

        Producto product = new Producto(); //Objeto de tipo Producto

        try {
            conx = cn.establecerConexion();
            String query = "select * from Producto where id_producto = ?";
            ps = conx.prepareStatement(query);
            ps.setString(1, valor);
            rs = ps.executeQuery();
            if (rs.next()) {
                System.out.println("Se encontro el producto");
                //Se asigna los datos al objeto product
                product.setIdProducto(rs.getString("id_producto"));
                product.setDescripcion(rs.getString("nombreProducto"));
                product.setMarca(rs.getString("marca"));
                product.setModelo(rs.getString("modelo"));
                product.setStock(rs.getInt("stock"));
                product.setPrecio(rs.getDouble("precioUnit"));
            } else {
                //Pop-up
                JOptionPane.showMessageDialog(null, "no se encontro el producto");
            }

            cn.CerrarSesionSQL();

        } catch (SQLException e) {
            System.out.println(e.toString());
        }

        return product;//Se retorna objeto
    }

    //Metodo para actualizar stock disponible
    public boolean Actualizar_pd(int id_venta) {

        try {
            int[] almacenDetalleVenta = new int[100];//Arreglo para almacenar id detalleVenta
            //Se obtiene los id detalleVenta segun el id venta
            String query_ids = "select id_venta,id_detalleVenta from Detalle_venta where id_venta = ?";
            conx = cn.establecerConexion();
            ps = conx.prepareStatement(query_ids);
            ps.setInt(1, id_venta);
            rs = ps.executeQuery();

            int index = 0;

            while (rs.next()) {//Se ejecuta mientras exista un dato
                if (index >= almacenDetalleVenta.length) {//Si el index es mayor a la longitud del arreglo
                    //Creamos una copia del arreglo con el doble de tamaño
                    almacenDetalleVenta = Arrays.copyOf(almacenDetalleVenta, almacenDetalleVenta.length * 2);
                }
                //Se almacena los id_detallaVenta
                almacenDetalleVenta[index] = rs.getInt("id_detalleVenta");
                index++;
            }

 
            try {
                String query_det = "EXEC ActualizarStockPorDetalleVenta @id_detalleVenta = ?";
                ps = conx.prepareStatement(query_det);

                for (int idDetalle : almacenDetalleVenta) {//Se recorre el arreglo
                    if (idDetalle != 0) {//Si no es cero
                        ps.setInt(1, idDetalle);
                        ps.executeUpdate();
                    }
                }
                
            } catch (SQLException e) {
                System.out.println(e.toString());
                return false;
                
            } finally { //Siempre se ejecuta
                try {
                    if (ps != null) {
                        ps.close();
                    }
                    if (conx != null) {
                        cn.CerrarSesionSQL();
                    }
                } catch (SQLException e) {
                    System.out.println(e.toString());//Imprime error
                    return false;//Devuelve falso
                }
            }
        } catch (SQLException e) {
            System.out.println("");
            return false;
        }
        
        return true;
    }
    
    
    // Getters y setters para cada campo
    public String getIdProducto() {
        return id;
    }

    public void setIdProducto(String idProducto) {
        this.id = idProducto;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return "Producto{"
                + "idProducto='" + id + '\''
                + ", marca='" + marca + '\''
                + ", modelo='" + modelo + '\''
                + ", descripcion='" + descripcion + '\''
                + ", precio='" + precio + '\''
                + ", stock='" + stock + '\''
                + '}';
    }

    

    

}
