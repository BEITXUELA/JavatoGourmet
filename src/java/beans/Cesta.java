package beans;

import java.util.Date;

/**
 * <strong>Parte de la gestión de ventas</strong>
 *
 * Se trata de la clase Cesta que muestra el precio y la disponibilidad de todos
 * los productos añadidos.
 *
 * <strong>2015/07/17 - Eduardo:</strong> Se ha añadido el atributo usuarioID,
 * que es el ID del usuario al que se asigna la cesta
 *
 * @author Marino Calero
 */
public class Cesta {

    // ATRIBUTOS //
    private int cestaID;
    private int usuarioID;
    private Date fecha;
    private double total;

    // CONSTRUCTORES //
    /**
     * Constructor por defecto (sin parámetros). Crea la cesta con todos sus
     * atributos inicializados a los valores por defecto correspondientes a sus
     * tipos de datos.
     */
    public Cesta() {
    }

    /**
     * Crea una cesta con las informaciones dadas
     *
     * @param cestaID la clave principal e la cesta
     * @param usuarioID El ID de usuario al que se asocia la cesta
     * @param fecha La fecha de la cesta que se enviara
     * @param total La cantidad total de productos que se requiere
     *
     */
    public Cesta(int cestaID, int usuarioID, Date fecha, double total) {
        this.cestaID = cestaID;
        this.usuarioID = usuarioID;
        this.fecha = fecha;
        this.total = total;
    }

    // MÉTODOS GET/IS Y SET //
    /**
     * Obtiene el ID de la cesta
     *
     * @return el ID de esta cesta
     */
    public int getCestaID() {
        return cestaID;
    }

    /**
     * Obtiene la fecha de la cesta
     *
     * @return la fecha de esta cesta
     */
    public Date getFecha() {
        return fecha;
    }

    /**
     * Obtiene el total de la cesta
     *
     * @return el total de esta cesta
     */
    public double getTotal() {
        return total;
    }

    /**
     * Establece la fecha de la cesta
     *
     * @param cestaID El nuevo ID de la cesta
     */
    public void setCestaID(int cestaID) {
        this.cestaID = cestaID;
    }

    /**
     * Establece la fecha de la cesta
     *
     * @param fecha La nueva fecha de la cesta
     */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    /**
     * Establece el total de la cesta
     *
     * @param total El nuevo total de la cesta
     */
    public void setTotal(double total) {
        this.total = total;
    }

    /**
     * Obtiene el ID de usuario al que se asocia la cesta
     *
     * @return int El ID del usuario
     */
    public int getUsuarioID() {
        return usuarioID;
    }

    /**
     * Establece el ID del usuario al que se asocia la cesta
     *
     * @param usuarioID El ID del usuario
     */
    public void setUsuarioID(int usuarioID) {
        this.usuarioID = usuarioID;
    }

    /**
     * Redefinición del método heredado de la clase base directa Object.
     * Convierte esta cesta en un String que contiene todas sus informaciones
     * internas en formato de texto.
     *
     * @return El String resultante de la conversión
     */
    @Override
    public String toString() {
        return "Cesta{" + "cestaID=" + cestaID + ", usuarioID=" + usuarioID + ", fecha=" + fecha + ", total=" + total + '}';
    }
}
