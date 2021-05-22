package datos;

import beans.Usuario;
import control.db.Database;
import java.util.List;

/**
 * Interfaz para que la implemente UsuarioDAO
 *
 * @author Eduardo C. Araujo
 */
public interface I_UsuarioDAO {

    public int alta(Usuario usu, Database con);

    public List<Usuario> lista(Database con);

    public int baja(int id, Database con);

    public int modificar(Usuario usu, Database con);

    public Usuario buscar(int id, Database con);
}
