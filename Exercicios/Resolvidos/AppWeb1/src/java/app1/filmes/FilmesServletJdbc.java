/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app1.filmes;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

@WebServlet("/filmesJDBC")
public class FilmesServletJdbc extends HttpServlet {
    
    @Resource(name = "filmes")
    private DataSource filmesDB;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException {
        try {
            response.setContentType("text/html;charset=UTF-8");
            
            Connection con = filmesDB.getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * from Filme");
            
            try (PrintWriter out = response.getWriter()) {
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Lista de Filmes</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>Lista de Filmes</h1>");
                
                out.println("<table border>");
                while(rs.next()) {
                    out.println("<tr>");
                    out.println("<td>"+ rs.getInt("id") +"</td>");
                    out.println("<td>"+ rs.getString("imdb") +"</td>");
                    out.println("<td>"+ rs.getString("titulo") +"</td>");
                    out.println("<td>"+ rs.getString("diretor") +"</td>");
                    out.println("<td>"+ rs.getInt("ano") +"</td>");
                    out.println("<td>"+ rs.getInt("duracao") +"</td>");
                    out.println("</tr>");
                }
                out.println("</table>");
                
                out.println("</body>");
                out.println("</html>");
            }
        }   catch (SQLException ex) {
            Logger.getLogger(FilmesServletJdbc.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


}
