/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca.web;

import biblioteca.dao.LivroDAO;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/admin/*")
public class AdminServlet extends HttpServlet {

    @Inject
    LivroDAO dao;
    
    private Map<String, Comando> comandos = new HashMap<>();
    
    @Override
    public void init() {
        comandos.put("/insert", new InsertComando(dao));
        comandos.put("/update", new UpdateComando(dao));
        comandos.put("/delete", new DeleteComando(dao));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String cmdString = request.getPathInfo();
        Comando comando = comandos.get(cmdString);
        String result = "/index.xhtml";
        if(comando != null) {
            result = comando.execute(request, response);
        }
        RequestDispatcher disp = request.getRequestDispatcher(result);
        disp.forward(request, response);
    }

}
