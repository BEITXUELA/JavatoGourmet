package datos;

import beans.Direccion;
import control.db.Database;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

/**
 * CAMPOS DEL POJO DIRECCION (para ayudarme en los SQLs)
 * -----------------------------------------------------
 * private int      direccionID;
 * private int      usuarioID;
 * private int      poblacionID;
 * private String   direccion;
 * private int      numero;
 * private String   bloque;
 * private String   escalera;
 * private String   piso;
 * private String   puerta;
 * private int      codPostal;
 * private int      tipoDireccion;
 * -----------------------------------------------------
 */

/**
 * Clase encargada de ejecutar consultas a la BBDD sobre la tabla de Direcciones
 *
 * @author Eduardo C. Araujo
 */
public class DireccionDAO implements I_DireccionDAO{

    /**
     * Encargado de solicitar un alta de un objeto direccion en la BBDD
     *
     * @param dir Objeto Direccion que queremos dar de alta en la BBDD
     * @param con Conexion a la BBDD
     * @return int Devuelve 1 si la inserccion es correcta
     */
    @Override
    public int alta(Direccion dir, Database con) {
        int res = 0;

        /*
         * CONSULTA DE INSERCCION
         */
        String query = "INSERT INTO direccion "
                + "(usuarioID,"
                + " poblacionID,"
                + " direccion,"
                + " numero,"
                + " bloque,"
                + " escalera,"
                + " piso,"
                + " puerta,"
                + " codPostal,"
                + " tipoDireccion)"
                + " VALUES"
                + " ('"
                + dir.getUsuarioID()+ "', '"
                + dir.getPoblacionID() + "', '"
                + "?, ?, ?, ?, ?, ?, ?, ?"
                + ");";
        
        try {
            /*
             * Para poder pasar un valor NULL a una columna de BBDD que lo
             * permita hay que hacer un PreparedStatement y usar setNull
             * donde corresponda.
             */
            PreparedStatement st = con.getConnection().prepareStatement(query);
            if (!"".equals(dir.getDireccion())) {
                st.setString(1, dir.getDireccion());
            } else {
                st.setNull(1, Types.VARCHAR);
            }
            if (dir.getNumero() != 0) {
                st.setInt(2, dir.getNumero());
            } else {
                st.setNull(2, Types.INTEGER);
            }
            if (!"".equals(dir.getBloque())) {
                st.setString(3, dir.getBloque());
            } else {
                st.setNull(3, Types.VARCHAR);
            }
            if (!"".equals(dir.getEscalera())) {
                st.setString(4, dir.getEscalera());
            } else {
                st.setNull(4, Types.VARCHAR);
            }
            if (!"".equals(dir.getPiso())) {
                st.setString(5, dir.getPiso());
            } else {
                st.setNull(5, Types.VARCHAR);
            }    
            if (!"".equals(dir.getPuerta())) {
                st.setString(6, dir.getPuerta());
            } else {
                st.setNull(6, Types.VARCHAR);
            }            
            if (dir.getCodPostal() != 0) {
                st.setInt(7, dir.getCodPostal());
            } else {
                st.setNull(7, Types.INTEGER);
            }    
            if (dir.getTipoDireccion() != 0) {
                st.setInt(8, dir.getTipoDireccion());
            } else {
                st.setNull(8, Types.INTEGER);
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
     * Encargado de devolver una coleccion de direcciones existentes
     *
     * @param con Conexion con la que hare la consulta
     * @return List Obtencion de direcciones 
     */
    @Override
    public List<Direccion> lista(Database con) {
        ResultSet rs = null;
        ArrayList<Direccion> list = new ArrayList<>();

        /*
         * CONSULTA DE DIRECCIONES EXISTENTES
         */
        String query
                = "SELECT direccionID,"
                + " usuarioID,"
                + " poblacionID,"
                + " direccion,"
                + " numero,"
                + " bloque,"
                + " escalera,"
                + " piso,"
                + " puerta,"
                + " codPostal,"
                + " tipoDireccion"
                + " FROM Direccion"
                + " ORDER BY direccion;";

        try {
            /*
             *Lanzo la consulta y el resultado lo guardo en un nuevo objeto 
             *Direccion y lo meto en la Lista que devolveremos
             */
            rs = con.consultaSQL(query);
            while (rs.next()) {
                list.add(new Direccion(
                        rs.getInt("direccionID"),
                        rs.getInt("usuarioID"),
                        rs.getInt("poblacionID"),
                        rs.getString("direccion"),
                        rs.getInt("numero"),
                        rs.getString("bloque"),
                        rs.getString("escalera"),
                        rs.getString("piso"),
                        rs.getString("puerta"),
                        rs.getInt("codPostal"),
                        rs.getInt("tipoDireccion")));
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
     * Encargado de eliminar una direccion mediante su ID
     *
     * @param id La direccion que daremos de baja en la BBDD
     * @param con Conexion a la BBDD
     * @return Devuelve 1 si la inserccion es correcta
     */
    @Override
    public int baja(int id, Database con) {
        int res = 0;

        /*
         * CONSULTA DE BORRADO
         */
        String query = "DELETE FROM direccion WHERE direccionID ='"
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
     * Encargado de modificar una direccion mediante un objeto pasado
     *
     * @param dir La direccion que modificaremos en la BBDD
     * @param con Conexion a la BBDD
     * @return Devuelve 1 si la inserccion es correcta
     */
    @Override
    public int modificar(Direccion dir, Database con) {
        int res = 0;

        /*
         * CONSULTA DE ACTUALIZACION
         */
        String query = "UPDATE direccion "
                + "SET usuarioID='" + dir.getUsuarioID() + "',"
                + " poblacionID='" + dir.getPoblacionID() + "',"
                + " direccion=?,"
                + " numero=?,"
                + " bloque=?,"
                + " escalera=?,"
                + " piso=?,"
                + " puerta=?,"
                + " codPostal=?,"
                + " tipoDireccion=?)"
                + " WHERE direccionID = '" + dir.getDireccionID() + "';";
        
        
        try {
            /*
             * Para poder pasar un valor NULL a una columna de BBDD que lo
             * permita hay que hacer un PreparedStatement y usar setNull
             * donde corresponda.
             */
            PreparedStatement st = con.getConnection().prepareStatement(query);
            if (!"".equals(dir.getDireccion())) {
                st.setString(1, dir.getDireccion());
            } else {
                st.setNull(1, Types.VARCHAR);
            }
            if (dir.getNumero() != 0) {
                st.setInt(2, dir.getNumero());
            } else {
                st.setNull(2, Types.INTEGER);
            }
            if (!"".equals(dir.getBloque())) {
                st.setString(3, dir.getBloque());
            } else {
                st.setNull(3, Types.VARCHAR);
            }
            if (!"".equals(dir.getEscalera())) {
                st.setString(4, dir.getEscalera());
            } else {
                st.setNull(4, Types.VARCHAR);
            }
            if (!"".equals(dir.getPiso())) {
                st.setString(5, dir.getPiso());
            } else {
                st.setNull(5, Types.VARCHAR);
            }    
            if (!"".equals(dir.getPuerta())) {
                st.setString(6, dir.getPuerta());
            } else {
                st.setNull(6, Types.VARCHAR);
            }            
            if (dir.getCodPostal() != 0) {
                st.setInt(7, dir.getCodPostal());
            } else {
                st.setNull(7, Types.INTEGER);
            }    
            if (dir.getTipoDireccion() != 0) {
                st.setInt(8, dir.getTipoDireccion());
            } else {
                st.setNull(8, Types.INTEGER);
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
     * Encargado de buscar una direccion mediante su ID, devolvemos la direccion
     * encontrada
     *
     * @param id La direccion que buscaremos en la BBDD
     * @param con Conexion a la BBDD
     * @return Devolvemos la Direccion encotrada
     */
    @Override
    public Direccion buscar(int id, Database con) {
        ResultSet rs = null;
        Direccion dir = new Direccion();

        /*
         * CONSULTA
         */
        String query
                = "SELECT direccionID,"
                + " usuarioID,"
                + " poblacionID,"
                + " direccion,"
                + " numero,"
                + " bloque,"
                + " escalera,"
                + " piso,"
                + " puerta,"
                + " codPostal,"
                + " tipoDireccion"
                + " FROM Direccion"
                + " WHERE direccionID = '" + id + "';";

        try {
            /*
             *Lanzo la consulta y el resultado lo guardo en un nuevo objeto 
             *Direccion y lo devolvemos
             */
            rs = con.consultaSQL(query);
            while (rs.next()) {
                dir.setDireccionID(rs.getInt("direccionID"));
                dir.setUsuarioID(rs.getInt("usuarioID"));
                dir.setPoblacionID(rs.getInt("poblacionID"));
                dir.setDireccion(rs.getString("direccion"));
                dir.setNumero(rs.getInt("numero"));
                dir.setBloque(rs.getString("bloque"));
                dir.setEscalera(rs.getString("escalera"));
                dir.setPiso(rs.getString("piso"));
                dir.setPuerta(rs.getString("puerta"));
                dir.setCodPostal(rs.getInt("codPostal"));
                dir.setTipoDireccion(rs.getInt("tipoDireccion"));
            }
        } catch (SQLException E) {
            System.out.println("Excepcion SQL....: " + E.getMessage());
            System.out.println("Estado SQL.......: " + E.getSQLState());
            System.out.println("Código del Error.: " + E.getErrorCode());
        }

        return dir;
    }
    
    public List<String> direcionesFacturacion(int usuarioID, Database con) {
       List<String> list = new ArrayList<>();
       ResultSet rs = null;

        /*
         * CONSULTA DE DIRECCIONES de Facturacion
         */
        String query
                = "SELECT d.direccion, d.numero, d.bloque, d.escalera, "
                + "d.piso, d.puerta, d.codPostal, pob.nombre, pro.nombre "
                + "FROM direccion d, usuario u, cliente c, "
                + "direccion_has_tipodireccion dt, tipodireccion td, "
                + "poblacion pob, provincia pro "
                + "WHERE u.usuarioID = d.usuarioID "
                + "AND u.usuarioID = c.usuarioID "
                + "AND d.direccionID = dt.direccionID "
                + "AND dt.tipoDireccionID = td.tipoDireccionID "
                + "AND pob.poblacionID = d.poblacionID "
                + "AND pro.provinciaID = pob.provinciaID "
                + "AND td.tipoDireccionID = '1'"
                + "AND u.usuarioID = '" + usuarioID + "'";

        try {
            /*
             *Lanzo la consulta y el resultado lo guardo en un nuevo objeto 
             *Direccion y lo meto en la Lista que devolveremos
             */
            
            String direccion = "";
            String numero = "";
            String bloque = "";
            String escalera = "";
            String piso = "";
            String puerta = "";
            String codPostal = "";
            String pobNombre = "";
            String proNombre = "";
            
            rs = con.consultaSQL(query);
            while (rs.next()) {
                
                if (rs.getString("d.direccion")!= null) direccion = rs.getString("d.direccion");
                if (rs.getInt("d.numero") > 0) numero = " " + String.valueOf(rs.getInt("d.numero"));
                if (rs.getString("d.bloque")!= null) bloque = ", " + rs.getString("d.bloque");
                if (rs.getString("d.escalera")!= null) escalera = ", " + rs.getString("d.escalera");
                if (rs.getString("d.piso")!= null) piso = ", " + rs.getString("d.piso");
                if (rs.getString("d.puerta")!= null) puerta = ", " + rs.getString("d.puerta");
                if (rs.getInt("d.codPostal") > 0) codPostal = ", " + String.valueOf(rs.getInt("d.codPostal"));
                if (rs.getString("pob.nombre")!= null) pobNombre = ", " + rs.getString("pob.nombre");
                if (rs.getString("pro.nombre")!= null) proNombre = ", " + rs.getString("pro.nombre");

                list.add(direccion + numero + bloque + escalera + piso + puerta + codPostal + pobNombre + proNombre);
            }
        } catch (SQLException E) {
            System.out.println("Excepcion SQL....: " + E.getMessage());
            System.out.println("Estado SQL.......: " + E.getSQLState());
            System.out.println("Código del Error.: " + E.getErrorCode());
        }

        //Si no ha habido problemas devolvemos la Lista de la consulta
        return list;
    }
    public List<String> direcionesEntrega(int usuarioID, Database con) {
       List<String> list = new ArrayList<>();
       ResultSet rs = null;

        /*
         * CONSULTA DE DIRECCIONES de Facturacion
         */
        String query
                = "SELECT d.direccion, d.numero, d.bloque, d.escalera, "
                + "d.piso, d.puerta, d.codPostal, pob.nombre, pro.nombre "
                + "FROM direccion d, usuario u, cliente c, "
                + "direccion_has_tipodireccion dt, tipodireccion td, "
                + "poblacion pob, provincia pro "
                + "WHERE u.usuarioID = d.usuarioID "
                + "AND u.usuarioID = c.usuarioID "
                + "AND d.direccionID = dt.direccionID "
                + "AND dt.tipoDireccionID = td.tipoDireccionID "
                + "AND pob.poblacionID = d.poblacionID "
                + "AND pro.provinciaID = pob.provinciaID "
                + "AND td.tipoDireccionID = '2'"
                + "AND u.usuarioID = '" + usuarioID + "'";

        try {
            /*
             *Lanzo la consulta y el resultado lo guardo en un nuevo objeto 
             *Direccion y lo meto en la Lista que devolveremos
             */
            String direccion = "";
            String numero = "";
            String bloque = "";
            String escalera = "";
            String piso = "";
            String puerta = "";
            String codPostal = "";
            String pobNombre = "";
            String proNombre = "";
            
            rs = con.consultaSQL(query);
            while (rs.next()) {
                
                if (rs.getString("d.direccion")!= null) direccion = rs.getString("d.direccion");
                if (rs.getInt("d.numero") > 0) numero = " " + String.valueOf(rs.getInt("d.numero"));
                if (rs.getString("d.bloque")!= null) bloque = ", " + rs.getString("d.bloque");
                if (rs.getString("d.escalera")!= null) escalera = ", " + rs.getString("d.escalera");
                if (rs.getString("d.piso")!= null) piso = ", " + rs.getString("d.piso");
                if (rs.getString("d.puerta")!= null) puerta = ", " + rs.getString("d.puerta");
                if (rs.getInt("d.codPostal") > 0) codPostal = ", " + String.valueOf(rs.getInt("d.codPostal"));
                if (rs.getString("pob.nombre")!= null) pobNombre = ", " + rs.getString("pob.nombre");
                if (rs.getString("pro.nombre")!= null) proNombre = ", " + rs.getString("pro.nombre");

                list.add(direccion + numero + bloque + escalera + piso + puerta + codPostal + pobNombre + proNombre);
            }
        } catch (SQLException E) {
            System.out.println("Excepcion SQL....: " + E.getMessage());
            System.out.println("Estado SQL.......: " + E.getSQLState());
            System.out.println("Código del Error.: " + E.getErrorCode());
        }

        //Si no ha habido problemas devolvemos la Lista de la consulta
        return list;
    }
}
