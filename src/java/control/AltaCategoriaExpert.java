package control;

import beans.Categoria;
import control.db.Database;
import datos.CategoriaDAO;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Eduardo C. Araujo
 */
public class AltaCategoriaExpert extends HttpServlet {

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

        Categoria cat = new Categoria();
        String nombre;
        int catPadre = 0;

        // Recojo los parametros y los asigno al objeto
        nombre = (String) request.getParameter("nombre");
        if (!"".equals(request.getParameter("catPadre"))) {
            catPadre = Integer.parseInt(request.getParameter("catPadre")); 
        }
        cat.setNombre(nombre);
        cat.setCategoriaCategoriaID(catPadre);
        

        //Solicito conexion a BBDD
        Database db = (Database) getServletContext().getAttribute("db");

        //Llamo al metodo alta del DAO
        new CategoriaDAO().alta(cat, db);
        
        // Redirecciono a Categorias.do para "pinte" de nuevo el listado
        response.sendRedirect("categorias.do");
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
