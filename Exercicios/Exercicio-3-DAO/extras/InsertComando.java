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

/**
 *
 * @author helderdarocha
 */
public class InsertComando implements Comando {

    LivroDAO dao;
    public InsertComando(LivroDAO dao) {
        this.dao = dao;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String isbn = request.getParameter("isbn");
        String titulo = request.getParameter("titulo");
        String idioma = request.getParameter("idioma");

        // Exercício: Use o DAO para implementar este método

        return "/index.xhtml";
    }

}
