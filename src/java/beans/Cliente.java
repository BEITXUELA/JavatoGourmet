package beans;

import java.util.Date;

/**
 * La Gestion de Usuarios/Clientes.
 *
 * Clase encargada de la gestion ultima de Usuarios tipo Cliente, por lo tanto
 * esta clase hereda de Usuario.
 *
 * @author Bea Robledillo Gómez
 */
public class Cliente extends Usuario {

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

    /**
     * CONSTRUCTORES
     */
    /**
     * Constructor por defecto (sin parámetros). Crea el Cliente con todos sus
     * atributos inicializados a los valores por defecto correspondientes a sus
     * tipos de datos.
     */
    public Cliente() {

    }

    /**
     * Crea un Cliente con las informaciones dadas
     * 
     * @param usuarioID De la clase padre Usuario
     * @param usuario De la clase padre Usuario
     * @param password De la clase padre Usuario
     * @param estadoID De la clase padre Usuario
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
     */
    public Cliente(int usuarioID, String usuario, String password, int estadoID, String nif, String nombre1, String nombre2, String apellido1, String apellido2, String telefono1, String telefono2, String email1, String email2, Date alta, Date baja, boolean isCerrado) {
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
    }

   

    /**
     * Metodos SET Y GET
     */
    /**
     *
     * Nif del cliente
     *
     * @return nif
     */
    public String getNif() {
        return nif;
    }

    /**
     * Nif del cliente
     *
     * @param nif pasamos el parametro Nif del cliente
     */
    public void setNif(String nif) {
        this.nif = nif;
    }

    /**
     * 1er nombre del cliente
     *
     * @return nombre1
     */
    public String getNombre1() {
        return nombre1;
    }

    /**
     * 1er nombre del cliente
     *
     * @param nombre1 pasamos el parametro del 1er nombre del cliente
     */
    public void setNombre1(String nombre1) {
        this.nombre1 = nombre1;
    }

    /**
     * 2º nombre del cliente
     *
     * @return nombre2
     */
    public String getNombre2() {
        return nombre2;
    }

    /**
     * 2º nombre del cliente
     *
     * @param nombre2 pasamos el parametro del 2º nombre del cliente
     */
    public void setNombre2(String nombre2) {
        this.nombre2 = nombre2;
    }

    /**
     * 1er apellido del cliente
     *
     * @return apellido1
     */
    public String getApellido1() {
        return apellido1;
    }

    /**
     * 1er apellido del cliente
     *
     * @param apellido1 pasamos el parametro del 1er apellido del cliente
     */
    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    /**
     * 2º apellido del cliente
     *
     * @return apellido2
     */
    public String getApellido2() {
        return apellido2;
    }

    /**
     * 2º apellido del cliente
     *
     * @param apellido2 pasamos el parametro del 2º apellido del cliente
     */
    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    /**
     * 1er teléfono del cliente
     *
     * @return telefono1
     */
    public String getTelefono1() {
        return telefono1;
    }

    /**
     * 1er teléfono del cliente
     *
     * @param telefono1 pasamos el parametro del 1er teléfono del cliente
     */
    public void setTelefono1(String telefono1) {
        this.telefono1 = telefono1;
    }

    /**
     * 2º teléfono del cliente
     *
     * @return telefono2
     */
    public String getTelefono2() {
        return telefono2;
    }

    /**
     * 2º teléfono del cliente
     *
     * @param telefono2 pasamos el parametro del 2º teléfono del cliente
     */
    public void setTelefono2(String telefono2) {
        this.telefono2 = telefono2;
    }

    /**
     * 1er email del cliente
     *
     * @return email1
     */
    public String getEmail1() {
        return email1;
    }

    /**
     * 1er email del cliente
     *
     * @param email1 pasamos el parametro del 1er email del cliente
     */
    public void setEmail1(String email1) {
        this.email1 = email1;
    }

    /**
     * 2º teléfono del cliente
     *
     * @return email2
     */
    public String getEmail2() {
        return email2;
    }

    /**
     * 2º teléfono del cliente
     *
     * @param email2 pasamos el parametro del 2º email del cliente
     */
    public void setEmail2(String email2) {
        this.email2 = email2;
    }

    /**
     * la fecha del alta del cliente
     *
     * @return alta
     */
    public Date getAlta() {
        return alta;
    }

    /**
     * la fecha del alta del cliente
     *
     * @param alta pasamos el parametro de la fecha del alta del cliente
     */
    public void setAlta(Date alta) {
        this.alta = alta;
    }

    /**
     * la fecha de la baja del cliente
     *
     * @return baja
     */
    public Date getBaja() {
        return baja;
    }

    /**
     * la fecha de la baja del cliente
     *
     * @param baja pasamos el parametro de la fecha de baja del cliente
     */
    public void setBaja(Date baja) {
        this.baja = baja;
    }

    /**
     * si está cerrado o no
     *
     * @return isCerrado
     */
    public boolean isIsCerrado() {
        return isCerrado;
    }

    /**
     * si está cerrado o no
     *
     * @param isCerrado pasamos el booleano de si es cerrado o no
     */
    public void setIsCerrado(boolean isCerrado) {
        this.isCerrado = isCerrado;
    }

    /**
     * METODO TOSTRING que deevuelve una cadena con los valore de todos los
     * atributos de la clase.
     *
     * @return String
     */
    @Override
    public String toString() {
        return "Cliente{" + "nif=" + nif + ", nombre1=" + nombre1
                + ", nombre2=" + nombre2 + ", apellido1=" + apellido1
                + ", apellido2=" + apellido2 + ", telefono1=" + telefono1
                + ", telefono2=" + telefono2 + ", email1=" + email1
                + ", email2=" + email2 + ", alta=" + alta + ", baja=" + baja
                + ", isCerrado=" + isCerrado + '}';
    }

}
