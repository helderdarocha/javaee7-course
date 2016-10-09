package br.com.argonavis.javaee7.jsf.facelets;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named("listings")
@RequestScoped
public class ListingsBean {

	public String getDataTableCode() {
		return "<h:dataTable value=\"#{cinemateca.filmes}\" var=\"filme\">\n" + 
				"	<h:column>\n" + 
				"		<f:facet name=\"header\">Título</f:facet>\n" + 
				"		#{filme.titulo}\n" + 
				"	</h:column>\n" + 
				"	<h:column>\n" + 
				"		<f:facet name=\"header\">Diretor</f:facet>\n" + 
				"		#{filme.diretor}\n" + 
				"	</h:column>\n" + 
				"	<h:column>\n" + 
				"		<f:facet name=\"header\">Ano</f:facet>\n" + 
				"		#{filme.ano}\n" + 
				"	</h:column>\n" + 
				"</h:dataTable>";
	}
	
	public String getDataTableCode2() {
		return "<h:dataTable value=\"#{cinemateca.filmes}\" var=\"filme\" rows=\"3\" first=\"2\">\n" + 
				"	<h:column>\n" + 
				"		<f:facet name=\"header\">Título</f:facet>\n" + 
				"		#{filme.titulo}\n" + 
				"	</h:column>\n" + 
				"	<h:column>\n" + 
				"		<f:facet name=\"header\">Diretor</f:facet>\n" + 
				"		#{filme.diretor}\n" + 
				"	</h:column>\n" + 
				"	<h:column>\n" + 
				"		<f:facet name=\"header\">Ano</f:facet>\n" + 
				"		#{filme.ano}\n" + 
				"	</h:column>\n" + 
				"</h:dataTable>";
	}
	
	public String getPanelGridCode() {
		return "<h:panelGrid columns=\"3\">\n" + 
				"	<h:inputText value=\"1.1\" />\n" + 
				"	<h:inputText value=\"1.2\" />\n" + 
				"\n" + 
				"	<h:panelGroup>\n" + 
				"		<h:panelGrid>\n" + 
				"			<h:inputText value=\"1.3.a\" />\n" + 
				"			<h:inputText value=\"1.3.b\" />\n" + 
				"			<h:inputText value=\"1.3.c\" />\n" + 
				"		</h:panelGrid>\n" + 
				"	</h:panelGroup>\n" + 
				"\n" + 
				"	<h:inputText value=\"2.1\" />\n" + 
				"	<h:inputText value=\"2.2\" />\n" + 
				"	<h:inputText value=\"2.3\" />\n" + 
				"\n" + 
				"</h:panelGrid>";
	}
	
	public String getShortCode() {
		return "<h:outputText value=\"#{bean.shortCode}\" escape=\"true\" />";
	}
	
	public String getOutputFormatCode() {
		return "<h:outputFormat value=\"O número da sorte de hoje é {1} e o de ontem foi {0}.\">\n" + 
				"	<f:param value=\"#{bean.sorte}\" />\n" + 
				"	<f:param value=\"#{bean.jogar()}\" />\n" + 
				"</h:outputFormat>";
	}
	
	public String getSelectOneListboxCode() {
		return "<h:selectOneListbox value=\"#{bean.filme}\">\n" + 
				"	<f:selectItems value=\"#{cinemateca.filmes}\" \n" + 
				"				   var=\"filme\" \n" + 
				"				   itemLabel=\"#{filme.titulo}\" \n" + 
				"				   itemValue=\"#{filme.imdb}\" />\n" + 
				"</h:selectOneListbox>";
	}
	public String getSelectOneMenuCode() {
		return "<h:selectOneMenu value=\"#{bean.filme}\">\n" + 
				"	<f:selectItems value=\"#{cinemateca.filmes}\" \n" + 
				"				   var=\"filme\" \n" + 
				"				   itemLabel=\"#{filme.titulo}\" \n" + 
				"				   itemValue=\"#{filme.imdb}\" />\n" + 
				"</h:selectOneMenu>";
	}
	public String getSelectOneRadioCode() {
		return "<h:selectOneRadio value=\"#{bean.filme}\">\n" + 
				"	<f:selectItems value=\"#{cinemateca.filmes}\" \n" + 
				"				   var=\"filme\" \n" + 
				"				   itemLabel=\"#{filme.titulo}\" \n" + 
				"				   itemValue=\"#{filme.imdb}\" />\n" + 
				"</h:selectOneRadio>";
	}
	public String getSelectManyListboxCode() {
		return "<h:selectManyListbox value=\"#{bean.selecaoFilmes}\">\n" + 
				"	<f:selectItems value=\"#{cinemateca.filmes}\" \n" + 
				"				   var=\"filme\" \n" + 
				"				   itemLabel=\"#{filme.titulo}\" \n" + 
				"				   itemValue=\"#{filme.imdb}\" />\n" + 
				"</h:selectManyListbox>";
	}
	public String getSelectManyMenuCode() {
		return "<h:selectManyMenu value=\"#{bean.turnos}\">\n" + 
				"	<f:selectItem itemValue=\"1\" itemLabel=\"manha\" />\n" + 
				"	<f:selectItem itemValue=\"2\" itemLabel=\"tarde\" />\n" + 
				"	<f:selectItem itemValue=\"3\" itemLabel=\"noite\" />\n" + 
				"</h:selectManyMenu>";
	}
	public String getSelectManyCheckboxCode() {
		return "<h:selectManyCheckbox value=\"#{bean.turnos}\">\n" + 
				"	<f:selectItem itemValue=\"1\" itemLabel=\"manha\" />\n" + 
				"	<f:selectItem itemValue=\"2\" itemLabel=\"tarde\" />\n" + 
				"	<f:selectItem itemValue=\"3\" itemLabel=\"noite\" />\n" + 
				"</h:selectManyCheckbox>";
	}

}
