package datos;

import beans.Foto;
import control.db.Database;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase encargada de ejecutar consultas a la BBDD sobre la tabla de Fotos
 *
 * @author Alonso Gil López
 */
public class FotoDAO implements I_FotoDAO {

    /**
     * Encargado de solicitar un alta de un objeto foto en la BBDD
     *
     * @param fot La foto que daremos de alta en la BBDD
     * @param con Conexion a la BBDD
     * @return int Devuelve 1 si la inserccion es correcta
     */
    @Override
    public int alta(Foto fot, Database con) {
        int res = 0;

        /*
         * CONSULTA DE INSERCCION
         */
        String query = "INSERT INTO fotoproducto "
                + "("
                + "productoID, "
                + "nombre, "
                + "url)"
                + " VALUES"
                + " ("
                + "'" + fot.getProductoID() + "', "
                + "'" + fot.getNombre() + "', "
                + "'" + fot.getUrl() + "');";

//        "INSERT INTO fotoproducto " (fotoID, productoID, nombre, url) VALUES (\"");
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
    public List<Foto> lista(Database con) {
        ResultSet rs = null;
        ArrayList<Foto> fo = new ArrayList<>();

        /*
         * CONSULTA DE FOTOS EXISTENTES
         */
        String query
                = "select fotoId, nombre, url, "
                + "productoID "
                + "from fotoproducto "
                + "order by nombre;";

        try {
            /*
             *Lanzo la consulta y el resultado lo guardo en un nuevo objeto 
             *Foto y lo meto en la Lista
             */
            rs = con.consultaSQL(query);
            while (rs.next()) {
                fo.add(new Foto(
                        rs.getInt("fotoID"),
                        rs.getInt("productoID"),
                        rs.getString("nombre"),
                        rs.getString("url")));
            }
        } catch (SQLException E) {
            System.out.println("Excepcion SQL....: " + E.getMessage());
            System.out.println("Estado SQL.......: " + E.getSQLState());
            System.out.println("Código del Error.: " + E.getErrorCode());
        }

        //Si no ha habido problemas devolvemos la Lista de la consulta
        return fo;
    }

    /**
     * Encargado de eliminar una foto mediante su ID
     *
     * @param id La foto que daremos de baja en la BBDD
     * @param con Conexion a la BBDD
     * @return Devuelve 1 si la inserccion es correcta
     */
    @Override
    public int baja(int id, Database con) {
        int res = 0;

        /*
         * CONSULTA DE BORRADO
         */
        String query = "DELETE FROM fotoproducto WHERE fotoID ='" + id + "';";

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
     * Encargado de modificar una foto mediante un objeto pasado
     *
     * @param fo La foto que daremos de alta en la BBDD
     * @param con Conexion a la BBDD
     * @return Devuelve 1 si la inserccion es correcta
     */
    @Override
    public int modificar(Foto fo, Database con) {
        int res = 0;

        /*
         * CONSULTA DE ACTUALIZACION
         */
        String query = "UPDATE fotoproducto "
                + "SET nombre ='" + fo.getNombre() + "', "
                + "productoID ='" + fo.getProductoID() + "', "
                + "url ='" + fo.getUrl() + "' "
                + "WHERE FotoID='" + fo.getFotoID() + "';";

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
     * Encargado de buscar una foto mediante su ID, devolvemos la foto
     * encontrada
     *
     * @param id La foto que daremos de alta en la BBDD
     * @param con Conexion a la BBDD
     * @return Devuelve 1 si la inserccion es correcta
     */
    @Override
    public Foto buscar(int id, Database con) {
        ResultSet rs = null;
        Foto fo = new Foto();

        /*
         * CONSULTA
         */
        String query
                = "select fotoId, nombre,"
                + " productoID, url"
                + " from fotoproducto"
                + " where fotoId = '" + id + "';";

        try {
            /*
             *Lanzo la consulta y el resultado lo guardo en un nuevo objeto 
             *Foto y lo devolvemos
             */
            rs = con.consultaSQL(query);
            while (rs.next()) {
                fo.setFotoID(id);
                fo.setProductoID(rs.getInt("productoID"));
                fo.setNombre(rs.getString("nombre"));
                fo.setUrl(rs.getString("url"));
            }
        } catch (SQLException E) {
            System.out.println("Excepcion SQL....: " + E.getMessage());
            System.out.println("Estado SQL.......: " + E.getSQLState());
            System.out.println("Código del Error.: " + E.getErrorCode());
        }

        return fo;
    }

    public List<Foto> listaPorProducto(int id, Database con) {
        int res = 0;
        ResultSet rs = null;
        ArrayList<Foto> fo = new ArrayList<>();

        String query = "SELECT fp.fotoID, fp.productoID, fp.nombre, fp.url, "
                + "p.productoID "
                + "FROM fotoproducto fp , producto p "
                + "WHERE fp.productoID = p.productoID "
                + "and p.productoID = '" + id + "';";

        try {
            /*
             *Lanzo la consulta y el resultado lo guardo en un nuevo objeto 
             *Foto y lo meto en la Lista
             */
            rs = con.consultaSQL(query);
            while (rs.next()) {
                fo.add(new Foto(
                        rs.getInt("fotoID"),
                        rs.getInt("productoID"),
                        rs.getString("nombre"),
                        rs.getString("url")));
            }
        } catch (SQLException E) {
            System.out.println("Excepcion SQL....: " + E.getMessage());
            System.out.println("Estado SQL.......: " + E.getSQLState());
            System.out.println("Código del Error.: " + E.getErrorCode());
        }

        return fo;
    }
}
