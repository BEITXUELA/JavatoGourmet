package datos;

import beans.Pais;
import control.db.Database;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

/**
 * CAMPOS DEL POJO PAIS (para ayudarme en los SQLs)
 * -----------------------------------------------------
 *
 * private int paisID;
 *
 * private String nombre;
 *
 * -----------------------------------------------------
 */
/**
 * Clase encargada de ejecutar consultas a la BBDD sobre la tabla de Pais
 *
 * @author Beatriz Robledillo Gómez
 */
public class PaisDAO implements I_PaisDAO {

    /**
     * Encargado de solicitar un alta de un objeto País en la BBDD
     *
     * @param paisID Objeto Pais para dar de alta
     * @param con Conexion a la BBDD
     * @return int Devuelve 1 si la inserccion es correcta
     */
    @Override
    public int alta(Pais paisID, Database con) {
        int res = 0;
        /*
         * CONSULTA DE INSERCCION
         */
        String query = "INSERT INTO pais "
                + "paisID, "
                + "nombre)"
                + "VALUES"
                + " ('"
                + paisID.getPaisID() + "', "
                + "?);";

        try {
            /*
             * Para poder pasar un valor NULL a una columna de BBDD que lo
             * permita hay que hacer un PreparedStatement y usar setNull
             * donde corresponda.
             */
            PreparedStatement st = con.getConnection().prepareStatement(query);
            if (!"".equals(paisID.getNombre())) {
                st.setString(1, paisID.getNombre());
            } else {
                st.setNull(1, Types.VARCHAR);
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
     * Encargado de devolver una coleccion de Paises existentes
     *
     * @param con Conexion con la que hare la consulta
     * @return List Obtencion de Paises
     */
    @Override
    public List<Pais> lista(Database con) {
        ResultSet rs = null;
        ArrayList<Pais> list = new ArrayList<>();
        /*
         * CONSULTA DE PAISES EXISTENTES
         */

        String query = "select paisID, nombre"
                + " from pais"
                + " order by nombre;";
        try {
            /*
             *Lanzo la consulta y el resultado lo guardo en un nuevo objeto
             *Pais y lo meto en la Lista
             */
            rs = con.consultaSQL(query);
            while (rs.next()) {
                list.add(new Pais(rs.getInt("paisID"), rs.getString("nombre")));
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
     * Encargado de eliminar un Pais mediante su ID
     *
     * @param id Pais que daremos de baja en la BBDD
     * @param con Conexion a la BBDD
     * @return Devuelve 1 si la inserccion es correcta
     */
    @Override
    public int baja(int id, Database con) {
        int res = 0;

        /*
         * CONSULTA DE BORRADO
         */
        String query = "DELETE FROM pais WHERE paisID ='"
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
     * Encargado de modificar un Pais mediante un objeto pasado
     *
     * @param paisID Pais que modificaremos en la BBDD
     * @param con Conexion a la BBDD
     * @return Devuelve 1 si la consulta es correcta
     */
    @Override
    public int modificar(Pais paisID, Database con) {
        int res = 0;

        /*
         * CONSULTA DE ACTUALIZACION
         */
        String query = "UPDATE pais "
                + "SET nombre=?"
                + " WHERE cuponID='" + paisID.getPaisID() + "';";
        try {
            /*
             * Para poder pasar un valor NULL a una columna de BBDD que lo
             * permita hay que hacer un PreparedStatement y usar setNull
             * donde corresponda.
             */
            PreparedStatement st = con.getConnection().prepareStatement(query);
            if (!"".equals(paisID.getNombre())) {
                st.setString(1, paisID.getNombre());
            } else {
                st.setNull(1, Types.VARCHAR);
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
     * Encargado de buscar un pais mediante su ID, devolvemos el pais encontrado
     *
     * @param id El pais que buscaremos en la BBDD
     * @param con Conexion a la BBDD
     * @return Devolvemos el Pais encontrado
     */
    @Override
    public Pais buscar(int id, Database con
    ) {

        ResultSet rs = null;
        Pais pais = new Pais();

        /*
         * CONSULTA
         */
        String query = "select paisID, nombre"
                + "from pais"
                + " where paisID = '" + id + "';";

        try {
            /*
             *Lanzo la consulta y el resultado lo guardo en un nuevo objeto 
             *Cupon y lo devolvemos
             */
            rs = con.consultaSQL(query);
            while (rs.next()) {
                pais.setPaisID(rs.getInt("paisID"));
                pais.setNombre(rs.getString("nombre"));
            }
        } catch (SQLException E) {
            System.out.println("Excepcion SQL....: " + E.getMessage());
            System.out.println("Estado SQL.......: " + E.getSQLState());
            System.out.println("Código del Error.: " + E.getErrorCode());
        }

        return pais;
    }

}
