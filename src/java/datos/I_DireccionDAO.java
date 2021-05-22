package datos;

import beans.Direccion;
import control.db.Database;
import java.util.List;

/**
 * Interfaz para que la implemente DireccionDAO
 *
 * @author Eduardo C. Araujo
 */
public interface I_DireccionDAO {

    public int alta(Direccion dir, Database con);

    public List<Direccion> lista(Database con);

    public int baja(int id, Database con);

    public int modificar(Direccion dir, Database con);

    public Direccion buscar(int id, Database con);
}
