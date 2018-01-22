package biblioteca.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

@WebServlet("/livros")
public class LivrosServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Resource(mappedName = "jdbc/sample")
    DataSource ds;
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection con = null;
        try {
            response.setContentType("text/html; charset=UTF-8");
            PrintWriter out = response.getWriter();

            // Exercício: obtenha a conexão, o statement e execute um query, para obter o ResultSet
            // (inclua as linhas necessárias e troque null abaixo pelo ResultSet obtido no query)
            con = ds.getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from Livro");

            out.println("<table border=1>");
            while (rs.next()) {
                // Exercício: troque 0 e null abaixo por chamadas aos métodos correspondentes de ResultSet
                // e obtenha o id, isbn, titulo e idioma para o registro atual do result set (getInt, getString, etc)
                int id = rs.getInt("id");
                String isbn = rs.getString("isbn");
                String titulo = rs.getString("titulo");
                String idioma = rs.getString("idioma");
                /////////////////////
                
                String deleteQuery = "?id=" + id;
                String editQuery   = "?id=" + id + "&isbn=" + isbn + "&titulo=" + URLEncoder.encode(titulo, "utf-8") + "&idioma=" + idioma;
                
                out.println("<tr>");
                out.println("<td>" + id + "</td>");
                out.println("<td>" + isbn + "</td>");
                out.println("<td>" + titulo + "</td>");
                out.println("<td>" + idioma + "</td>");
                out.println("<td><form method='post' action='"+request.getContextPath()+"/admin/delete" + deleteQuery + "'><button type='submit'>Remover</button></form></td>");
                out.println("<td><form method='post' action='"+request.getContextPath()+"/livro.xhtml" + editQuery + "'><button type='submit'>Editar</button></form></td>");
                out.println("</tr>");
            }
            out.println("</table>");
            
            out.println("<form action='"+request.getContextPath()+"/livro.xhtml'><button type='submit'>Inserir</button></form>");
                
        } catch (SQLException ex) {
            throw new ServletException(ex);
        } finally {
            try { if(con != null) con.close(); } catch (SQLException ex) {}
        }

    }
}
