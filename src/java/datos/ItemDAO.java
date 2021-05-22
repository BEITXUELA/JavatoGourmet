package datos;

import beans.Item;
import beans.Producto;
import control.db.Database;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

/**
 * CAMPOS DEL POJO ITEM (para ayudarme en los SQLs)
 * ------------------------------------------------- 
 * private int itemID; 
 * private int pedidoID; 
 * private int cestaID; 
 * private int productoID; 
 * private int cantidad; 
 * private double precio;
 * -------------------------------------------------
 */
/**
 * Clase encargada de ejecutar consultas a la BBDD sobre la tabla de Cesta
 *
 * @author Eduardo C. Araujo
 */
public class ItemDAO implements I_ItemDAO {

    /**
     * Encargado de solicitar un alta de un objeto item en la BBDD
     *
     * @param it Objeto Item que queremos dar de alta en la BBDD
     * @param con Conexion a la BBDD
     * @return int Devuelve 1 si la inserccion es correcta
     */
    @Override
    public int alta(Item it, Database con) {
        int res = 0;

        /*
         * CONSULTA DE INSERCCION
         */
        String query = "INSERT INTO item "
                + "(pedidoID,"
                + " cestaID,"
                + " productoID,"
                + " cantidad,"
                + " precio)"
                + " VALUES"
                + " ("
                + "?, '"
                + it.getCestaID() + "', '"
                + it.getProductoID() + "', "
                + "?, "
                + "?"
                + ");";

        try {
            /*
             * Para poder pasar un valor NULL a una columna de BBDD que lo
             * permita hay que hacer un PreparedStatement y usar setNull
             * donde corresponda.
             */
            PreparedStatement st = con.getConnection().prepareStatement(query);
            if (it.getPedidoID() != 0) {
                st.setInt(1, it.getPedidoID());
            } else {
                st.setNull(1, Types.INTEGER);
            }
            if (it.getCantidad() != 0) {
                st.setInt(2, it.getCantidad());
            } else {
                st.setNull(2, Types.INTEGER);
            }
            if (it.getPrecio() != 0) {
                st.setDouble(3, it.getPrecio());
            } else {
                st.setNull(3, Types.DOUBLE);
            }

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

    /**
     * Encargado de devolver una coleccion de items existentes
     *
     * @param con Conexion con la que hare la consulta
     * @return List Obtencion de items
     */
    @Override
    public List<Item> lista(Database con) {
        ResultSet rs = null;
        ArrayList<Item> list = new ArrayList<>();

        /*
         * CONSULTA DE ITEMS EXISTENTES
         */
        String query = "SELECT "
                + " itemID,"
                + " pedidoID,"
                + " cestaID,"
                + " productoID,"
                + " cantidad,"
                + " precio"
                + " FROM item;";

        try {
            /*
             *Lanzo la consulta y el resultado lo guardo en un nuevo objeto 
             *item y lo meto en la Lista que devolveremos
             */
            rs = con.consultaSQL(query);
            while (rs.next()) {
                list.add(new Item(
                        rs.getInt("itemID"),
                        rs.getInt("pedidoID"),
                        rs.getInt("cestaID"),
                        rs.getInt("productoID"),
                        rs.getInt("cantidad"),
                        rs.getDouble("precio")
                ));
            }
        } catch (SQLException E) {
            System.out.println("Excepcion SQL....: " + E.getMessage());
            System.out.println("Estado SQL.......: " + E.getSQLState());
            System.out.println("Código del Error.: " + E.getErrorCode());
        }

        //Si no ha habido problemas devolvemos la Lista de la consulta
        return list;
    }

    /**
     * Encargado de eliminar un item mediante su ID
     *
     * @param id El item que daremos de baja en la BBDD
     * @param con Conexion a la BBDD
     * @return Devuelve 1 si la inserccion es correcta
     */
    @Override
    public int baja(int id, Database con) {
        int res = 0;

        /*
         * CONSULTA DE BORRADO
         */
        String query = "DELETE FROM item "
                + "WHERE itemID ='" + id + "';";

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

    /**
     * Encargado de modificar una item mediante un objeto pasado
     *
     * @param it El item que modificaremos en la BBDD
     * @param con Conexion a la BBDD
     * @return Devuelve 1 si la inserccion es correcta
     */
    @Override
    public int modificar(Item it, Database con) {
        int res = 0;

        /*
         * CONSULTA DE ACTUALIZACION
         */
        String query = "UPDATE item "
                + "SET "
                + " pedidoID = ?,"
                + " cestaID = " + it.getCestaID() + ","
                + " productoID = " + it.getProductoID() + ","
                + " cantidad = ?,"
                + " precio = ?"
                + " WHERE cestaID = '" + it.getItemID() + "';";

        try {
            /*
             * Para poder pasar un valor NULL a una columna de BBDD que lo
             * permita hay que hacer un PreparedStatement y usar setNull
             * donde corresponda.
             */
            PreparedStatement st = con.getConnection().prepareStatement(query);
            if (it.getPedidoID() != 0) {
                st.setInt(1, it.getPedidoID());
            } else {
                st.setNull(1, Types.INTEGER);
            }
            if (it.getCantidad() != 0) {
                st.setInt(2, it.getCantidad());
            } else {
                st.setNull(2, Types.INTEGER);
            }
            if (it.getPrecio() != 0) {
                st.setDouble(3, it.getPrecio());
            } else {
                st.setNull(3, Types.DOUBLE);
            }

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

    /**
     * Encargado de buscar un item mediante su ID, devolvemos el item encontrada
     *
     * @param id El item que buscaremos en la BBDD
     * @param con Conexion a la BBDD
     * @return Devolvemos el item encotrado
     */
    @Override
    public Item buscar(int id, Database con) {
        ResultSet rs = null;
        Item it = new Item();

        /*
         * CONSULTA
         */
        String query = "SELECT "
                + " itemID,"
                + " pedidoID,"
                + " cestaID,"
                + " productoID,"
                + " cantidad,"
                + " precio"
                + " FROM item"
                + " WHERE itemID = '" + id + "';";

        try {
            /*
             *Lanzo la consulta y el resultado lo guardo en un nuevo objeto 
             *Item y lo devolvemos
             */
            rs = con.consultaSQL(query);
            while (rs.next()) {
                it.setItemID(rs.getInt("itemID"));
                it.setPedidoID(rs.getInt("pedidoID"));
                it.setCestaID(rs.getInt("cestaID"));
                it.setProductoID(rs.getInt("productoID"));
                it.setCantidad(rs.getInt("cantidad"));
                it.setPrecio(rs.getDouble("precio"));
            }
        } catch (SQLException E) {
            System.out.println("Excepcion SQL....: " + E.getMessage());
            System.out.println("Estado SQL.......: " + E.getSQLState());
            System.out.println("Código del Error.: " + E.getErrorCode());
        }

        return it;
    }
    
    public List<Item> listaPorCesta(int cestaID, Database con) {
        ResultSet rs = null;
        ArrayList<Item> list = new ArrayList<>();

        /*
         * CONSULTA DE ITEMS EXISTENTES
         */
        String query = "SELECT "
                + " i.itemID,"
                + " i.pedidoID,"
                + " i.cestaID,"
                + " i.productoID,"
                + " i.cantidad,"
                + " i.precio,"
                + " p.nombre"
                + " FROM item i, producto p"
                + " WHERE i.productoID = p.productoID AND i.cestaID = '" + cestaID + "';";

        try {
            /*
             *Lanzo la consulta y el resultado lo guardo en un nuevo objeto 
             *item y lo meto en la Lista que devolveremos
             */
            rs = con.consultaSQL(query);
            while (rs.next()) {
                list.add(new Item(
                        rs.getInt("itemID"),
                        rs.getInt("pedidoID"),
                        rs.getInt("cestaID"),
                        rs.getInt("productoID"),
                        rs.getInt("cantidad"),
                        rs.getDouble("precio"),
                        rs.getString("nombre")
                ));
            }
        } catch (SQLException E) {
            System.out.println("Excepcion SQL....: " + E.getMessage());
            System.out.println("Estado SQL.......: " + E.getSQLState());
            System.out.println("Código del Error.: " + E.getErrorCode());
        }

        //Si no ha habido problemas devolvemos la Lista de la consulta
        return list;    
    }
}
