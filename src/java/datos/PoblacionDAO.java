package datos;

import beans.Poblacion;
import control.db.Database;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * CAMPOS DEL POJO CUPON (para ayudarme en los SQLs)
 * -----------------------------------------------------
 *
 * private int poblacionID;
 *
 *
 * private String nombre;
 *
 *
 * private int provinciaID;
 *
 * -----------------------------------------------------
 */
/**
 * Clase encargada de ejecutar consultas a la BBDD sobre la tabla de Poblacion
 *
 * @author Beatriz Robledillo Gómez
 */
public class PoblacionDAO implements I_PoblacionDAO {

    /**
     * Encargado de solicitar un alta de un objeto Poblacion en la BBDD
     *
     * @param poblacion Objeto Poblacion para dar de alta
     * @param con Conexion a la BBDD
     * @return int Devuelve 1 si la inserccion es correcta
     */
    @Override
    public int alta(Poblacion poblacion, Database con) {

        int res = 0;

        /*
         * CONSULTA DE INSERCCION
         */
        String query = "INSERT INTO poblacion"
                + " (nombre,"
                + " provinciaID)"
                + "VALUES"
                + " ('"
                + poblacion.getNombre() + "', "
                + poblacion.getProvinciaID() + "' "
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
     * Encargado de devolver una coleccion de Poblaciones existentes
     *
     * @param con Conexion con la que hare la consulta
     * @return List Obtencion de Poblaciones
     */
    @Override
    public List<Poblacion> lista(Database con) {
        ResultSet rs = null;
        ArrayList<Poblacion> list = new ArrayList<>();
        /*
         * CONSULTA DE POBLACIONES EXISTENTES
         */
        String query = "select poblacionID, nombre, provinciaID"
                + " from poblacion"
                + " order by nombre;";
        try {
            /*
             *Lanzo la consulta y el resultado lo guardo en un nuevo objeto
             *Pais y lo meto en la Lista
             */
            rs = con.consultaSQL(query);
            while (rs.next()) {
                list.add(new Poblacion(
                        rs.getInt("poblacionID"),
                        rs.getString("nombre"),
                        rs.getInt("provinciaID")
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
     * Encargado de eliminar una Poblacion mediante su ID
     *
     * @param id Poblacion que daremos de baja en la BBDD
     * @param con Conexion a la BBDD
     * @return Devuelve 1 si la inserccion es correcta
     */
    @Override
    public int baja(int id, Database con) {
        int res = 0;

        /*
         * CONSULTA DE BORRADO
         */
        String query = "DELETE FROM poblacion WHERE poblacionID ='"
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
     * Encargado de modificar una Poblacion mediante un objeto pasado
     *
     * @param poblacionID Poblacion que modificaremos en la BBDD
     * @param con Conexion a la BBDD
     * @return Devuelve 1 si la consulta es correcta
     */
    @Override
    public int modificar(Poblacion poblacionID, Database con) {
        int res = 0;

        /*
         * CONSULTA DE ACTUALIZACION
         */
        String query = "UPDATE poblacion "
                + "SET nombre='" + poblacionID.getNombre() + "', "
                + "provincia='" + poblacionID.getProvinciaID() + "' "
                + " WHERE provinciaID='" + poblacionID.getPoblacionID() + "';";
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
     * Encargado de buscar una Poblacion mediante su ID, devolvemos la Poblacion
     * encontrada
     *
     * @param id La Poblacion que buscaremos en la BBDD
     * @param con Conexion a la BBDD
     * @return Devolvemos la Población encontrada
     */
    @Override
    public Poblacion buscar(int id, Database con) {
        ResultSet rs = null;
        Poblacion pobla = new Poblacion();

        /*
         * CONSULTA
         */
        String query
                = "SELECT poblacionID,"
                + " nombre,"
                + " provinciaID"
                + " FROM poblacion"
                + " WHERE poblacionID = '" + id + "';";

        try {
            /*
             *Lanzo la consulta y el resultado lo guardo en un nuevo objeto 
             *Poblacion y lo devolvemos
             */
            rs = con.consultaSQL(query);
            while (rs.next()) {
                pobla.setPoblacionID(id);
                pobla.setNombre(rs.getString("nombre"));
                pobla.setProvinciaID(id);

            }
        } catch (SQLException E) {
            System.out.println("Excepcion SQL....: " + E.getMessage());
            System.out.println("Estado SQL.......: " + E.getSQLState());
            System.out.println("Código del Error.: " + E.getErrorCode());
        }

        return pobla;
    }

}
