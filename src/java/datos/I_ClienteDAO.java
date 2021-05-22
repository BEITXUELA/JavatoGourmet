package datos;

import beans.Cliente;
import control.db.Database;
import java.util.List;

/**
 * Interfaz para que la implemente ClienteDAO
 *
 * @author Eduardo C. Araujo
 */
public interface I_ClienteDAO {

    public int alta(Cliente cli, Database con);

    public List<Cliente> lista(Database con);

    public int baja(int id, Database con);

    public int modificar(Cliente cli, Database con);

    public Cliente buscar(int id, Database con);
}
