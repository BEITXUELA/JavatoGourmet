package beans;

/**
 * La Gestion de Usuarios/Clientes.
 *
 * Se trata de la clase Direccion. Para las direcciones para mandar los pedidos,
 * a dicha dirección.
 *
 * @author Bea Robledillo Gómez
 */
public class Direccion {

    //ATRIBUTOS//
    private int direccionID;
    private int usuarioID;
    private int poblacionID;
    private String direccion;
    private int numero;
    private String bloque;
    private String escalera;
    private String piso;
    private String puerta;
    private int codPostal;
    private int tipoDireccion;

    // CONSTRUCTORES //
    /**
     * Constructor por defecto (sin parámetros). Crea la Direccion con todos sus
     * atributos inicializados a los valores por defecto correspondientes a sus
     * tipos de datos.
     */
    public Direccion() {

    }

    /**
     * Crea una direccion con las informaciones dadas
     *
     * @param direccionID Es el Identificador de la direccion 
     * @param direccion Es el nombre de la dirección
     * @param usuarioID Es el Identificador del Usuario
     * @param numero Es el número de la dirección
     * @param bloque Es el bloque de la dirección
     * @param escalera Es la escalera de la dirección
     * @param piso Es el piso de la dirección
     * @param puerta Es la puerta de la direccion
     * @param codPostal Es el número del código postal de la dirección
     * @param poblacionID Es el Identificador de la población a la que pertenece la dirección
     * @param tipoDireccion Es el tipo de dirección(facturación, envio) de la
     * dirección
     */
    public Direccion(int direccionID, int usuarioID, int poblacionID, String direccion, int numero, String bloque, String escalera, String piso, String puerta, int codPostal, int tipoDireccion) {
        this.direccionID = direccionID;
        this.usuarioID = usuarioID;
        this.poblacionID = poblacionID;
        this.direccion = direccion;
        this.numero = numero;
        this.bloque = bloque;
        this.escalera = escalera;
        this.piso = piso;
        this.puerta = puerta;
        this.codPostal = codPostal;
        this.tipoDireccion = tipoDireccion;
    }

    /**
     * Obtiene el ID del Tipo de pago
     *
     * @return el ID de este Tipo de pago
     */
    public int getDireccionID() {
        return direccionID;
    }

    /**
     * Establece la ID del Tipo de pago
     *
     * @param direccionID la nueva ID del Tipo de pago
     */
    public void setDireccionID(int direccionID) {
        this.direccionID = direccionID;
    }

    /**
     * Obtiene la direccion del Tipo de pago
     *
     * @return la direccion de este Tipo de pago
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * Establece la direccion del Tipo de pago
     *
     * @param direccion la nueva direccion del Tipo de pago
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     * Obtiene el numero del Tipo de pago
     *
     * @return el numero de este Tipo de pago
     */
    public int getNumero() {
        return numero;
    }

    /**
     * Establece el numero del Tipo de pago
     *
     * @param numero el nuevo numero del Tipo de pago
     */
    public void setNumero(int numero) {
        this.numero = numero;
    }

    /**
     * Obtiene el bloque del Tipo de pago
     *
     * @return el bloque de este Tipo de pago
     */
    public String getBloque() {
        return bloque;
    }

    /**
     * Establece el bloque del Tipo de pago
     *
     * @param bloque el nuevo bloque del Tipo de pago
     */
    public void setBloque(String bloque) {
        this.bloque = bloque;
    }

    /**
     * Obtiene la escalera del Tipo de pago
     *
     * @return la escalera de este Tipo de pago
     */
    public String getEscalera() {
        return escalera;
    }

    /**
     * Establece la escalera del Tipo de pago
     *
     * @param escalera la nueva escalera del Tipo de pago
     */
    public void setEscalera(String escalera) {
        this.escalera = escalera;
    }

    /**
     * Obtiene el piso del Tipo de pago
     *
     * @return el piso de este Tipo de pago
     */
    public String getPiso() {
        return piso;
    }

    /**
     * Establece el piso del Tipo de pago
     *
     * @param piso el nuevo piso del Tipo de pago
     */
    public void setPiso(String piso) {
        this.piso = piso;
    }

    /**
     * Obtiene la puerta del Tipo de pago
     *
     * @return la puerta de este Tipo de pago
     */
    public String getPuerta() {
        return puerta;
    }

    /**
     * Establece la puerta del Tipo de pago
     *
     * @param puerta la nueva puerta del Tipo de pago
     */
    public void setPuerta(String puerta) {
        this.puerta = puerta;
    }

    /**
     * Obtiene el codPostal del Tipo de pago
     *
     * @return el codPostal de este Tipo de pago
     */
    public int getCodPostal() {
        return codPostal;
    }

    /**
     * Establece el codPostal del Tipo de pago
     *
     * @param codPostal el nuevo codPostal del Tipo de pago
     */
    public void setCodPostal(int codPostal) {
        this.codPostal = codPostal;
    }

    /**
     * Obtiene el tipoDireccion del Tipo de pago
     *
     * @return el tipoDireccion de este Tipo de pago
     */
    public int getTipoDireccion() {
        return tipoDireccion;
    }

    /**
     * Establece el tipoDireccion del Tipo de pago
     *
     * @param tipoDireccion el nuevo tipoDireccion del Tipo de pago
     */
    public void setTipoDireccion(int tipoDireccion) {
        this.tipoDireccion = tipoDireccion;
    }

    /**
     * Obtiene el ID del usuario al que pertence esta direccion
     *
     * @return int El usuarioID
     */
    public int getUsuarioID() {
        return usuarioID;
    }

    /**
     * Establece el ID de usuario al que pertence este ID
     *
     * @param usuarioID El codigo del usuario
     */
    public void setUsuarioID(int usuarioID) {
        this.usuarioID = usuarioID;
    }

    /**
     * Obtiene el ID de la poblacion a la que pertence esta direccion
     *
     * @return int El ID de poblacion
     */
    public int getPoblacionID() {
        return poblacionID;
    }

    /**
     * Establece el ID de la poblacion a la que pertence esta direccion
     *
     * @param poblacionID El ID de la poblacion
     */
    public void setPoblacionID(int poblacionID) {
        this.poblacionID = poblacionID;
    }

    /**
     * Redefinición del método heredado de la clase base directa Object.
     * Convierte esta direccion en un String que contiene todas sus
     * informaciones internas en formato de texto, cumpliendo con todos los
     * requisitos descritos en la documentación del método toString() de la
     * clase Object
     *
     * @return El String resultante de la conversión
     */
    @Override
    public String toString() {
        return "Direccion{" + "direccionID=" + direccionID + ", usuarioID=" + usuarioID + ", poblacionID=" + poblacionID + ", direccion=" + direccion + ", numero=" + numero + ", bloque=" + bloque + ", escalera=" + escalera + ", piso=" + piso + ", puerta=" + puerta + ", codPostal=" + codPostal + ", tipoDireccion=" + tipoDireccion + '}';
    }
}
