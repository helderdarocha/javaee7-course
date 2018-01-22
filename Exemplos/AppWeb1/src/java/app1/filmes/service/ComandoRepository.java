/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app1.filmes.service;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author helderdarocha
 */
public class ComandoRepository {
    private Map<String, Comando> comandos = new HashMap<>();
    private Map<String, View>    views    = new HashMap<>();
    
    public ComandoRepository() {
        comandos.put("/criar", new ComandoCriar());
        comandos.put("/listar", new ComandoListar());
        
        views.put("/view/listar", new ViewListar());
    }
    
    public Comando getComando(String nome) {
        return comandos.get(nome);
    }
    
    public View getView(String nome) {
        return views.get(nome);
    }
}
