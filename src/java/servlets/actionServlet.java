/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import clasesPOJO.*;
import beans.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author alber
 */
@WebServlet(name = "actionServlet", urlPatterns = { "/actionServlet" })
public class actionServlet extends HttpServlet {

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
            List<Alumnos> alumnos;
            List<Notas> notas;
            List<Profesores> profesores;
            List<Modulos> modulos;
            List<Historial> historial;
            Timestamp timestamp;

            // Recuperamos los datos del profesor de la sesion
            HttpSession session = request.getSession();
            Profesores profesor = (Profesores) session.getAttribute("profesor");

            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Admin Notas</title>");
            out.println("<link rel='stylesheet' href='style.css'>");
            out.println("</head>");
            out.println("<body>");
            out.println("<main>");

            switch (action) {

                case "ListarAlumnos":
                    out.println("<div class='container'>");
                    // Código para listar alumnos
                    out.println("<h1>Listar alumnos</h1>");
                    out.println("<br>");
                    out.println("<table>");
                    out.println("<tr>");
                    out.println("<th>ID ALumno</th>");
                    out.println("<th>Nombre Alumno</th>");
                    out.println("<th>Usuario</th>");
                    out.println("<th>Password</th>");
                    out.println("</tr>");
                    alumnos = notasEJB.findAllAlumnos();
                    for (Alumnos a : alumnos) {
                        out.println("<tr>");
                        out.println("<td>" + a.getIdAlumno() + "</td>");
                        out.println("<td>" + a.getNombre() + "</td>");
                        out.println("<td>" + a.getNomUser() + "</td>");
                        out.println("<td>" + a.getPassword() + "</td>");
                        out.println("</tr>");
                    }
                    out.println("</table>");

                    break;

                case "InsertarAlumno":
                    // Código para insertar alumno
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

                    // imprimir los datos del alumno en una tabla
                    out.println("<table>");
                    out.println("<tr>");
                    out.println("<th>ID ALumno</th>");
                    out.println("<th>Nombre Alumno</th>");
                    out.println("<th>Usuario</th>");
                    out.println("<th>Password</th>");
                    out.println("</tr>");
                    alumnos = notasEJB.findAllAlumnos();
                    for (Alumnos al : alumnos) {
                        out.println("<tr>");
                        out.println("<td>" + al.getIdAlumno() + "</td>");
                        out.println("<td>" + al.getNombre() + "</td>");
                        out.println("<td>" + al.getNomUser() + "</td>");
                        out.println("<td>" + al.getPassword() + "</td>");
                        out.println("</tr>");
                    }
                    out.println("</table>");

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

                        } else {
                            out.println("<br");
                            out.println("<h2>Alumno no insertado</h2>");
                            out.println("<br>");
                        }

