package beans;

/**
 * La Gestion de Almacen.
 *
 * Define <b>la o las</b> categorias a las que puede pertecer un producto. Como
 una categoria puede estar dentro de otra, exite un atributo "categoriaCategoriaID" que
 indica de que catogoria depende.
 *
 * @author Alonso Gil
 */
public class Categoria {

    //ATRIBUTOS
    private int categoriaID;

    private String nombre;

    private int categoriaCategoriaID;

    // CONSTRUCTORES //
    /**
     * Constructor por defecto (sin parámetros). Crea el cupon con todos sus
     * atributos inicializados a los valores por defecto correspondientes a sus
     * tipos de datos.
     */
    public Categoria() {
    }

    /**
     * Crea una categoria con las informaciones dadas
     *
     * @param categoriaID el ID de la categoria
     * @param nombre El nombre de la categoria
     * @param catPadre Las categorias padre, que a su vez se divide en
     * subcategorias
     */
    public Categoria(int categoriaID, String nombre, int catPadre) {
        this.categoriaID = categoriaID;
        this.nombre = nombre;
        this.categoriaCategoriaID = catPadre;
    }

    // MÉTODOS GET/IS Y SET //
    /**
     * Define el ID de la categoria
     *
     * @param categoriaID El identificador de la categoria especifica
     */
    public void setCategoriaID(int categoriaID) {
        this.categoriaID = categoriaID;
    }

    /**
     * Define el nombre de la categoria
     *
     * @param nombre el nombre de la categoria
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Define la categoria especifica
     *
     * @param categoriaCategoriaID Es el Identificador de la Categoria
     */
    public void setCategoriaCategoriaID(int categoriaCategoriaID) {
        this.categoriaCategoriaID = categoriaCategoriaID;
    }

    /**
     * Obtiene el ID de la categoria
     *
     * @return ID el nuevo identificador de la categoria especifica
     */
    public int getCategoriaID() {

        return categoriaID;
    }

    /**
     * Obtiene el nombre de la categoria
     *
     * @return nombre el nuevo nombre de la categoria
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Obtiene la categoria
     *
     * @return categoria la nueva categoria
     */
    public int getCategoriaCategoriaID() {
        return categoriaCategoriaID;
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
        
        return "Categoria{"
                + "id=" + categoriaID + ", "
                + "nombre=" + nombre + ", "
                + "catPadre=" + categoriaCategoriaID + '}';
    }

}
