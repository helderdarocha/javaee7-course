/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app1.filmes.service;

import app1.filmes.Filme;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author helderdarocha
 */
class ViewListar implements View {

    public ViewListar() {
    }

    @Override
    public void render(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       
        List<Filme> filmes = (List)request.getAttribute("filmes");
        
        try (PrintWriter out = response.getWriter()) {
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Lista de Filmes</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>Lista de Filmes</h1>");

                out.println("<table border>");
                for (Filme f : filmes) {
                    out.println("<tr>");
                    out.println("<td>" + f.getImdb() + "</td>");
                    out.println("<td>" + f.getTitulo() + "</td>");
                    out.println("<td>" + f.getDiretor() + "</td>");
                    out.println("<td>" + f.getAno() + "</td>");
                    out.println("<td>" + f.getDuracao() + "</td>");
                    out.println("</tr>");
                }
                out.println("</table>");

                out.println("</body>");
                out.println("</html>");
            }
    }
 
}
