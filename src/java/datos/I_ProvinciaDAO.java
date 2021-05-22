package datos;

import beans.Provincia;
import control.db.Database;
import java.util.List;

/**
 * Interfaz para que la implemente ProvinciaDAO
 *
 * @author Bea Robledillo Gómez
 */
public interface I_ProvinciaDAO {

    public int alta(Provincia provincia, Database con);

    public List<Provincia> lista(Database con);

    public int baja(int id, Database con);

    public int modificar(Provincia provincia, Database con);

    public Provincia buscar(int id, Database con);

}
