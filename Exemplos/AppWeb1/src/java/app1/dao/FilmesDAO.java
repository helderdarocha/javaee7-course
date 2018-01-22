package app1.dao;

import app1.filmes.Filme;
import java.sql.SQLException;
import java.util.List;

public interface FilmesDAO {
    List<Filme> getFilmes() throws SQLException;
    Filme getByIMDB(String imdb) throws SQLException;
    
    void delete(int id) throws SQLException;
    void update(Filme alterado) throws SQLException;
    int insert(Filme novo) throws SQLException;

}
