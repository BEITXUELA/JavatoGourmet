package control;

import beans.Categoria;
import control.db.Database;
import datos.CategoriaDAO;
import java.io.IOException;
import javax.servlet.RequestDispatcher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Eduardo C. Araujo
 */
public class ModificarCategoriaExpert extends HttpServlet {

    //Atributo privado que almacenara el ID de la categoria que estamos editando
    private int catId = 0;

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

        //Solicito conexion a BBDD
        Database db = (Database) getServletContext().getAttribute("db");

        if (null != request.getMethod())
        switch (request.getMethod()) {
            //Si la peticion es tipo GET significa que estamos viniendo de listaCategorias.jsp
            case "GET":
                //Solicitd de modificacion de un ID que me viene por GET
                catId = Integer.parseInt(request.getParameter("categoriaID"));
                cat = new CategoriaDAO().buscar(catId, db);
                request.setAttribute("Categoria", cat);
                // Paso el testigo al JSP para que "rellene" el formulario
                RequestDispatcher view = request.getRequestDispatcher("modificarCategoria.jsp");
                view.forward(request, response);
                break;
            //Si la peticion es tipo POST significa que estamos viniendo de modificarCategoria.jsp
            case "POST":
                String nombre;
                int catPadre = 0;
                // Recojo los parametros y los asigno al objeto
                nombre = (String) request.getParameter("nombre");
                if (!"".equals(request.getParameter("catPadre"))) {
                    catPadre = Integer.parseInt(request.getParameter("catPadre"));
                }   //Guardo en el nuevo objeto
                cat.setCategoriaID(catId);
                cat.setNombre(nombre);
                cat.setCategoriaCategoriaID(catPadre);
                //Llamo al metodo alta del DAO
                new CategoriaDAO().modificar(cat, db);
                // Redirecciono a Categorias.do para "pinte" de nuevo el listado
                response.sendRedirect("categorias.do");
                break;
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
