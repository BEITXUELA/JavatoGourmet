package beans;

/**
 * La Gestion de Usuarios/Clientes.
 *
 * Se trata de la clase Provincia.
 *
 * @author Bea Robledillo Gómez
 */
public class Provincia {

    //ATRIBUTOS//
    private int provinciaID;
    private String nombre;
    private int paisID;

    // CONSTRUCTORES //
    /**
     * Constructor por defecto (sin parámetros). Crea la Provincia con todos sus
     * atributos inicializados a los valores por defecto correspondientes a sus
     * tipos de datos.
     */
    public Provincia() {
    }

    /**
     * Crea un paisID con las informaciones dadas
     *
     * @param provinciaID Es el Identificador de la provincia
     * @param nombre Es el nombre de la provincia
     * @param pais Es el paisID de la provincia
     */
    public Provincia(int provinciaID, String nombre, int pais) {
        this.provinciaID = provinciaID;
        this.nombre = nombre;
        this.paisID = pais;
    }

    // MÉTODOS GET/IS Y SET //

    /**
     * Obtiene el ID de la Provincia
     *
     * @return ID de la provincia
     */
    
    public int getProvinciaID() {
        return provinciaID;
    }

     /**
     * Establece el ID de la provincia
     *
     * @param provinciaID de la provincia
     *
     */
    
    public void setProvinciaID(int provinciaID) {
        this.provinciaID = provinciaID;
    }    
    
    /**
     * Obtiene el nombre de la Provincia
     *
     * @return nombre de la provincia
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre de la provincia
     *
     * @param nombre de la provincia
     *
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene el paisID de la Provincia
     *
     * @return paisID de la provincia
     */
    public int getPaisID() {
        return paisID;
    }

    /**
     * Establece el paisID de la provincia
     *
     * @param paisID del usuario
     *
     */
    public void setPaisID(int paisID) {
        this.paisID = paisID;
    }   

    /**
     * Redefinición del método heredado de la clase base directa Object.
     * Convierte esta provincia en un String que contiene todas sus
     * informaciones internas en formato de texto, cumpliendo con todos los
     * requisitos descritos en la documentación del método toString() de la
     * clase Object
     *
     * @return El String resultante de la conversión
     */
    
    @Override
    public String toString() {
        return "Provincia{" + "provinciaID=" + provinciaID + ", nombre=" + nombre + ", pais=" + paisID + '}';
    }
}
