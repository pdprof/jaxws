package pdprof.jaxws;

import javax.jws.WebService;


@WebService (targetNamespace="http://jaxws.pdprof/", serviceName="HelloService", portName="HelloPort", wsdlLocation="WEB-INF/wsdl/HelloService.wsdl")
public class HelloDelegate{

    pdprof.jaxws.Hello _hello = null;

    public String sayMessage (String msg) {
        return _hello.sayMessage(msg);
    }

    public HelloDelegate() {
        _hello = new pdprof.jaxws.Hello(); }

}