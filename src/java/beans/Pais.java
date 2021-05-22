package beans;

/**
 * La Gestion de Usuarios/Clientes.
 *
 * Se trata de la clase Pais. Para los paises de las direcciones de envio
 *
 * @author Bea Robledillo Gómez
 */
public class Pais {

    //ATRIBUTOS//
    private int paisID;
    private String nombre;

    // CONSTRUCTORES //
    /**
     * Constructor por defecto (sin parámetros). Crea el Pais con todos sus
     * atributos inicializados a los valores por defecto correspondientes a sus
     * tipos de datos.
     */
    public Pais() {
    }

    /**
     * Crea un Pais con las informaciones dadas
     *
     * @param paisID Es el Identificador del Pais
     * @param nombre Es el nombre del Pais
     */
    public Pais(int paisID, String nombre) {
        this.paisID = paisID;
        this.nombre = nombre;
    }

    // MÉTODOS GET/IS Y SET //

    /**
     * devuelve el ID del pais
     *
     * @return ID
     */
    
    public int getPaisID() {
        return paisID;
    }   
    
    /**
     * devuelve el nombre del pais
     *
     * @return nombre
     */
    
    public String getNombre() {
        return nombre;
    }

     /**
     * Establece la referencia del ID del pais
     *
     * @param paisID
     *
     * *
     */
    
     public void setPaisID(int paisID) {
        this.paisID = paisID;
    }
    
    /**
     * Establece la referencia del nombre del pais
     *
     * @param nombre
     *
     * *
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
  
    /**
     * Redefinición del método heredado de la clase base directa Object.
     * Convierte este pais en un String que contiene todas sus informaciones
     * internas en formato de texto, cumpliendo con todos los requisitos
     * descritos en la documentación del método toString() de la clase Object
     *
     * @return El String resultante de la conversión
     */
   
      @Override
    public String toString() {
        return "Pais{" + "paisID=" + paisID + ", nombre=" + nombre + '}';
    }

}
