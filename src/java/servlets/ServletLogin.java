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
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            // Recogemos los datos del formulario
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String rol = request.getParameter("rol");
            Boolean existe = false;

            // Mostramos los datos del usuario
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Busqueda por Socio</title>");
            out.println("<link rel='stylesheet' href='style.css'>");
            out.println("</head>");
            out.println("<main>");

            out.println("<div class='container'>");
            out.println("<h1>Menú de " + rol + "</h1>");
            out.println("<br>");

            // Si el usuario es alumno
            if (rol.equals("alumno")) {
                // Buscamos el alumno por nombre
                Alumnos a = new Alumnos(username, password);

                // si objeto a esta vacio
                if (!notasEJB.existeAlumno(a)) {
                    out.println("<b>El alumno no existe</b><br>");
                } else {
                    existe = true;
                    // Mostramos los datos del alumno en una tabla
                    a = notasEJB.findAlumnoByName(username);
                    out.println("<table>");
                    out.println("<tr>");
                    out.println("<th>Nombre</th>");
                    out.println("<th>Usuario</th>");
                    out.println("<th>Contraseña</th>");
                    out.println("</tr>");
                    out.println("<tr>");
                    out.println("<td>" + a.getNombre() + "</td>");
                    out.println("<td>" + a.getNomUser() + "</td>");
                    out.println("<td>" + a.getPassword() + "</td>");
                    out.println("</tr>");
                    out.println("</table>");

                    // Mostramos las notas del alumno
                    List<Notas> notas = notasEJB.findNotasByAlumno(a);
                    out.println("<table>");
                    out.println("<tr>");
                    out.println("<th>Asignatura</th>");
                    out.println("<th>Nota</th>");
                    out.println("</tr>");
                    for (Notas n : notas) {
                        out.println("<tr>");
                        out.println("<td>" + n.getIdModulo().getNombre() + "</td>");
                        out.println("<td>" + n.getNotas() + "</td>");
                        out.println("</tr>");
                    }
                    out.println("</table>");
                    out.println("<br>");
                }

            } else {

                // Buscamos el profesor por nombre
                Profesores p = new Profesores(username, password);

                // si objeto p esta vacio
                if (!notasEJB.existeProfesor(p)) {
                    out.println("<b>El profesor no existe</b><br>");
                    existe = false;
                } else {
                    existe = true;
                    // Mostramos los datos del profesor
                    p = notasEJB.findProfesorByName(username);

                    out.println("<table>");
                    out.println("<tr>");
                    out.println("<th>Nombre</th>");
                    out.println("<th>Usuario</th>");
                    out.println("<th>Contraseña</th>");
                    out.println("</tr>");
                    out.println("<tr>");
                    out.println("<td>" + p.getNombre() + "</td>");
                    out.println("<td>" + p.getNomUser() + "</td>");
                    out.println("<td>" + p.getPassword() + "</td>");
                    out.println("</tr>");
                    out.println("</table>");

                    out.println("<table>");
                    out.println("<tr>");
                    out.println("<th>Alumnos</th>");
                    out.println("<th>Notas</th>");
                    out.println("<th>Módulos</th>");
                    out.println("<th>Profesores</th>");
                    out.println("<th>Historial</th>");
                    out.println("</tr>");
                    out.println("<tr>");
                    out.println("<td>");
                    out.println("<form action='actionServlet' method='post' style='all:unset'>");
                    out.println("<select name='action'>");
                    out.println("<option value='ListarAlumnos'>Listar</option>");
                    out.println("<option value='InsertarAlumno'>Insertar</option>");
                    out.println("<option value='ModificarAlumno'>Modificar</option>");
                    out.println("<option value='BorrarAlumno'>Borrar</option>");
                    out.println("<option value='ListarModulosPorAlumno'>Módulos</option>");
                    out.println("</select>");
                    out.println("<input type='submit' value='Enviar' class='blue''>");
                    out.println("</form>");
                    out.println("</td>");
                    out.println("<td>");
                    out.println("<form action='actionServlet' method='post' style='all:unset'>");
                    out.println("<select name='action'>");
                    out.println("<option value='ListarNotas'>Listar</option>");
                    out.println("<option value='InsertarNota'>Insertar</option>");
                    out.println("<option value='ModificarNota'>Modificar</option>");
                    out.println("<option value='BorrarNota'>Borrar</option>");
                    out.println("<option value='ListarNotasPorAlumno'>Alumnos</option>");
                    out.println("</select>");
                    out.println("<input type='submit' value='Enviar' class='blue''>");
                    out.println("</form>");
                    out.println("</td>");
                    out.println("<td>");
                    out.println("<form action='actionServlet' method='post' style='all:unset'>");
                    out.println("<select name='action'>");
                    out.println("<option value='ListarModulos'>Listar</option>");
                    out.println("<option value='InsertarModulo'>Insertar</option>");
                    out.println("<option value='ModificarModulo'>Modificar</option>");
                    out.println("<option value='BorrarModulo'>Borrar</option>");
                    out.println("<option value='ListarAlumnosPorModulo'>Alumnos</option>");
                    out.println("</select>");
                    out.println("<input type='submit' value='Enviar' class='blue''>");
                    out.println("</form>");
                    out.println("</td>");
                    out.println("<td>");
                    out.println("<form action='actionServlet' method='post' style='all:unset'>");
                    out.println("<select name='action'>");
                    out.println("<option value='ListarProfesores'>Listar</option>");
                    out.println("<option value='InsertarProfesor'>Insertar</option>");
                    out.println("<option value='ModificarProfesor'>Modificar</option>");
                    out.println("<option value='BorrarProfesor'>Borrar</option>");
                    out.println("</select>");
                    out.println("<input type='submit' value='Enviar' class='blue''>");
                    out.println("</form>");
                    out.println("</td>");
                    out.println("<td>");
                    out.println("<form action='actionServlet' method='post' style='all:unset'>");
                    out.println("<select name='action'>");
                    out.println("<option value='ListarHistorialEventos'>Listar</option>");
                    out.println("<option value='InsertarHistorialEvento'>Insertar</option>");
                    out.println("<option value='ModificarHistorialEvento'>Modificar</option>");
                    out.println("<option value='BorrarHistorialEvento'>Borrar</option>");
                    out.println("<option value='ListarHistorialPorEvento'>Eventos</option>");
                    out.println("</select>");
                    out.println("<input type='submit' value='Enviar' class='blue''>");
                    out.println("</form>");
                    out.println("</td>");
                    out.println("</tr>");
                    out.println("</table>");
                    

                }
            }

            // Si usuario no existe, ocultamos el boton de cambiar contraseña
            if (existe) {
                // Boton para cambiar la contraseña
                out.println("<form action='ServletCambiarPassword' method='post' style='all:unset'>");
                out.println("<input type='submit' value='Cambiar contraseña'>");
                out.println("<input type='hidden' name='username' value='" + username + "'>");
                out.println("</form>");
            }

            out.println("<br>");

            // Boton para volver al menu
            out.println("<form action='index.html' method='post' style='all:unset'>");
            out.println("<input class='red' type='submit' value='Cerrar sesión'>");
            out.println("</form>");

            out.println("</div>");
            out.println("</main>");
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
