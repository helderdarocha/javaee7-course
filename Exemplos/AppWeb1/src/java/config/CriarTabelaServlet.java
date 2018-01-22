/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package config;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 *
 * @author helderdarocha
 */
@WebServlet(name = "CriarTabelaServlet", urlPatterns = {"/CriarTabelaServlet"})
public class CriarTabelaServlet extends HttpServlet {
    @Resource(name = "filmes")
    private DataSource filmesDB;

    
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

            try {
                
                response.setContentType("text/html;charset=UTF-8");
                
                Connection con = filmesDB.getConnection();
                Statement stmt = con.createStatement();
                stmt.execute("DROP TABLE Filme");
                stmt.execute("CREATE TABLE Filme (id INTEGER PRIMARY KEY,"
                        + "titulo VARCHAR(64),"
                        + "diretor VARCHAR(64),"
                        + "imdb CHAR(9),"
                        + "duracao INTEGER,"
                        + "ano INTEGER)");
                con.close();
                
                try (PrintWriter out = response.getWriter()) {
                    /* TODO output your page here. You may use following sample code. */
                    out.println("<!DOCTYPE html>");
                    out.println("<html>");
                    out.println("<head>");
                    out.println("<title>Servlet CriarTabelaServlet</title>");
                    out.println("</head>");
                    out.println("<body>");
                    out.println("<h1>Tabela criada</h1>");
                    out.println("</body>");
                    out.println("</html>");
                }
            }   catch (SQLException ex) {
            Logger.getLogger(CriarTabelaServlet.class.getName()).log(Level.SEVERE, null, ex);
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
