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
import javax.persistence.criteria.CriteriaBuilder.In;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author alber
 */
@WebServlet(name = "actionServlet", urlPatterns = { "/actionServlet" })
public class actionServlet2 extends HttpServlet {

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
            String action = request.getParameter("action");
            List<Alumnos> alumnos = notasEJB.findAllAlumnos();
            List<Profesores> profesores = notasEJB.findAllProfesores();
            List<Notas> notas = notasEJB.findAllNotas();
            List<Modulos> modulos = notasEJB.findAllAsignaturas();
            

            switch (action) {

                case "ListarAlumnos":
                    // Código para listar alumnos
                    out.println("<!DOCTYPE html>");
                    out.println("<html>");
                    out.println("<head>");
                    out.println("<title>Listar alumnos</title>");
                    out.println("<link rel='stylesheet' href='style.css'>");
                    out.println("</head>");
                    out.println("<body>");
                    out.println("<main>");
                    out.println("<div class='container'>");
                    out.println("<h1>Listar alumnos</h1>");
                    out.println("<br>");
                    out.println("<table>");
                    out.println("<tr>");
                    out.println("<th>ID ALumno</th>");
                    out.println("<th>Nombre Alumno</th>");
                    out.println("<th>Usuario</th>");
                    out.println("<th>Password</th>");
                    out.println("</tr>");
                    for (Alumnos a : alumnos) {
                        out.println("<tr>");
                        out.println("<td>" + a.getIdAlumno() + "</td>");
                        out.println("<td>" + a.getNombre() + "</td>");
                        out.println("<td>" + a.getNomUser() + "</td>");
                        out.println("<td>" + a.getPassword() + "</td>");
                        out.println("</tr>");
                    }
                    out.println("</table>");
                    out.println("<br>");
                    out.println("<input type='submit' value='Volver' onclick=\"history.back()\" class='btn-volver'>");
                    out.println("<script>");
                    out.println("function goBack() {");
                    out.println("    window.history.back();");
                    out.println("}");
                    out.println("</script>");
                    out.println("</div>");
                    out.println("</main>");
                    out.println("</body>");
                    out.println("</html>");
                    break;

                case "InsertarAlumno":
                    out.println("<!DOCTYPE html>");
                    out.println("<html>");
                    out.println("<head>");
                    out.println("<title>Formulario de inserción de alumno</title>");
                    out.println("<link rel='stylesheet' href='style.css'>");
                    out.println("</head>");
                    out.println("<body>");
                    out.println("<main>");
                    out.println("<div class='container'>");
                    out.println("<h1>Insertar Alumno</h1>");
                    out.println("<br>");
                    out.println("<form action='actionServlet' method='POST'>");
                    /*
                     * out.println("<label>ID Alumno: </label>");
                     * out.println("<input type='text' name='id' required><br>");
                     */
                    out.println("<label>Nombre: </label>");
                    out.println("<input type='text' name='nombre' ><br>");
                    out.println("<label>Usuario: </label>");
                    out.println("<input type='text' name='usuario' ><br>");
                    out.println("<label>Contraseña: </label>");
                    out.println("<input type='text' name='password' ><br>");
                    out.println("<input type='hidden' name='action' value='InsertarAlumno'>");
                    out.println("<input type='submit' value='Enviar'>");

                    out.println("</form>");

                    out.println("<br>");

                    // Si los campos estan vacios, no se inserta el alumno
                    if (request.getParameter("nombre") == null || request.getParameter("usuario") == null
                            || request.getParameter("password") == null) {

                    } else {
                        // REcojo los datos del formulario
                        String nombre = request.getParameter("nombre");
                        String usuario = request.getParameter("usuario");
                        String contraseña = request.getParameter("password");

                        Alumnos a = new Alumnos(null, nombre, usuario, contraseña);

                        Boolean insertar = notasEJB.insertarAlumno(a);

                        if (insertar) {
                            out.println("<h2>Alumno insertado correctamente</h2>");
                            out.println("<br");

                            // imprimir los datos del alumno en una tabla
                            out.println("<table>");
                            out.println("<tr>");
                            out.println("<th>ID Alumno</th>");
                            out.println("<th>Nombre</th>");
                            out.println("<th>Usuario</th>");
                            out.println("<th>Contraseña</th>");
                            out.println("</tr>");
                            out.println("<tr>");
                            out.println("<td>" + a.getIdAlumno() + "</td>");
                            out.println("<td>" + a.getNombre() + "</td>");
                            out.println("<td>" + a.getNomUser() + "</td>");
                            out.println("<td>" + a.getPassword() + "</td>");
                            out.println("</tr>");
                        } else {
                            out.println("<br");
                            out.println("<h2>Alumno no insertado</h2>");
                            out.println("<br>");
                        }

                        out.println("<br>");

                        // Boton para volver al menu
                        out.println("<form action='/NotasEJB' method='post' style='all:unset'>");
                        out.println("<input class='red' type='submit' value='Salir'>");
                        out.println("</form>");

                        out.println("</form>");
                        out.println("</div>");
                        out.println("</main>");
                        out.println("</body>");
                        out.println("</html>");
                    }

                    break;

                case "ModificarAlumno":
                    Boolean inicio = true;
                    // Moodificar alumno
                    out.println("<!DOCTYPE html>");
                    out.println("<html>");
                    out.println("<head>");
                    out.println("<title>Modificar alumno</title>");
                    out.println("<link rel='stylesheet' href='style.css'>");
                    out.println("</head>");
                    out.println("<body>");
                    out.println("<main>");
                    out.println("<div class='container'>");
                    out.println("<h1>Modificar Alumno</h1>");
                    out.println("<br>");
                    out.println("<form action='actionServlet' method='POST'>");

                    out.println("<label>ID Alumno: </label>");
                    out.println("<input type='text' name='id' required><br>");

                    out.println("<label>Nombre: </label>");
                    out.println("<input type='text' name='nombre' ><br>");
                    out.println("<label>Usuario: </label>");
                    out.println("<input type='text' name='usuario' ><br>");
                    out.println("<label>Contraseña: </label>");
                    out.println("<input type='text' name='password' ><br>");
                    out.println("<input type='hidden' name='action' value='ModificarAlumno'>");
                    out.println("<input type='submit' value='Enviar'>");
                    out.println("</form>");
                    out.println("<br>");
                    out.println("<table>");
                    out.println("<tr>");
                    out.println("<th>ID ALumno</th>");
                    out.println("<th>Nombre Alumno</th>");
                    out.println("<th>Usuario</th>");
                    out.println("<th>Password</th>");
                    out.println("</tr>");
                    for (Alumnos a : alumnos) {
                        out.println("<tr>");
                        out.println("<td>" + a.getIdAlumno() + "</td>");
                        out.println("<td>" + a.getNombre() + "</td>");
                        out.println("<td>" + a.getNomUser() + "</td>");
                        out.println("<td>" + a.getPassword() + "</td>");
                        out.println("</tr>");
                    }
                    out.println("</table>");
                    out.println("<br>");

                    out.println("<br>");

                    // Si los campos estan vacios, no se inserta el alumno
                    if (request.getParameter("nombre") == null || request.getParameter("usuario") == null
                            || request.getParameter("password") == null) {

                    } else {
                        // recojo los datos del formulario
                        String id = request.getParameter("id");
                        String nombre = request.getParameter("nombre");
                        String usuario = request.getParameter("usuario");
                        String contraseña = request.getParameter("password");

                        Alumnos a1 = new Alumnos(Integer.parseInt(id), nombre, usuario, contraseña);

                        Boolean modificar = notasEJB.modificarAlumno(a1);

                        if (modificar) {
                            out.println("<h2>Alumno modificado correctamente</h2>");
                            out.println("<br");

                        } else {
                            out.println("<br");
                            out.println("<h2>Alumno no modificado</h2>");
                            out.println("<br>");
                        }

                        out.println("<br>");

                        // Boton para volver al menu
                        out.println("<form action='/NotasEJB' method='post' style='all:unset'>");
                        out.println("<input class='red' type='submit' value='Salir'>");
                        out.println("</form>");

                        out.println("</form>");
                        out.println("</div>");
                        out.println("</main>");
                        out.println("</body>");
                        out.println("</html>");
                    }

                    break;

                case "BorrarAlumno":
                    // Borrar alumno
                    out.println("<!DOCTYPE html>");
                    out.println("<html>");
                    out.println("<head>");
                    out.println("<title>Borrar alumno</title>");
                    out.println("<link rel='stylesheet' href='style.css'>");
                    out.println("</head>");
                    out.println("<body>");
                    out.println("<main>");
                    out.println("<div class='container'>");
                    out.println("<h1>Borrar Alumno</h1>");
                    out.println("<br>");
                    out.println("<form action='actionServlet' method='POST'>");

                    out.println("<label>ID Alumno: </label>");
                    out.println("<input type='text' name='id' required><br>");
                    out.println("<input type='hidden' name='action' value='BorrarAlumno'>");
                    out.println("<input type='submit' value='Enviar'>");
                    out.println("</form>");
                    out.println("<br>");
                    out.println("<table>");
                    out.println("<tr>");
                    out.println("<th>ID ALumno</th>");
                    out.println("<th>Nombre Alumno</th>");
                    out.println("<th>Usuario</th>");
                    out.println("<th>Password</th>");
                    out.println("</tr>");
                    for (Alumnos a : alumnos) {
                        out.println("<tr>");
                        out.println("<td>" + a.getIdAlumno() + "</td>");
                        out.println("<td>" + a.getNombre() + "</td>");
                        out.println("<td>" + a.getNomUser() + "</td>");
                        out.println("<td>" + a.getPassword() + "</td>");
                        out.println("</tr>");
                    }
                    out.println("</table>");
                    out.println("<br>");

                    out.println("<br>");

                    // Si los campos estan vacios, no se inserta el alumno
                    if (request.getParameter("id") == null) {

                    } else {
                        // recojo los datos del formulario
                        String id = request.getParameter("id");

                        Alumnos a1 = new Alumnos(Integer.parseInt(id), null, null, null);

                        Boolean borrar = notasEJB.borrarAlumno(a1);

                        if (borrar) {
                            out.println("<h2>Alumno borrado correctamente</h2>");
                            out.println("<br");

                        } else {
                            out.println("<br");
                            out.println("<h2>Alumno no borrado</h2>");
                            out.println("<br>");
                        }

                        out.println("<br>");

                        // Boton para volver al menu
                        out.println("<form action='/NotasEJB' method='post' style='all:unset'>");
                        out.println("<input class='red' type='submit' value='Salir'>");
                        out.println("</form>");

                        out.println("</form>");
                        out.println("</div>");
                        out.println("</main>");
                        out.println("</body>");
                        out.println("</html>");
                    }
                    break;

                case "ListarNotas":
                    // Listar notas
                    out.println("<!DOCTYPE html>");
                    out.println("<html>");
                    out.println("<head>");
                    out.println("<title>Listar notas</title>");
                    out.println("<link rel='stylesheet' href='style.css'>");
                    out.println("</head>");
                    out.println("<body>");
                    out.println("<main>");
                    out.println("<div class='container'>");
                    out.println("<h1>Listar notas</h1>");
                    out.println("<br>");
                    out.println("<table>");
                    out.println("<tr>");
                    out.println("<th>ID Nota</th>");
                    out.println("<th>Nombre Alumno</th>");
                    out.println("<th>Modulo</th>");
                    out.println("<th>Nota</th>");
                    out.println("</tr>");
                    for (Alumnos a : alumnos) {
                        out.println("<tr>");
                        //obtener notas del alumno
                        for (Notas n : a.getNotasCollection()) {
                            out.println("<td>" + n.getIdNotas() + "</td>");
                            out.println("<td>" + a.getNombre() + "</td>");
                            out.println("<td>" + n.getIdModulo().getNombre() + "</td>");
                            out.println("<td>" + n.getNotas() + "</td>");
                            out.println("</tr>");
                        }
                    }
                    out.println("</table>");
                    out.println("<br>");
                    out.println("<input type='submit' value='Volver' onclick=\"history.back()\" class='btn-volver'>");
                    out.println("<script>");
                    out.println("function goBack() {");
                    out.println("    window.history.back();");
                    out.println("}");
                    out.println("</script>");
                    out.println("</div>");
                    out.println("</main>");
                    out.println("</body>");
                    out.println("</html>");
                    break;

                case "InsertarNota":
                    // Código para insertar nota
                    break;
                case "ModificarNota":
                    // Código para modificar nota
                    break;
                case "BorrarNota":
                    // Código para borrar nota
                    break;
                case "ListarModulos":
                    // Código para listar módulos
                    break;
                case "InsertarModulo":
                    // Código para insertar módulo
                    break;
                case "ModificarModulo":
                    // Código para modificar módulo
                    break;
                case "BorrarModulo":
                    // Código para borrar módulo
                    break;
                case "ListarProfesores":
                    // Código para listar profesores
                    break;
                case "InsertarProfesor":
                    // Código para insertar profesor
                    break;
                case "ModificarProfesor":
                    // Código para modificar profesor
                    break;
                case "BorrarProfesor":
                    // Código para borrar profesor
                    break;
                case "ListarHistorialEventos":
                    // Código para listar historial de eventos
                    break;
                case "InsertarHistorialEvento":
                    // Código para insertar historial de evento
                    break;
                case "ModificarHistorialEvento":
                    // Código para modificar historial de evento
                    break;
                case "BorrarHistorialEvento":
                    // Código para borrar historial de evento
                    break;
                case "ListarHistorialPorEvento":
                    // Código para listar historial por evento
                    break;
                default:
                    // Código si no se reconoce la opción
                    break;
            }
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