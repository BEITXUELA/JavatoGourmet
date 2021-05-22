package datos;

import beans.Staff;
import control.db.Database;
import java.util.List;

/**
 * Interfaz para que la implemente StaffDAO
 *
 * @author Eduardo C. Araujo
 */
public interface I_StaffDAO {

    public int alta(Staff sta, Database con);

    public List<Staff> lista(Database con);

    public int baja(int id, Database con);

    public int modificar(Staff sta, Database con);

    public Staff buscar(int id, Database con);
}
