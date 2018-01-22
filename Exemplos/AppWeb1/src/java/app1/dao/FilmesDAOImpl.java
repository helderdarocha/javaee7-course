package app1.dao;

import app1.filmes.Filme;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;

public class FilmesDAOImpl implements FilmesDAO {
    
    private DataSource ds;
    
    public FilmesDAOImpl(DataSource ds) {
        this.ds = ds;
    }

    @Override
    public List<Filme> getFilmes() throws SQLException {
        Connection con = ds.getConnection();
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * from Filme");
        
        List<Filme> filmes = new ArrayList<>();
        
        while(rs.next()) {
            Filme f = new Filme();     
            f.setId(rs.getInt("id"));
            f.setTitulo(rs.getString("titulo"));
            f.setDiretor(rs.getString("diretor"));
            f.setImdb(rs.getString("imdb"));
            f.setAno(rs.getInt("ano"));
            f.setDuracao(rs.getInt("duracao"));
            filmes.add(f);
        }
        return filmes;
    }

    @Override
    public Filme getByIMDB(String imdb) {
        return null;
    }

    @Override
    public void delete(int id) {
    }

    @Override
    public void update(Filme alterado) {
    }

    @Override
    public int insert(Filme novo) throws SQLException {
        Connection con = ds.getConnection();
        PreparedStatement stmt = con.prepareStatement("INSERT INTO Filme (id, titulo, diretor, imdb, ano, duracao) VALUES (?,?,?,?,?,?)");
        int id = nextID();
        stmt.setInt(1, id);
        stmt.setString(2, novo.getTitulo());
        stmt.setString(3, novo.getDiretor());
        stmt.setString(4, novo.getImdb());
        stmt.setInt(5, novo.getAno());
        stmt.setInt(6, novo.getDuracao());
        stmt.executeUpdate();
        return id;
    }
    
    private int nextID() throws SQLException {
        Connection con = ds.getConnection();
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT MAX(id) FROM Filme");
        if(rs.next()) {
            return rs.getInt(1) + 1;
        }
        return 1;
    }
}
