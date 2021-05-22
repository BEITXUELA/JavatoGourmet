package control;

import beans.Foto;
import beans.Producto;
import control.db.Database;
import datos.FotoDAO;
import datos.ProductoDAO;
import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Experto para gestionar las fichas de los productos
 *
 * @author BEA Robledillo Gómez
 */
public class FichaProductoExpert extends HttpServlet {

    //Atributo privado que almacenara el ID de la ficha de producto
    private int prdID = 0;

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
        //Es un objeto de tipo Producto para luego pasarlo
        Producto prd;
        List<Foto> fotos;
        //Solicito conexion a BBDD
        Database db = (Database) getServletContext().getAttribute("db");

        //Solicitd de modificacion de un ID que me viene por GET
        prdID = Integer.parseInt(request.getParameter("prd"));
        prd = new ProductoDAO().buscar(prdID, db);
        request.setAttribute("Producto", prd);

        fotos = new FotoDAO().listaPorProducto(prdID, db);
        request.setAttribute("Fotos", fotos);

        // Paso el testigo al JSP para que "rellene" el formulario
        RequestDispatcher view = request.getRequestDispatcher("ficha.jsp");
        view.forward(request, response);

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
