
package pdprof.jaxws;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the pdprof.jaxws package. 
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

    private final static QName _SayMessage_QNAME = new QName("http://jaxws.pdprof/", "sayMessage");
    private final static QName _SayMessageResponse_QNAME = new QName("http://jaxws.pdprof/", "sayMessageResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: pdprof.jaxws
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link SayMessage }
     * 
     */
    public SayMessage createSayMessage() {
        return new SayMessage();
    }

    /**
     * Create an instance of {@link SayMessageResponse }
     * 
     */
    public SayMessageResponse createSayMessageResponse() {
        return new SayMessageResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SayMessage }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://jaxws.pdprof/", name = "sayMessage")
    public JAXBElement<SayMessage> createSayMessage(SayMessage value) {
        return new JAXBElement<SayMessage>(_SayMessage_QNAME, SayMessage.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SayMessageResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://jaxws.pdprof/", name = "sayMessageResponse")
    public JAXBElement<SayMessageResponse> createSayMessageResponse(SayMessageResponse value) {
        return new JAXBElement<SayMessageResponse>(_SayMessageResponse_QNAME, SayMessageResponse.class, null, value);
    }

}
