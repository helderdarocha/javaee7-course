/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca.web;

import biblioteca.Livro;
import biblioteca.dao.LivroDAO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 *
 * @author helderdarocha
 */
public class UpdateComando implements Comando {

    LivroDAO dao;
    public UpdateComando(LivroDAO dao) {
        this.dao = dao;
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
        
        Livro livro = new Livro();
        livro.setId(id);
        livro.setTitulo(titulo);
        livro.setIsbn(isbn);
        livro.setIdioma(idioma);

        dao.update(livro);

        return "/index.xhtml";
    }

}
