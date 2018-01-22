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
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;

/**
 *
 * @author helderdarocha
 */
class ComandoListar implements Comando {

    private DataSource ds;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        FilmesDAO dao = new FilmesDAOImpl(ds);
        try {
            List<Filme> filmes = dao.getFilmes();
            
            request.setAttribute("filmes", filmes);

            //return "/FilmesService/view/listar";
            return "/faces/listaDeFilmes.xhtml";

        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    @Override
    public void setDataSource(DataSource ds) {
        this.ds = ds;
    }

}
