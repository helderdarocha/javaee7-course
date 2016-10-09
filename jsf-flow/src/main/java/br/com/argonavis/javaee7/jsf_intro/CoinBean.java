package br.com.argonavis.javaee7.jsf_intro;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class CoinBean implements Serializable {
    public String throwCoin() {
    	int coin = (int)(Math.random()*2);
    	if(coin == 0) {
    		return "coroa";
    	} else {
    		return "cara";
    	}
    }
}
