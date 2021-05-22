package datos;

import beans.Item;
import control.db.Database;
import java.util.List;

/**
 * Interfaz para que la implemente ItemDAO
 *
 * @author Eduardo C. Araujo
 */
public interface I_ItemDAO {

    public int alta(Item it, Database con);

    public List<Item> lista(Database con);

    public int baja(int id, Database con);

    public int modificar(Item it, Database con);

    public Item buscar(int id, Database con);
}
