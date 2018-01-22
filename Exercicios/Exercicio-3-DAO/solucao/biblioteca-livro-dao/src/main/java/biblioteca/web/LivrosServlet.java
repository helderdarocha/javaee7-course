package biblioteca.web;

import biblioteca.Livro;
import biblioteca.dao.LivroDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;

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
        
        request.setAttribute("livros", dao.getLivros());
        RequestDispatcher disp = request.getRequestDispatcher("/livros.xhtml");
        disp.forward(request, response);
        
    }

}
