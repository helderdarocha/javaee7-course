package br.com.argonavis.javaee7.jsf.converter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named
@SessionScoped
public class TempoBean implements Serializable {
      
	private static final long serialVersionUID = -6006657680237638467L;
	
	private Date hoje = new Date();
	private Date amanha = new Date(new Date().getTime() + 86400000000L);
	private Date[] horarios = {hoje, amanha};
	
	public Date getHoje() {
		return hoje;
	}
	public void setHoje(Date hoje) {
		this.hoje = hoje;
	}
	public Date getAmanha() {
		return amanha;
	}
	public void setAmanha(Date amanha) {
		this.amanha = amanha;
	}
	public Date[] getHorarios() {
		return horarios;
	}
	public void setHorarios(Date[] horarios) {
		this.horarios = horarios;
	}
	

}
