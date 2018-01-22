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

@WebServlet(name = "AssuntoClientServlet", urlPatterns = {"/AssuntoClientServlet"})
public class AssuntoClientServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        // 
        Service service = Service.create(
                new URL("EXERCICIO"), // EXERCICIO: Coloque aqui a URL do seu WSDL, e
                new QName("EXERCICIO", "EXERCICIO")); // TargetNamespace, e Nome do serviço
        
        // EXERCICIO: Obtenha um port (proxy) para a referencia abaixo, a partir
        // do service configurado acima.
        AssuntoWebService assuntoService = null;
        
        // EXERCICIO: Chame métodos remotos do serviço através de sua
        // interface e proxy. Por exemplo, obtenha uma lista de Assunto
        Collection<Assunto> assuntos = null;
        
        // EXERCICIO: mostre os dados. Opções: grave um atributo de request,
        // contendo os dados (request.setAttribute), despache a requisição
        // para um JSF e acesse os dados via JSTL; ou imprima diretamente
        // o HTML usando métodos de PrintWriter abaixo.

        try (PrintWriter out = response.getWriter()) {
            String dados = "assuntos";
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Assuntos</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<p>" + dados + "</p>");
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
