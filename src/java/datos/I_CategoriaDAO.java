package datos;

import beans.Categoria;
import control.db.Database;
import java.util.List;

/**
 * Interfaz para que la implemente CategoriaDAO
 *
 * @author Eduardo C. Araujo
 */
public interface I_CategoriaDAO {

    public int alta(Categoria cat, Database con);

    public List<Categoria> lista(Database con);

    public int baja(int id, Database con);

    public int modificar(Categoria cat, Database con);

    public Categoria buscar(int id, Database con);
}
