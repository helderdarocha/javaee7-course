/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca.ws;

import biblioteca.jpa.Assunto;
import java.util.Collection;

// EXERCICIO: configure esta interface como uma Endpoint Interface SOAP (opcional)
// Declare usando o mesmo targetNamespace declarado no EJB.
public interface AssuntoWebService {
    Assunto getAssunto(String cdd, int summary);
    Collection<Assunto> getRoots();
    Collection<Assunto> getAll();
    Collection<Assunto> getByCriteria(int summary, String classe, String descricao);
    long dataSize();
    
}
