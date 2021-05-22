package control;

import beans.Cesta;
import beans.TipoEnvio;
import beans.TipoPago;
import control.db.Database;
import datos.DireccionDAO;
import datos.TipoEnvioDAO;
import datos.TipoPagoDAO;
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
 * @author Eduardo C. Araujo
 */
public class ProcesoCompraExpert extends HttpServlet {

    /**
     * Experto para gestionar el proceso de venta desde la cesta que el cliente
     * ha creado hasta la generacion del pedido despues de pasar por la peticion
     * de datos correspndientes
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        //Si no hay sesion activa, nos vamos a login y vuelta a empezar
        if (session != null) {
            //Solicito conexion a BBDD
            Database db = (Database) getServletContext().getAttribute("db");
            RequestDispatcher view;

            if (null != request.getMethod()) {
                switch (request.getMethod()) {
                    //Si la peticion es tipo GET
                    case "GET":
                        if (null != request.getParameter("p")) {
                            switch (Integer.parseInt(request.getParameter("p"))) {
                                //Primer paso de la compra, pedimos datos de cliente
                                case 1:
                                    Cesta cesta = (Cesta) session.getAttribute("cesta");
                                    //Sacamos las direcciones de entrega del usuario
                                    List<String> dirEntrega = new DireccionDAO().direcionesEntrega(cesta.getUsuarioID(), db);
                                    session.setAttribute("dirEntrega", dirEntrega);

                                    //Sacamos las direcciones de facturacion del usuario
                                    List<String> dirFacturacion = new DireccionDAO().direcionesFacturacion(cesta.getUsuarioID(), db);
                                    session.setAttribute("dirFacturacion", dirFacturacion);

                                    // Paso el testigo al JSP para que "rellene" el formulario
                                    view = request.getRequestDispatcher("datosCliente.jsp");
                                    view.forward(request, response);
                                    break;
                                //Segundo paso de la compra, pedimos los datos pago y entrega
                                case 2:
                                    //Sacamos un listado con los tipos de pago disponibles
                                    List<TipoPago> tipoPago = new TipoPagoDAO().lista(db);
                                    session.setAttribute("tipoPago", tipoPago);

                                    //Sacamos un listado con los tipos de envio que existen
                                    List<TipoEnvio> tipoEnvio = new TipoEnvioDAO().lista(db);
                                    session.setAttribute("tipoEnvio", tipoEnvio);

                                    // Paso el testigo al JSP para que "rellene" el formulario
                                    view = request.getRequestDispatcher("datosPagoEnvio.jsp");
                                    view.forward(request, response);
                                    break;
                                //Tercer paso de la compra, simulamos la pasarela de pago
                                case 3:
                                    // Paso el testigo al JSP para que "rellene" el formulario
                                    view = request.getRequestDispatcher("pasarela.jsp");
                                    view.forward(request, response);
                                    break;
                                //Cuarto paso de la compra, simulamos la finalizacion de la compra
                                case 4:
                                    //Eliminamos la cesta de la sesion
                                    session.setAttribute("cesta", null);
                                    session.setAttribute("Items", null);
                                    session.setAttribute("itemsCount", null);
                                    session.setAttribute("totalCesta", null);
                                    // Paso el testigo al JSP para que "rellene" el formulario
                                    view = request.getRequestDispatcher("compraFinalizada.jsp");
                                    view.forward(request, response);
                                    break;
                            }
                        }
                        break;
                    //Si la peticion es tipo POST
                    case "POST":
                        break;
                }
            }
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
