/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filmes.web;
;
import filmes.ejb.FilmeBeanEJB;
import filmes.entity.Filme;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@RequestScoped
@Named
public class FilmeBean {
    private String titulo;
    private String diretor;
    private String imdb;
    private int ano;
    private int duracao;
    private int filmeId;

    @EJB
    FilmeBeanEJB dao;
    
    public String criarOuEditarFilme() {
        Filme filme = new Filme();
        filme.setTitulo(titulo);
        filme.setDiretor(diretor);
        filme.setImdb(imdb);
        filme.setAno(ano);
        filme.setDuracao(duracao);
        filme.setId(filmeId);
        
        dao.criarOuEditar(filme);
        
        return "/ListarFilmes";
    }
    
    public List<Filme> getFilmes() {
        return dao.getAll();
    }
    
    public String remover(Filme f) {
        dao.delete(f);
        return "/ListarFilmes";
    }
    
    public String editar(Filme f) {
        this.titulo = f.getTitulo();
        this.diretor = f.getDiretor();
        this.imdb = f.getImdb();
        this.ano = f.getAno();
        this.duracao = f.getDuracao();
        this.filmeId = f.getId();
        return "/CriarFilme";
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDiretor() {
        return this.diretor;
    }

    public void setDiretor(String diretor) {
        this.diretor = diretor;
    }
    
    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public int getDuracao() {
        return duracao;
    }

    public void setDuracao(int duracao) {
        this.duracao = duracao;
    }
    
    public String getImdb() {
        return imdb;
    }

    public void setImdb(String imdb) {
        this.imdb = imdb;
    }
    
    public int getFilmeId() {
        return filmeId;
    }

    public void setFilmeId(int filmeId) {
        this.filmeId = filmeId;
    }

}
