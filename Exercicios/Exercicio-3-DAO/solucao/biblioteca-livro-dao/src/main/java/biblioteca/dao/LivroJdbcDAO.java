/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca.dao;

import biblioteca.Livro;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import javax.annotation.Resource;
import javax.sql.DataSource;

public class LivroJdbcDAO implements LivroDAO {

    @Resource(mappedName = "jdbc/sample")
    DataSource ds;

    @Override
    public Livro findByID(int id) throws IOException {
        Connection con = null;
        Livro livro = null;
        try {
            con = ds.getConnection();
            PreparedStatement stmt = con.prepareStatement("select * from Livro where id = ?");
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                livro = new Livro();
                livro.setId(rs.getInt("id"));
                livro.setIsbn(rs.getString("isbn"));
                livro.setTitulo(rs.getString("titulo"));
                livro.setIdioma(rs.getString("idioma"));
            }

        } catch (SQLException ex) {
            throw new IOException(ex);
        } finally {
            try {if (con != null) {con.close();}} catch (SQLException ex) {}
        }
        return livro;
    }

    @Override
    public Livro findByISBN(String isbn) throws IOException {
        Connection con = null;
        Livro livro = null;
        try {
            con = ds.getConnection();
            PreparedStatement stmt = con.prepareStatement("select distinct * from Livro where isbn = ?");
            stmt.setString(1, isbn);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                livro = new Livro();
                livro.setId(rs.getInt("id"));
                livro.setIsbn(rs.getString("isbn"));
                livro.setTitulo(rs.getString("titulo"));
                livro.setIdioma(rs.getString("idioma"));
            }

        } catch (SQLException ex) {
            throw new IOException(ex);
        } finally {
            try {if (con != null) {con.close();}} catch (SQLException ex) {}
        }
        return livro;
    }

    @Override
    public Collection<Livro> getLivros() throws IOException {
        Connection con = null;
        Collection<Livro> livros = new ArrayList<>();
        try {
            con = ds.getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from Livro");

            while (rs.next()) {
                Livro livro = new Livro();
                livro.setId(rs.getInt("id"));
                livro.setIsbn(rs.getString("isbn"));
                livro.setTitulo(rs.getString("titulo"));
                livro.setIdioma(rs.getString("idioma"));
                livros.add(livro);
            }

        } catch (SQLException ex) {
            throw new IOException(ex);
        } finally {
            try {if (con != null) {con.close();}} catch (SQLException ex) {}
        }
        return livros;
    }

    @Override
    public int insert(Livro livro) throws IOException {
        if (livro.getId() != 0) {
            update(livro);
            return livro.getId();
        }

        Connection con = null;
        int id = 0;
        try {
            id = nextId();

            con = ds.getConnection();
            PreparedStatement pstmt = con.prepareStatement("INSERT INTO Livro (id, isbn, titulo, idioma) VALUES (?,?,?,?)");
            pstmt.setInt(1, id);
            pstmt.setString(2, livro.getIsbn());
            pstmt.setString(3, livro.getTitulo());
            pstmt.setString(4, livro.getIdioma());
            pstmt.executeUpdate();

        } catch (SQLException ex) {
            throw new IOException(ex);
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
            }
        }

        return id;
    }

    @Override
    public void update(Livro livro) throws IOException {
        Connection con = null;
        try {
            con = ds.getConnection();
            PreparedStatement pstmt = con.prepareStatement("UPDATE Livro SET isbn=?, titulo=?, idioma=? WHERE id = ?");
            pstmt.setString(1, livro.getIsbn());
            pstmt.setString(2, livro.getTitulo());
            pstmt.setString(3, livro.getIdioma());
            pstmt.setInt(4, livro.getId());
            pstmt.executeUpdate();

        } catch (SQLException ex) {
            throw new IOException(ex);
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
            }
        }
    }

    @Override
    public void delete(Livro livro) throws IOException {
        Connection con = null;
        try {
            con = ds.getConnection();
            PreparedStatement stmt = con.prepareStatement("DELETE FROM Livro WHERE id = ?");
            stmt.setInt(1, livro.getId());
            stmt.executeUpdate();

        } catch (SQLException ex) {
            throw new IOException(ex);
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
            }
        }
    }

    private int nextId() throws SQLException {
        Connection con = null;
        try {
            con = ds.getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select max(id) from Livro");

            if (rs.next()) {
                return rs.getInt(1) + 1;
            }
            return 1;
        } finally {
            if (con != null) {
                con.close();
            }
        }
    }

}
