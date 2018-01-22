
package filmes.ws;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the filmes.ws package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _GetAll_QNAME = new QName("http://ejb.filmes/", "getAll");
    private final static QName _GetAllResponse_QNAME = new QName("http://ejb.filmes/", "getAllResponse");
    private final static QName _CriarOuEditar_QNAME = new QName("http://ejb.filmes/", "criarOuEditar");
    private final static QName _DeleteResponse_QNAME = new QName("http://ejb.filmes/", "deleteResponse");
    private final static QName _Update_QNAME = new QName("http://ejb.filmes/", "update");
    private final static QName _CriarOuEditarResponse_QNAME = new QName("http://ejb.filmes/", "criarOuEditarResponse");
    private final static QName _UpdateResponse_QNAME = new QName("http://ejb.filmes/", "updateResponse");
    private final static QName _Delete_QNAME = new QName("http://ejb.filmes/", "delete");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: filmes.ws
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetAll }
     * 
     */
    public GetAll createGetAll() {
        return new GetAll();
    }

    /**
     * Create an instance of {@link CriarOuEditar }
     * 
     */
    public CriarOuEditar createCriarOuEditar() {
        return new CriarOuEditar();
    }

    /**
     * Create an instance of {@link DeleteResponse }
     * 
     */
    public DeleteResponse createDeleteResponse() {
        return new DeleteResponse();
    }

    /**
     * Create an instance of {@link GetAllResponse }
     * 
     */
    public GetAllResponse createGetAllResponse() {
        return new GetAllResponse();
    }

    /**
     * Create an instance of {@link Update }
     * 
     */
    public Update createUpdate() {
        return new Update();
    }

    /**
     * Create an instance of {@link Delete }
     * 
     */
    public Delete createDelete() {
        return new Delete();
    }

    /**
     * Create an instance of {@link CriarOuEditarResponse }
     * 
     */
    public CriarOuEditarResponse createCriarOuEditarResponse() {
        return new CriarOuEditarResponse();
    }

    /**
     * Create an instance of {@link UpdateResponse }
     * 
     */
    public UpdateResponse createUpdateResponse() {
        return new UpdateResponse();
    }

    /**
     * Create an instance of {@link Filme }
     * 
     */
    public Filme createFilme() {
        return new Filme();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAll }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ejb.filmes/", name = "getAll")
    public JAXBElement<GetAll> createGetAll(GetAll value) {
        return new JAXBElement<GetAll>(_GetAll_QNAME, GetAll.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ejb.filmes/", name = "getAllResponse")
    public JAXBElement<GetAllResponse> createGetAllResponse(GetAllResponse value) {
        return new JAXBElement<GetAllResponse>(_GetAllResponse_QNAME, GetAllResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CriarOuEditar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ejb.filmes/", name = "criarOuEditar")
    public JAXBElement<CriarOuEditar> createCriarOuEditar(CriarOuEditar value) {
        return new JAXBElement<CriarOuEditar>(_CriarOuEditar_QNAME, CriarOuEditar.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeleteResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ejb.filmes/", name = "deleteResponse")
    public JAXBElement<DeleteResponse> createDeleteResponse(DeleteResponse value) {
        return new JAXBElement<DeleteResponse>(_DeleteResponse_QNAME, DeleteResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Update }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ejb.filmes/", name = "update")
    public JAXBElement<Update> createUpdate(Update value) {
        return new JAXBElement<Update>(_Update_QNAME, Update.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CriarOuEditarResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ejb.filmes/", name = "criarOuEditarResponse")
    public JAXBElement<CriarOuEditarResponse> createCriarOuEditarResponse(CriarOuEditarResponse value) {
        return new JAXBElement<CriarOuEditarResponse>(_CriarOuEditarResponse_QNAME, CriarOuEditarResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdateResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ejb.filmes/", name = "updateResponse")
    public JAXBElement<UpdateResponse> createUpdateResponse(UpdateResponse value) {
        return new JAXBElement<UpdateResponse>(_UpdateResponse_QNAME, UpdateResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Delete }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ejb.filmes/", name = "delete")
    public JAXBElement<Delete> createDelete(Delete value) {
        return new JAXBElement<Delete>(_Delete_QNAME, Delete.class, null, value);
    }

}
