/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 *
 * @author helderdarocha
 */
public class InsertComando implements Comando {

    DataSource ds;

    public InsertComando(DataSource ds) {
        this.ds = ds;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Exercício: leia os parâmetros passados via request (getParameter)

        int id = nextId(); // gera um ID para o próximo objeto

        Connection con = null;
        try {
            // Exercício: obtenha uma conexão do DataSource ds
            // Exercício: crie uma declaração insert com os dados obtidos (id, isbn, titulo, idioma) e execute
            // Exercício: descomente as duas linhas a seguir, se necessário

      //  } catch (SQLException ex) {
      //      throw new ServletException(ex);
        } finally {
            try { if(con != null) con.close(); } catch (SQLException ex) {}
        }

        return "/index.xhtml"; // URL relativa ao contexto da proxima URL local (página, servlet mapeado) a exibir
    }

    private int nextId() throws ServletException, IOException {
        Connection con = null;
        try {
            con = ds.getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select max(id) from Livro");

            if (rs.next()) {
                return rs.getInt(1) + 1;
            }
            return 1;
        } catch (SQLException ex) {
            throw new ServletException(ex);
        } finally {
            try {
                if(con != null) con.close();
            } catch (SQLException ex) {
            }
        }
    }

}
