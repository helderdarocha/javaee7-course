/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca.web;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 *
 * @author helderdarocha
 */
public class UpdateComando implements Comando {

    DataSource ds;

    public UpdateComando(DataSource ds) {
        this.ds = ds;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idStr = request.getParameter("id");
        if(idStr == null) {
            return "/index.html";
        }
        int id = Integer.parseInt(idStr);

        String isbn = request.getParameter("isbn");
        String titulo = request.getParameter("titulo");
        String idioma = request.getParameter("idioma");

        Connection con = null;
        try {
            con = ds.getConnection();
            // Exercício: obtenha um PreparedStatement, configure com parametros recebidos via Web e execute o update
        } catch (SQLException ex) {
            throw new ServletException(ex);
        } finally {
            try {
                if(con != null) con.close();
            } catch (SQLException ex) {
            }
        }

        return "/index.xhtml";
    }

}
