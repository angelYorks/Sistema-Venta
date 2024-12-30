package Logica;

import java.sql.Connection; //Para representar una conexion con BD
import java.sql.DriverManager; //Para gestionar los controladores
import java.sql.SQLException; //Información de errores de acceso a BD

public class ConexionSQL {
    //Atributos para la conexion
    private String usuario = "usuarioSQL";
    private String contraseña = "123";
    private String bd = "ElectroTech_8";
    private String ip = "localhost";//Direccion del servidor
    private String puerto = "1433";

    private String url = "jdbc:sqlserver://" + ip + ":" + puerto + ";" + "database=" + bd + ";"
            + "encrypt=true;trustServerCertificate=true";
    
    //Atributo de tipo Connection (almacena el objeto de la conexion a la BD)
    public Connection cx = null;
    

    // Metodo para establecer la conexión
    public Connection establecerConexion() {

        try { //Hace la conexion con la base de datos con un metodo
            //Se le asigna un objeto Connection a cx
            cx = DriverManager.getConnection(url, usuario, contraseña);
            System.out.println("//////// Se conecto a la bd: " + bd);
        } catch (SQLException e) {//Mensaje de excepción 
            System.out.println(">>>>>> Error en la conexion:" + e.toString());
        }
        return cx;//Retorna variable de tipo conexion
    }
   
    // Metodo para cerrar la conexion a la base de datos
    public void CerrarSesionSQL() {

        try {
            if (cx != null) { //Verifica que la conexion no es nula
                cx.close(); //Si la conexion existe, la cierra
                System.out.println("//// Se cerro la sesion a la db: " + bd);
            }
        } catch (SQLException e) {//Mensaje de error
            System.out.println(">> Error al cerrar sesion: " + e.toString());
        }
    }
}
