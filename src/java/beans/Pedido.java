
package beans;

import java.util.Date;

/**
 * La Gestion de Ventas.
 * 
 * Se trata de la clase Pedido donde nuestros clienets efectuan la eleccion de 
 * los productos que requiera para luego mostrar una orden de compra.
 *  
 * @author Marino Calero
 */


/** ....*/

public class Pedido {
    
    
     // ATRIBUTOS //
    
    private int pedidoID;
    
    private int direccionFacID;
    
    private int direccionEnvID;
    
    private int tiipoPagoID;
    
    private int cuponID;
    
    private int estadoPedidoID;
    
    private int referencia;
    
    private Date fecha;
    
    private double total;
    
    private String cupon;
    
    
    
     // CONSTRUCTORES //

     /**
     * Constructor por defecto (sin parámetros).
     * Crea el pedido con todos sus atributos inicializados a los valores por
     * defecto correspondientes a sus tipos de datos.
     */
    
    
    public Pedido() {
    }

     /**
     * Crea un pedido con las informaciones dadas
     * @param pedidoID Es un Identificador para localizar el pedido
     * @param referencia Es una forma como localizar el pedido
     * @param fecha La fecha del pedido que se enviara
     * @param total La cantidad total de productos que se requiere
     * @param direccionEnvID A donde se le enviara el pedido
     * @param direccionFacID Si hubiera alguna otra direccion donde facturar
     * @param estadoPedidoID Se selecciona de un enumerado las alternativas 
     * @param tipoPagoID De que forma se hara efectivo el pago del pedido
     * @param cuponID  Identificador del cupon
     * @param cupon A nuestros mejores clientes o por temporadas se le hara llegar cupones. Este es el nombre del Cupón
     */
    
        public Pedido(int pedidoID, int direccionFacID, int direccionEnvID, int tipoPagoID, int cuponID, int estadoPedidoID, int referencia, Date fecha, double total, String cupon) {
        this.pedidoID = pedidoID;
        this.direccionFacID = direccionFacID;
        this.direccionEnvID = direccionEnvID;
        this.tiipoPagoID = tipoPagoID;
        this.cuponID = cuponID;
        this.estadoPedidoID = estadoPedidoID;
        this.referencia = referencia;
        this.fecha = fecha;
        this.total = total;
        this.cupon = cupon;
    }
  
    
    
     // MÉTODOS GET/IS Y SET //

     /**
     * Establece el ID del pedido
     * @param pedidoID LEl nuevo ID del pedido debe ser un identificador
     */
    
      public void setPedidoID(int pedidoID) {
        this.pedidoID = pedidoID;
    }

     /**
     * Obtiene el ID del Pedido
     * @return el ID de este Pedido
     */ 
      
    public int getPedidoID() {
        return pedidoID;
    }

    /**
     * Establece la referencia del pedido
     * @param referencia La nueva referencia del pedido debe ser un identificador
     * único, es decir, cada pedido debe tener una referencia diferente
     */
    public void setReferencia(int referencia) {
        this.referencia = referencia;
    }
    
     /**
     * Obtiene la referencia del Pedido
     * @return la referencia de este Pedido
     */  
    
    public int getReferencia() {
        return referencia;
    }
    
     /**
     * Establece la fecha del pedido
     * @param fecha La nueva fecha del pedido 
     */
    
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    
     /**
     * Obtiene la fecha del Pedido
     * @return la fecha de este Pedido
     */ 
    
    public Date getFecha() {
        return fecha;
    }
    
     /**
     * Establece el total del pedido
     * @param total El nuevo total del pedido 
     */
    
    public void setTotal(double total) {
        this.total = total;
    }

     /**
     * Obtiene el total del Pedido
     * @return el total de este Pedido
     */ 

    public double getTotal() {
        return total;
    }
    
     /**
     * Establece la Direccion de Envio del pedido
     * @param direccionEnvID La nueva Direccion de Envio del pedido 
     */
    
    public void setDireccionEnvID(int direccionEnvID) {
        this.direccionEnvID = direccionEnvID;
    }

     /**
     * Obtiene la Direccion de Envio del Pedido
     * @return la Direccion de Envio de este Pedido
     */ 
    
    public int getDireccionEnvID() {
        return direccionEnvID;
    }
        
     /**
     * Establece la Direccion de Facturacion del pedido
     * @param direccionFacID La nueva Direccion de Facturacion del pedido 
     */   
     
    public void setDireccionFacID(int direccionFacID) {
        this.direccionFacID = direccionFacID;
    }

     /**
     * Obtiene la Direccion de Facturacion del Pedido
     * @return la Direccion de Facturacion de este Pedido
     */ 
    
    public int getDireccionFacID() {
        return direccionFacID;
    }
    
     /**
     * Establece el estado del pedido
     * @param estadoPedidoID El nuevo estado del pedido 
     */
    
    public void setEstadoPedidoID(int estadoPedidoID) {
        this.estadoPedidoID = estadoPedidoID;
    }

     /**
     * Obtiene el estado del Pedido
     * @return el estado de este Pedido
     */ 
    
    public int getEstadoPedidoID() {
        return estadoPedidoID;
    }
    
     /**
     * Establece el tipo de pago del pedido
     * @param tipoPagoID El Identificador del tipo de pago del pedido 
     */
    
    public void setTipoPagoID(int tipoPagoID) {
        this.tiipoPagoID = tipoPagoID;
    }
       
     /**
     * Obtiene el tipo de pago del Pedido
     * @return el tipo de pago de este Pedido
     */ 
    
    public int getTiipoPagoID() {
        return tiipoPagoID;
    }
        
     /**
     * Establece el cupon del pedido
     * @param cuponID El nuevo cupon del pedido 
     */ 
    
    public void setCuponID(int cuponID) {
        this.cuponID = cuponID;
    }
    
     /**
     * Obtiene el cupon del Pedido
     * @return el cupon de este Pedido
     */ 

    public int getCuponID() {
        return cuponID;
    }   

    
     /**
     * Obtiene el tipo de pago del Pedido
     * @return el tipo de pago de este Pedido
     */ 
    
    public String getCupon() {
        return cupon;
    }

    
     /**
     * Establece el cupon del pedido
     * @param cupon El nuevo cupon del pedido 
     */ 
    public void setCupon(String cupon) {
        this.cupon = cupon;
    }

     /**
     * Redefinición del método heredado de la clase base directa Object.
     * Convierte este pedido en un String que contiene todas sus informaciones
     * internas en formato de texto, cumpliendo con todos los requisitos
     * descritos en la documentación del método toString() de la clase Object
     * @return El String resultante de la conversión
     */
   
     @Override
    public String toString() {
        return "Pedido{" + "pedidoID=" + pedidoID + ", direccionFacID=" + direccionFacID + ", direccionEnvID=" + direccionEnvID + ", tiipoPagoID=" + tiipoPagoID + ", cuponID=" + cuponID + ", estadoPedidoID=" + estadoPedidoID + ", referencia=" + referencia + ", fecha=" + fecha + ", total=" + total + ", cupon=" + cupon + '}';
    }
    
            
}
