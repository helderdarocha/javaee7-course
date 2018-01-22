package biblioteca.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import biblioteca.Biblioteca;
import biblioteca.Livro;

/**
 * Um servlet precisa ser mapeado a uma URL. Uma forma de fazer isto é através
 * da anotação @WebServlet. Outra é usabndo o arquivo web.xml. Este servlet foi
 * mapeado através do arquivo web.xml (confira na pasta WEB-INF)
 */
public class BibliotecaServlet extends HttpServlet {

    @Inject
    Biblioteca biblioteca;  // Isto é possível porque o bean está registrado em CDI (declara um escopo)

    // Este servlet responde apenas a método GET
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();

        Collection<Livro> livros = biblioteca.getLivros();

        out.println("<table border=1>");
        for (Livro livro : livros) {
            out.println("<tr>");
            out.println("<td>" + livro.getId() + "</td>");
            out.println("<td>" + livro.getIsbn() + "</td>");
            out.println("<td>" + livro.getTitulo() + "</td>");
            out.println("<td>" + livro.getIdioma() + "</td>");
            out.println("</tr>");
        }
        out.println("</table>");
        out.close();

    }
}
