package datos;

import beans.Cupon;
import control.db.Database;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

/**
 * CAMPOS DEL POJO CUPON (para ayudarme en los SQLs)
 * -----------------------------------------------------
 *
 * private int cuponID;
 *
 * private String nombre;
 *
 * private int descuento;
 *
 * private Date fechaExpiracion;
 * -----------------------------------------------------
 */
/**
 * Clase encargada de ejecutar consultas a la BBDD sobre la tabla de Cupon
 *
 * @author Beatriz Robledillo Gómez
 */
public class CuponDAO implements I_CuponDAO {

    /**
     * Encargado de solicitar un alta de un objeto Cupon en la BBDD
     *
     * @param cupon Objeto cupon para dar de alta
     * @param con Conexion a la BBDD
     * @return int Devuelve 1 si la inserccion es correcta
     */
    @Override
    public int alta(Cupon cupon, Database con) {
        int res = 0;

        /*
         * CONSULTA DE INSERCCION
         */
        String query = "INSERT INTO cupon"
                + "(nombre,"
                + "descuento,"
                + "fechaExpiracion)"
                + " VALUES"
                + " ('"
                + cupon.getNombre() + "', "
                + cupon.getDescuento() + "', "
                + cupon.getFechaExpiracion()
                + ");";

        try {
            /*
             * Para poder pasar un valor NULL a una columna de BBDD que lo
             * permita hay que hacer un PreparedStatement y usar setNull
             * donde corresponda.
             */
            PreparedStatement st = con.getConnection().prepareStatement(query);
            if (cupon.getDescuento() != 0) {
                st.setInt(1, cupon.getDescuento());
            } else {
                st.setNull(1, Types.INTEGER);
            }
            if (cupon.getFechaExpiracion() != null) {
                st.setDate(2, (Date) cupon.getFechaExpiracion());
            } else {
                st.setNull(2, Types.DATE);
            }

            //Lanzo la consulta de inserccion
            res = con.updateSQL(st);
            return res;
        } catch (SQLException E) {
            System.out.println("Excepcion SQL....: " + E.getMessage());
            System.out.println("Estado SQL.......: " + E.getSQLState());
            System.out.println("Código del Error.: " + E.getErrorCode());
            //Relanzamos un RuntimeException para que se pueda cargar el custom
            //JSP de error
            throw new RuntimeException(E);
        }
    }

    /**
     * Encargado de devolver una coleccion de Cupones existentes
     *
     * @param con Conexion con la que hare la consulta
     * @return List Obtencion de Cupones
     */
    @Override
    public List<Cupon> lista(Database con) {

        ResultSet rs = null;
        ArrayList<Cupon> list = new ArrayList<>();
        /*
         * CONSULTA DE CUPONES EXISTENTES
         */

        String query
                = "select cuponID, nombre, descuento, fechaExpiracion"
                + " from cupon"
                + " order by nombre;";
        try {
            /*
             *Lanzo la consulta y el resultado lo guardo en un nuevo objeto
             *Cupon y lo meto en la Lista
             */
            rs = con.consultaSQL(query);
            while (rs.next()) {
                list.add(new Cupon(rs.getInt("cuponID"), rs.getString("nombre"), rs.getInt("descuento"), rs.getDate("fechaExpiracion")));
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
     * Encargado de eliminar un Cupon mediante su ID
     *
     * @param id Cupon que daremos de baja en la BBDD
     * @param con Conexion a la BBDD
     * @return Devuelve 1 si la inserccion es correcta
     */
    @Override
    public int baja(int id, Database con) {
        int res = 0;

        /*
         * CONSULTA DE BORRADO
         */
        String query = "DELETE FROM cupon WHERE cuponID ='"
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
     * Encargado de modificar un Cupon mediante un objeto pasado
     *
     * @param cupon Cupon que modificaremos en la BBDD
     * @param con Conexion a la BBDD
     * @return Devuelve 1 si la consulta es correcta
     */
    @Override
    public int modificar(Cupon cupon, Database con) {
        int res = 0;

        /*
         * CONSULTA DE ACTUALIZACION
         */
        String query = "UPDATE cupon "
                + "SET nombre='" + cupon.getNombre() + "', "
                + "descuento='" + cupon.getDescuento() + "', "
                + "fechaExpiracion" + cupon.getFechaExpiracion() + "', "
                + "' WHERE cuponID='" + cupon.getCuponID() + "';";

        try {
            /*
             * Para poder pasar un valor NULL a una columna de BBDD que lo
             * permita hay que hacer un PreparedStatement y usar setNull
             * donde corresponda.
             */
            PreparedStatement st = con.getConnection().prepareStatement(query);
            if (cupon.getCuponID() != 0) {
                st.setInt(1, cupon.getCuponID());
            } else {
                st.setNull(1, Types.INTEGER);
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
     * Encargado de buscar un cupon mediante su ID, devolvemos el cupon
     * encontrado
     *
     * @param id El cupon que buscaremos en la BBDD
     * @param con Conexion a la BBDD
     * @return Devolvemos el Cupon encontrado
     */
    @Override
    public Cupon buscar(int id, Database con) {
        ResultSet rs = null;
        Cupon cupon = new Cupon();

        /*
         * CONSULTA
         */
        String query
                = "select cuponID, nombre, descuento, fechaExpiracion"
                + " from Cupon"
                + " where cuponID = '" + id + "';";

        try {
            /*
             *Lanzo la consulta y el resultado lo guardo en un nuevo objeto 
             *Cupon y lo devolvemos
             */
            rs = con.consultaSQL(query);
            while (rs.next()) {
                cupon.setCuponID(id);
                cupon.setNombre(rs.getString("nombre"));
                cupon.setDescuento(rs.getInt("descuento"));
                cupon.setFechaExpiracion(rs.getDate("fechaExpiracion"));

            }
        } catch (SQLException E) {
            System.out.println("Excepcion SQL....: " + E.getMessage());
            System.out.println("Estado SQL.......: " + E.getSQLState());
            System.out.println("Código del Error.: " + E.getErrorCode());
        }

        return cupon;
    }
}
