package control;

import beans.Foto;
import control.db.Database;
import datos.CategoriaDAO;
import datos.FotoDAO;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Alonso Gil López
 */
public class ModificarFotoExpert extends HttpServlet {

    //Atributo privado que almacenara el ID de la foto que estamos editando
    private int fotoId = 0;

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

        //Solicito conexion a BBDD
        Database db = (Database) getServletContext().getAttribute("db");

        if (null != request.getMethod()) {
            switch (request.getMethod()) {
                //Si la peticion es tipo GET significa que estamos viniendo de listaFotos.jsp
                case "GET":
                    //Solicitd de modificacion de un ID que me viene por GET
                    fotoId = Integer.parseInt(request.getParameter("fotoID"));
                    fot = new FotoDAO().buscar(fotoId, db);
                    request.setAttribute("Foto", fot);
                    // Paso el testigo al JSP para que "rellene" el formulario
                    RequestDispatcher view = request.getRequestDispatcher("modificarFoto.jsp");
                    view.forward(request, response);
                    break;
                //Si la peticion es tipo POST significa que estamos viniendo de modificarFoto.jsp
                case "POST":
                    String nombre;
                    int productoID = 0;
                    String url;
                    
                    // Recojo los parametros y los asigno al objeto
                    nombre = (String) request.getParameter("nombre");
                    url = (String) request.getParameter("url");
                    if (!"".equals(request.getParameter("productoID"))) {
                        productoID = Integer.parseInt(request.getParameter("productoID"));
                    }   

                    //Guardo en el nuevo objeto
                    fot.setFotoID(fotoId);
                    fot.setProductoID(productoID);
                    fot.setNombre(nombre);
                    fot.setUrl(url);
                    
                    //Llamo al metodo alta del DAO
                    new FotoDAO().modificar(fot, db);
                    
                    // Redirecciono a Categorias.do para "pinte" de nuevo el listado
                    response.sendRedirect("fotos.do");
                    break;
            }
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

