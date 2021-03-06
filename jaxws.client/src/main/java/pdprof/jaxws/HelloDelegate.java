
package pdprof.jaxws;

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
 * JAX-WS RI 2.2.10
 * Generated source version: 2.2
 * 
 */
@WebService(name = "HelloDelegate", targetNamespace = "http://jaxws.pdprof/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface HelloDelegate {


    /**
     * 
     * @param arg0
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "sayMessage", targetNamespace = "http://jaxws.pdprof/", className = "pdprof.jaxws.SayMessage")
    @ResponseWrapper(localName = "sayMessageResponse", targetNamespace = "http://jaxws.pdprof/", className = "pdprof.jaxws.SayMessageResponse")
    @Action(input = "http://jaxws.pdprof/HelloDelegate/sayMessageRequest", output = "http://jaxws.pdprof/HelloDelegate/sayMessageResponse")
    public String sayMessage(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0);

}
