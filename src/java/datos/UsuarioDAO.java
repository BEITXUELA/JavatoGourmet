package datos;

import beans.Usuario;
import control.db.Database;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase encargada de ejecutar consultas a la BBDD sobre la tabla de Usuarios
 *
 * @author Eduardo C. Araujo
 */
public class UsuarioDAO implements I_UsuarioDAO {

    /**
     * Encargado de solicitar un alta de un objeto usuario en la BBDD
     *
     * @param usu El usuario que daremos de alta en la BBDD
     * @param con Conexion a la BBDD
     * @return int Devuelve 1 si la inserccion es correcta
     */
    @Override
    public int alta(Usuario usu, Database con) {
        int res = 0;

        /*
         * CONSULTA DE INSERCCION
         */
        String query = "INSERT INTO usuario "
                + "(estadoUsuarioID,"
                + " usuario, "
                + " password)"
                + " VALUES"
                + " ("
                + usu.getUsuarioID() + ", "
                + "'" + usu.getUsuario() + "', "
                + "'" + usu.getPassword() + "');";

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
     * Encargado de devolver una coleccion de usuarios existentes
     *
     * @param con Conexion con la que hare la consulta
     * @return List Obtencion de usuarios
     */
    @Override
    public List<Usuario> lista(Database con) {
        ResultSet rs = null;
        ArrayList<Usuario> list = new ArrayList<>();

        /*
         * CONSULTA DE USUARIOS EXISTENTES
         */
        String query
                = "SELECT UsuarioID, estadoUsuarioID, usuario, password "
                + "FROM usuario";

        try {
            /*
             *Lanzo la consulta y el resultado lo guardo en un nuevo objeto 
             *Usuario y lo meto en la Lista
             */
            rs = con.consultaSQL(query);
            while (rs.next()) {
                list.add(new Usuario(rs.getInt("UsuarioID"), rs.getString("usuario"), rs.getString("password"), rs.getInt("estadoUsuarioID")));
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
     * Encargado de eliminar un usuario mediante su ID
     *
     * @param id El usuario que daremos de baja en la BBDD
     * @param con Conexion a la BBDD
     * @return Devuelve 1 si la inserccion es correcta
     */
    @Override
    public int baja(int id, Database con) {
        int res = 0;

        /*
         * CONSULTA DE BORRADO
         */
        String query = "DELETE FROM usuario WHERE usuarioID ='"
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
     * Encargado de modificar un usuario mediante un objeto pasado
     *
     * @param usu El usuario que modificaremos en la BBDD
     * @param con Conexion a la BBDD
     * @return Devuelve 1 si la consulta es correcta
     */
    @Override
    public int modificar(Usuario usu, Database con) {
        int res = 0;

        /*
         * CONSULTA DE ACTUALIZACION
         */
        String query = "UPDATE usuario "
                + " SET "
                + "usuario='" + usu.getUsuario() + "', "
                + "usuario='" + usu.getPassword() + "', "
                + "estadoUsuarioID='" + usu.getEstado() + "' "
                + " WHERE usuarioID='" + usu.getUsuarioID() + "';";

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
     * Encargado de buscar un usuario mediante su ID, devolvemos el usuario
     * encontrado
     *
     * @param id El usuario que buscaremos en la BBDD
     * @param con Conexion a la BBDD
     * @return Devolvemos el Usuario encontrado
     */
    @Override
    public Usuario buscar(int id, Database con) {
        ResultSet rs = null;
        Usuario usu = new Usuario();

        /*
         * CONSULTA
         */
        String query
                = "SELECT UsuarioID, estadoUsuarioID, usuario, password "
                + "FROM usuario "
                + "WHERE UsuarioID = '" + id + "';";

        try {
            /*
             *Lanzo la consulta y el resultado lo guardo en un nuevo objeto 
             *Usuario y lo devolvemos
             */
            rs = con.consultaSQL(query);
            while (rs.next()) {
                usu.setUsuarioID(id);
                usu.setUsuario(rs.getString("usuario"));
                usu.setPassword(rs.getString("password"));
                usu.setEstado(rs.getInt("estadoUsuarioID"));
            }
        } catch (SQLException E) {
            System.out.println("Excepcion SQL....: " + E.getMessage());
            System.out.println("Estado SQL.......: " + E.getSQLState());
            System.out.println("Código del Error.: " + E.getErrorCode());
        }

        return usu;
    }

}
