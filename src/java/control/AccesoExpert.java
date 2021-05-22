package control;

import beans.Cliente;
import beans.Staff;
import control.db.Database;
import datos.ClienteDAO;
import datos.StaffDAO;
import java.io.IOException;
import java.util.ArrayList;
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
public class AccesoExpert extends HttpServlet {

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

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("currentSessionUser") == null) {
            /**
             * EL SIGUIENTE CODIGO NO ES MUY OPTIMO SI EXISTEN MUCHAS FILAS EN
             * CLIENTE O EN STAFF Como las clases Cliente y Staff heredan de
             * Usuario, recorreremos cada tabla (staff y cliente) y de este modo
             * cuando encontremos la coincidencia para la dupla usuario/password
             * ya sabremos de quien estamos hablando ¿?¿?
             */
            ArrayList<Staff> staffs;
            ArrayList<Cliente> clientes;

            boolean falloLogin = true;

            String usuario = (String) request.getParameter("usuario");
            String password = (String) request.getParameter("password");

            if (usuario != null || password != null) {
                //Solicito conexion a BBDD
                Database db = (Database) getServletContext().getAttribute("db");

                // Pido el listado de staff facilitando para ello la conexion a la BBDD.
                staffs = (ArrayList<Staff>) new StaffDAO().lista(db);
                // Pido el listado de clientes faacilitando para ello la conexion a la BBDD.
                clientes = (ArrayList<Cliente>) new ClienteDAO().lista(db);

                //Recorro los Staff y voy comprobando la dupla usuario/password
                for (Staff item : staffs) {
                    if (usuario.equals(item.getUsuario()) && password.equals(item.getPassword())) {
                        falloLogin = false;
                        //Si encuentro la pareja, inicio sesion
                        session = request.getSession(true);
                        //Añado un atributo de sesion que contiene el nombre de usuario
                        session.setAttribute("currentSessionUser", item);
                        session.setAttribute("currentSessionType", "STAFF");
                        //Como todo ha ido bien, pongo errorLogin a false
                        request.setAttribute("errorLogin", "false");
                        //Paso la patata al indel del Back-Office
                        response.sendRedirect("admin/index.jsp");
                    } else {
                        //Creo un atributo para mostrar un mensaje de error en el login
                        request.setAttribute("errorLogin", "true");
                    }
                }
                //Recorro los Clientes y voy comprobando la dupla usuario/password
                for (Cliente item : clientes) {
                    if (usuario.equals(item.getUsuario()) && password.equals(item.getPassword())) {
                        falloLogin = false;
                        //Si encuentro la pareja, inicio sesion
                        session = request.getSession(true);
                        //Añado un atributo de sesion que contiene el nombre de usuario
                        session.setAttribute("currentSessionUser", item);
                        session.setAttribute("currentSessionType", "CLIENTE");
                        //Indico una cesta sin ID, para poder crearla a partir de 
                        //ahora y manejarla en esta sesion
                        session.setAttribute("cesta", null);
                        //Como todo ha ido bien, pongo errorLogin a false
                        request.setAttribute("errorLogin", "false");
                        //Paso la patata al index del Front-End
                        response.sendRedirect("index.jsp");
                    } else {
                        //Creo un atributo para mostrar un mensaje de error en el login
                        request.setAttribute("errorLogin", "true");
                    }
                }
            }
            if (falloLogin) {
                //Paso el testigo al JSP para que vuelva a "pintar" el formulario de login
                RequestDispatcher view = request.getRequestDispatcher("acceso.jsp");
                view.forward(request, response);
            }
        } else if (session.getAttribute("currentSessionType") == "CLIENTE" && !"true".equals(request.getParameter("salir"))) {
            //Paso la patata al index del Front-End
            response.sendRedirect("index.jsp");
        } else if (session.getAttribute("currentSessionType") == "STAFF" && !"true".equals(request.getParameter("salir"))) {
            //Paso la patata al indel del Back-Office
            response.sendRedirect("admin/index.jsp");
        } else {
            //Eliminamos la sesion actual
            session.invalidate();
            //Renviamos al index
            response.sendRedirect("index.jsp");
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
