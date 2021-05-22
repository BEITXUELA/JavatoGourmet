/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;


import beans.IVA;
import control.db.Database;
import java.util.List;

/**
 * Interfaz para que la implemente IvaDAO
 *
 * @author Marino Calero
 */

public interface I_IvaDAO {
    
    public int alta(IVA iv, Database con);

    public List<IVA> lista(Database con);

    public int baja(int id, Database con);

    public int modificar(IVA iv, Database con);

    public IVA buscar(int id, Database con);
}
    
    
    
