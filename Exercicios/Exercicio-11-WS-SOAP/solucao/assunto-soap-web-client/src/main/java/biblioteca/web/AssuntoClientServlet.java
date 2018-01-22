/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca.web;

import biblioteca.jpa.Assunto;
import biblioteca.ws.AssuntoWebService;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.Collection;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;

/**
 *
 * @author helderdarocha
 */
@WebServlet(name = "AssuntoClientServlet", urlPatterns = {"/AssuntoClientServlet"})
public class AssuntoClientServlet extends HttpServlet {

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

        Service service = Service.create(
                new URL("http://localhost:8080/biblioteca-ws-soap/AssuntoWebService?WSDL"),
                new QName("http://biblioteca.ws", "AssuntoWebService"));
        AssuntoWebService assuntoService = service.getPort(AssuntoWebService.class);
        Collection<Assunto> assuntos = assuntoService.getRoots();

        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Assuntos</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<table style='border: solid red 1px'>");
            for (Assunto assunto : assuntos) {
                out.println("<tr><td>" + assunto.getId().getDdcClass() + ": " + assunto.getDescricao() + "</td></tr>");
                if (assunto.getAssuntos() != null) {
                    out.println("<tr><td><table  style='border: solid blue 1px'>");
                    for (Assunto assunto2 : assunto.getAssuntos()) {
                        out.println("<tr><td>" + assunto2.getId().getDdcClass() + ": " + assunto2.getDescricao() + "</td></tr>");
                        if (assunto.getAssuntos() != null) {
                            out.println("<tr><td><table  style='border: solid green 1px'>");
                            for (Assunto assunto3 : assunto2.getAssuntos()) {
                                out.println("<tr><td>" + assunto3.getId().getDdcClass() + ": " + assunto3.getDescricao() + "</td></tr>");
                            }
                            out.println("</table></td></tr>");
                        }
                    }
                    out.println("</table></td></tr>");
                }
            }
            out.println("</table>");
            out.println("</body>");
            out.println("</html>");
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
