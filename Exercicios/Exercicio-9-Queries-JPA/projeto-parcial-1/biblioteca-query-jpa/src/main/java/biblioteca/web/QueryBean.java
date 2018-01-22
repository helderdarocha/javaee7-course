/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca.web;

import biblioteca.jpa.Autor;
import biblioteca.jpa.Editora;
import biblioteca.jpa.Livro;
import java.util.Collection;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

// Exercício - implementar todos os queries.
@Named(value = "queryBean")
@RequestScoped
public class QueryBean {

    @PersistenceContext(unitName = "biblioteca-PU")
    private EntityManager em;

    public Collection<Autor> getAutorPorNome() {
            // EXERCICIO 1 - selecione os autores cujo nome contém o substring "an"
            return null;
        }

        public Collection<Editora> getEditoraPorNome() {
            // EXERCICIO 2 - selecione as editoras cujo nome contém a letra "B"
            return null;
        }

        public Collection<Livro> getLivroPorTitulo() {
            // EXERCICIO 3 - selecione os livros cujos títulos contenham o substring "The"
            return null;
        }
        
        public Collection<Livro> getLivroPorAssunto() {
            // EXERCICIO 4 - selecione os livros cujo assunto tenha um código Dewey (ddcClass) iniciando em 5
            return null;
        }
        
        public Collection<Livro> getLivroPorAutor() {
            // EXERCICIO 5 - selecione os livros que tenham entre seus autores um autor com nome que começa com "Mary"
            return null;
        }

        public Collection<Autor> getAutorPorEditora() {
            // EXERCICIO 6 - selecione autores que possuem livros publicados por uma editora que tenha nome contendo o substring "Boo"
            return null;
        }

}
