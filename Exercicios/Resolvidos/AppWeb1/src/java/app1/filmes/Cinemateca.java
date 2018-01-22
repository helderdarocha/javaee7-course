package app1.filmes;

import java.util.ArrayList;
import java.util.List;

public class Cinemateca {
   private List<Filme> filmes = new ArrayList<>(); 
   
   public Cinemateca() {
       Filme f1 = new Filme();
       f1.setImdb("tt2543164");
       f1.setAno(2016);
       f1.setDiretor("Não sei");
       f1.setTitulo("A Chegada");
       f1.setDuracao(120);
       
       Filme f2 = new Filme();
       f2.setImdb("tt3748528");
       f2.setAno(2016);
       f2.setDiretor("???");
       f2.setTitulo("Rogue One");
       f2.setDuracao(120);
       
       filmes.add(f2);
       filmes.add(f1);
   }

    public List<Filme> getFilmes() {
        return filmes;
    }
}
