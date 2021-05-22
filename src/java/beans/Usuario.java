package beans;

/**
 * La Gestion de Usuarios/Clientes.
 *
 * Se trata de la clase Usuario. Para los usuarios de la web
 *
 * @author Bea Robledillo Gómez
 */
public class Usuario {

    //ATRIBUTOS//
    private int usuarioID;
    private String usuario;
    private String password;
    private int estadoID;

    // CONSTRUCTORES //
    /**
     * Constructor por defecto (sin parámetros). Crea el usuario con todos sus
     * atributos inicializados a los valores por defecto correspondientes a sus
     * tipos de datos.
     */
    public Usuario() {
    }

    /**
     * Crea un usuario con las informaciones dadas
     *
     * @param usuarioID Es un Identificador para el usuario
     * @param usuario Es un nombre para el usuario
     * @param password Es la contraseña del usuario
     * @param estadoID Es el estado del usuario
     */
    public Usuario(int usuarioID, String usuario, String password, int estadoID) {
        this.usuarioID = usuarioID;
        this.usuario = usuario;
        this.password = password;
        this.estadoID = estadoID;
    }

    // MÉTODOS GET/IS Y SET //
    /**
     * Obtiene el ID del Usuario
     *
     * @return ID
     *
     */
    public int getUsuarioID() {
        return usuarioID;
    }

    /**
     * Establece el ID del usuario
     *
     * @param usuarioID referencia del usuario
     *
     */
    public void setUsuarioID(int usuarioID) {
        this.usuarioID = usuarioID;
    }

    /**
     * Obtiene la referencia del Usuario
     *
     * @return usuario
     *
     */
    public String getUsuario() {
        return usuario;
    }

    /**
     * Establece la referencia del usuario
     *
     * @param usuario referencia del usuario
     *
     */
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    /**
     * Obtiene la referencia de la contraseña/password del usuario
     *
     * @return paswword
     */
    public String getPassword() {
        return password;
    }

    /**
     * Establece la referencia de la password del usuario
     *
     * @param password del usuario
     *
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Obtiene la referencia del estadoID del usuario
     *
     * @return estadoID
     *
     */
    public int getEstado() {
        return estadoID;
    }

    /**
     * Establece la referencia del estadoID del usuario
     *
     * @param estado del usuario
     */
    public void setEstado(int estado) {
        this.estadoID = estado;
    }

    /**
     * Redefinición del método heredado de la clase base directa Object.
     * Convierte este usuario en un String que contiene todas sus informaciones
     * internas en formato de texto, cumpliendo con todos los requisitos
     * descritos en la documentación del método toString() de la clase Object
     *
     * @return El String resultante de la conversión
     */
    @Override
    public String toString() {
        return "Usuario{" + "usuarioID=" + usuarioID + ", usuario=" + usuario + ", password=" + password + ", estado=" + estadoID + '}';
    }
}
