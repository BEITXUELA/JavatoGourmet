package control;

import beans.Categoria;
import beans.Producto;
import control.db.Database;
import datos.CategoriaDAO;
import datos.ProductoDAO;
import java.io.IOException;
import java.util.Date;
import javax.servlet.RequestDispatcher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Eduardo C. Araujo
 */
public class ModificarProductoExpert extends HttpServlet {

    //Atributo privado que almacenara el ID del producto que estamos editando
    private int productoID = 0;

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

        Producto prd = new Producto();

        //Solicito conexion a BBDD
        Database db = (Database) getServletContext().getAttribute("db");

        if (null != request.getMethod()) {
            switch (request.getMethod()) {
                //Si la peticion es tipo GET significa que estamos viniendo de listaProductos.jsp
                case "GET":
                    //Solicitd de modificacion de un ID que me viene por GET
                    productoID = Integer.parseInt(request.getParameter("productoID"));
                    prd = new ProductoDAO().buscar(productoID, db);
                    request.setAttribute("Producto", prd);
                    // Paso el testigo al JSP para que "rellene" el formulario
                    RequestDispatcher view = request.getRequestDispatcher("modificarProducto.jsp");
                    view.forward(request, response);
                    break;
                //Si la peticion es tipo POST significa que estamos viniendo de modificarProducto.jsp
                case "POST":
                    int ivaID = 0;
                    int referencia = 0;
                    String nombre;
                    String descripcion;
                    double precioBase = 0.0;
                    double peso = 0.0;
                    Date fechaAlta;
                    int stock = 0;
                    boolean isNovedad = false;
                    boolean isDeluxe = false;

                    // Recojo los parametros y los asigno a las variables temporales
                    if (!"".equals(request.getParameter("ivaID"))) {
                        ivaID = Integer.parseInt(request.getParameter("ivaID"));
                    }
                    if (!"".equals(request.getParameter("referencia"))) {
                        referencia = Integer.parseInt(request.getParameter("referencia"));
                    }
                    nombre = (String) request.getParameter("nombre");
                    descripcion = (String) request.getParameter("descripcion");
                    if (!"".equals(request.getParameter("precioBase"))) {
                        precioBase = Double.parseDouble(request.getParameter("precioBase"));
                    }
                    if (!"".equals(request.getParameter("peso"))) {
                        peso = Double.parseDouble(request.getParameter("peso"));
                    }
                    if (!"".equals(request.getParameter("stock"))) {
                        stock = Integer.parseInt(request.getParameter("stock"));
                    }
                    if ("on".equals(request.getParameter("isNovedad"))) {
                        isNovedad = true;
                    }
                    if ("on".equals(request.getParameter("isDeluxe"))) {
                        isDeluxe = true;
                    }

                    //Paso los datos al objeto que pasaremos al DAO
                    prd.setProductoID(productoID);
                    prd.setivaID(ivaID);
                    prd.setReferencia(referencia);
                    prd.setNombre(nombre);
                    prd.setDescripcion(descripcion);
                    prd.setPrecioBase(precioBase);
                    prd.setPeso(peso);
                    prd.setStock(stock);
                    prd.setIsNovedad(isNovedad);
                    prd.setIsDeluxe(isDeluxe);

                    //Llamo al metodo alta del DAO
                    new ProductoDAO().modificar(prd, db);
                    // Redirecciono a Categorias.do para "pinte" de nuevo el listado
                    response.sendRedirect("productos.do");
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
