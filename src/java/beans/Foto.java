package beans;

/**
 * La Gestion de Almacen.
 *
 * Se trata de la clase Foto.
 *
 * @author Alonso Gil
 */
public class Foto {

    //ATRIBUTOS
    private int fotoID;

    private int productoID;

    private String nombre;

    private String url;

    // CONSTRUCTORES //
    /**
     * Constructor por defecto (sin parámetros). Crea el cupon con todos sus
     * atributos inicializados a los valores por defecto correspondientes a sus
     * tipos de datos.
     */
    public Foto() {
    }

    /**
     * Crea una foto con las informaciones dadas
     *
     * @param fotoID El Identificador de la foto
     * @param nombre El nombre de la foto
     * @param productoID El Identificador del Producto
     * @param url El nombre de la url
     */
    public Foto(int fotoID, int productoID, String nombre, String url) {
        this.fotoID = fotoID;
        this.productoID = productoID;
        this.nombre = nombre;
        this.url = url;
    }

    // MÉTODOS GET/IS Y SET //
    /**
     * Establece el Identificador de la foto
     *
     * @param fotoID el ID de esta foto
     */
    public void setFotoID(int fotoID) {
        this.fotoID = fotoID;
    }

    /**
     * Establece el nombre de la foto
     *
     * @param nombre el nombre de esta foto
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Establece la url de la foto
     *
     * @param url la url de esta foto
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * Establece la ID de la producto
     *
     * @param productoID la ID de esta producto
     */

    public void setProductoID(int productoID) {
        this.productoID = productoID;
    }

    /**
     * Obtiene el ID de la foto
     *
     * @return ID el nuevo ID de la foto
     */
    public int getFotoID() {
        return fotoID;
    }

    
    
    /**
     * Obtiene la ID del producto
     * @return productoID Devuelve el ID del producto
     */
    
    
    public int getProductoID() {
        return productoID;
    }

  
    /**
     * Obtiene el nombre de la foto
     *
     * @return nombre el nuevo nombre de la foto
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Obtiene la url de la foto
     *
     * @return url la nueva url de la foto
     */
    public String getUrl() {
        return url;
    }

    /**
     * Redefinición del método heredado de la clase base directa Object.
     * Convierte esta foto en un String que contiene todas sus informaciones
     * internas en formato de texto.
     *
     * @return El String resultante de la conversión
     */
    @Override
    public String toString() {
        return "Foto{" + "fotoID=" + fotoID + ", productoID=" + productoID + ", nombre=" + nombre + ", url=" + url + '}';
    }
}
