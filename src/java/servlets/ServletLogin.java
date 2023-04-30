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

/**
 *
 * @author alber
 */
@WebServlet(name = "ServletLogin", urlPatterns = { "/ServletLogin" })
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
            String usuario;
            String password;
            String rol;
            usuario = request.getParameter("username");
            password = request.getParameter("password");
            rol = request.getParameter("rol");

            Boolean validado = false;
            validado = notasEJB.validarUsuario(usuario, password, rol);
           
            List<Alumnos> l = notasEJB.findAllAlumnos();
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ServletActivitat</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Validado: " + validado + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

            //mostar pagina con valor validado
            
/* 
            if (validado) {
                if (rol.equals("alumno")) {
                    List<Alumnos> listaAlumnos = notasEJB.findAllAlumnos();
                    request.setAttribute("listaAlumnos", listaAlumnos);
                    request.getRequestDispatcher("/alumnos.jsp").forward(request, response);
                } else if (rol.equals("profesor")) {
                    List<Profesores> listaProfesores = notasEJB.findAllProfesores();
                    request.setAttribute("listaProfesores", listaProfesores);
                    request.getRequestDispatcher("/profesores.jsp").forward(request, response);
                }
            } else {
                request.getRequestDispatcher("/error.jsp").forward(request, response);
            }
        } catch (Exception e) {
            System.out.println("Error en el servlet de login: " + e.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }

    } */

            

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
