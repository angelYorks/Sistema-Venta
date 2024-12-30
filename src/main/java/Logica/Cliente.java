
package Logica;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern; //Para validar patrones de correo
import javax.swing.JOptionPane;

///////////////// CLASE PARA REGISTRAR Y BUSCAR CLIENTES ///////////////
public class Cliente extends Persona {  //Hereda de la superclase Persona
    //Atributos
    private String email = null;
    
    Connection cx;
    ConexionSQL csql = new ConexionSQL(); 
    
    //Constructor vacio
    public Cliente() {
    }
    //Constructor completo
    public Cliente(String ID, String nombre, String apellido, int tipo_doc, String num_doc, String email) {

        super(ID, nombre, apellido, tipo_doc, num_doc); //Hereda del constructor completo

        this.email = email;
    }

    //Metodos para las interfaces 
    
    // Metodo para registrar cliente (parametro objeto cliente)
    public boolean RegistrarCliente(Cliente cl) {

        String sql = """
                 EXEC InsertarClienteNuevo @nombre = ?, @apellido = ?,@num_documento = ?,@email = ?,@tipo_documento = ?;
                 """;

        try {
            cx = csql.establecerConexion(); //Almacena en cx la conexion con la BD
            PreparedStatement ps = cx.prepareStatement(sql); //Prepara la consulta
            //Se establece la posición de los parametros ingresados en el objeto
            ps.setString(1, cl.nombre);
            ps.setString(2, cl.apellido);
            ps.setString(3, cl.num_doc);
            ps.setString(4, cl.email);
            ps.setInt(5, cl.tipo_doc);
            ps.executeUpdate(); //Ejecuta la actualización
            csql.CerrarSesionSQL(); //Cierra conexion con BD

            return true; //Si se registro correctamente

        } catch (SQLException e) {
            System.out.println(e.toString());
        }

        return false; //Si no se registro correctamente
    }
    
    //Metodo que buscar cliente y retorna un objeto Cliente
    public Cliente BuscarCliente(String numero) {
        
        Cliente cl = new Cliente(); //Instancia de cliente
        
        if (VerificarDoc(numero)) { //Se verifica N°Documento
            System.out.println("Verificación del documento exitosa.");
            //Consulta para mostrar datos del cliente segun numero
            String sql = """
                     select c.id_cliente, p.id_persona, p.nombre, p.apellido, d.nombre_documento, p.tipo_documento, p.num_documento, c.email
                     from Cliente c
                     join Personas p ON c.id_persona = p.id_persona
                     join Documento_cliente d ON p.tipo_documento = d.tipo_documento
                     where p.num_documento = ?;""";

            try {
                cx = csql.establecerConexion();
                System.out.println("Conexión a la base de datos establecida.");
                PreparedStatement ps = cx.prepareStatement(sql);
                ps.setString(1, numero);//Se estable el parametro de la consulta
                System.out.println("Parámetro establecido: " + numero);
                ResultSet rs = ps.executeQuery();//Se ejecuta la consulta y almacena
                if (rs.next()) {//Si hay resultado
                    System.out.println("Resultado de la consulta encontrado.");
                    cl.setID(rs.getString("id_cliente"));
                    cl.setNombre(rs.getString("nombre"));
                    cl.setApellido(rs.getString("apellido"));
                    cl.setEmail(rs.getString("email"));
                    cl.setTipo_doc(rs.getInt("tipo_documento"));
                    cl.setNum_doc(rs.getString("num_documento"));
                    System.out.println("Datos del cliente establecidos.");
                } else {
                    JOptionPane.showMessageDialog(null, "Numero no encontrado");
                    System.out.println("No se encontraron resultados para el número de documento: " + numero);
                }
                csql.CerrarSesionSQL();//Cierra conexion con BD
                System.out.println("Conexión a la base de datos cerrada.");
            } catch (SQLException e) {
                System.out.println(e.toString());
            }
        } else {
            System.out.println("Verificación del documento fallida.");
        }
        return cl;//Retorna objeto cliente con los datos o vacio
    }
    //Metodo para determinar la longitud del numero de documento
    public static boolean Longitud(String numero_cl){
        int lon = numero_cl.length();
        
        if(lon == 3 || lon == 8 || lon == 20 || lon == 11){
            return true;
        }else{
            return false;
        }
        
    }
    
    //Metodo para determinar tipo de documento segun prefijo
    public static int TipoDoc(String numero) {  

        int doc = 9;
        int carnet = 20;
        int dni = 8;
        int ruc = 11;
        int vacio = 3;

        if (VerificarDoc(numero)) {//Verifica que sean numeros
            //Asignamos un indice de doc segun cantidad de numeros
            if (numero.length() == dni) {
                doc = 1;

            } else if (numero.length() == carnet) {
                doc = 3;
            } else if (numero.length() == ruc) {
                //Se diferencia ruc si empieza en 10 o 20
                if (numero.startsWith("10") || numero.startsWith("20")) {
                    doc = 2;
                }
            } 
            else if (numero.length() == vacio & "000".equals(numero) ){
                doc = 1;
            } else {
                System.out.println("Numero invalido");
            }

        }

        return doc; //Retorna numero doc segun tipo de documento
    }
    
    //Metodo que establece el nombre del documento
    public static String Nom_doc(int tipo_doc) {

        String nombre_doc;

        nombre_doc = switch (tipo_doc) {
            case 1 ->
                "DNI";
            case 2 ->
                "RUC";
            case 3 ->
                "CARNET";
            default ->
                "No hay ese tipo de documento";
        };

        return nombre_doc;
    }
    
    //Metodo que verifica que el documento solo son numeros
    public static boolean VerificarDoc(String numero) {
        boolean esNumero = true;
        boolean spacio = numero.contains(" ");
        boolean letra = false;

        //For each para recorrido
        for (char c : numero.toCharArray()) {//Convierte numero en array de caracteres
            if (Character.isLetter(c)) {//Verifica si un caracter es una letra
                letra = true;
                break;
            }
        }

        //Esto se puede cambiar
        if (spacio || letra) {
            esNumero = false;

            if (spacio && letra) {
                System.out.println("El numero contiene espacios y letras");
            } else {
                if (spacio) {
                    System.out.println("El numero contiene "+spacio+" espacios");
                } else {
                    System.out.println("El numero contiene letras");
                }
            }
        }

        return esNumero;
    }

    //Metodo para verificar estructura de Email
    public static boolean VerificarEmail(String email) {
        //Variable con expresion regex para formato de Email
        String EMAIL_PATTERN = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        
        //Compilamos string en un objeto Pattern
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);

        if (email == null) {
            return false;
        } else {
            //Objeto matcher con estructura y email ingresado
            Matcher matcher = pattern.matcher(email);
            return matcher.matches(); //Devuelve la verificación de la estructura
        }

    }

    
    //Getters and Setters
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    @Override
    public String toString() {
        return "Cliente{"
                + "ID ='" + ID + '\''
                + ", Nombre ='" + nombre + '\''
                + ", apellido ='" + apellido + '\''
                + ", tipo de documento ='" + tipo_doc + '\''
                + ", numero de documento ='" + num_doc + '\''
                + ", email ='" + email + '\''
                + '}';
    }
}
