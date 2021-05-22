/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import beans.Categoria;
import beans.Producto;
import control.db.Database;
import datos.CategoriaDAO;
import datos.ProductoDAO;
import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author alonsit0
 */
public class ListadoExpert extends HttpServlet {

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
        //Conexion  a la BBDD
        Database db = (Database) getServletContext().getAttribute("db");

        //Listado de productos donde guardaremos los productos de la categoria.
        List<Producto> pro;

        if (null != request.getMethod()) {
            switch (request.getMethod()) {
                //Si la peticion es tipo GET significa que mostraremos listado por ID
                case "GET":
                    //El ID de la categoria con la que filtraremos el listado de productos
                    int categoria = Integer.parseInt(request.getParameter("cat"));

                    //Lista de productos filtrada
                    pro = new ProductoDAO().listaPorCategoria(categoria, db);

                    //Consulto la BBDD para obtener el objeto categoria y asi poder disponer
                    //del nombre
                    Categoria cat = new CategoriaDAO().buscar(categoria, db);

                    //Pasamos el nombre de categoria al JSP
                    request.setAttribute("Categoria", cat.getNombre());
                    //Pasamos el listado del productos al JSP
                    request.setAttribute("Productos", pro);

                    break;
                //Si la peticion llega por POST significa que buscaremos por un filtro
                case "POST":
                    //El filtro que el usuario ha introducido
                    String filtro = (String) request.getParameter("filtro");

                    //Lista de productos filtrada
                    pro = new ProductoDAO().listaPorFiltro(filtro, db);

                    //Pasamos de nuevo el filtro para mostrarlo en el titulo
                    request.setAttribute("Filtro", filtro);
                    //Pasamos el listado del productos al JSP
                    request.setAttribute("Productos", pro);

                    break;
            }
            // Paso el testigo al JSP
            RequestDispatcher view = request.getRequestDispatcher("categoria.jsp");
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
