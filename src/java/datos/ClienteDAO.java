package datos;

import beans.Cliente;
import control.db.Database;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Permite acciones sobre la tabla Cliente
 *
 * @author Eduardo C. Araujo
 */
public class ClienteDAO implements I_ClienteDAO {

    /**
     * Encargado de solicitar un alta de un objeto Cliente en la BBDD
     *
     * @param cli Objeto cliente para dar de alta
     * @param con Conexion a la BBDD
     * @return int Devuelve 1 si la inserccion es correcta
     */
    @Override
    public int alta(Cliente cli, Database con) {
        int res = 0;

        /*
         * CONSULTA DE INSERCCION 
         */
        //Inserccion en dos pasos (Usuario y Cliente)
        res = new UsuarioDAO().alta(cli, con);
        
        String query2 = "INSERT INTO cliente (nif, nombre1, nombre2, "
                + "apellido1, apellido2, telefono1, telefono2, email1, email2, "
                + "alta, baja, isCerrado) "
                + "VALUES ('"
                + "'" + cli.getNif() + "', "
                + "'" + cli.getNombre1() + "', "
                + "'" + cli.getNombre2() + "', "
                + "'" + cli.getApellido1() + "', "
                + "'" + cli.getApellido2() + "', "
                + "'" + cli.getTelefono1() + "', "
                + "'" + cli.getTelefono2() + "', "
                + "'" + cli.getEmail1() + "', "
                + "'" + cli.getEmail2() + "', "
                + "'" + cli.getAlta() + "', "
                + "'" + cli.getBaja() + "', "
                + "'" + cli.isIsCerrado() + "', "
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
     * Encargado de devolver una coleccion de Cliente existentes
     *
     * @param con Conexion con la que hare la consulta
     * @return List Obtencion de Clientes
     */
    @Override
    public List<Cliente> lista(Database con) {
        ResultSet rs = null;
        ArrayList<Cliente> list = new ArrayList<>();

        /*
         * CONSULTA DE CLIENTES EXISTENTES
         * Tenemos que coger datos de Usuarios y Cliente (JOIN)
         */
        String query
                = "SELECT s.usuarioID, u.usuario, u.password, u.estadoUsuarioID, "
                + "s.nif, s.nombre1, s.nombre2, "
                + "s.apellido1, s.apellido2, s.telefono1, s.telefono2, s.email1, "
                + "s.email2, s.alta, s.baja, s.isCerrado "
                + "FROM cliente s "
                + "JOIN usuario u ON s.usuarioID = u.usuarioID";
        
        try {
            /*
             *Lanzo la consulta y el resultado lo guardo en un nuevo objeto 
             *Cliente y lo meto en la Lista
             */
            rs = con.consultaSQL(query);
            while (rs.next()) {
                list.add(new Cliente(
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
                        rs.getBoolean("s.isCerrado")));
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
     * Encargado de eliminar un cliente mediante su ID
     *
     * @param id El Cliente que daremos de baja en la BBDD
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
        String query = "DELETE FROM cliente WHERE usuarioID ='"
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
     * Encargado de modificar un cliente mediante un objeto pasado
     *
     * @param cli El cliente que modificaremos en la BBDD
     * @param con Conexion a la BBDD
     * @return Devuelve 1 si la consulta es correcta
     */
    @Override
    public int modificar(Cliente cli, Database con) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Encargado de buscar un cliente mediante su ID, devolvemos el cliente
     * encontrado
     *
     * @param id El cliente que buscaremos en la BBDD
     * @param con Conexion a la BBDD
     * @return Devolvemos el cliente encontrado
     */
    @Override
    public Cliente buscar(int id, Database con) {
        ResultSet rs = null;
        Cliente cli = new Cliente();

        /*
         * CONSULTA
         */
        String query
                = "SELECT s.usuarioID, u.estadoUsuarioID, u.usuario, u.password "
                + "s.nif, s.nombre1, s.nombre2, "
                + "s.apellido1, s.apellido2, s.telefono1, s.telefono2, s.email1, "
                + "s.email2, s.alta, s.baja, s.isCerrado "
                + "FROM cliente s "
                + "JOIN usuario u ON s.usuarioID = u.usuarioID "
                + "WHERE UsuarioID = '" + id + "';";
        
        try {
            /*
             *Lanzo la consulta y el resultado lo guardo en un nuevo objeto 
             *Usuario y lo devolvemos
             */
            rs = con.consultaSQL(query);
            while (rs.next()) {
                cli.setUsuarioID(id);
                cli.setUsuario(rs.getString("u.usuario"));
                cli.setPassword(rs.getString("u.password"));
                cli.setEstado(rs.getInt("u.estadoUsuarioID"));
                cli.setNif(rs.getString("s.nif"));
                cli.setNombre1(rs.getString("s.nombre1"));
                cli.setNombre2(rs.getString("s.nombre2"));
                cli.setApellido1(rs.getString("s.apellido1"));
                cli.setApellido2(rs.getString("s.apellido2"));
                cli.setApellido2(rs.getString("s.telefono1"));
                cli.setApellido2(rs.getString("s.telefono2"));
                cli.setApellido2(rs.getString("s.email1"));
                cli.setApellido2(rs.getString("s.email2"));
                cli.setAlta(rs.getDate("s.alta"));
                cli.setBaja(rs.getDate("s.baja"));
                cli.setIsCerrado(rs.getBoolean("s.isCerrado"));    
            }
        } catch (SQLException E) {
            System.out.println("Excepcion SQL....: " + E.getMessage());
            System.out.println("Estado SQL.......: " + E.getSQLState());
            System.out.println("Código del Error.: " + E.getErrorCode());
        }
        
        return cli;
    }
    
}
