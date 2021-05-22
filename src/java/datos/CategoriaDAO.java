package datos;

import beans.Categoria;
import control.db.Database;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase encargada de ejecutar consultas a la BBDD sobre la tabla de Categorias
 *
 * @author Eduardo C. Araujo
 */
public class CategoriaDAO implements I_CategoriaDAO{

    /**
     * Encargado de solicitar un alta de un objeto categoria en la BBDD
     *
     * @param cat La categoria que daremos de alta en la BBDD
     * @param con Conexion a la BBDD
     * @return int Devuelve 1 si la inserccion es correcta
     */
    @Override
    public int alta(Categoria cat, Database con) {
        int res = 0;

        /*
         * CONSULTA DE INSERCCION
         */
        String query = "INSERT INTO categoria "
                + "(nombre,"
                + " categoriaCategoriaID)"
                + " VALUES"
                + " (\""
                + cat.getNombre() + "\", ?);";

        try {
            /*
             * Para poder pasar un valor NULL a una columna de BBDD que lo
             * permita hay que hacer un PreparedStatement y usar setNull
             * donde corresponda.
             */
            PreparedStatement st = con.getConnection().prepareStatement(query);
            if (cat.getCategoriaCategoriaID() != 0) {
                st.setInt(1, cat.getCategoriaCategoriaID());
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
            //Relanzamos un RuntimeException para que se pueda cargar el custom 
            //JSP de error
            throw new RuntimeException(E);
        }
    }

    /**
     * Encargado de devolver una coleccion de categorias existentes
     *
     * @param con Conexion con la que hare la consulta
     * @return List Obtencion de categorias principales (que no tienen ningun
     * padre)
     */
    @Override
    public List<Categoria> lista(Database con) {
        ResultSet rs = null;
        ArrayList<Categoria> list = new ArrayList<>();

        /*
         * CONSULTA DE CATEGORIAS EXISTENTES
         */
        String query
                = "select categoriaId, nombre,"
                + " categoriaCategoriaID"
                + " from Categoria"
                + " order by nombre;";

        try {
            /*
             *Lanzo la consulta y el resultado lo guardo en un nuevo objeto 
             *Categoria y lo meto en la Lista
             */
            rs = con.consultaSQL(query);
            while (rs.next()) {
                list.add(new Categoria(rs.getInt("categoriaId"), rs.getString("nombre"), rs.getInt("categoriaCategoriaID")));
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
     * Encargado de eliminar una categoria mediante su ID
     *
     * @param id La categoria que daremos de baja en la BBDD
     * @param con Conexion a la BBDD
     * @return Devuelve 1 si la inserccion es correcta
     */
    @Override
    public int baja(int id, Database con) {
        int res = 0;

        /*
         * CONSULTA DE BORRADO
         */
        String query = "DELETE FROM categoria WHERE categoriaID ='"
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
     * Encargado de modificar una categoria mediante un objeto pasado
     *
     * @param cat La categoria que modificaremos en la BBDD
     * @param con Conexion a la BBDD
     * @return Devuelve 1 si la inserccion es correcta
     */
    @Override
    public int modificar(Categoria cat, Database con) {
        int res = 0;

        /*
         * CONSULTA DE ACTUALIZACION
         */
        String query = "UPDATE categoria SET categoriaCategoriaID=?, nombre='" + cat.getNombre()
                + "' WHERE categoriaID='" + cat.getCategoriaID() + "';";

        try {
            /*
             * Para poder pasar un valor NULL a una columna de BBDD que lo
             * permita hay que hacer un PreparedStatement y usar setNull
             * donde corresponda.
             */
            PreparedStatement st = con.getConnection().prepareStatement(query);
            if (cat.getCategoriaCategoriaID() != 0) {
                st.setInt(1, cat.getCategoriaCategoriaID());
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
     * Encargado de buscar una categoria mediante su ID, devolvemos la categoria
     * encontrada
     *
     * @param id La categoria que buscaremos en la BBDD
     * @param con Conexion a la BBDD
     * @return Devolvemos la Categoria encotrada
     */
    @Override
    public Categoria buscar(int id, Database con) {
        ResultSet rs = null;
        Categoria cat = new Categoria();

        /*
         * CONSULTA
         */
        String query
                = "select categoriaId, nombre,"
                + " categoriaCategoriaID"
                + " from Categoria"
                + " where categoriaId = '" + id + "';";

        try {
            /*
             *Lanzo la consulta y el resultado lo guardo en un nuevo objeto 
             *Categoria y lo devolvemos
             */
            rs = con.consultaSQL(query);
            while (rs.next()) {
                cat.setCategoriaID(id);
                cat.setNombre(rs.getString("nombre"));
                cat.setCategoriaCategoriaID(rs.getInt("categoriaCategoriaID"));
            }
        } catch (SQLException E) {
            System.out.println("Excepcion SQL....: " + E.getMessage());
            System.out.println("Estado SQL.......: " + E.getSQLState());
            System.out.println("Código del Error.: " + E.getErrorCode());
        }

        return cat;
    }
}
