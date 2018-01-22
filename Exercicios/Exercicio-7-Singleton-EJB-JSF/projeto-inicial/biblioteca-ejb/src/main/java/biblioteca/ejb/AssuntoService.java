/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca.ejb;

import biblioteca.jpa.Assunto;
import java.util.Collection;
import javax.ejb.Local;

@Local
public interface AssuntoService {
    void configure();
    Assunto getAssunto(String cdd, int summary);
    Collection<Assunto> getRoots();
    Collection<Assunto> getAll();
    long dataSize();
}
