package beans;

/**
 * La Gestion de Usuarios/Clientes.
 *
 * Se trata de la clase Poblacion.
 *
 * @author Bea Robledillo Gómez
 */
public class Poblacion {

    //ATRIBUTOS//
    private int poblacionID;
    private String nombre;
    private int provinciaID;

    // CONSTRUCTORES //
    /**
     * Constructor por defecto (sin parámetros). Crea la Poblacion con todos sus
     * atributos inicializados a los valores por defecto correspondientes a sus
     * tipos de datos.
     */
    public Poblacion() {
    }

    /**
     * Crea una poblacion con las informaciones dadas
     *
     * @param poblacionID Es el Identificador de la poblacion
     * @param nombre Es el nombre de la poblacion
     * @param provincia Es la provinciaID de la poblacion
     */
    public Poblacion(int poblacionID, String nombre, int provincia) {
        this.poblacionID = poblacionID;
        this.nombre = nombre;
        this.provinciaID = provincia;
    }

    // MÉTODOS GET/IS Y SET //  
    
     /**
     * Obtiene el ID de la Poblacion
     *
     * @return ID de la poblacion
     */
    
    public int getPoblacionID() {
        return poblacionID;
    }    
    
     /**
     * Establece el ID de la poblacion
     *
     * @param poblacionID de la poblacion
     *
     */  
    
     public void setPoblacionID(int poblacionID) {
        this.poblacionID = poblacionID;
    }
    
    /**
     * Obtiene el nombre de la Poblacion
     *
     * @return nombre de la poblacion
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre de la poblacion
     *
     * @param nombre de la poblacion
     *
     */   
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene la provincua de la Poblacion
     *
     * @return provinciaID de la poblacion
     */
    public int getProvinciaID() {
        return provinciaID;
    }

    /**
     * Establece la provinciaID de la poblacion
     *
     * @param provinciaID de la poblacion
     *
     */
    public void setProvinciaID(int provinciaID) {
        this.provinciaID = provinciaID;
    }   

    /**
     * Redefinición del método heredado de la clase base directa Object.
     * Convierte esta poblacion en un String que contiene todas sus
     * informaciones internas en formato de texto, cumpliendo con todos los
     * requisitos descritos en la documentación del método toString() de la
     * clase Object
     *
     * @return El String resultante de la conversión
     */
   
    @Override
    public String toString() {
        return "Poblacion{" + "poblacionID=" + poblacionID + ", nombre=" + nombre + ", provincia=" + provinciaID + '}';
    }
}
