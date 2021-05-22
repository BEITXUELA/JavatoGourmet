package control.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Clase encargada de generar y devolver una conexion a la BBDD
 *
 * @author Eduardo C. Araujo
 */
public class Database {

    private Connection conn = null;

    /**
     * Constructor de la clase que inicia la conexion
     *
     * @param url URL de la BBDD a la cual conectamos
     * @param user_name Nombre de usuario de la BBDD
     * @param password Contraseña de l usuario de la BBDD
     */
    public Database(String url, String user_name, String password) {
        try {
            Class.forName("com.mysql.jdbc.Driver");

            this.conn = DriverManager.getConnection(url, user_name, password);

        } catch (ClassNotFoundException | SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * Devuelve una conexion a la BBDD
     *
     * @return Connection La conexion
     */
    public Connection getConnection() {
        return this.conn;
    }

    /**
     * Metodo especifico para lanzar Sentencias SELECT
     *
     * @param sql La consulta a ejecutar
     * @return ResultSet El resultset con los datos devueltos
     * @throws SQLException Devuelve Error si pasa algo con la ejecucion de la
     * consulta
     */
    public ResultSet consultaSQL(String sql) throws SQLException {
        Statement sta = conn.createStatement();
        return sta.executeQuery(sql);
    }

    /**
     * Metodo especifico para lanzar Sentencias INSERT, UPDATE o DELETE
     *
     * @param sql La consulta a ejecutar
     * @return int Devuelve el numero de filas afectadas
     * @throws SQLException Devuelve Error si pasa algo con la ejecucion de la
     * consulta
     */
    public int updateSQL(String sql) throws SQLException {
        Statement sta = conn.createStatement();
        return sta.executeUpdate(sql);
    }
    /**
     * SOBRECARGA
     * Util para cuando necestamos ejecutar Sentencias "Preparadas" de antemano
     * 
     * @param sta PreparedStatement que nos pasan listo para ejecutar
     * @return int Devuelve el numero de filas afectadas
     * @throws SQLException  Devuelve Error si pasa algo con la ejecucion de la
     * consulta
     */
    public int updateSQL(PreparedStatement sta) throws SQLException {
        return sta.executeUpdate();
    }    
}
