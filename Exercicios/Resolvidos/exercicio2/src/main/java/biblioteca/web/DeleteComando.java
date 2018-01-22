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
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 *
 * @author helderdarocha
 */
public class DeleteComando implements Comando {
    
    DataSource ds;
    public DeleteComando(DataSource ds) {
        this.ds = ds;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        String idStr = request.getParameter("id");
        if(idStr == null) {
            return "/index.html";
        }
        int id = Integer.parseInt(idStr);
        
        Connection con = null;
        try {
            con = ds.getConnection();
            PreparedStatement stmt = con.prepareStatement("DELETE FROM Livro WHERE id = ?");
            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException ex) {
            throw new ServletException(ex);
        } finally {
            try { con.close(); } catch (SQLException ex) {}
        }
        
        return "/livros";
    }
    
}
