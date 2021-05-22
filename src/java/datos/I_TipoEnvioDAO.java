
package datos;

import beans.TipoEnvio;
import control.db.Database;
import java.util.List;

/**
 * Interfaz para que la implemente TipoEnvioDAO
 *
 * @author Alonso Gil López
 */
public interface I_TipoEnvioDAO {
    
    public int alta(TipoEnvio env, Database con);
    
    public List<TipoEnvio> lista(Database con);
    
    public int baja(int id, Database con);
    
    public int modificar(TipoEnvio env, Database con);
    
    public TipoEnvio buscar(int id, Database con);
}
