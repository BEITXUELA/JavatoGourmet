package datos;

import beans.Provincia;
import control.db.Database;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * CAMPOS DEL POJO CUPON (para ayudarme en los SQLs)
 * -----------------------------------------------------
 *
 * private int provinciaID;
 *
 *
 * private String nombre;
 *
 *
 * private int paisID;
 *
 *
 * -----------------------------------------------------
 */
/**
 * Clase encargada de ejecutar consultas a la BBDD sobre la tabla de Provincia
 *
 * @author Beatriz Robledillo Gómez
 */
public class ProvinciaDAO implements I_ProvinciaDAO {

    /**
     * Encargado de solicitar un alta de un objeto Provincia en la BBDD
     *
     * @param provincia Objeto Provincia para dar de alta
     * @param con Conexion a la BBDD
     * @return int Devuelve 1 si la inserccion es correcta
     */
    @Override
    public int alta(Provincia provincia, Database con) {
        int res = 0;

        /*
         * CONSULTA DE INSERCCION
         */
        String query = "INSERT INTO provincia"
                + " paisID,"
                + " nombre)"
                + " VALUES"
                + " ('"
                + provincia.getProvinciaID() + "', "
                + provincia.getPaisID() + "', "
                + provincia.getNombre() + "', "
                + ");";
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
     * Encargado de devolver una coleccion de Provincias existentes
     *
     * @param con Conexion con la que hare la consulta
     * @return List Obtencion de Provincias
     */
    @Override
    public List<Provincia> lista(Database con) {

        ResultSet rs = null;
        ArrayList<Provincia> list = new ArrayList<>();
        /*
         * CONSULTA DE PROVINCIAS EXISTENTES
         */

        String query = "select provinciaID, paisID, nombre "
                + " from provincia"
                + " order by nombre;";
        try {
            /*
             *Lanzo la consulta y el resultado lo guardo en un nuevo objeto
             *Provincia y lo meto en la Lista
             */
            rs = con.consultaSQL(query);
            while (rs.next()) {
                list.add(new Provincia(
                        rs.getInt("provinciaID"),
                        rs.getString("nombre"),
                        rs.getInt("paisID")
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
     * Encargado de eliminar una Provincia mediante su ID
     *
     * @param id Provincia que daremos de baja en la BBDD
     * @param con Conexion a la BBDD
     * @return Devuelve 1 si la inserccion es correcta
     */
    @Override
    public int baja(int id, Database con) {
        int res = 0;

        /*
         * CONSULTA DE BORRADO
         */
        String query = "DELETE FROM provincia WHERE provinciaID ='"
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
     * Encargado de modificar una Provincia mediante un objeto pasado
     *
     * @param provinciaID Provincia que modificaremos en la BBDD
     * @param con Conexion a la BBDD
     * @return Devuelve 1 si la consulta es correcta
     */
    @Override
    public int modificar(Provincia provinciaID, Database con) {

        int res = 0;

        /*
         * CONSULTA DE ACTUALIZACION
         */
        String query = "UPDATE provincia "
                + "SET nombre='" + provinciaID.getNombre() + "', "
                + "paisID='" + provinciaID.getPaisID() + "' "
                + " WHERE provinciaID='" + provinciaID.getProvinciaID() + "';";
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
     * Encargado de buscar una Provincia mediante su ID, devolvemos la Provincia
     * encontrada
     *
     * @param id La Provincia que buscaremos en la BBDD
     * @param con Conexion a la BBDD
     * @return Devolvemos la Provincia encontrada
     */
    @Override
    public Provincia buscar(int id, Database con) {
        ResultSet rs = null;
        Provincia provincia = new Provincia();

        /*
         * CONSULTA
         */
        String query
                = "SELECT provinciaID,"
                + " nombre,"
                + " paisID"
                + " FROM provincia"
                + " WHERE provinciaID = '" + id + "';";
        try {
            /*
             *Lanzo la consulta y el resultado lo guardo en un nuevo objeto 
             *Cupon y lo devolvemos
             */
            rs = con.consultaSQL(query);
            while (rs.next()) {
                provincia.setProvinciaID(id);
                provincia.setNombre(rs.getString("nombre"));
                provincia.setPaisID(id);

            }
        } catch (SQLException E) {
            System.out.println("Excepcion SQL....: " + E.getMessage());
            System.out.println("Estado SQL.......: " + E.getSQLState());
            System.out.println("Código del Error.: " + E.getErrorCode());
        }

        return provincia;
    }

}
