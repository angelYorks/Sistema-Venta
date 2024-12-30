package Logica;

import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement; //Crear sentencias de sql con parametros
import java.sql.ResultSet; //Resultados de consultas
import javax.swing.JOptionPane;

///////////////// CLASE PARA OBTENER Y VERIFICAR DATOS DE EMPLEADO ///////////////
public class Empleado extends Persona { //Hereda de la superclase Persona
    //Atributos
    private String usuario;
    private String pass;

    Connection conx; //Variable para manejar la conexion
    PreparedStatement ps; //Objeto para las sentencias sql preparada*
    ResultSet rs; //Objeto para el resultados de las consultas
    ConexionSQL cn = new ConexionSQL(); //Objeto cn de la clase ConexionSQL
    
    //Constructor completo (puede que no se use aparentemente)
    public Empleado(String ID, String nombre, String apellido, String usuario, String pass) {
        super(ID, nombre, apellido); //Hereda del constructor sobrecargado
        this.usuario = usuario;
        this.pass = pass;

    }
    //Constructor vacio
    public Empleado() {

    }
    
    //Metodo para autentificar existencia del empleado
    String idVendedor = "";
    public Empleado log(String usuario, String pass) {
        Empleado l = new Empleado(); //Objeto l de clase empleado con constructor vacio
        //Variable para almacenar consulta de datos del empleado
        String user_log = """
                          select e.id_empleado, p.nombre, p.apellido, dc.nombre_documento ,e.log_empleado, e.pass
                          from Empleado e
                          join Personas p ON e.id_persona = p.id_persona
                          join Documento_cliente dc ON p.tipo_documento = dc.tipo_documento
                          where e.log_empleado = ? AND e.pass = ?""";//Aqui van los parametros
        try {
            conx = cn.establecerConexion(); //Almacena en conx la conexion con la BD
            //Prepara la consulta alojada en user_log dentro de conx
            ps = conx.prepareStatement(user_log);
            //Se establece los parametros de la consulta
            ps.setString(1, usuario);
            ps.setString(2, pass);
            //Se ejecuta la consulta y se almacena en rs
            rs = ps.executeQuery();

            if (rs.next()) { //Si se encuentra un resultado
                //Se ingresa datos de la consulta en la instancia empleado
                //Se llama el resultado de la columna solicitada
                l.setID(rs.getString("id_empleado"));
                l.setNombre(rs.getString("nombre"));
                l.setApellido(rs.getString("apellido"));
                l.setPass(rs.getString("pass"));
                l.setUsuario(rs.getString("log_empleado"));
            } else {
                // Si no hay resultados, el usuario no existe
                System.out.println(" //// El usuario no existe");
            }
            cn.CerrarSesionSQL();//Se ejecuta metodo para cerrar conexion

        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        idVendedor = l.getID(); //Guardo el id del vendedor que se logeo
        System.out.println(idVendedor);
        return l;//Retorna la instancia empleado
    }
    //Busca los datos del empleado
    public Empleado Buscar_empleado(String id) {
        Empleado empleado = new Empleado();
        try {
            conx = cn.establecerConexion();
            String query = """
                           SELECT e.id_empleado, p.nombre,p.apellido,p.num_documento
                           FROM Empleado e
                           INNER JOIN Personas p ON e.id_persona = p.id_persona
                           WHERE e.id_empleado = ?;""";
            ps = conx.prepareStatement(query);
            ps.setString(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {

                empleado.setID(rs.getString("id_empleado"));
                empleado.setNombre(rs.getString("nombre"));
                empleado.setApellido(rs.getString("apellido"));
                //empleado.setUsuario(rs.getString("log_empleado"));
               // empleado.setPass(rs.getString("pass"));

                System.out.println("Se encontro al empleado");

            } else {
                JOptionPane.showMessageDialog(null, "no se encontro al empleado");
            }

            cn.CerrarSesionSQL();

        } catch (SQLException e) {
            System.out.println(e.toString());
        }

        return empleado;
    }
    
    public String getIdVendedor() {
        return idVendedor;
    }

    
    //Getters and Setters
    public String getUsuario() {
        return usuario;
    }
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    public String getPass() {
        return pass;
    }
    public void setPass(String pass) {
        this.pass = pass;
    }
    @Override
    public String toString() {
        return "Producto{"
                + "idUsuario='" + ID + '\''
                + ", nombre='" + nombre + '\''
                + ", apellido='" + apellido + '\''
                + ", usuario='" + usuario + '\''
                + ", pass='" + pass + '\''
                + '}';
    }


}
