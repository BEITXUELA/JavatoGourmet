
package datos;

import beans.TipoEnvio;
import control.db.Database;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase encargada de ejecutar consultas a la BBDD sobre la tabla de TipoEnvio
 *
 * @author Alonso Gil López
 */
public class TipoEnvioDAO implements I_TipoEnvioDAO {

    /**
     * Encargado de solicitar un alta de un objeto tipoEnvio en la BBDD
     *
     * @param env El tipoPago que daremos de alta en la BBDD
     * @param con Conexion a la BBDD
     * @return int Devuelve 1 si la inserccion es correcta
     */
    @Override
    public int alta(TipoEnvio env, Database con) {

        int res = 0;

        /*
         * CONSULTA DE INSERCCION
         */
        String query = "INSERT INTO tipoenvio "
                + "("
                + "tipoEnvioID, "
                + "nombre, "
                + "descripcion "
                + " VALUES"
                + "'" + env.getTipoEnvioID() + "', "
                + "'" + env.getNombre() + "', "
                + "'" + env.getDescripcion() + "');";

        try {

            //Lanzo la consulta de insercion
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
    public List<TipoEnvio> lista(Database con) {
        ResultSet rs = null;
        ArrayList<TipoEnvio> env = new ArrayList<>();

        /*
         * CONSULTA DE TIPOS ENVIOS EXISTENTES
         */
        String query = "SELECT "
                + "tipoEnvioID, "
                + "nombre, "
                + "descripcion, "
                + "incremento "
                + "FROM tipoenvio";

        try {
            /*
             *Lanzo la consulta y el resultado lo guardo en un nuevo objeto 
             *TipoEnvio y lo meto en la Lista
             */
            rs = con.consultaSQL(query);
            while (rs.next()) {
                env.add(new TipoEnvio(
                        rs.getInt("tipoEnvioID"),
                        rs.getString("nombre"),
                        rs.getString("descripcion"),
                        rs.getInt("incremento")));

            }
        } catch (SQLException E) {
            System.out.println("Excepcion SQL....: " + E.getMessage());
            System.out.println("Estado SQL.......: " + E.getSQLState());
            System.out.println("Código del Error.: " + E.getErrorCode());
        }
        //Si no ha habido problemas devolvemos la Lista de la consulta
        return env;
    }

    /**
     * Encargado de eliminar un tipoEnvio mediante su ID
     *
     * @param id El tipoEnvio que daremos de baja en la BBDD
     * @param con Conexion a la BBDD
     * @return Devuelve 1 si la inserccion es correcta
     */
    @Override
    public int baja(int id, Database con) {
        int res = 0;

        /*
         * CONSULTA DE BORRADO
         */
        String query = "DELETE FROM tipoenvio WHERE `tipoEnvioID";

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
     * Encargado de modificar un tipoEnvio mediante un objeto pasado
     *
     * @param env El tipoEnvio que modificaremos en la BBDD
     * @param con Conexion a la BBDD
     * @return Devuelve 1 si la inserccion es correcta
     */
    @Override
    public int modificar(TipoEnvio env, Database con) {

        int res = 0;

        /*
         * CONSULTA DE ACTUALIZACION
         */
        String query = "UPDATE tipoenvio SET nombre='" + env.getNombre()
                + "', descripcion='" + env.getDescripcion()
                + "', incremento='" + env.getIncremento()
                + "', WHERE tipoEnvioID=" + env.getTipoEnvioID()
                + "'";
        try {
            //Lanzo la consulta de insercion
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
     * Encargado de buscar un tipoEnvio mediante su ID, devolvemos el tipoEnvio
     * encontrada
     *
     * @param id El tipoEnvio que buscaremos en la BBDD
     * @param con Conexion a la BBDD
     * @return Devuelve 1 si la inserccion es correcta
     */
    @Override
    public TipoEnvio buscar(int id, Database con) {

        ResultSet rs = null;
        TipoEnvio env = new TipoEnvio();

        /*
         * CONSULTA
         */
        String query = "select tipoEnvioID, nombre"
                + "descripcion "
                + "incremento "
                + "where tipoEnvioID = '" + id + "';";

        try {
            /*
             *Lanzo la consulta y el resultado lo guardo en un nuevo objeto 
             *tipoEnvio y lo devolvemos
             */
            rs = con.consultaSQL(query);
            while (rs.next()) {
                env.setTipoEnvioID(id);
                env.setNombre(rs.getString("nombre"));
                env.setDescripcion(rs.getString("descripcion"));
                env.setIncremento(rs.getInt("incremento"));

            }
        } catch (SQLException E) {
            System.out.println("Excepcion SQL....: " + E.getMessage());
            System.out.println("Estado SQL.......: " + E.getSQLState());
            System.out.println("Código del Error.: " + E.getErrorCode());
        }

        return env;
    }
}


