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
    
    // Exercício: implemente os métodos do DAO (use os comandos do exercício anterior
    // como ponto de partida, ou os métodos que já estão prontos)

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
            // Exercício: implemente este método

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
            // Exercício: implemente este método

        } catch (SQLException ex) {
            throw new IOException(ex);
        } finally {
            try {if (con != null) {con.close();}} catch (SQLException ex) {}
        }
        return livros;
    }

    @Override
    public int insert(Livro livro) throws IOException {
        int id = 0;
        // Exercício: implemente este método
        return id;
    }

    @Override
    public void update(Livro livro) throws IOException {
        // Exercício: implemente este método
    }

    @Override
    public void delete(Livro livro) throws IOException {
        // Exercício: implemente este método
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
