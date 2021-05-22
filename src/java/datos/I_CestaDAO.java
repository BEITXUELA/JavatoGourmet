package datos;

import beans.Cesta;
import control.db.Database;
import java.util.List;

/**
 * Interfaz para que la implemente CestaDAO
 *
 * @author Eduardo C. Araujo
 */
public interface I_CestaDAO {

    public int alta(Cesta ce, Database con);

    public List<Cesta> lista(Database con);

    public int baja(int id, Database con);

    public int modificar(Cesta ce, Database con);

    public Cesta buscar(int id, Database con);
}
