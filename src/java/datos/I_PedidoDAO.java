/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

import beans.Pedido;
import control.db.Database;
import java.util.List;

/**
 *
 * @author Marino Calero
 */

/** Faltan comentarios */

public interface I_PedidoDAO {
    
    public int alta(Pedido pe, Database con);

    public List<Pedido> lista(Database con);

    public int baja(int id, Database con);

    public int modificar(Pedido pe, Database con);

    public Pedido buscar(int id, Database con);
    
    
    
}
