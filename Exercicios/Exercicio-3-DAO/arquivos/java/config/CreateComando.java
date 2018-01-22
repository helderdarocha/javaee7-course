/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca.web.config;

import biblioteca.web.Comando;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 *
 * @author helderdarocha
 */
public class CreateComando implements Comando {

    DataSource ds;

    public CreateComando(DataSource ds) {
        this.ds = ds;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection con = null;
        try {
            con = ds.getConnection();
            Statement stmt = con.createStatement();
            stmt.execute("CREATE TABLE Livro(id INTEGER PRIMARY KEY, isbn VARCHAR(13) UNIQUE, titulo VARCHAR(256), idioma CHAR(2))");
            stmt.close();
            con.close();

            con = ds.getConnection();
            PreparedStatement pstmt = con.prepareStatement("INSERT INTO Livro (id, isbn, titulo, idioma) VALUES (?,?,?,?)");
            addLivro(pstmt, 1, "0785819118", "Origin of Species", "en");
            addLivro(pstmt, 2, "1906787271", "Plato's Republic", "en");
            addLivro(pstmt, 3, "9720301600", "Os Lusíadas", "pt");
            addLivro(pstmt, 4, "0140624155", "Les Miserables", "fr");
            addLivro(pstmt, 5, "5779310157", "Евгений Онегин", "ru");
        } catch (SQLException ex) {
            throw new ServletException(ex);
        } finally {
            try { con.close(); } catch (SQLException ex) {}
        }
        
        return "/livros";
        
    }

    private void addLivro(PreparedStatement pstmt, int id, String isbn, String titulo, String idioma) throws SQLException {
        pstmt.setInt(1, id);
        pstmt.setString(2, isbn);
        pstmt.setString(3, titulo);
        pstmt.setString(4, idioma);
        pstmt.executeUpdate();
    }

}
