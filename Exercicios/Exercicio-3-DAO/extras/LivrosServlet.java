package biblioteca.web;

import biblioteca.Livro;
import biblioteca.dao.LivroDAO;
import java.io.IOException;
import java.util.Collection;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/livros")
public class LivrosServlet extends HttpServlet {

    @Inject
    LivroDAO dao;
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Collection<Livro> livros = null;
        
        // Exercício: Use o DAO para obter a lista de livros
        
        request.setAttribute("livros", livros);
        RequestDispatcher disp = request.getRequestDispatcher("/livros.xhtml");
        disp.forward(request, response);
        
    }

}
