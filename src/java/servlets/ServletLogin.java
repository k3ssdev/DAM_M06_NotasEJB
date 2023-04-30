/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import clasesPOJO.*;
import beans.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = { "/ServletLogin" })
public class ServletLogin extends HttpServlet {

    @EJB
    NotasEJB notasEJB;

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
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Busqueda por Socio</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Resultado de la busqueda</h1>");
            
             // Recogemos los datos del formulario
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String rol = request.getParameter("rol");

            //Si el usuario es alumno
            if (rol.equals("alumno")) {
                //Buscamos el alumno por nombre
                Alumnos a = new Alumnos(username, password);

            // si objeto a esta vacio
            if (!notasEJB.existeAlumno(a)) {
                out.println("<b>El alumno no existe</b><br>");
            } else {
                // Mostramos los datos del alumno
                a = notasEJB.findAlumnoByName(username);
                out.println("<b>Nombre: </b>" + a.getNombre() + "<br>");
                out.println("<b>Usuario: </b>" + a.getNomUser() + "<br>");
                out.println("<b>Contraseña: </b>" + a.getPassword() + "<br>");
                out.println("<b>Existe alumno</b><br>");
            }
            } else {
                //Buscamos el profesor por nombre
                Profesores p = new Profesores(username, password);

            // si objeto p esta vacio
            if (!notasEJB.existeProfesor(p)) {
                out.println("<b>El profesor no existe</b><br>");
            } else {
                // Mostramos los datos del profesor
                p = notasEJB.findProfesorByName(username);
                out.println("<b>Nombre: </b>" + p.getNombre() + "<br>");
                out.println("<b>Usuario: </b>" + p.getNomUser() + "<br>");
                out.println("<b>Contraseña: </b>" + p.getPassword() + "<br>");
                out.println("<b>Existe profesor</b><br>");
            }
            }
    
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the
    // + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
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
