package datos;

import beans.Poblacion;
import control.db.Database;
import java.util.List;

/**
 * Interfaz para que la implemente PoblacionDAO
 *
 * @author Bea Robledillo Gómez
 */
public interface I_PoblacionDAO {

    public int alta(Poblacion poblacion, Database con);

    public List<Poblacion> lista(Database con);

    public int baja(int id, Database con);

    public int modificar(Poblacion poblacion, Database con);

    public Poblacion buscar(int id, Database con);

}
