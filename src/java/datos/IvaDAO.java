/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

import beans.Categoria;
import beans.IVA;
import beans.Usuario;
import control.db.Database;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase encargada de ejecutar consultas a la BBDD sobre la tabla de IVA
 *
 * @author Marino Calero
 */

public class IvaDAO implements I_IvaDAO{

    @Override
    public int alta(IVA iv, Database con) {
        
        int res = 0;

        /*
         * CONSULTA DE INSERCCION
         */
        String query = "INSERT INTO IVA "
                + "(ivaID,"
                + " nombre, "
                + " porcentaje)"
                + " VALUES"
                + " ("
                + iv.getIvaID()+ ", "
                + "'" + iv.getNombre()+ "', "
                + "'" + iv.getPorcentaje() + "');";

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

    @Override
    public List<IVA> lista(Database con) {
    ResultSet rs = null;
        ArrayList<IVA> list = new ArrayList<>();

       
        String query
                = "SELECT ivaD, nombre, porcentaje "
                + "FROM IVA";

        try {
            /*
             *Lanzo la consulta y el resultado lo guardo en un nuevo objeto 
             *Usuario y lo meto en la Lista
             */
            rs = con.consultaSQL(query);
            while (rs.next()) {
                list.add(new IVA(rs.getInt("ivaD"), rs.getString("nombre"), (int) rs.getDouble("porcentaje")));
            }
        } catch (SQLException E) {
            System.out.println("Excepcion SQL....: " + E.getMessage());
            System.out.println("Estado SQL.......: " + E.getSQLState());
            System.out.println("Código del Error.: " + E.getErrorCode());
        }

        //Si no ha habido problemas devolvemos la Lista de la consulta
        return list;
        
    }

    @Override
    public int baja(int id, Database con) {
      int res = 0;

        /*
         * CONSULTA DE BORRADO
         */
        String query = "DELETE FROM IVA WHERE ivaID ='"
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

    @Override
    public int modificar(IVA iv, Database con) {
          int res = 0;

        /*
         * CONSULTA DE ACTUALIZACION
         */
        String query = "UPDATE usuario "
                + " SET "
                + "usuario='" + iv.getNombre() + "', "
                + "estadoUsuarioID='" + iv.getPorcentaje() + "' "
                + " WHERE ivaID='" + iv.getIvaID() + "';";

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

    @Override
    public IVA buscar(int id, Database con) {
        ResultSet rs = null;
        IVA iv = new IVA();

        /*
         * CONSULTA
         */
        String query
                = "SELECT ivaID, nombre, porcentaje "
                + "FROM IVA "
                + "WHERE ivaID = '" + id + "';";

        try {
            /*
             *Lanzo la consulta y el resultado lo guardo en un nuevo objeto 
             *Usuario y lo devolvemos
             */
            rs = con.consultaSQL(query);
            while (rs.next()) {
                iv.setIvaID(id);
                iv.setNombre(rs.getString("nombre"));
                iv.setPorcentaje((int) rs.getDouble("porcenatje"));              
            }
        } catch (SQLException E) {
            System.out.println("Excepcion SQL....: " + E.getMessage());
            System.out.println("Estado SQL.......: " + E.getSQLState());
            System.out.println("Código del Error.: " + E.getErrorCode());
        }

        return iv;
    }
    
}
