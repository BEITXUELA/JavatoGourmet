package datos;

import beans.Categoria;
import beans.Foto;
import control.db.Database;
import java.util.List;

/**
 * Interfaz para que la implemente FotoDAO
 *
 * @author Alonso Gil López
 */
public interface I_FotoDAO {

    public int alta(Foto fo, Database con);

    public List<Foto> lista(Database con);

    public int baja(int id, Database con);

    public int modificar(Foto fo, Database con);

    public Foto buscar(int id, Database con);
}
