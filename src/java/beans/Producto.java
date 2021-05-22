package beans;

import java.util.Date;

/**
 * La Gestion de Almacen.
 *
 * Se trata de la clase Producto en el que hace referencia a un producto
 * especifico elegido por el cliente.
 *
 * @author Alonso Gil
 */
public class Producto {

    //ATRIBUTOS.
    private int productoID;

    private int ivaID;

    private int referencia;

    private String nombre;

    private String descripcion;

    private double precioBase;

    private double peso;

    private Date fechaAlta;

    private int stock;

    private boolean isNovedad;

    private boolean isDeluxe;

    //ATRIBUTO URL DE FOTO PARA QUE UN PRODUCTO AL MENOS TENGA UNA FOTO PARA EL LISTADO DE PRODUCTOS
    private String fotoDefault;

    // CONSTRUCTORES //
    /**
     * Constructor por defecto (sin parámetros). Crea el cupon con todos sus
     * atributos inicializados a los valores por defecto correspondientes a sus
     * tipos de datos.
     */
    public Producto() {
    }

    /**
     * Crea un producto con las informaciones dadas
     *
     * @param productoID El Identificador del producto
     * @param referencia La referencia del producto
     * @param nombre El nombre del producto
     * @param descripcion La descripcion del producto
     * @param precioBase El precio base del producto
     * @param peso El peso del producto
     * @param fechaAlta La fecha de alta del producto
     * @param stock El stock del producto
     * @param isNovedad El booleano de una novedad del producto
     * @param isDeluxe El booleano de un producto deluxe
     * @param ivaID El IVA del producto
     */
    public Producto(int productoID, int ivaID, int referencia, String nombre, String descripcion, double precioBase, double peso, Date fechaAlta, int stock, boolean isNovedad, boolean isDeluxe) {
        this.productoID = productoID;
        this.ivaID = ivaID;
        this.referencia = referencia;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precioBase = precioBase;
        this.peso = peso;
        this.fechaAlta = fechaAlta;
        this.stock = stock;
        this.isNovedad = isNovedad;
        this.isDeluxe = isDeluxe;
    }

    /**
     *
     * Crea un producto con las informaciones dadas y la foto por defecto
     *
     * @param productoID El Identificador del producto
     * @param referencia La referencia del producto
     * @param nombre El nombre del producto
     * @param descripcion La descripcion del producto
     * @param precioBase El precio base del producto
     * @param peso El peso del producto
     * @param fechaAlta La fecha de alta del producto
     * @param stock El stock del producto
     * @param isNovedad El booleano de una novedad del producto
     * @param isDeluxe El booleano de un producto deluxe
     * @param ivaID El IVA del producto
     * @param fotoDefault La foto por defecto del producto, util para los
     * listados de productos
     */
    public Producto(int productoID, int ivaID, int referencia, String nombre, String descripcion, double precioBase, double peso, Date fechaAlta, int stock, boolean isNovedad, boolean isDeluxe, String fotoDefault) {
        this.productoID = productoID;
        this.ivaID = ivaID;
        this.referencia = referencia;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precioBase = precioBase;
        this.peso = peso;
        this.fechaAlta = fechaAlta;
        this.stock = stock;
        this.isNovedad = isNovedad;
        this.isDeluxe = isDeluxe;
        this.fotoDefault = fotoDefault;
    }

    // MÉTODOS GET/IS Y SET //    
    /**
     * Obtiene el ID del producto
     *
     * @param productoID el ID del producto
     */
    public void setProductoID(int productoID) {
        this.productoID = productoID;
    }

    /**
     * Obtiene la referencia del producto
     *
     * @param referencia la referencia del producto
     */
    public void setReferencia(int referencia) {
        this.referencia = referencia;
    }

    /**
     * Obtiene el nombre del producto
     *
     * @param nombre El nombre del producto
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene la descripcion del producto
     *
     * @param descripcion La descripcion del producto
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Obtiene el precio base del producto
     *
     * @param precioBase El precio base del producto
     */
    public void setPrecioBase(double precioBase) {
        this.precioBase = precioBase;
    }

    /**
     * Obtiene el peso del producto
     *
     * @param peso El peso del producto
     */
    public void setPeso(double peso) {
        this.peso = peso;
    }

    /**
     * Obtiene la fecha de alta del producto
     *
     * @param fechaAlta La fecha de alta del producto
     */
    public void setFechaAlta(Date fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    /**
     * Obtiene el stock del producto
     *
     * @param stock El stock del producto
     */
    public void setStock(int stock) {
        this.stock = stock;
    }

    /**
     * Obtiene la novedad del producto
     *
     * @param isNovedad La novedad del producto
     */
    public void setIsNovedad(boolean isNovedad) {
        this.isNovedad = isNovedad;
    }

    /**
     * Obtiene el producto deluxe
     *
     * @param isDeluxe El producto deluxe
     */
    public void setIsDeluxe(boolean isDeluxe) {
        this.isDeluxe = isDeluxe;
    }

    /**
     * Obtiene el IVA del producto
     *
     * @param ivaID El IVA del producto
     */
    public void setivaID(int ivaID) {
        this.ivaID = ivaID;
    }

    /**
     * Establece el ID del producto
     *
     * @return ID El nuevo ID del producto
     */
    public int getProductoID() {
        return productoID;
    }

    /**
     * Establece la referencia del producto
     *
     * @return referencia La nueva referencia del producto
     */
    public int getReferencia() {
        return referencia;
    }

    /**
     * Establece el nombre del producto
     *
     * @return nombre El nuevo nombre del producto
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece la descripcion del producto
     *
     * @return descripcion La nueva descripcion del producto
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Establece el precio base del producto
     *
     * @return precioBase El nuevo precio base del producto
     */
    public double getPrecioBase() {
        return precioBase;
    }

    /**
     * Establece el peso del producto
     *
     * @return peso El nuevo peso del producto
     */
    public double getPeso() {
        return peso;
    }

    /**
     * Establece la fecha de alta del producto
     *
     * @return fechaAlta La nueva fecha de alta del producto
     */
    public Date getFechaAlta() {
        return fechaAlta;
    }

    /**
     * Establece el stock del producto
     *
     * @return stock El nuevo stock del producto
     */
    public int getStock() {
        return stock;
    }

    /**
     * Establece un boleano de la novedad del producto
     *
     * @return isNovedad El nuevo boleano de la novedad del producto
     */
    public boolean isIsNovedad() {
        return isNovedad;
    }

    /**
     * Establece el boleano del producto deluxe
     *
     * @return isDeluxe El nuevo boleano del producto deluxe
     */
    public boolean isIsDeluxe() {
        return isDeluxe;
    }

    /**
     * Establece el IVA del producto
     *
     * @return IVA El nuevo IVA del producto
     */
    public int getivaID() {
        return ivaID;
    }

    /**
     * Obtiene la URL de la foto del producto
     *
     * @return String La URL
     */
    public String getFotoDefault() {
        return fotoDefault;
    }

    /**
     * Establece ña URL de la foto del producto
     *
     * @param fotoDefault URL del producto
     */
    public void setFotoDefault(String fotoDefault) {
        this.fotoDefault = fotoDefault;
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
        return "Producto{" + "productoID=" + productoID
                + ", ivaID=" + ivaID
                + ", referencia=" + referencia
                + ", nombre=" + nombre
                + ", descripcion=" + descripcion
                + ", precioBase=" + precioBase
                + ", peso=" + peso
                + ", fechaAlta=" + fechaAlta
                + ", stock=" + stock
                + ", isNovedad=" + isNovedad
                + ", isDeluxe=" + isDeluxe + '}';
    }

}
