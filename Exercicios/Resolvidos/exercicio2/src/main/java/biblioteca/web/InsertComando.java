/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
public class InsertComando implements Comando {

    DataSource ds;

    public InsertComando(DataSource ds) {
        this.ds = ds;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String isbn = request.getParameter("isbn");
        String titulo = request.getParameter("titulo");
        String idioma = request.getParameter("idioma");

        int id = nextId();

        Connection con = null;
        try {
            con = ds.getConnection();
            PreparedStatement pstmt = con.prepareStatement("INSERT INTO Livro (id, isbn, titulo, idioma) VALUES (?,?,?,?)");
            pstmt.setInt(1, id);
            pstmt.setString(2, isbn);
            pstmt.setString(3, titulo);
            pstmt.setString(4, idioma);
            pstmt.executeUpdate();

        } catch (SQLException ex) {
            throw new ServletException(ex);
        } finally {
            try { if(con != null) con.close(); } catch (SQLException ex) {}
        }

        return "/livros";
    }

    private int nextId() throws ServletException, IOException {
        Connection con = null;
        try {
            con = ds.getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select max(id) from Livro");

            if (rs.next()) {
                return rs.getInt(1) + 1;
            }
            return 1;
        } catch (SQLException ex) {
            throw new ServletException(ex);
        } finally {
            try {
                if(con != null) con.close();
            } catch (SQLException ex) {
            }
        }
    }

}
