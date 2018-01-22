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

    // Exercício: inclua uma referência para o objeto Biblioteca
    // (pode-se injetar, ou instanciar diretamente).

    // Este servlet responde apenas a método GET
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // A linha seguinte é importante para garantir que os acentos e
        // caracteres internacionais sejam impressos corretamente
        response.setContentType("text/html; charset=UTF-8");
        
        // Um objeto de saída é obtido do response
        PrintWriter out = response.getWriter();

        // Exercício: use a referência para Biblioteca para obter uma lista de livros

        // Exercício: imprima uma tabela em HTML com um livro em cada linha
        // Use um for(objeto : coleção) e imprima os atributos do livro em
        // cada <td>
        out.println("<table border=1>");
        // Exercício: repita para cada Livro
            out.println("<tr>");
            out.println("<td></td>");
            out.println("<td></td>");
            out.println("<td></td>");
            out.println("<td></td>");
            out.println("</tr>");
        // 
        out.println("</table>");
        out.close();

    }
}
