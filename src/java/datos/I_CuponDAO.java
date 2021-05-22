package datos;

import beans.Cupon;
import control.db.Database;
import java.util.List;

/**
 * Interfaz para que la implemente CuponDAO
 *
 * @author Bea Robledillo Gómez
 */
public interface I_CuponDAO {

    public int alta(Cupon cupon, Database con);

    public List<Cupon> lista(Database con);

    public int baja(int id, Database con);

    public int modificar(Cupon cupon, Database con);

    public Cupon buscar(int id, Database con);

}
