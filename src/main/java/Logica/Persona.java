package Logica;


public abstract class Persona {
    //Inicializo los atributos en nulo
    String ID = null;
    String nombre = null;
    String apellido =  null;
    int tipo_doc = 9;
    String num_doc = null;
    String celular = null;

    //Metodo constructor completo
    public Persona(String ID, String nombre, String apellido, int tipo_doc, String num_doc) {
        
        this.ID = ID;
        this.nombre = nombre;
        this.apellido = apellido;
        this.tipo_doc = tipo_doc;
        this.num_doc = num_doc;
        
    }
    
    //Metodo constructor sobrecargado
    public Persona(String ID, String nombre, String apellido){
        
        this.ID = ID;
        this.nombre = nombre;
        this.apellido = apellido;
        
    }
    
    //Constructor vacio
    public Persona(){
    }    
    
    //Getters and Setters
    public String getID() {
        return ID;
    }
    public void setID(String ID) {
        this.ID = ID;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getApellido() {
        return apellido;
    }
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
    public int getTipo_doc() {
        return tipo_doc;
    }
    public void setTipo_doc(int tipo_doc) {
        this.tipo_doc = tipo_doc;
    }
    public String getNum_doc() {
        return num_doc;
    }
    public void setNum_doc(String num_doc) {
        this.num_doc = num_doc;
    }
    public String getCelular() {
        return celular;
    }
    public void setCelular(String celular) {
        this.celular = celular;
    }
    
    
        
}
