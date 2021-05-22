package datos;

import beans.Staff;
import control.db.Database;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Permite acciones sobre la tabla Staff
 *
 * @author Eduardo C. Araujo
 */
public class StaffDAO implements I_StaffDAO {

    /**
     * Encargado de solicitar un alta de un objeto staff en la BBDD
     *
     * @param sta El staff que daremos de alta en la BBDD
     * @param con Conexion a la BBDD
     * @return int Devuelve 1 si la inserccion es correcta
     */
    @Override
    public int alta(Staff sta, Database con) {
        int res = 0;

        /*
         * CONSULTA DE INSERCCION 
         */
        //Inserccion en dos pasos (Usuario y Staff)
        res = new UsuarioDAO().alta(sta, con);
        
        String query2 = "INSERT INTO staff (rolID, nif, nombre1, nombre2, "
                + "apellido1, apellido2, telefono1, telefono2, email1, email2, "
                + "alta, baja, isCerrado) "
                + "VALUES ('" + sta.getRolID() + "', "
                + "'" + sta.getNif() + "', "
                + "'" + sta.getNombre1() + "', "
                + "'" + sta.getNombre2() + "', "
                + "'" + sta.getApellido1() + "', "
                + "'" + sta.getApellido2() + "', "
                + "'" + sta.getTelefono1() + "', "
                + "'" + sta.getTelefono2() + "', "
                + "'" + sta.getEmail1() + "', "
                + "'" + sta.getEmail2() + "', "
                + "'" + sta.getAlta() + "', "
                + "'" + sta.getBaja() + "', "
                + "'" + sta.isIsCerrado() + "', "
                + ");";
        
        try {

            //Lanzo las consulta de inserccion
            if (res == 1) {
                res = con.updateSQL(query2);
            }
            return res;
        } catch (SQLException E) {
            System.out.println("Excepcion SQL....: " + E.getMessage());
            System.out.println("Estado SQL.......: " + E.getSQLState());
            System.out.println("Código del Error.: " + E.getErrorCode());
            return res;
        }
    }

    /**
     * Encargado de devolver una coleccion de staff existentes
     *
     * @param con Conexion con la que hare la consulta
     * @return List Obtencion de staffs
     */
    @Override
    public List<Staff> lista(Database con) {
        ResultSet rs = null;
        ArrayList<Staff> list = new ArrayList<>();

        /*
         * CONSULTA DE STAFF EXISTENTES
         * Tenemos que coger datos de Usuarios y Staff (JOIN)
         */
        String query
                = "SELECT s.usuarioID, u.usuario, u.password, u.estadoUsuarioID, "
                + "s.rolID, s.nif, s.nombre1, s.nombre2, "
                + "s.apellido1, s.apellido2, s.telefono1, s.telefono2, s.email1, "
                + "s.email2, s.alta, s.baja, s.isCerrado "
                + "FROM staff s "
                + "JOIN usuario u ON s.usuarioID = u.usuarioID";
        
        try {
            /*
             *Lanzo la consulta y el resultado lo guardo en un nuevo objeto 
             *Staff y lo meto en la Lista
             */
            rs = con.consultaSQL(query);
            while (rs.next()) {
                list.add(new Staff(
                        rs.getInt("s.usuarioID"),
                        rs.getString("u.usuario"),
                        rs.getString("u.password"),
                        rs.getInt("u.estadoUsuarioID"),
                        rs.getString("s.nif"),
                        rs.getString("s.nombre1"),
                        rs.getString("s.nombre2"),
                        rs.getString("s.apellido1"),
                        rs.getString("s.apellido2"),
                        rs.getString("s.telefono1"),
                        rs.getString("s.telefono2"),
                        rs.getString("s.email1"),
                        rs.getString("s.email2"),
                        rs.getDate("s.alta"),
                        rs.getDate("s.baja"),
                        rs.getBoolean("s.isCerrado"),
                        rs.getInt("s.rolID")));
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
     * Encargado de eliminar un staff mediante su ID
     *
     * @param id El staff que daremos de baja en la BBDD
     * @param con Conexion a la BBDD
     * @return Devuelve 1 si la inserccion es correcta
     */
    @Override
    public int baja(int id, Database con) {
        int res = 0;

        /*
         * CONSULTA DE BORRADO
         */
        //Borrado en dos pasos (Usuario y Staff)
        String query = "DELETE FROM staff WHERE usuarioID ='"
                + id + "';";
        
        res = new UsuarioDAO().baja(id, con);
        
        try {
            //Lanzo la consulta de borrado
            if (res == 1) {
                res = con.updateSQL(query);
            }
            return res;
        } catch (SQLException E) {
            System.out.println("Excepcion SQL....: " + E.getMessage());
            System.out.println("Estado SQL.......: " + E.getSQLState());
            System.out.println("Código del Error.: " + E.getErrorCode());
            return res;
        }
    }

    /**
     * Encargado de modificar un staff mediante un objeto pasado
     *
     * @param sta El staff que modificaremos en la BBDD
     * @param con Conexion a la BBDD
     * @return Devuelve 1 si la consulta es correcta
     */
    @Override
    public int modificar(Staff sta, Database con) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Encargado de buscar un staff mediante su ID, devolvemos el staff
     * encontrado
     *
     * @param id El staff que buscaremos en la BBDD
     * @param con Conexion a la BBDD
     * @return Devolvemos el Staff encontrado
     */
    @Override
    public Staff buscar(int id, Database con) {
        ResultSet rs = null;
        Staff sta = new Staff();

        /*
         * CONSULTA
         */
        String query
                = "SELECT s.usuarioID, u.estadoUsuarioID, u.usuario, u.password "
                + "s.rolID, s.nif, s.nombre1, s.nombre2, "
                + "s.apellido1, s.apellido2, s.telefono1, s.telefono2, s.email1, "
                + "s.email2, s.alta, s.baja, s.isCerrado "
                + "FROM staff s "
                + "JOIN usuario u ON s.usuarioID = u.usuarioID "
                + "WHERE UsuarioID = '" + id + "';";
        
        try {
            /*
             *Lanzo la consulta y el resultado lo guardo en un nuevo objeto 
             *Usuario y lo devolvemos
             */
            rs = con.consultaSQL(query);
            while (rs.next()) {
                sta.setUsuarioID(id);
                sta.setUsuario(rs.getString("u.usuario"));
                sta.setPassword(rs.getString("u.password"));
                sta.setEstado(rs.getInt("u.estadoUsuarioID"));
                sta.setRolID(rs.getInt("s.rolID"));
                sta.setNif(rs.getString("s.nif"));
                sta.setNombre1(rs.getString("s.nombre1"));
                sta.setNombre2(rs.getString("s.nombre2"));
                sta.setApellido1(rs.getString("s.apellido1"));
                sta.setApellido2(rs.getString("s.apellido2"));
                sta.setApellido2(rs.getString("s.telefono1"));
                sta.setApellido2(rs.getString("s.telefono2"));
                sta.setApellido2(rs.getString("s.email1"));
                sta.setApellido2(rs.getString("s.email2"));
                sta.setAlta(rs.getDate("s.alta"));
                sta.setBaja(rs.getDate("s.baja"));
                sta.setIsCerrado(rs.getBoolean("s.isCerrado"));    
            }
        } catch (SQLException E) {
            System.out.println("Excepcion SQL....: " + E.getMessage());
            System.out.println("Estado SQL.......: " + E.getSQLState());
            System.out.println("Código del Error.: " + E.getErrorCode());
        }
        
        return sta;
    }
    
}
