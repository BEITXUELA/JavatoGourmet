package control.db;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Esta clase sera ejecutada al comienzo de la carga de la aplicacion y sera la
 * encargada de crear y destruir la conexion a la BBDD
 *
 * @author Eduardo C. Araujo
 */
public class JavatoServletContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent event) {
        ServletContext sc = event.getServletContext();

        /**
         * Recojo los valores de los parametros de contexto definidos en WEB.XML
         */
        String url = sc.getInitParameter("url");
        String user_name = sc.getInitParameter("user_name");
        String password = sc.getInitParameter("password");
        String database = sc.getInitParameter("database");
        
        /**
         * Instanciamos un objeto Database de la cual sacaremos la conexion
         */
        Database db = new Database(url + database, user_name, password);
        
        /**
         * Pasamos el objeto creado a un nuevo atributo de Contecto
         */
        sc.setAttribute("db", db);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        //En principio no se hace nada al acabar la aplicacion ya que ella misma
        //tira las conexiones existentes
    }

}
