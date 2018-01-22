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
public class DeleteComando implements Comando {
    
    LivroDAO dao;
    public DeleteComando(LivroDAO dao) {
        this.dao = dao;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        String idStr = request.getParameter("id");
        if(idStr == null) {
            return "/index.html";
        }
        int id = Integer.parseInt(idStr);
        
        Livro livro = new Livro();
        livro.setId(id);
        dao.delete(livro);
        
        return "/livros";
    }
    
}
