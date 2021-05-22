package datos;

import beans.TipoPago;
import control.db.Database;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase encargada de ejecutar consultas a la BBDD sobre la tabla de TipoPago
 *
 * @author Alonso Gil López
 */
public class TipoPagoDAO implements I_TipoPagoDAO {

    /**
     * Encargado de solicitar un alta de un objeto tipoPago en la BBDD
     *
     * @param tp El tipoPago que daremos de alta en la BBDD
     * @param con Conexion a la BBDD
     * @return int Devuelve 1 si la inserccion es correcta
     */
    @Override
    public int alta(TipoPago tp, Database con) {
        int res = 0;

        /*
         * CONSULTA DE INSERCCION
         */
        String query = "INSERT INTO tipopago "
                + "("
                + "tipoPagoID, "
                + "tipoPagoNombre, "
                + "tipoPagoIncremento "
                + " VALUES"
                + "'" + tp.getTipoPagoID() + "', "
                + "'" + tp.getNombre() + "', "
                + "'" + tp.getIncremento() + "');";

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
    public List<TipoPago> lista(Database con) {
        ResultSet rs = null;
        ArrayList<TipoPago> tip = new ArrayList<>();

        /*
         * CONSULTA DE TIPOS PAGOS EXISTENTES
         */
        String query = "SELECT "
                + "tipoPagoID, "
                + "tipoPagoNombre, "
                + "tipoPagoIncremento "
                + "FROM tipopago "
                + "ORDER BY tipoPagoNombre";

        try {
            /*
             *Lanzo la consulta y el resultado lo guardo en un nuevo objeto 
             *TipoPago y lo meto en la Lista
             */
            rs = con.consultaSQL(query);
            while (rs.next()) {
                tip.add(new TipoPago(
                        rs.getInt("tipoPagoID"),
                        rs.getString("tipoPagoNombre"),
                        rs.getInt("tipoPagoIncremento")));

            }
        } catch (SQLException E) {
            System.out.println("Excepcion SQL....: " + E.getMessage());
            System.out.println("Estado SQL.......: " + E.getSQLState());
            System.out.println("Código del Error.: " + E.getErrorCode());
        }

        //Si no ha habido problemas devolvemos la Lista de la consulta
        return tip;
    }

    /**
     * Encargado de eliminar un tipoPago mediante su ID
     *
     * @param id El tipoPago que daremos de baja en la BBDD
     * @param con Conexion a la BBDD
     * @return Devuelve 1 si la inserccion es correcta
     */
    @Override
    public int baja(int id, Database con) {

        int res = 0;

        /*
         * CONSULTA DE BORRADO
         */
        String query = "DELETE FROM tipopago WHERE tipoPagoID";

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
     * Encargado de modificar un tipoPago mediante un objeto pasado
     *
     * @param tp El tipoPago que modificaremos en la BBDD
     * @param con Conexion a la BBDD
     * @return Devuelve 1 si la inserccion es correcta
     */
    @Override
    public int modificar(TipoPago tp, Database con) {

        int res = 0;

        /*
         * CONSULTA DE ACTUALIZACION
         */
        String query = "UPDATE tipopago SET tipoPagoNombre='" + tp.getNombre()
                + "', tipoPagoIncremento='" + tp.getIncremento()
                + "' WHERE tipoPagoID='" + tp.getTipoPagoID()
                + "'";
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

    /**
     * Encargado de buscar un tipoPago mediante su ID, devolvemos el tipoPago
     * encontrada
     *
     * @param id El tipoPago que buscaremos en la BBDD
     * @param con Conexion a la BBDD
     * @return Devuelve 1 si la inserccion es correcta
     */
    @Override
    public TipoPago buscar(int id, Database con) {

        ResultSet rs = null;
        TipoPago tp = new TipoPago();

        /*
         * CONSULTA
         */
        String query = "select tipoPagoID, tipoPagoNombre,"
                + "tipoPagoIncremento "
                + "from tipopago"
                + "where tipoPagoID = '" + id + "';";

        try {
            /*
             *Lanzo la consulta y el resultado lo guardo en un nuevo objeto 
             *tipoPago y lo devolvemos
             */
            rs = con.consultaSQL(query);
            while (rs.next()) {
                tp.setTipoPagoID(id);
                tp.setNombre(rs.getString("tipoPagoNombre"));
                tp.setIncremento(rs.getInt("tipoPagoIncremento"));
                
            }
        } catch (SQLException E) {
            System.out.println("Excepcion SQL....: " + E.getMessage());
            System.out.println("Estado SQL.......: " + E.getSQLState());
            System.out.println("Código del Error.: " + E.getErrorCode());
        }

        return tp;
    }

}


