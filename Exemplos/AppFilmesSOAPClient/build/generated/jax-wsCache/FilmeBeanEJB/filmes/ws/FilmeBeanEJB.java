
package filmes.ws;

import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.10-b140803.1500
 * Generated source version: 2.2
 * 
 */
@WebService(name = "FilmeBeanEJB", targetNamespace = "http://ejb.filmes/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface FilmeBeanEJB {


    /**
     * 
     * @param arg0
     */
    @WebMethod
    @RequestWrapper(localName = "update", targetNamespace = "http://ejb.filmes/", className = "filmes.ws.Update")
    @ResponseWrapper(localName = "updateResponse", targetNamespace = "http://ejb.filmes/", className = "filmes.ws.UpdateResponse")
    @Action(input = "http://ejb.filmes/FilmeBeanEJB/updateRequest", output = "http://ejb.filmes/FilmeBeanEJB/updateResponse")
    public void update(
        @WebParam(name = "arg0", targetNamespace = "")
        Filme arg0);

    /**
     * 
     * @param arg0
     */
    @WebMethod
    @RequestWrapper(localName = "delete", targetNamespace = "http://ejb.filmes/", className = "filmes.ws.Delete")
    @ResponseWrapper(localName = "deleteResponse", targetNamespace = "http://ejb.filmes/", className = "filmes.ws.DeleteResponse")
    @Action(input = "http://ejb.filmes/FilmeBeanEJB/deleteRequest", output = "http://ejb.filmes/FilmeBeanEJB/deleteResponse")
    public void delete(
        @WebParam(name = "arg0", targetNamespace = "")
        Filme arg0);

    /**
     * 
     * @param arg0
     */
    @WebMethod
    @RequestWrapper(localName = "criarOuEditar", targetNamespace = "http://ejb.filmes/", className = "filmes.ws.CriarOuEditar")
    @ResponseWrapper(localName = "criarOuEditarResponse", targetNamespace = "http://ejb.filmes/", className = "filmes.ws.CriarOuEditarResponse")
    @Action(input = "http://ejb.filmes/FilmeBeanEJB/criarOuEditarRequest", output = "http://ejb.filmes/FilmeBeanEJB/criarOuEditarResponse")
    public void criarOuEditar(
        @WebParam(name = "arg0", targetNamespace = "")
        Filme arg0);

    /**
     * 
     * @return
     *     returns java.util.List<filmes.ws.Filme>
     */
    @WebMethod
    @WebResult(name = "filme", targetNamespace = "")
    @RequestWrapper(localName = "getAll", targetNamespace = "http://ejb.filmes/", className = "filmes.ws.GetAll")
    @ResponseWrapper(localName = "getAllResponse", targetNamespace = "http://ejb.filmes/", className = "filmes.ws.GetAllResponse")
    @Action(input = "http://ejb.filmes/FilmeBeanEJB/getAllRequest", output = "http://ejb.filmes/FilmeBeanEJB/getAllResponse")
    public List<Filme> getAll();

}