                        out.println("</div>");

                    }

                    break;

                case "ModificarAlumno":
                    out.println("<div class='container'>");
                    Boolean inicio = true;
                    // Moodificar alumno
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
                    alumnos = notasEJB.findAllAlumnos();
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

                    }

                    break;

                case "BorrarAlumno":
                    // Borrar alumno
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
                    alumnos = notasEJB.findAllAlumnos();
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

                    }
                    break;

                case "ListarModulosPorAlumno":
                    // Listar modulos por alumno
                    out.println("<div class='container'>");
                    out.println("<h1>Listar modulos por alumno</h1>");
                    out.println("<br>");
                    out.println("<form action='actionServlet' method='POST'>");

                    out.println("<label>ID Alumno: </label>");
                    out.println("<input type='text' name='id' required><br>");
                    out.println("<input type='hidden' name='action' value='ListarModulosPorAlumno'>");
                    out.println("<input type='submit' value='Enviar'>");
                    out.println("</form>");
                    out.println("<br>");
                    // Si los campos estan vacios, no se inserta el alumno
                    if (request.getParameter("id") == null) {

                    } else {
                        // recojo los datos del formulario
                        String id = request.getParameter("id");

                        // Creo el objeto
                        Alumnos a1 = new Alumnos(Integer.parseInt(id), null, null, null);

                        // Mostramos las notas del alumno
                        List<Notas> n1 = notasEJB.findNotasByAlumnoId(a1);
                        out.println("<table>");
                        out.println("<tr>");
                        out.println("<th>ID Alumno</th>");
                        out.println("<th>Nombre Alumno</th>");
                        out.println("<th>Asignatura</th>");
                        out.println("</tr>");
                        for (Notas n : n1) {
                            out.println("<tr>");
                            out.println("<td>" + n.getIdAlumno().getIdAlumno() + "</td>");
                            out.println("<td>" + n.getIdAlumno().getNombre() + "</td>");
                            out.println("<td>" + n.getIdModulo().getNombre() + "</td>");
                            out.println("</tr>");
                        }
                        out.println("</table>");
                        out.println("<br>");
                    }
                    break;

                case "ListarNotas":
                    // Listar notas
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
                    notas = notasEJB.findAllNotas();
                    for (Notas n : notas) {
                        out.println("<tr>");
                        out.println("<td>" + n.getIdNotas() + "</td>");
                        out.println("<td>" + n.getIdAlumno().getNombre() + "</td>");
                        out.println("<td>" + n.getIdModulo().getNombre() + "</td>");
                        out.println("<td>" + n.getNotas() + "</td>");
                        out.println("</tr>");
                    }
                    out.println("</table>");

                    break;

                case "InsertarNota":
                    // Insertar nota
                    out.println("<div class='container'>");
                    out.println("<h1>Insertar Nota</h1>");
                    out.println("<br>");

                    out.println("<form action='actionServlet' method='POST'>");
                    /*
                     * out.println("<label>ID Alumno: </label>");
                     * out.println("<input type='text' name='id' required><br>");
                     */
                    out.println("<label>ID Alumno: </label>");
                    out.println("<input type='text' name='idAlumno' required>");
                    out.println("<label>ID Modulo: </label>");
                    out.println("<input type='text' name='idModulo' required>");
                    out.println("<label>Nota: </label>");
                    out.println("<input type='text' name='nota' required>");
                    out.println("<input type='hidden' name='action' value='InsertarNota'>");
                    out.println("<input type='submit' value='Enviar'>");

                    out.println("</form>");

                    out.println("<div class='container2'>");
                    // imprimir los datos del alumno en una tabla
                    out.println("<table class='table2'>");
                    out.println("<tr>");
                    out.println("<th>ID Nota</th>");
                    out.println("<th>Alumno</th>");
                    out.println("<th>Modulo</th>");
                    out.println("<th>Nota</th>");
                    out.println("</tr>");
                    out.println("<tr>");
                    notas = notasEJB.findAllNotas();
                    for (Notas n : notas) {
                        out.println("<tr>");
                        out.println("<td>" + n.getIdNotas() + "</td>");
                        out.println("<td>" + n.getIdAlumno().getNombre() + "</td>");
                        out.println("<td>" + n.getIdModulo().getNombre() + "</td>");
                        out.println("<td>" + n.getNotas() + "</td>");
                        out.println("</tr>");
                    }
                    out.println("</table>");
                    out.println("<br>");

                    out.println("<table class='table2'>");
                    out.println("<tr>");
                    out.println("<th>ID ALumno</th>");
                    out.println("<th>Nombre Alumno</th>");
                    out.println("<th>Usuario</th>");
                    out.println("</tr>");
                    alumnos = notasEJB.findAllAlumnos();
                    for (Alumnos a : alumnos) {
                        out.println("<tr>");
                        out.println("<td>" + a.getIdAlumno() + "</td>");
                        out.println("<td>" + a.getNombre() + "</td>");
                        out.println("<td>" + a.getNomUser() + "</td>");
                        out.println("</tr>");
                    }
                    out.println("</table>");
                    out.println("<br>");

                    out.println("<table class='table2'>");
                    out.println("<tr>");
                    out.println("<th>ID Modulo</th>");
                    out.println("<th>Nombre Modulo</th>");
                    out.println("</tr>");
                    modulos = notasEJB.findAllModulos();
                    for (Modulos m : modulos) {
                        out.println("<tr>");
                        out.println("<td>" + m.getId() + "</td>");
                        out.println("<td>" + m.getNombre() + "</td>");
                        out.println("</tr>");
                    }
                    out.println("</table>");
                    out.println("</div>");

                    // Si los campos estan vacios, no se inserta el alumno
                    if (request.getParameter("idAlumno") == null || request.getParameter("idModulo") == null
                            || request.getParameter("nota") == null) {

                    } else {
                        // REcojo los datos del formulario
                        String idAlumno = request.getParameter("idAlumno");
                        String idModulo = request.getParameter("idModulo");
                        String nota = request.getParameter("nota");

                        // Creo el objeto nota
                        Notas n1 = new Notas(Integer.parseInt(idAlumno), Integer.parseInt(idModulo),
                                Float.parseFloat(nota));

                        Boolean insertar = notasEJB.insertarNota(n1);

                        if (insertar) {
                            // Creamos un objeto Historial y lo insertamos en la base de datos
                            timestamp = new Timestamp(System.currentTimeMillis());
                            Historial h = new Historial(null, "N", profesor.getId(), timestamp.toString());
                            notasEJB.InsertarEvento(h);
                            out.println("<h2>Nota insertada correctamente</h2>");
                            out.println("<br");

                        } else {
                            out.println("<br");
                            out.println("<h2>Nota no insertada</h2>");
                            out.println("<br>");
                        }
                    }

                    out.println("<br>");
                    break;

                case "ModificarNota":

                    // Modificar nota
                    out.println("<div class='container'>");
                    out.println("<h1>Modificar Nota</h1>");
                    out.println("<br>");

                    out.println("<form action='actionServlet' method='POST'>");
                    out.println("<label>ID Nota: </label>");
                    out.println("<input type='text' name='idNota' required><br>");
                    out.println("<label>Nota: </label>");
                    out.println("<input type='text' name='nota' required><br>");
                    out.println("<input type='hidden' name='action' value='ModificarNota'>");
                    out.println("<input type='submit' value='Enviar'>");
                    out.println("</form>");

                    // imprimir los datos del alumno en una tabla
                    out.println("<table>");
                    out.println("<tr>");
                    out.println("<th>ID Nota</th>");
                    out.println("<th>Nombre Alumno</th>");
                    out.println("<th>Modulo</th>");
                    out.println("<th>Nota</th>");
                    out.println("</tr>");
                    notas = notasEJB.findAllNotas();
                    for (Notas n : notas) {
                        out.println("<tr>");
                        out.println("<td>" + n.getIdNotas() + "</td>");
                        out.println("<td>" + n.getIdAlumno().getNombre() + "</td>");
                        out.println("<td>" + n.getIdModulo().getNombre() + "</td>");
                        out.println("<td>" + n.getNotas() + "</td>");
                        out.println("</tr>");
                    }
                    out.println("</table>");

                    out.println("<br>");

                    // Si los campos estan vacios, no se inserta el alumno

                    if (request.getParameter("idNota") == null || request.getParameter("nota") == null) {

                    } else {
                        // REcojo los datos del formulario
                        String idNota = request.getParameter("idNota");
                        String nota = request.getParameter("nota");

                        // Creo el objeto nota
                        Notas n1 = new Notas(Integer.parseInt(idNota), Float.parseFloat(nota));

                        Boolean modificar = notasEJB.modificarNota(n1);

                        if (modificar) {
                            out.println("<h2>Nota modificada correctamente</h2>");
                            out.println("<br");

                        } else {
                            out.println("<br");
                            out.println("<h2>Nota no modificada</h2>");
                            out.println("<br>");
                        }
                    }
                    out.println("<br>");
                    break;

                case "BorrarNota":
                    // Borrar nota
                    out.println("<div class='container'>");
                    out.println("<h1>Borrar Nota</h1>");
                    out.println("<br>");

                    // imprimir los datos del alumno en una tabla
                    out.println("<table>");
                    out.println("<tr>");
                    out.println("<th>ID Nota</th>");
                    out.println("<th>Alumno</th>");
                    out.println("<th>Modulo</th>");
                    out.println("<th>Nota</th>");
                    out.println("</tr>");
                    out.println("<tr>");
                    alumnos = notasEJB.findAllAlumnos();

                    for (Alumnos a : alumnos) {
                        out.println("<tr>");
                        // obtener notas del alumno
                        for (Notas n : a.getNotasCollection()) {
                            out.println("<td>" + n.getIdNotas() + "</td>");
                            out.println("<td>" + a.getNombre() + "</td>");
                            out.println("<td>" + n.getIdModulo().getNombre() + "</td>");
                            out.println("<td>" + n.getNotas() + "</td>");
                            out.println("</tr>");
                        }
                    }

                    out.println("</tr>");
                    out.println("</table>");
                    out.println("<form action='actionServlet' method='POST'>");
                    out.println("<label>ID Nota: </label>");
                    out.println("<input type='text' name='idNota' required><br>");
                    out.println("<input type='hidden' name='action' value='BorrarNota'>");
                    out.println("<input type='submit' value='Enviar'>");

                    out.println("</form>");

                    out.println("<br>");

                    // Si los campos estan vacios, no se hace nada

                    if (request.getParameter("idNota") == null) {

                    } else {
                        // REcojo los datos del formulario
                        String idNota = request.getParameter("idNota");

                        // Creo el objeto nota
                        Notas n1 = new Notas(Integer.parseInt(idNota));

                        Boolean borrar = notasEJB.borrarNota(n1);

                        if (borrar) {
                            out.println("<h2>Nota borrada correctamente</h2>");
                            out.println("<br");

                        } else {
                            out.println("<br");
                            out.println("<h2>Nota no borrada</h2>");
                            out.println("<br>");
                        }
                        out.println("</div>");
                    }
                    break;

                case "ListarNotasPorAlumno":
                    // Listar notas por alumno
                    out.println("<div class='container'>");
                    out.println("<h1>Listar notas por alumno</h1>");
                    out.println("<br>");
                    out.println("<form action='actionServlet' method='POST'>");

                    out.println("<label>ID Alumno: </label>");
                    out.println("<input type='text' name='id' required><br>");
                    out.println("<input type='hidden' name='action' value='ListarNotasPorAlumno'>");
                    out.println("<input type='submit' value='Enviar'>");
                    out.println("</form>");
                    out.println("<br>");
                    // Si los campos estan vacios, no se inserta el alumno
                    if (request.getParameter("id") == null) {

                    } else {
                        // recojo los datos del formulario
                        String id = request.getParameter("id");

                        // Creo el objeto
                        Alumnos a1 = new Alumnos(Integer.parseInt(id), null, null, null);

                        // Mostramos las notas del alumno
                        List<Notas> n1 = notasEJB.findNotasByAlumnoId(a1);
                        out.println("<table>");
                        out.println("<tr>");
                        out.println("<th>ID Alumno</th>");
                        out.println("<th>Nombre Alumno</th>");
                        out.println("<th>Asignatura</th>");
                        out.println("<th>Nota</th>");
                        out.println("</tr>");
                        for (Notas n : n1) {
                            out.println("<tr>");
                            out.println("<td>" + n.getIdAlumno().getIdAlumno() + "</td>");
                            out.println("<td>" + n.getIdAlumno().getNombre() + "</td>");
                            out.println("<td>" + n.getIdModulo().getNombre() + "</td>");
                            out.println("<td>" + n.getNotas() + "</td>");
                            out.println("</tr>");
                        }
                        out.println("</table>");
                        out.println("<br>");
                    }
                    break;

                case "ListarModulos":

                    // Código para listar módulos
                    out.println("<div class='container'>");
                    out.println("<h1>Listar módulos</h1>");
                    out.println("<br>");
                    out.println("<table>");
                    out.println("<tr>");
                    out.println("<th>ID Modulo</th>");
                    out.println("<th>Nombre Modulo</th>");
                    out.println("</tr>");
                    modulos = notasEJB.findAllModulos();
                    for (Modulos m : modulos) {
                        out.println("<tr>");
                        out.println("<td>" + m.getId() + "</td>");
                        out.println("<td>" + m.getNombre() + "</td>");
                        out.println("</tr>");
                    }
                    out.println("</table>");
                    break;

                case "InsertarModulo":
                    // Código para insertar módulo
                    out.println("<div class='container'>");
                    out.println("<h1>Insertar Modulo</h1>");
                    out.println("<br>");
                    out.println("<form action='actionServlet' method='POST'>");
                    out.println("<label>Nombre: </label>");
                    out.println("<input type='text' name='nombre' ><br>");
                    out.println("<input type='hidden' name='action' value='InsertarModulo'>");
                    out.println("<input type='submit' value='Enviar'>");
                    out.println("</form>");

                    out.println("<br>");

                    // imprimir los datos del alumno en una tabla
                    out.println("<table>");
                    out.println("<tr>");
                    out.println("<th>ID Modulo</th>");
                    out.println("<th>Nombre Modulo</th>");
                    out.println("</tr>");
                    modulos = notasEJB.findAllModulos();
                    for (Modulos m : modulos) {
                        out.println("<tr>");
                        out.println("<td>" + m.getId() + "</td>");
                        out.println("<td>" + m.getNombre() + "</td>");
                        out.println("</tr>");
                    }
                    out.println("</table>");

                    // Si los campos estan vacios, no se inserta el alumno
                    if (request.getParameter("nombre") == null) {

                    } else {
                        // REcojo los datos del formulario
                        String nombre = request.getParameter("nombre");

                        // Creo el objeto modulo
                        Modulos modulo = new Modulos(null, nombre);

                        Boolean insertar = notasEJB.InsertarModulo(modulo);

                        if (insertar) {
                            out.println("<h2>Modulo insertado correctamente</h2>");
                            out.println("<br");

                        } else {
                            out.println("<br");
                            out.println("<h2>Modulo no insertado</h2>");
                            out.println("<br>");
                        }
                        out.println("</div>");
                    }
                    break;

                case "ModificarModulo":
                    // Código para modificar módulo
                    out.println("<div class='container'>");
                    out.println("<h1>Modificar Modulo</h1>");
                    out.println("<br>");

                    out.println("<form action='actionServlet' method='POST'>");
                    out.println("<label>ID Modulo: </label>");
                    out.println("<input type='text' name='idModulo' required><br>");
                    out.println("<label>Nombre: </label>");
                    out.println("<input type='text' name='nombre' required><br>");
                    out.println("<input type='hidden' name='action' value='ModificarModulo'>");
                    out.println("<input type='submit' value='Enviar'>");
                    out.println("</form>");

                    out.println("<br>");
                    out.println("<table>");
                    out.println("<tr>");
                    out.println("<th>ID Modulo</th>");
                    out.println("<th>Nombre Modulo</th>");
                    out.println("</tr>");
                    modulos = notasEJB.findAllModulos();
                    for (Modulos m : modulos) {
                        out.println("<tr>");
                        out.println("<td>" + m.getId() + "</td>");
                        out.println("<td>" + m.getNombre() + "</td>");
                        out.println("</tr>");
                    }

                    out.println("</table>");

                    out.println("<br>");

                    // Si los campos estan vacios, no se inserta el alumno

                    if (request.getParameter("idModulo") == null || request.getParameter("nombre") == null) {

                    } else {
                        // REcojo los datos del formulario
                        String idModulo = request.getParameter("idModulo");
                        String nombre = request.getParameter("nombre");

                        // Creo el objeto modulo
                        Modulos m1 = new Modulos(Integer.parseInt(idModulo), nombre);

                        Boolean modificar = notasEJB.ModificarModulo(m1);

                        if (modificar) {
                            out.println("<h2>Modulo modificado correctamente</h2>");
                            out.println("<br");

                        } else {
                            out.println("<h2>Modulo no modificado</h2>");
                            out.println("<br>");
                        }
                        // out.println("</div>");
                    }
                    out.println("<br>");
                    break;
                case "BorrarModulo":
                    // Código para borrar módulo
                    out.println("<div class='container'>");
                    out.println("<h1>Borrar Modulo</h1>");
                    out.println("<br>");

                    out.println("<form action='actionServlet' method='POST'>");
                    out.println("<label>ID Modulo: </label>");
                    out.println("<input type='text' name='idModulo' required><br>");
                    out.println("<input type='hidden' name='action' value='BorrarModulo'>");
                    out.println("<input type='submit' value='Enviar'>");
                    out.println("</form>");

                    out.println("<br>");
                    out.println("<table>");
                    out.println("<tr>");
                    out.println("<th>ID Modulo</th>");
                    out.println("<th>Nombre Modulo</th>");
                    out.println("</tr>");
                    modulos = notasEJB.findAllModulos();
                    for (Modulos m : modulos) {
                        out.println("<tr>");
                        out.println("<td>" + m.getId() + "</td>");
                        out.println("<td>" + m.getNombre() + "</td>");
                        out.println("</tr>");
                    }

                    out.println("</table>");

                    out.println("<br>");

                    // Si los campos estan vacios, no se inserta el alumno

                    if (request.getParameter("idModulo") == null) {

                    } else {
                        // REcojo los datos del formulario
                        String idModulo = request.getParameter("idModulo");

                        // Creo el objeto modulo
                        Modulos m1 = new Modulos(Integer.parseInt(idModulo), null);

                        Boolean modificar = notasEJB.borrarModulo(m1);

                        if (modificar) {
                            out.println("<h2>Modulo borrado correctamente</h2>");
                            out.println("<br");

                        } else {

                            out.println("<h2>Modulo no borrado</h2>");
                            out.println("<br>");
                        }
                        // out.println("</div>");
                    }
                    out.println("<br>");
                    break;
                case "ListarAlumnosPorModulo":
                    // Listar alumnos por modulo
                    out.println("<div class='container'>");
                    out.println("<h1>Listar alumno por modulo</h1>");
                    out.println("<br>");
                    out.println("<form action='actionServlet' method='POST'>");

                    out.println("<label>ID Modulo: </label>");
                    out.println("<input type='text' name='id' required><br>");
                    out.println("<input type='hidden' name='action' value='ListarNotasPorAlumno'>");
                    out.println("<input type='submit' value='Enviar'>");
                    out.println("</form>");
                    out.println("<br>");
                    // Si los campos estan vacios, no se inserta el alumno
                    if (request.getParameter("id") == null) {

                    } else {
                        // recojo los datos del formulario
                        String id = request.getParameter("id");

                        // Creo el objeto
                        Modulos m1 = new Modulos(Integer.parseInt(id), null);

                        // Mostramos las notas del alumno
                        List<Notas> n1 = notasEJB.findAlumnosByModulo(m1);
                        out.println("<table>");
                        out.println("<tr>");
                        out.println("<th>ID Alumno</th>");
                        out.println("<th>Nombre Alumno</th>");
                        out.println("<th>Asignatura</th>");
                        out.println("<th>Nota</th>");
                        out.println("</tr>");
                        for (Notas n : n1) {
                            out.println("<tr>");
                            out.println("<td>" + n.getIdAlumno().getIdAlumno() + "</td>");
                            out.println("<td>" + n.getIdAlumno().getNombre() + "</td>");
                            out.println("<td>" + n.getIdModulo().getNombre() + "</td>");
                            out.println("<td>" + n.getNotas() + "</td>");
                            out.println("</tr>");
                        }
                        out.println("</table>");
                        out.println("<br>");
                    }
                    break;
                case "ListarProfesores":
                    // Código para listar profesores
                    out.println("<div class='container'>");
                    out.println("<h1>Listar profesores</h1>");
                    out.println("<br>");
                    out.println("<table>");
                    out.println("<tr>");
                    out.println("<th>ID Profesor</th>");
                    out.println("<th>Nombre</th>");
                    out.println("<th>Usuario</th>");
                    out.println("<th>Password</th>");
                    out.println("</tr>");
                    profesores = notasEJB.findAllProfesores();
                    for (Profesores p : profesores) {
                        out.println("<tr>");
                        out.println("<td>" + p.getId() + "</td>");
                        out.println("<td>" + p.getNombre() + "</td>");
                        out.println("<td>" + p.getNomUser() + "</td>");
                        out.println("<td>" + p.getPassword() + "</td>");
                        out.println("</tr>");
                    }
                    out.println("</table>");
                    break;
                case "InsertarProfesor":
                    // Código para insertar profesor
                    out.println("<div class='container'>");
                    out.println("<h1>Insertar Profesor</h1>");
                    out.println("<br>");
                    out.println("<form action='actionServlet' method='POST'>");
                    out.println("<label>Nombre: </label>");
                    out.println("<input type='text' name='nombre' ><br>");
                    out.println("<label>Usuario: </label>");
                    out.println("<input type='text' name='usuario' ><br>");
                    out.println("<label>Contraseña: </label>");
                    out.println("<input type='text' name='password' ><br>");
                    out.println("<input type='hidden' name='action' value='InsertarProfesor'>");
                    out.println("<input type='submit' value='Enviar'>");

                    out.println("</form>");

                    out.println("<br>");

                    // imprimir los datos d
                    out.println("<table>");
                    out.println("<tr>");
                    out.println("<th>ID Profesor</th>");
                    out.println("<th>Nombre</th>");
                    out.println("<th>Usuario</th>");
                    out.println("<th>Password</th>");
                    out.println("</tr>");
                    profesores = notasEJB.findAllProfesores();
                    for (Profesores p : profesores) {
                        out.println("<tr>");
                        out.println("<td>" + p.getId() + "</td>");
                        out.println("<td>" + p.getNombre() + "</td>");
                        out.println("<td>" + p.getNomUser() + "</td>");
                        out.println("<td>" + p.getPassword() + "</td>");
                        out.println("</tr>");
                    }
                    out.println("</table>");

                    out.println("<br>");

                    // Si los campos estan vacios, no se inserta el alumno
                    if (request.getParameter("nombre") == null || request.getParameter("usuario") == null
                            || request.getParameter("password") == null) {

                    } else {
                        // REcojo los datos del formulario
                        String nombre = request.getParameter("nombre");
                        String usuario = request.getParameter("usuario");
                        String contraseña = request.getParameter("password");

                        Profesores p = new Profesores(null, nombre, usuario, contraseña);

                        Boolean insertar = notasEJB.InsertarProfesor(p);

                        if (insertar) {
                            out.println("<h2>Profesor insertado correctamente</h2>");
                            out.println("<br");

                        } else {
                            out.println("<br");
                            out.println("<h2>Alumno no insertado</h2>");
                            out.println("<br>");
                        }
                        out.println("</div>");
                    }

                    break;
                case "ModificarProfesor":
                    // Código para modificar profesor
                    out.println("<div class='container'>");
                    out.println("<h1>Modificar Profesor</h1>");
                    out.println("<br>");
                    out.println("<form action='actionServlet' method='POST'>");

                    out.println("<label>ID Profesor: </label>");
                    out.println("<input type='text' name='id' required><br>");

                    out.println("<label>Nombre: </label>");
                    out.println("<input type='text' name='nombre' ><br>");
                    out.println("<label>Usuario: </label>");
                    out.println("<input type='text' name='usuario' ><br>");
                    out.println("<label>Contraseña: </label>");
                    out.println("<input type='text' name='password' ><br>");
                    out.println("<input type='hidden' name='action' value='ModificarProfesor'>");
                    out.println("<input type='submit' value='Enviar'>");
                    out.println("</form>");
                    out.println("<br>");
                    out.println("<table>");
                    out.println("<tr>");
                    out.println("<th>ID Profesor</th>");
                    out.println("<th>Nombre </th>");
                    out.println("<th>Usuario</th>");
                    out.println("<th>Password</th>");
                    out.println("</tr>");
                    profesores = notasEJB.findAllProfesores();
                    for (Profesores p : profesores) {
                        out.println("<tr>");
                        out.println("<td>" + p.getId() + "</td>");
                        out.println("<td>" + p.getNombre() + "</td>");
                        out.println("<td>" + p.getNomUser() + "</td>");
                        out.println("<td>" + p.getPassword() + "</td>");
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

                        Profesores p1 = new Profesores(Integer.parseInt(id), nombre, usuario, contraseña);

                        Boolean modificar = notasEJB.ModificarProfesor(p1);

                        if (modificar) {
                            out.println("<h2>Profesor modificado correctamente</h2>");
                            out.println("<br");

                        } else {
                            out.println("<br");
                            out.println("<h2>Profesor no modificado</h2>");
                            out.println("<br>");
                        }

                        out.println("<br>");

                    }
                    break;
                case "BorrarProfesor":
                    // Código para borrar profesor
                    out.println("<div class='container'>");
                    out.println("<h1>Borrar Profesor</h1>");
                    out.println("<br>");

                    out.println("<form action='actionServlet' method='POST'>");
                    out.println("<label>ID Profesor: </label>");
                    out.println("<input type='text' name='id' required><br>");
                    out.println("<input type='hidden' name='action' value='BorrarProfesor'>");
                    out.println("<input type='submit' value='Enviar'>");
                    out.println("</form>");

                    out.println("<br>");
                    out.println("<table>");
                    out.println("<tr>");
                    out.println("<th>ID Profesor</th>");
                    out.println("<th>Nombre</th>");
                    out.println("<th>Usuario</th>");
                    out.println("<th>Password</th>");
                    out.println("</tr>");
                    profesores = notasEJB.findAllProfesores();
                    for (Profesores p : profesores) {
                        out.println("<tr>");
                        out.println("<td>" + p.getId() + "</td>");
                        out.println("<td>" + p.getNombre() + "</td>");
                        out.println("<td>" + p.getNomUser() + "</td>");
                        out.println("<td>" + p.getPassword() + "</td>");
                        out.println("</tr>");
                    }
                    out.println("</table>");

                    out.println("<br>");

                    // Si los campos estan vacios, no se inserta el alumno

                    if (request.getParameter("id") == null) {

                    } else {
                        // REcojo los datos del formulario
                        String id = request.getParameter("id");

                        // Creo el objeto
                        Profesores p1 = new Profesores(Integer.parseInt(id), null, null, null);

                        Boolean modificar = notasEJB.borrarProfesor(p1);

                        if (modificar) {
                            out.println("<h2>Profesor borrado correctamente</h2>");
                            out.println("<br");

                        } else {

                            out.println("<h2>Profesor no borrado</h2>");
                            out.println("<br>");
                        }
                        // out.println("</div>");
                    }
                    out.println("<br>");
                    break;
                case "ListarHistorialEventos":
                    // Código para listar historial de eventos
                    out.println("<div class='container'>");
                    out.println("<h1>Listar Historial de Eventos</h1>");
                    out.println("<br>");
                    out.println("<table>");
                    out.println("<tr>");
                    out.println("<th>ID Evento</th>");
                    out.println("<th>Tipo Evento</th>");
                    out.println("<th>Usuario</th>");
                    out.println("<th>Detalles</th>");
                    out.println("</tr>");
                    historial = notasEJB.findAllHistorialEventos();
                    for (Historial h : historial) {
                        out.println("<tr>");
                        out.println("<td>" + h.getId() + "</td>");
                        out.println("<td>" + h.getTipo() + "</td>");
                        out.println("<td>" + h.getUser() + "</td>");
                        out.println("<td>" + h.getDetalle() + "</td>");
                        out.println("</tr>");
                    }
                    out.println("</table>");
                    out.println("<br>");

                    break;
                case "InsertarHistorialEvento":
                    // Código para insertar historial de evento
                    out.println("<div class='container'>");
                    break;
                case "ModificarHistorialEvento":
                    // Código para modificar historial de evento
                    out.println("<div class='container'>");
                    break;
                case "BorrarHistorialEvento":
                    // Código para borrar historial de evento
                    out.println("<div class='container'>");
                    break;
                case "ListarHistorialPorEvento":
                    // Código para listar historial por evento
                    out.println("<div class='container'>");

                    out.println("<h1>Listar Historial de Eventos por Tipo</h1>");
                    out.println("<br>");

                    out.println("<form action='actionServlet' method='POST'>");
                    out.println("<label>Tipo Evento: </label>");
                    // Select con los tipos de eventos, Profesor, Alumno, Nota
                    out.println("<select name='tipo'>");
                    out.println("<option value='P'>Profesor</option>");
                    out.println("<option value='A'>Alumno</option>");
                    out.println("<option value='N'>Nota</option>");
                    out.println("</select>");
                    out.println("<input type='hidden' name='action' value='ListarHistorialPorEvento'>");
                    out.println("<input type='submit' value='Enviar'>");
                    out.println("</form>");

                    out.println("<br>");

                    // Si los campos estan vacios, no se inserta el alumno
                    if (request.getParameter("tipo") == null) {

                    } else {
                        // recojo los datos del formulario
                        String tipo = request.getParameter("tipo");

                        // Creo el objeto
                        Historial h1 = new Historial(tipo);

                        historial = notasEJB.findHistorialByTipo(h1);
                        out.println("<table>");
                        out.println("<tr>");
                        out.println("<th>ID Evento</th>");
                        out.println("<th>Tipo Evento</th>");
                        out.println("<th>Usuario</th>");
                        out.println("<th>Detalles</th>");
                        out.println("</tr>");
                        for (Historial h : historial) {
                            out.println("<tr>");
                            out.println("<td>" + h.getId() + "</td>");
                            out.println("<td>" + h.getTipo() + "</td>");
                            out.println("<td>" + h.getUser() + "</td>");
                            out.println("<td>" + h.getDetalle() + "</td>");
                            out.println("</tr>");
                        }
                        out.println("</table>");
                        out.println("<br>");

                        out.println("<br>");

                    }

                    break;
                default:
                    // Código si no se reconoce la opción
                    out.println("<div class='container'>");
                    break;
            }

            out.println("<input type='submit' value='Volver' onclick=\"history.back()\" class='btn-volver'>");
            out.println("<script>");
            out.println("function goBack() {");
            out.println("    window.history.back();");
            out.println("}");
            out.println("</script>");
            out.println("<br>   ");
            out.println("<br>   ");
            out.println("</div>");
            out.println("</body>");
            out.println("</main>");
            out.println("<footer class='footer'>");
            out.println("<p>2023 DAM M06 - Alberto Pérez</p>");
            out.println("</footer>");
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
