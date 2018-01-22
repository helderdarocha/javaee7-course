/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

@WebServlet("/admin/*")
public class AdminServlet extends HttpServlet {

    @Resource(mappedName = "jdbc/sample")
    DataSource ds;
    
    private Map<String, Comando> comandos = new HashMap<>();
    
    @Override
    public void init() {
        comandos.put("/insert", new InsertComando(ds));
        comandos.put("/update", new UpdateComando(ds));
        comandos.put("/delete", new DeleteComando(ds));
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
