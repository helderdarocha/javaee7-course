/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca.ws.client;

import biblioteca.jpa.Assunto;
import biblioteca.ws.AssuntoWebService;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collection;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;

/**
 *
 * @author helderdarocha
 */
public class AssuntoSoapClient {
    public static void main(String[] args) throws MalformedURLException {
        Service service = Service.create(
                new URL("http://localhost:8080/biblioteca-ws-soap/AssuntoWebService?WSDL"),
                new QName("http://biblioteca.ws", "AssuntoWebService"));
        AssuntoWebService assuntoService = service.getPort(AssuntoWebService.class);
        Collection<Assunto> assuntos = assuntoService.getRoots();
        for(Assunto assunto: assuntos) {
            System.out.println(assunto.getId().getDdcClass() + ": " + assunto.getDescricao());
        }
    }
}
