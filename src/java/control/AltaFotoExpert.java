
package control;

import beans.Foto;
import control.db.Database;
import datos.FotoDAO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Alonso Gil López
 */

 public class AltaFotoExpert extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Foto fot = new Foto();
        
        int fotoID;
        int productoID;
        String nombre;
        String url;

        // Recojo los parametros y los asigno al objeto
        productoID = Integer.parseInt (request.getParameter("productoID"));
        nombre = (String) request.getParameter("nombre");
        url = (String) request.getParameter("url");
        
        if (!"".equals(request.getParameter("productoID"))) {
            productoID = Integer.parseInt(request.getParameter("productoID")); 
        }
        
        fot.setProductoID(productoID);
        fot.setNombre(nombre);
        fot.setUrl(url);
        

        //Solicito conexion a BBDD
        Database db = (Database) getServletContext().getAttribute("db");

        //Llamo al metodo alta del DAO
        new FotoDAO().alta(fot, db);
        
        // Redirecciono a Fotos.do para que "pinte" de nuevo el listado
        response.sendRedirect("fotos.do");
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

