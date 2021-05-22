package datos;

import beans.Pais;
import control.db.Database;
import java.util.List;

/**
 * Interfaz para que la implemente PaisDAO
 *
 * @author Bea Robledillo Gómez
 */
public interface I_PaisDAO {

    public int alta(Pais pais, Database con);

    public List<Pais> lista(Database con);

    public int baja(int id, Database con);

    public int modificar(Pais pais, Database con);

    public Pais buscar(int id, Database con);

}
