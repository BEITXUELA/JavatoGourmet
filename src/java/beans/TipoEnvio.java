
package beans;

/**
 * La Gestion de Ventas.
 * 
 * Se trata de la clase Tipo de envio se realizara a su domicilio en un tiempo
 * determinado y depenediendo de la prioridad que se requiera.
 *  
 * @author Marino Calero
 */


public class TipoEnvio {
    
    // ATRIBUTOS //
    
    private int tipoEnvioID;
    private String nombre;
    private String descripcion;
    private int incremento;

    
     // CONSTRUCTORES //

     /**
     * Constructor por defecto (sin parámetros).
     * Crea el Tipo de envio con todos sus atributos inicializados a los valores por
     * defecto correspondientes a sus tipos de datos.
     */
    
    public TipoEnvio() {
    }

    
     /**
     * Crea una cesta con las informaciones dadas
     * @param tipoEnvioID El Identificador de envio que se enviara
     * @param nombre El nombre del Tipo de envio que se enviara
     * @param descripcion La descripcion que se requiere
     * @param incremento El incremento que se requiere
     **/ 
    
    public TipoEnvio(int tipoEnvioID, String nombre, String descripcion, int incremento) {
        this.tipoEnvioID = tipoEnvioID;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.incremento = incremento;
    }

    
      // MÉTODOS GET/IS Y SET //  
   

     /**
     * Obtiene el ID del Tipo de envio
     * @return el ID de este Tipo de envio
     */
    
    public int getTipoEnvioID() {
        return tipoEnvioID;
    }

    /**
     * Obtiene el nombre del Tipo de envio
     * @return el nombre de este Tipo de envio
     */
    
    public String getNombre() {
        return nombre;
    }

     /**
     * Obtiene la descripcion del Tipo de envio
     * @return la descripcion de este Tipo de envio
     */
    
    public String getDescripcion() {
        return descripcion;
    }

     /**
     * Obtiene el incremento del Tipo de envio
     * @return el incremento de este Tipo de envio
     */
    
    public int getIncremento() {
        return incremento;
    }

     /**
     * Establece el ID del Tipo de envio
     * @param tipoEnvioID el nuevo ID del Tipo de envio
     */ 
    
     public void setTipoEnvioID(int tipoEnvioID) {
        this.tipoEnvioID = tipoEnvioID;
    }
     
     /**
     * Establece el nombre del Tipo de envio
     * @param nombre el nuevo nombre del Tipo de envio
     */ 
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

     /**
     * Establece la descripcion del Tipo de envio
     * @param descripcion La nueva descripcion del Tipo de envio
     */ 
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

     /**
     * Establece el incremento del Tipo de envio
     * @param incremento el nuevo incremento del Tipo de envio
     */ 
    
    public void setIncremento(int incremento) {
        this.incremento = incremento;
    }

       
     /**
     * Redefinición del método heredado de la clase base directa Object.
     * Convierte esta cesta en un String que contiene todas sus informaciones
     * internas en formato de texto.
     * @return El String resultante de la conversión
     */
    
   @Override
    public String toString() {
        return "TipoEnvio{" + "tipoEnvioID=" + tipoEnvioID + ", nombre=" + nombre + ", descripcion=" + descripcion + ", incremento=" + incremento + '}';
    }

    
    
    
    
}
