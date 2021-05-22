package control;

import beans.Cesta;
import beans.Item;
import beans.Producto;
import beans.Usuario;
import control.date.TimeMachine;
import control.db.Database;
import datos.CestaDAO;
import datos.ItemDAO;
import datos.ProductoDAO;
import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author EduardoCarlos
 */
public class AltaCestaExpert extends HttpServlet {

    /**
     * Servlet especialista para el alta de nuevas cestas de la compra
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        //creamos la cesta
        Cesta cesta = new Cesta();
        //Producto y cantidad que nos pasan para agregar a la cesta
        int productoID;
        productoID = Integer.parseInt(request.getParameter("prd"));
        int cantidad;
        cantidad = Integer.parseInt(request.getParameter("cantidad"));

        //Solicito conexion a BBDD
        Database db = (Database) getServletContext().getAttribute("db");

        //Compruebo si existe alguna sesion activa
        if (session.getAttribute("currentSessionUser") != null) {

            //Compruebo si existe cesta
            if (session.getAttribute("cesta") == null) {;
                //Si no hay cesta creada
                if (session.getAttribute("cesta") == null) {

                    int cestaID;

                    //Usamos el objeto Usuario que hay en sesion para utilizar el ID
                    Usuario usuario = (Usuario) session.getAttribute("currentSessionUser");

                    cesta.setUsuarioID(usuario.getUsuarioID());
                    cesta.setFecha(TimeMachine.getCurrentTimeStamp());
                    cesta.setTotal(0);

                    //Llamo al metodo alta del DAO
                    cestaID = new CestaDAO().alta(cesta, db);

                    //completamos los datos de la cesta con el ID
                    cesta.setCestaID(cestaID);

                    //se la asignamos a la sesion
                    session.setAttribute("cesta", cesta);
                }
            } else {
                //La cesta esta en sesion por lo tanto se la asigno a un objeto local
                //para trabajar con ella
                cesta = (Cesta) session.getAttribute("cesta");
            }

            //INSERTAR LINEA DE CESTA
            Item item = new Item();
            Producto producto = new Producto();
            producto = new ProductoDAO().buscar(productoID, db);
          

            item.setCestaID(cesta.getCestaID());
            item.setProductoID(producto.getProductoID());
            item.setCantidad(cantidad);
            item.setPrecio(producto.getPrecioBase() * cantidad);

            new ItemDAO().alta(item, db);

            //Ahora recojo todos los items de esta cesta para mostrarlos en la vista
            List items = new ItemDAO().listaPorCesta(cesta.getCestaID(), db);
            session.setAttribute("Items", items);
            //Tambien voy incrementando un contador de lineas de cesta para mostrarlo en
            //el enlace de la cesta de la cabecera
            session.setAttribute("itemsCount", items.size());
            
            //Alamaceno el total de la cesta
            Double totalCesta = new CestaDAO().total(cesta.getCestaID(), db);
            session.setAttribute("totalCesta", totalCesta);

            // Redirecciono a la cesta para que se muestre que lo que hay
            response.sendRedirect(request.getHeader("referer") + "&enCesta=true");
        } else {
            //Asigno un atributo que le indique a acceso.jsp el motivo del reenvio
            request.setAttribute("errorSesion", "true");            
            RequestDispatcher view = request.getRequestDispatcher("acceso.jsp");
            view.forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
