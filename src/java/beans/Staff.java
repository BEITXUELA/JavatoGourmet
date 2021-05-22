package beans;

import java.util.Date;

/**
 * La Gestion de Usuarios/Staff.
 *
 * Clase de getion de los usuarios de la gestion de la tienda/back-end. Esta
 * clase hereda de Usuario y añade sus propios metodos y atributos.
 *
 * @author Eduardo C. Araujo
 */
public class Staff extends Usuario {

    private String nif;
    private String nombre1;
    private String nombre2;
    private String apellido1;
    private String apellido2;
    private String telefono1;
    private String telefono2;
    private String email1;
    private String email2;
    private Date alta;
    private Date baja;
    private boolean isCerrado;
    private int rolID;

    /**
     * CONSTRUCTORES
     */
    /**
     * Constructor por defecto (sin parámetros). Crea el Staff con todos sus
     * atributos inicializados a los valores por defecto correspondientes a sus
     * tipos de datos.
     */
    public Staff() {

    }

    /**
     * Crea una instancia de la clase Staff con las informaciones dadas
     *
     * @param usuarioID De la clase padre 
     * @param usuario De la clase padre 
     * @param password De la clase padre 
     * @param estadoID De la clase padre 
     * @param nif Es un String del nif del cliente
     * @param nombre1 El 1er nombre del cliente
     * @param nombre2 El 2º nombre del cliente
     * @param apellido1 El 1er apellido del cliente
     * @param apellido2 El 2º apellido del cliente
     * @param telefono1 El 1er teléfono del cliente
     * @param telefono2 El 2º teléfono del cliente
     * @param email1 El 1er email del cliente
     * @param email2 El 2º email del cliente
     * @param alta La fecha de alta del cliente
     * @param baja La fecha de baja del cliente
     * @param isCerrado Si está cerrado
     * @param rolID Define el rolID que tiene el Staff User
     */
    public Staff(int usuarioID, String usuario, String password, int estadoID, String nif, String nombre1, String nombre2, String apellido1, String apellido2, String telefono1, String telefono2, String email1, String email2, Date alta, Date baja, boolean isCerrado, int rolID) {
        super(usuarioID, usuario, password, estadoID);
        this.nif = nif;
        this.nombre1 = nombre1;
        this.nombre2 = nombre2;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.telefono1 = telefono1;
        this.telefono2 = telefono2;
        this.email1 = email1;
        this.email2 = email2;
        this.alta = alta;
        this.baja = baja;
        this.isCerrado = isCerrado;
        this.rolID = rolID;
    }

    /**
     * METODOS SET Y GET
     *
     * Metodos especializados para la inserccion y extraccion de informacion
     */
    /**
     * @return nif NIF del Staff
     */
    public String getNif() {
        return nif;
    }

    /**
     * @param nif NIF del Staff
     */
    public void setNif(String nif) {
        this.nif = nif;
    }

    /**
     * @return nombre1 Primer nombre del Staff
     */
    public String getNombre1() {
        return nombre1;
    }

    /**
     * @param nombre1 Primer nombre del Staff
     */
    public void setNombre1(String nombre1) {
        this.nombre1 = nombre1;
    }

    /**
     * @return nombre2 Segundo nombre del Staff
     */
    public String getNombre2() {
        return nombre2;
    }

    /**
     * @param nombre2 Segundo nombre del Staff
     */
    public void setNombre2(String nombre2) {
        this.nombre2 = nombre2;
    }

    /**
     * @return apellido1 Primer apellido del Staff
     */
    public String getApellido1() {
        return apellido1;
    }

    /**
     * @param apellido1 Primer apellido del Staff
     */
    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    /**
     * @return apellido2 segundo apellido del Staff
     */
    public String getApellido2() {
        return apellido2;
    }

    /**
     * @param apellido2 segundo apellido del Staff
     */
    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    /**
     * @return telefono1 primer telefono del Staff
     */
    public String getTelefono1() {
        return telefono1;
    }

    /**
     * @param telefono1 primer telefono del Staff
     */
    public void setTelefono1(String telefono1) {
        this.telefono1 = telefono1;
    }

    /**
     * @return telefono2 segundo telefono del Staff
     */
    public String getTelefono2() {
        return telefono2;
    }

    /**
     * @param telefono2 segundo telefono del Staff
     */
    public void setTelefono2(String telefono2) {
        this.telefono2 = telefono2;
    }

    /**
     * @return email1 primer email del Staff
     */
    public String getEmail1() {
        return email1;
    }

    /**
     * @param email1 primer email del Staff
     */
    public void setEmail1(String email1) {
        this.email1 = email1;
    }

    /**
     * @return email2 segundo email del Staff
     */
    public String getEmail2() {
        return email2;
    }

    /**
     * @param email2 segundo email del Staff
     */
    public void setEmail2(String email2) {
        this.email2 = email2;
    }

    /**
     * @return alta la fecha del alta del Staff
     */
    public Date getAlta() {
        return alta;
    }

    /**
     * @param alta la fecha del alta del Staff
     */
    public void setAlta(Date alta) {
        this.alta = alta;
    }

    /**
     * @return baja la fecha de la baja del Staff
     */
    public Date getBaja() {
        return baja;
    }

    /**
     * @param baja la fecha de la baja del Staff
     */
    public void setBaja(Date baja) {
        this.baja = baja;
    }

    /**
     * @return isCerrado indica si tiene sesion cerrada o no
     */
    public boolean isIsCerrado() {
        return isCerrado;
    }

    /**
     * @param isCerrado indica si tiene sesion cerrada o no
     */
    public void setIsCerrado(boolean isCerrado) {
        this.isCerrado = isCerrado;
    }

    /**
     * @return rolID Indica el rolID del Staff
     */
    public int getRolID() {
        return rolID;
    }

    /**
     * @param rolID indica el ID del rol al que pertenece este Staff
     */
    public void setRolID(int rolID) {
        this.rolID = rolID;
    }

    /**
     * @return String Devuelve una cadena de texto con todos los valores de los
     * atributos del objeto
     */
    @Override
    public String toString() {
        return "Cliente{" + " nif=" + nif + ", nombre1=" + nombre1 + ", nombre2="
                + nombre2 + ", apellido1=" + apellido1 + ", apellido2="
                + apellido2 + ", telefono1=" + telefono1 + ", telefono2="
                + telefono2 + ", email1=" + email1 + ", email2=" + email2
                + ", alta=" + alta + ", baja=" + baja + ", isCerrado="
                + isCerrado + '}';
    }

}
