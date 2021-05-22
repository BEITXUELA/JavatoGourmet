
package datos;

import beans.TipoPago;
import control.db.Database;
import java.util.List;

/**
 * Interfaz para que la implemente TipoPagoDAO
 *
 * @author Alonso Gil López
 */
public interface I_TipoPagoDAO {
    
    public int alta (TipoPago tp, Database con);
    
    public List<TipoPago> lista(Database con);
    
    public int baja(int id, Database con);
    
    public int modificar(TipoPago tp, Database con);
    
    public TipoPago buscar(int id, Database con);
    
    
    
}
