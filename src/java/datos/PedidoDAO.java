
package datos;

import beans.Pedido;
import control.db.Database;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Marino Calero
 */

  /** ATRIBUTOS 
    private int pedidoID;    
    private int referencia;    
    private Date fecha;    
    private double total;    
    private int direccionEnvio;    
    private int direccionFacturacion;    
    private int estado;    
    private int tiipoPago;    
    private int cupon;
*/

/** Faltan comentarios */

public class PedidoDAO implements I_PedidoDAO{
    /**
     * Encargado de solicitar un alta de un objeto direccion en la BBDD     *
     * @param pe Objeto Direccion que queremos dar de alta en la BBDD
     * @param con Conexion a la BBDD
     * @return int Devuelve 1 si la inserccion es correcta
     */
    @Override
    public int alta(Pedido pe, Database con) {   
        int res = 0;
        /*
         * CONSULTA DE INSERCCION
         */
        String query = "INSERT INTO pedido "
                + "(pedidoID,"
                + " direccionFacID,"
                + " direccionEnvID,"
                + " tiipoPagoID(),"
                + " cuponID(),"
                + " estadoPedidoID"
                + " referencia,"
                + " fecha,"
                + " total,"              
                + " cupon)"               
                + " VALUES"
                + " ('"
                + pe.getDireccionFacID()+ "', '"
                + pe.getDireccionEnvID()+ "', '"
                + pe.getTiipoPagoID()+ "', '"
                + pe.getCuponID()+ "', '"
                + pe.getEstadoPedidoID()+ "', '"
                + ");";
        
        try {
            /*
             * Para poder pasar un valor NULL a una columna de BBDD que lo
             * permita hay que hacer un PreparedStatement y usar setNull
             * donde corresponda.
             */
            PreparedStatement st = con.getConnection().prepareStatement(query);                    
            
            //Lanzo la consulta de inserccion
            res = con.updateSQL(st);
            return res;
        } catch (SQLException E) {
            System.out.println("Excepcion SQL....: " + E.getMessage());
            System.out.println("Estado SQL.......: " + E.getSQLState());
            System.out.println("Código del Error.: " + E.getErrorCode());
            return res;
        }
    }
    @Override
    public List<Pedido> lista(Database con) {
        ResultSet rs = null;
        ArrayList<Pedido> list = new ArrayList<>();
        
         /*
         * CONSULTA DE DIRECCIONES EXISTENTES
         */
        String query
                = "SELECT pedidoID,"
                + " direccionFacID,"
                + " direccionEnvID,"
                + " tiipoPagoID,"
                + " cuponID,"
                + " estadoPedidoID,"
                + " referencia,"
                + " fecha,"
                + " total,"
                + " cupon"
                + " FROM pedido";              

        try {
            /*
             *Lanzo la consulta y el resultado lo guardo en un nuevo objeto 
             *Direccion y lo meto en la Lista que devolveremos
             */
            rs = con.consultaSQL(query);
            while (rs.next()) {
                list.add(new Pedido(         
                        rs.getInt("pedidoID"),
                        rs.getInt("direccionFacID"),
                        rs.getInt("direccionEnvID"),
                        rs.getInt("tiipoPagoID"),
                        rs.getInt("cuponID"),
                        rs.getInt("estadoPedidoID"),
                        rs.getInt("referencia"),
                        rs.getDate("fecha"),
                        rs.getDouble("total"),
                        rs.getString("cupon")));
            }
        } catch (SQLException E) {
            System.out.println("Excepcion SQL....: " + E.getMessage());
            System.out.println("Estado SQL.......: " + E.getSQLState());
            System.out.println("Código del Error.: " + E.getErrorCode());
        }

        //Si no ha habido problemas devolvemos la Lista de la consulta
        return list;        
        
    }

    @Override
    public int baja(int id, Database con) {
       int res = 0;

        /*
         * CONSULTA DE BORRADO
         */
        String query = "DELETE FROM pedido WHERE pedidoID ='"
                + id + "';";

        try {
            //Lanzo la consulta de borrado
            res = con.updateSQL(query);
            return res;
        } catch (SQLException E) {
            System.out.println("Excepcion SQL....: " + E.getMessage());
            System.out.println("Estado SQL.......: " + E.getSQLState());
            System.out.println("Código del Error.: " + E.getErrorCode());
            return res;
        }
     
    }

    @Override
    public int modificar(Pedido pe, Database con) {
       int res = 0;
        /*
         * CONSULTA DE ACTUALIZACION
         */
        String query = "UPDATE pedido "
                + "SET direccionFacID='" + pe.getDireccionFacID()+ "',"
                + " direccionEnvID='" + pe.getDireccionEnvID() + "',"
                + " tiipoPagoID='" + pe.getTiipoPagoID() + "',"
                + " cuponID='" + pe.getCuponID() + "',"
                + " estadoPedidoID='" + pe.getEstadoPedidoID() + "',"
                + " referencia=?,"
                + " fecha=?,"
                + " total=?,"
                + " cupon=?,"             
                + " WHERE pedidoID = '" + pe.getPedidoID() + "';";
        
        
        try {
            /*
             * Para poder pasar un valor NULL a una columna de BBDD que lo
             * permita hay que hacer un PreparedStatement y usar setNull
             * donde corresponda.
             */
            PreparedStatement st = con.getConnection().prepareStatement(query);
                       //Lanzo la consulta de inserccion
            res = con.updateSQL(st);
            return res;
        } catch (SQLException E) {
            System.out.println("Excepcion SQL....: " + E.getMessage());
            System.out.println("Estado SQL.......: " + E.getSQLState());
            System.out.println("Código del Error.: " + E.getErrorCode());
            return res;
        }
    }

    @Override
    public Pedido buscar(int id, Database con) {
        ResultSet rs = null;
        Pedido pe = new Pedido();

        /*
         * CONSULTA
         */
        String query
                = "SELECT pedidoID,"
                + " direccionFacID,"
                + " direccionEnvID,"
                + " tiipoPagoID,"
                + " cuponID,"
                + " estadoPedidoID,"
                + " referencia,"
                + " fecha,"
                + " total,"
                + " cupon,"
                + " FROM pedido"
                + " WHERE pedidoID = '" + id + "';";

             
        try {
            /*
             *Lanzo la consulta y el resultado lo guardo en un nuevo objeto 
             *Direccion y lo devolvemos
             */
            rs = con.consultaSQL(query);
            while (rs.next()) {
                pe.setPedidoID(rs.getInt("pedidoID"));
                pe.setDireccionFacID(rs.getInt("dierccionFacID"));
                pe.setDireccionEnvID(rs.getInt("direccionEnvID"));
                pe.setTipoPagoID(rs.getInt("tiipoPagoID"));
                pe.setCuponID(rs.getInt("cuponID"));
                pe.setEstadoPedidoID(rs.getInt("estadoPedidoID"));
                pe.setReferencia(rs.getInt("referencia"));
                pe.setFecha(rs.getDate("fecha"));
                pe.setTotal(rs.getDouble("total"));
                pe.setCupon(rs.getString("cupon"));
            }
        } catch (SQLException E) {
            System.out.println("Excepcion SQL....: " + E.getMessage());
            System.out.println("Estado SQL.......: " + E.getSQLState());
            System.out.println("Código del Error.: " + E.getErrorCode());
        }

        return pe;
    }
 
    
}
