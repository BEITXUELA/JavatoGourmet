
package beans;

import java.util.Date;

/**
 * La Gestion de Ventas.
 * 
 * Se trata de la clase Cupon que ofrece código de descuento personalizado por
 * cliente o bien código promocional general para todos los clientes.
 *  
 * @author Marino Calero
 */


public class Cupon {
    
    // ATRIBUTOS //
    
    private int cuponID;
    
    private String nombre;
    
    private int descuento;
    
    private Date fechaExpiracion;

    
     // CONSTRUCTORES //

     /**
     * Constructor por defecto (sin parámetros).
     * Crea el cupon con todos sus atributos inicializados a los valores por
     * defecto correspondientes a sus tipos de datos.
     */
    
    public Cupon() {
    }

     /**
     * Crea una cesta con las informaciones dadas
     * @param cuponID El Identificador del cupon que se enviara
     * @param nombre El nombre del cupon que se enviara
     * @param descuento El descuento total que se requiere
     * @param fechaExpiracion La fecha de expiracion que se requiere
    **/ 
    
    public Cupon(int cuponID, String nombre, int descuento, Date fechaExpiracion) {
        this.cuponID = cuponID;
        this.nombre = nombre;
        this.descuento = descuento;
        this.fechaExpiracion = fechaExpiracion;
    }

    
      // MÉTODOS GET/IS Y SET //
 
     /**
     * Obtiene el ID del cupon
     * @return el ID de este cupon
     */
   
     public int getCuponID() {
        return cuponID;
    }
    
    /**
     * Obtiene el nombre del cupon
     * @return el nombre de este cupon
     */
     
    public String getNombre() {
        return nombre;
    }

     /**
     * Obtiene el descuento del cupon
     * @return el descuento de este cupon
     */
    
    public int getDescuento() {
        return descuento;
    }

     /**
     * Obtiene la fecha de expiracion del cupon
     * @return la fecha de expiracion de este cupon
     */
    
    public Date getFechaExpiracion() {
        return fechaExpiracion;
    }
    
     /**
     * Establece el nombre del cupon
     * @param cuponID el nuevo nombre del cupon
     */  
    
     public void setCuponID(int cuponID) {
        this.cuponID = cuponID;
    }
    
     /**
     * Establece el nombre del cupon
     * @param nombre el nuevo nombre del cupon
     */        
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

     /**
     * Establece el descuento del cupon
     * @param descuento El nuevo descuento del cupon
     */
        
    public void setDescuento(int descuento) {
        this.descuento = descuento;
    }

     /**
     * Establece la fecha de expiracion del cupon
     * @param fechaExpiracion La nueva la fecha de expiracion del cupon
     */
     
    
    public void setFechaExpiracion(Date fechaExpiracion) {
        this.fechaExpiracion = fechaExpiracion;
    }
 
    
     /**
     * Redefinición del método heredado de la clase base directa Object.
     * Convierte esta cesta en un String que contiene todas sus informaciones
     * internas en formato de texto.
     * @return El String resultante de la conversión
     */
    
    @Override
    public String toString() {
        return "Cupon{" + "cuponID=" + cuponID + ", nombre=" + nombre + ", descuento=" + descuento + ", fechaExpiracion=" + fechaExpiracion + '}';
    }    
    
}
