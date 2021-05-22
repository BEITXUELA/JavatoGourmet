package beans;

/**
 * La Gestion de Almacen.
 *
 * Se trata de la clase IVA.
 * 
 * @author Alonso Gil
 */


public class IVA {

    //ATRIBUTOS
    
    private int ivaID;
    
    private String nombre;
    
    private int porcentaje;
    
    
    // CONSTRUCTORES //
    
    
    /**
     * Constructor por defecto (sin parámetros). Crea el iva con todos sus
     * atributos inicializados a los valores por defecto correspondientes a sus
     * tipos de datos.
     */

    public IVA() {
    }

     /**
     * Crea el iva con las informaciones dadas
     * @param ivaID El Identificador del iva
     * @param nombre El nombre del iva
     * @param porcentaje el numero del porcentaje
     */

    public IVA(int ivaID, String nombre, int porcentaje) {
        this.ivaID = ivaID;
        this.nombre = nombre;
        this.porcentaje = porcentaje;
    }
    
    // MÉTODOS GET/IS Y SET //
   
    
    /**
     * Obtiene el ID del iva
     * @param ivaID el Identificador del iva
     */    
    
    public void setIvaID(int ivaID) {
        this.ivaID = ivaID;
    }    
    
    /**
     * Obtiene el nombre del iva
     * @param nombre el nombre del iva
     */
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene el porcentaje del iva
     * @param porcentaje el numero del porcentaje del iva
    */
    
    
    public void setPorcentaje(int porcentaje) {
        this.porcentaje = porcentaje;
    }

     /**
     * Establece el ID del iva
     * @return ID el nuevo ID del iva
     */
    
     public int getIvaID() {
        return ivaID;
    }
    
    /**
     * Establece el nombre del iva
     * @return nombre el nuevo nombre del iva
     */
    
    public String getNombre() {
        return nombre;
    }
    
     /**
     * Establece el numero de porcentaje del iva
     * @return nombre el nuevo porcentaje del iva
     */ 
    
    
    public int getPorcentaje() {
        return porcentaje;
    }   

    /**
     * Redefinición del método heredado de la clase base directa Object.
     * Convierte esta foto en un String que contiene todas sus informaciones
     * internas en formato de texto.
     * @return El String resultante de la conversión
     */
    
    @Override
    public String toString() {
        return "IVA{" + "ivaID=" + ivaID + ", nombre=" + nombre + ", porcentaje=" + porcentaje + '}';
    }

}
