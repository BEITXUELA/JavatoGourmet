package datos;

import beans.Producto;
import static control.date.TimeMachine.*;
import control.db.Database;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Permite acciones sobre la tabla Producto
 *
 * @author Alonso Gil López
 */
public class ProductoDAO implements I_ProductoDAO {

    /**
     * Encargado de solicitar un alta de un objeto Producto en la BBDD
     *
     * @param prod Objeto producto para dar de alta
     * @param con Conexion a la BBDD
     * @return int Devuelve 1 si la inserccion es correcta
     */
    @Override
    public int alta(Producto prod, Database con) {
        int res = 0;

        /*
         * CONSULTA DE INSERCION
         */
        //Inserccion de un nuevo producto
        //AUN NO SE TIENEN EN CUENTA CATEGORIAS NI FOTOS
        String query = "INSERT INTO producto (ivaID, referencia, nombre, "
                + "descripcion, precioBase, peso, fechaAlta, stock, isNovedad, isDeluxe) "
                + "VALUES ("
                + "'" + prod.getivaID() + "', "
                + "'" + prod.getReferencia() + "', "
                + "'" + prod.getNombre() + "', "
                + "'" + prod.getDescripcion() + "', "
                + "'" + prod.getPrecioBase() + "', "
                + "'" + prod.getPeso() + "', "
                + "?, "
                + "'" + prod.getStock() + "', "
                + "?, "
                + "?"
                + ");";

        try {
            /*
             * Para convertir valores booleanos de java a valores TINYINT de mySql
             * debemos usar una consulta preparada.
             */
            PreparedStatement st = con.getConnection().prepareStatement(query);
            st.setTimestamp(1, getCurrentTimeStamp());
            st.setBoolean(2, prod.isIsNovedad());
            st.setBoolean(3, prod.isIsDeluxe());

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
     * Encargado de devolver una coleccion de Producto existentes
     *
     * @param con Conexion con la que hare la consulta
     * @return List Obtencion de Productos
     */
    @Override
    public List<Producto> lista(Database con) {
        ResultSet rs = null;
        ArrayList<Producto> pro = new ArrayList<>();

        /*
         * CONSULTA DE PRODUCTOS EXISTENTES
         */
        String query
                = "SELECT p.productoID, p.ivaID, p.referencia, p.nombre, "
                + "p.descripcion, p.precioBase, p.peso, p.fechaAlta, p.stock, p.isNovedad, p.isDeluxe "
                + "FROM producto p";

        try {
            /*
             *Lanzo la consulta y el resultado lo guardo en un nuevo objeto
             *Producto y lo meto en la Lista
             */
            rs = con.consultaSQL(query);
            while (rs.next()) {
                pro.add(new Producto(
                        rs.getInt("p.productoID"),
                        rs.getInt("p.ivaID"),
                        rs.getInt("p.referencia"),
                        rs.getString("p.nombre"),
                        rs.getString("p.descripcion"),
                        rs.getDouble("p.precioBase"),
                        rs.getDouble("p.peso"),
                        getTimeStampFromDB(rs.getTimestamp("p.fechaAlta")),
                        rs.getInt("p.stock"),
                        rs.getBoolean("p.isNovedad"),
                        rs.getBoolean("p.isDeluxe")));
            }
        } catch (SQLException E) {
            System.out.println("Excepcion SQL....: " + E.getMessage());
            System.out.println("Estado SQL.......: " + E.getSQLState());
            System.out.println("Código del Error.: " + E.getErrorCode());
        }

        //Si no ha habido problemas devolvemos la Lista de la consulta
        return pro;
    }

    /**
     * Encargado de eliminar un producto mediante su ID
     *
     * @param id El producto que daremos de baja en la BBDD
     * @param con Conexion a la BBDD
     * @return Devuelve 1 si la inserccion es correcta
     */
    @Override
    public int baja(int id, Database con) {
        int res = 0;

        /*
         * CONSULTA DE BORRADO
         */
        //Borrado en dos pasos (Usuario y Cliente)
        String query = "DELETE FROM producto WHERE productoID ='"
                + id + "';";

        res = new UsuarioDAO().baja(id, con);

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
     * Encargado de modificar un producto mediante un objeto pasado
     *
     * @param pro El producto que modificaremos en la BBDD
     * @param con Conexion a la BBDD
     * @return Devuelve 1 si la consulta es correcta
     */
    @Override
    public int modificar(Producto pro, Database con) {
        int res = 0;

        /*
         * CONSULTA DE ACTUALIZACION
         */
        String query = "UPDATE producto "
                + " SET "
                + "ivaID = '" + pro.getivaID() + "', "
                + "referencia = '" + pro.getReferencia() + "', "
                + "nombre = '" + pro.getNombre() + "', "
                + "descripcion = '" + pro.getDescripcion() + "', "
                + "precioBase = '" + pro.getPrecioBase() + "', "
                + "peso = '" + pro.getPeso() + "', "
                + "stock = '" + pro.getStock() + "', "
                + "isNovedad =  ?, "
                + "isDeluxe =   ? "
                + " WHERE productoID = '" + pro.getProductoID() + "';";

        try {
            /*
             * Para convertir valores booleanos de java a valores TINYINT de mySql
             * debemos usar una consulta preparada.
             */
            PreparedStatement st = con.getConnection().prepareStatement(query);
            st.setBoolean(1, pro.isIsNovedad());
            st.setBoolean(2, pro.isIsDeluxe());

            //Lanzo la consulta de actualizacion
            res = con.updateSQL(st);
        } catch (SQLException E) {
            System.out.println("Excepcion SQL....: " + E.getMessage());
            System.out.println("Estado SQL.......: " + E.getSQLState());
            System.out.println("Código del Error.: " + E.getErrorCode());
        }
        return res;
    }

    /**
     * Encargado de buscar un producto mediante su ID, devolvemos el producto
     * encontrado
     *
     * @param id El producto que buscaremos en la BBDD
     * @param con Conexion a la BBDD
     * @return Devolvemos el producto encontrado
     */
    @Override
    public Producto buscar(int id, Database con) {
        ResultSet rs = null;
        Producto pro = new Producto();

        /*
         * CONSULTA
         */
        String query
                = "SELECT p.productoID, p.ivaID, p.referencia, p.nombre, "
                + "p.descripcion, p.precioBase, p.peso, p.fechaAlta, p.stock, p.isNovedad, p.isDeluxe "
                + "FROM producto p "
                + "WHERE ProductoID = '" + id + "';";

        try {
            /*
             *Lanzo la consulta y el resultado lo guardo en un nuevo objeto
             *Producto y lo devolvemos
             */
            rs = con.consultaSQL(query);
            while (rs.next()) {
                pro.setProductoID(id);
                pro.setivaID(rs.getInt("ivaID"));
                pro.setReferencia(rs.getInt("p.referencia"));
                pro.setNombre(rs.getString("p.nombre"));
                pro.setDescripcion(rs.getString("p.descripcion"));
                pro.setPrecioBase(rs.getDouble("p.precioBase"));
                pro.setPeso(rs.getDouble("p.peso"));
                pro.setFechaAlta(rs.getDate("p.fechaAlta"));
                pro.setStock(rs.getInt("p.stock"));
                pro.setIsNovedad(rs.getBoolean("isNovedad"));
                pro.setIsDeluxe(rs.getBoolean("p.isDeluxe"));
            }
        } catch (SQLException E) {
            System.out.println("Excepcion SQL....: " + E.getMessage());
            System.out.println("Estado SQL.......: " + E.getSQLState());
            System.out.println("Código del Error.: " + E.getErrorCode());
        }

        return pro;
    }

    /**
     * Encargado de devolver un listado de productos pertenecientes a la
     * categoria pasada por argumento
     *
     * @param cat El ID de la categoria a buscar
     * @param con La conexion a la BBDD
     * @return List Listado de productos obtenidos
     */
    public List<Producto> listaPorCategoria(int cat, Database con) {

        int res = 0;
        ResultSet rs = null;
        ArrayList<Producto> pro = new ArrayList<>();

        String query = "SELECT p.productoID, p.ivaID, p.referencia, p.nombre, "
                + "p.descripcion, p.precioBase, p.peso, p.fechaAlta, p.stock, p.isNovedad, p.isDeluxe, "
                + "pc.categoriaID, f.url "
                + "FROM producto p, producto_has_categoria pc, fotoProducto f "
                + "where p.productoID = pc.productoID AND p.productoID = f.productoID "
                + "AND pc.categoriaID = '" + cat + "'"
                + "GROUP BY p.nombre";

        try {
            /*
             *Lanzo la consulta y el resultado lo guardo en un nuevo objeto
             *Producto y lo meto en la Lista
             */
            rs = con.consultaSQL(query);
            while (rs.next()) {
                pro.add(new Producto(
                        rs.getInt("p.productoID"),
                        rs.getInt("p.ivaID"),
                        rs.getInt("p.referencia"),
                        rs.getString("p.nombre"),
                        rs.getString("p.descripcion"),
                        rs.getDouble("p.precioBase"),
                        rs.getDouble("p.peso"),
                        getTimeStampFromDB(rs.getTimestamp("p.fechaAlta")),
                        rs.getInt("p.stock"),
                        rs.getBoolean("p.isNovedad"),
                        rs.getBoolean("p.isDeluxe"),
                        rs.getString("f.url")));
            }
        } catch (SQLException E) {
            System.out.println("Excepcion SQL....: " + E.getMessage());
            System.out.println("Estado SQL.......: " + E.getSQLState());
            System.out.println("Código del Error.: " + E.getErrorCode());
        }

        return pro;
    }

    /**
     * Encargado de devolver un listado de productos que contengan en su
     * denominacion o descripcion el patron de texto pasado por argumento
     *
     * @param filtro El texto que usaremos como patron para encontrar productos
     * @param con La conexion a la BBDD
     * @return List Listado de productos obtenidos
     */
    public List<Producto> listaPorFiltro(String filtro, Database con) {

        int res = 0;
        ResultSet rs = null;
        ArrayList<Producto> pro = new ArrayList<>();

        String query = "SELECT p.productoID, p.ivaID, p.referencia, p.nombre, "
                + "p.descripcion, p.precioBase, p.peso, p.fechaAlta, p.stock, p.isNovedad, p.isDeluxe, "
                + "pc.categoriaID, f.url "
                + "FROM producto p, producto_has_categoria pc, fotoProducto f "
                + "where p.productoID = pc.productoID AND p.productoID = f.productoID "
                + "AND (p.nombre LIKE '%" + filtro + "%' OR p.descripcion LIKE '%" + filtro + "%') "
                + "GROUP BY p.nombre";

        try {
            /*
             *Lanzo la consulta y el resultado lo guardo en un nuevo objeto
             *Producto y lo meto en la Lista
             */
            rs = con.consultaSQL(query);
            while (rs.next()) {
                pro.add(new Producto(
                        rs.getInt("p.productoID"),
                        rs.getInt("p.ivaID"),
                        rs.getInt("p.referencia"),
                        rs.getString("p.nombre"),
                        rs.getString("p.descripcion"),
                        rs.getDouble("p.precioBase"),
                        rs.getDouble("p.peso"),
                        getTimeStampFromDB(rs.getTimestamp("p.fechaAlta")),
                        rs.getInt("p.stock"),
                        rs.getBoolean("p.isNovedad"),
                        rs.getBoolean("p.isDeluxe"),
                        rs.getString("f.url")));
            }
        } catch (SQLException E) {
            System.out.println("Excepcion SQL....: " + E.getMessage());
            System.out.println("Estado SQL.......: " + E.getSQLState());
            System.out.println("Código del Error.: " + E.getErrorCode());
        }

        return pro;
    }

    /**
     * Encargado de devolver todos los productos que esten marcados como Deluxe
     * @param con Conexion a la BBDD
     * @return List Lista de productos obtenidos
     */
    public List<Producto> listaDeluxe(Database con) {

        int res = 0;
        ResultSet rs = null;
        ArrayList<Producto> pro = new ArrayList<>();

        String query = "SELECT p.productoID, p.ivaID, p.referencia, p.nombre, "
                + "p.descripcion, p.precioBase, p.peso, p.fechaAlta, p.stock, p.isNovedad, p.isDeluxe, "
                + "pc.categoriaID, f.url "
                + "FROM producto p, producto_has_categoria pc, fotoProducto f "
                + "where p.productoID = pc.productoID AND p.productoID = f.productoID "
                + "AND p.isDeluxe = TRUE "
                + "GROUP BY p.nombre";

        try {
            /*
             *Lanzo la consulta y el resultado lo guardo en un nuevo objeto
             *Producto y lo meto en la Lista
             */
            rs = con.consultaSQL(query);
            while (rs.next()) {
                pro.add(new Producto(
                        rs.getInt("p.productoID"),
                        rs.getInt("p.ivaID"),
                        rs.getInt("p.referencia"),
                        rs.getString("p.nombre"),
                        rs.getString("p.descripcion"),
                        rs.getDouble("p.precioBase"),
                        rs.getDouble("p.peso"),
                        getTimeStampFromDB(rs.getTimestamp("p.fechaAlta")),
                        rs.getInt("p.stock"),
                        rs.getBoolean("p.isNovedad"),
                        rs.getBoolean("p.isDeluxe"),
                        rs.getString("f.url")));
            }
        } catch (SQLException E) {
            System.out.println("Excepcion SQL....: " + E.getMessage());
            System.out.println("Estado SQL.......: " + E.getSQLState());
            System.out.println("Código del Error.: " + E.getErrorCode());
        }

        return pro;
    }
}
