
package beans;

/**
 * La Gestion de Ventas.
 * 
 * Se trata de la clase Tipo de pago se podra hacer de diferentes formas entre
 * ellas esta el paypal, tarjeta de credito, visa electron, entre otros.
 *  
 * @author Marino Calero
 */


public class TipoPago {
    
    // ATRIBUTOS //
    
    private int tipoPagoID;
    private String nombre;
    private int incremento;

    
      // CONSTRUCTORES //

     /**
     * Constructor por defecto (sin parámetros).
     * Crea el Tipo de pago con todos sus atributos inicializados a los valores por
     * defecto correspondientes a sus tipos de datos.
     */
    
    public TipoPago() {
    }

     /**
     * Crea una cesta con las informaciones dadas
     * @param tipoPagoID El Identificador del Tipo de envio que se enviara
     * @param nombre El nombre del Tipo de envio que se enviara
     * @param incremento El incremento que se requiere
     **/ 
    
    public TipoPago(int tipoPagoID, String nombre, int incremento) {
        this.tipoPagoID = tipoPagoID;
        this.nombre = nombre;
        this.incremento = incremento;
    }

    
      // MÉTODOS GET/IS Y SET //
    

    /**
     * Obtiene el ID del Tipo de pago
     * @return el ID de este Tipo de pago
     */
    
    public int getTipoPagoID() {
        return tipoPagoID;
    }

    /**
     * Obtiene el nombre del Tipo de pago
     * @return el nombre de este Tipo de pago
     */
    
    public String getNombre() {
        return nombre;
    }
    /**
     * Obtiene el incremento del Tipo de pago
     * @return el incremento de este Tipo de pago
     */    
    
    public int getIncremento() {
        return incremento;
    }

     /**
     * Establece el ID del Tipo de pago
     * @param tipoPagoID el nuevo ID del Tipo de pago
     */
    
    
    public void setTipoPagoID(int tipoPagoID) {
        this.tipoPagoID = tipoPagoID;
    }
    
     /**
     * Establece el nombre del Tipo de pago
     * @param nombre el nuevo nombre del Tipo de pago
     */
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
     /**
     * Establece el incremento del Tipo de pago
     * @param incremento el nuevo incremento del Tipo de pago
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
        return "TipoPago{" + "tipoPagoID=" + tipoPagoID + ", nombre=" + nombre + ", incremento=" + incremento + '}';
    }
    
    
    
}
