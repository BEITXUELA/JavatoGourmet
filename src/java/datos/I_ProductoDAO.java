package datos;

import beans.Producto;
import control.db.Database;
import java.util.List;

/**
 *
 * @author Alonso Gil López
 */
public interface I_ProductoDAO {

    public int alta(Producto prod, Database con);

    public List<Producto> lista(Database con);

    public int baja(int id, Database con);

    public int modificar(Producto prod, Database con);

    public Producto buscar(int id, Database con);
}

