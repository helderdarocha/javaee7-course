/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app1.filmes.service;

import app1.dao.FilmesDAO;
import app1.dao.FilmesDAOImpl;
import app1.filmes.Filme;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 *
 * @author helderdarocha
 */
class ComandoCriar implements Comando {

    private DataSource ds;
    
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        String imdb = request.getParameter("imdb");
        String titulo = request.getParameter("titulo");
        String diretor = request.getParameter("diretor");
        String anoStr = request.getParameter("ano");
        String duracaoStr = request.getParameter("duracao");
        
        int ano = 0;
        int duracao = 0;
        
        if(anoStr != null) {
            ano = Integer.parseInt(anoStr);
        }
        if(duracaoStr != null) {
            duracao = Integer.parseInt(duracaoStr);
        }
        
        Filme f = new Filme();
        f.setTitulo(titulo);
        f.setDiretor(diretor);
        f.setImdb(imdb);
        f.setAno(ano);
        f.setDuracao(duracao);
        
        FilmesDAO dao = new FilmesDAOImpl(ds);
        try {
            int id = dao.insert(f);
        } catch (SQLException ex) {
           throw new ServletException(ex);
        }
        
        return "/FilmesService/listar";

    }
    
    @Override
    public void setDataSource(DataSource ds) {
        this.ds = ds;
    }
    
}
