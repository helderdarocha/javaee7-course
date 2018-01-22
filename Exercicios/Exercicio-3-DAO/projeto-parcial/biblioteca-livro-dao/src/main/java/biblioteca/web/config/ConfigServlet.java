/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca.web.config;

import biblioteca.web.Comando;
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

@WebServlet("/config/*")
public class ConfigServlet extends HttpServlet {

    @Resource(mappedName = "jdbc/sample")
    DataSource ds;
    
    private Map<String, Comando> comandos = new HashMap<>();
    
    @Override
    public void init() {
        comandos.put("/create-table", new CreateComando(ds));
        comandos.put("/drop-table", new DropComando(ds));
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cmdString = request.getPathInfo();
        Comando comando = comandos.get(cmdString);
        String result = "/livros";
        if(comando != null) {
            result = comando.execute(request, response);
        }
        RequestDispatcher disp = request.getRequestDispatcher(result);
        disp.forward(request, response);
    }

}
