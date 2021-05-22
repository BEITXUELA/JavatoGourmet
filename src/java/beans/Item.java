package beans;

/**
 * <strong>Parte de la gestion de ventas</strong>
 *
 * Este Bean almacena registros ITEM que seran utilizados por las cestas y los
 * pedidos. Estos ITEMS permiten relacionar una cesta con un pedido, para ello,
 * en el evento de finalizar compra, se generara el pedido por un lado y despues
 * a todos los items de la cesta en sesion se le asignara el ID de pedido al que
 * perteneceran. Por esta razon, pedidoID estara vacio cuando solo tengamos la
 * cesta en sesion.
 *
 * <strong>2015/07/17 - Eduardo:</strong> He agregado atributos nuevos para
 * adaptar a la BBDD
 *
 * @author Marino Calero
 */
public class Item {

    // ATRIBUTOS //
    private int itemID;
    private int pedidoID;
    private int cestaID;
    private int productoID;
    private int cantidad;
    private double precio;
    //OPCIONAL
    private String productoNombre;

    // CONSTRUCTORES //
    /**
     * Constructor por defecto (sin parámetros). Crea el item con todos sus
     * atributos inicializados a los valores por defecto correspondientes a sus
     * tipos de datos.
     */
    public Item() {
    }

    /**
     * Crea un item con las informaciones dadas
     *
     * @param itemID El Identificador que se enviara
     * @param pedidoID El ID del pedido al que se asocia el item
     * @param cestaID Es el Identificador de la Cesta
     * @param productoID Es el Identificador del Producto
     * @param cantidad La cantidad que se enviara
     * @param precio La precio total que se requiere
     *
     */
    public Item(int itemID, int pedidoID, int cestaID, int productoID, int cantidad, double precio) {
        this.itemID = itemID;
        this.pedidoID = pedidoID;
        this.cestaID = cestaID;
        this.productoID = productoID;
        this.cantidad = cantidad;
        this.precio = precio;
    }

    /**
     * Crea un item con las informaciones dadas y ademas con el nombre del producto asociado.
     * Esto es para poder mostrarlo en la cesta de compra
     * 
     * @param itemID El Identificador que se enviara
     * @param pedidoID El ID del pedido al que se asocia el item
     * @param cestaID Es el Identificador de la Cesta
     * @param productoID Es el Identificador del Producto
     * @param cantidad La cantidad que se enviara
     * @param precio La precio total que se requiere
     * @param productoNombre El nombre del prodcuto asociado
     */
    public Item(int itemID, int pedidoID, int cestaID, int productoID, int cantidad, double precio, String productoNombre) {
        this.itemID = itemID;
        this.pedidoID = pedidoID;
        this.cestaID = cestaID;
        this.productoID = productoID;
        this.cantidad = cantidad;
        this.precio = precio;
        this.productoNombre = productoNombre;
    }
    
    

    // MÉTODOS GET/IS Y SET //    
    /**
     * Obtiene el ID de la item
     *
     * @return el ID de esta item
     */
    public int getItemID() {
        return itemID;
    }

    /**
     * Obtiene la cantidad del item
     *
     * @return la cantidad de este item
     */
    public int getCantidad() {
        return cantidad;
    }

    /**
     * Obtiene el precio del item
     *
     * @return el precio de este item
     */
    public double getPrecio() {
        return precio;
    }

    /**
     * Establece el ID del item
     *
     * @param itemID El nuevo ID de item
     */
    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    /**
     * Establece la cantidad de items
     *
     * @param cantidad La nueva cantidad de item
     */
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    /**
     * Establece el precio del item
     *
     * @param precio El nuevo precio del item
     */
    public void setPrecio(double precio) {
        this.precio = precio;
    }

    /**
     * Obtiene el ID de pedido asociado a este item
     *
     * @return int El ID de pedido
     */
    public int getPedidoID() {
        return pedidoID;
    }

    /**
     * Establece el ID de pedido asociado a este item
     *
     * @param pedidoID El ID de pedido
     */
    public void setPedidoID(int pedidoID) {
        this.pedidoID = pedidoID;
    }

    /**
     * Obtiene el ID la cesta asociada a este item
     *
     * @return int El ID de la cesta
     */
    public int getCestaID() {
        return cestaID;
    }

    /**
     * Establece el ID de la cesta asociada a este Item
     *
     * @param cestaID El ID de la cesta
     */
    public void setCestaID(int cestaID) {
        this.cestaID = cestaID;
    }

    /**
     * Obtiene el ID de producto asociado al item
     *
     * @return int ID de prodcuto
     */
    public int getProductoID() {
        return productoID;
    }

    /**
     * Establece el ID del producto asociado al item
     *
     * @param productoID El ID de producto
     */
    public void setProductoID(int productoID) {
        this.productoID = productoID;
    }

    /**
     * Obtiene el nombre del producto asociado al item
     * @return String Nombre del producto
     */
    public String getProductoNombre() {
        return productoNombre;
    }

    /**
     * Establece el nombre del producto asociado al item
     * @param productoNombre El nombre del producto
     */
    public void setProductoNombre(String productoNombre) {
        this.productoNombre = productoNombre;
    }
    
    

    /**
     * Redefinición del método heredado de la clase base directa Object.
     * Convierte este item en un String que contiene todas sus informaciones
     * internas en formato de texto.
     *
     * @return El String resultante de la conversión
     */
    @Override
    public String toString() {
        return "Item{" + "itemID=" + itemID + ", cantidad=" + cantidad + ", precio=" + precio + '}';
    }
}
