package datos;

import beans.Cesta;
import control.db.Database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * CAMPOS DEL POJO CESTA (para ayudarme en los SQLs)
 * ------------------------------------------------- private int cestaID;
 * private int usuarioID; private Date fecha; private double total;
 * -------------------------------------------------
 */
/**
 * Clase encargada de ejecutar consultas a la BBDD sobre la tabla de Cesta
 *
 * @author Eduardo C. Araujo
 */
public class CestaDAO implements I_CestaDAO {

    /**
     * Encargado de solicitar un alta de un objeto cesta en la BBDD
     *
     * @param ces Objeto Cesta que queremos dar de alta en la BBDD
     * @param con Conexion a la BBDD
     * @return int Devuelve el ID del registro insertado
     */
    @Override
    public int alta(Cesta ces, Database con) {
        int id = 0;
        Statement stmt = null;
        Connection conn = con.getConnection();

        /*
         * CONSULTA DE INSERCCION
         */
        String query = "INSERT INTO cesta "
                + "(usuarioID, fecha, total)"
                + " VALUES"
                + " ('"
                + ces.getUsuarioID() + "', '"
                + ces.getFecha() + "', '"
                + ces.getTotal() + "');";

        try {
            stmt = conn.createStatement(java.sql.ResultSet.TYPE_FORWARD_ONLY,
                                        java.sql.ResultSet.CONCUR_UPDATABLE);
            stmt.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = stmt.getGeneratedKeys();
            
            while (rs.next()) {
                id =  rs.getInt(1);
            } 
            rs.close();
            
            return id;
            
        } catch (SQLException E) {
            System.out.println("Excepcion SQL....: " + E.getMessage());
            System.out.println("Estado SQL.......: " + E.getSQLState());
            System.out.println("Código del Error.: " + E.getErrorCode());
            return id;
        }
    }

    /**
     * Encargado de devolver una coleccion de cestas existentes
     *
     * @param con Conexion con la que hare la consulta
     * @return List Obtencion de cestas
     */
    @Override
    public List<Cesta> lista(Database con) {
        ResultSet rs = null;
        ArrayList<Cesta> list = new ArrayList<>();

        /*
         * CONSULTA DE CESTAS EXISTENTES
         */
        String query
                = "SELECT cestaID,"
                + " usuarioID,"
                + " fecha,"
                + " total"
                + " FROM cesta;";

        try {
            /*
             *Lanzo la consulta y el resultado lo guardo en un nuevo objeto 
             *cesta y lo meto en la Lista que devolveremos
             */
            rs = con.consultaSQL(query);
            while (rs.next()) {
                list.add(new Cesta(
                        rs.getInt("cestaID"),
                        rs.getInt("usuarioID"),
                        rs.getDate("fecha"),
                        rs.getDouble("total")
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
     * Encargado de eliminar una cesta mediante su ID
     *
     * @param id La cesta que daremos de baja en la BBDD
     * @param con Conexion a la BBDD
     * @return Devuelve 1 si la inserccion es correcta
     */
    @Override
    public int baja(int id, Database con) {
        int res = 0;

        /*
         * CONSULTA DE BORRADO
         */
        String query = "DELETE FROM cesta WHERE cestaID ='"
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

    /**
     * Encargado de modificar una cesta mediante un objeto pasado
     *
     * @param ces La cesta que modificaremos en la BBDD
     * @param con Conexion a la BBDD
     * @return Devuelve 1 si la inserccion es correcta
     */
    @Override
    public int modificar(Cesta ces, Database con) {
        int res = 0;

        /*
         * CONSULTA DE ACTUALIZACION
         */
        String query = "UPDATE cesta "
                + "SET usuarioID='" + ces.getUsuarioID() + "',"
                + " fecha='" + ces.getFecha() + "',"
                + " total='" + ces.getTotal() + "')"
                + " WHERE cestaID = '" + ces.getCestaID() + "';";

        try {
            //Lanzo la consulta de inserccion
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
     * Encargado de buscar una cesta mediante su ID, devolvemos la cesta
     * encontrada
     *
     * @param id La cesta que buscaremos en la BBDD
     * @param con Conexion a la BBDD
     * @return Devolvemos la cesta encotrada
     */
    @Override
    public Cesta buscar(int id, Database con) {
        ResultSet rs = null;
        Cesta ces = new Cesta();

        /*
         * CONSULTA
         */
        String query
                = "SELECT cestaID,"
                + " usuarioID,"
                + " fecha,"
                + " total,"
                + " FROM cesta"
                + " WHERE cestaID = '" + id + "';";

        try {
            /*
             *Lanzo la consulta y el resultado lo guardo en un nuevo objeto 
             *Cesta y lo devolvemos
             */
            rs = con.consultaSQL(query);
            while (rs.next()) {
                ces.setCestaID(rs.getInt("cestaID"));
                ces.setUsuarioID(rs.getInt("usuarioID"));
                ces.setFecha(rs.getDate("fecha"));
                ces.setTotal(rs.getDouble("total"));
            }
        } catch (SQLException E) {
            System.out.println("Excepcion SQL....: " + E.getMessage());
            System.out.println("Estado SQL.......: " + E.getSQLState());
            System.out.println("Código del Error.: " + E.getErrorCode());
        }

        return ces;
    }
    public Double total(int id, Database con) {
        ResultSet rs = null;
        Double total = null;

        /*
         * CONSULTA
         */
        String query
                = "SELECT sum(item.precio) total "
                + "FROM cesta, item "
                + "WHERE cesta.cestaID = item.cestaID AND cesta.cestaID = '" + id + "' "
                + "GROUP BY cesta.cestaID;";

        try {
            /*
             *Lanzo la consulta y el resultado lo guardo en un nuevo objeto 
             *Cesta y lo devolvemos
             */
            rs = con.consultaSQL(query);
            while (rs.next()) {
                total = rs.getDouble("total");
            }
        } catch (SQLException E) {
            System.out.println("Excepcion SQL....: " + E.getMessage());
            System.out.println("Estado SQL.......: " + E.getSQLState());
            System.out.println("Código del Error.: " + E.getErrorCode());
        }

        return total;
    }   
}
