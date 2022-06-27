package pdprof.jaxws;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.transform.Source;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.Dispatch;
import javax.xml.ws.Service;
import javax.xml.ws.soap.SOAPBinding;
import javax.xml.ws.Action;

public class HelloPortProxy{

    protected Descriptor _descriptor;

    public class Descriptor {
        private pdprof.jaxws.HelloService _service = null;
        private pdprof.jaxws.HelloDelegate _proxy = null;
        private Dispatch<Source> _dispatch = null;
        private boolean _useJNDIOnly = false;
        private String proxyhost="localhost";
        private String proxyport="8080";

        public Descriptor() {
            init();
        }

        public Descriptor(URL wsdlLocation, QName serviceName) {
            _service = new pdprof.jaxws.HelloService(wsdlLocation, serviceName);
            initCommon();
        }

        public void init() {
            _service = null;
            _proxy = null;
            _dispatch = null;
            try
            {
                InitialContext ctx = new InitialContext();
                _service = (pdprof.jaxws.HelloService)ctx.lookup("java:comp/env/service/HelloService");
            }
            catch (NamingException e)
            {
                if ("true".equalsIgnoreCase(System.getProperty("DEBUG_PROXY"))) {
                    System.out.println("JNDI lookup failure: javax.naming.NamingException: " + e.getMessage());
                    e.printStackTrace(System.out);
                }
            }

            if (_service == null && !_useJNDIOnly)
                _service = new pdprof.jaxws.HelloService();
            initCommon();
        }

        private void initCommon() {
            _proxy = _service.getHelloPort();
        }

        public pdprof.jaxws.HelloDelegate getProxy() {
            return _proxy;
        }

        public void useJNDIOnly(boolean useJNDIOnly) {
            _useJNDIOnly = useJNDIOnly;
            init();
        }

        public Dispatch<Source> getDispatch() {
            if (_dispatch == null ) {
                QName portQName = new QName("", "HelloPort");
                _dispatch = _service.createDispatch(portQName, Source.class, Service.Mode.MESSAGE);

                String proxyEndpointUrl = getEndpoint();
                BindingProvider bp = (BindingProvider) _dispatch;
                bp.getRequestContext().put(com.ibm.wsspi.webservices.Constants.RESPONSE_TIMEOUT_PROPERTY, "7");
                String dispatchEndpointUrl = (String) bp.getRequestContext().get(BindingProvider.ENDPOINT_ADDRESS_PROPERTY);
               
                if (!dispatchEndpointUrl.equals(proxyEndpointUrl))
                    bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, proxyEndpointUrl);
            }
            return _dispatch;
        }

        public String getEndpoint() {
            BindingProvider bp = (BindingProvider) _proxy;
            return (String) bp.getRequestContext().get(BindingProvider.ENDPOINT_ADDRESS_PROPERTY);
        }

        public void setEndpoint(String endpointUrl) {
            BindingProvider bp = (BindingProvider) _proxy;
            bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, endpointUrl);

            if (_dispatch != null ) {
                bp = (BindingProvider) _dispatch;
                bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, endpointUrl);
            }
        }

        public void setMTOMEnabled(boolean enable) {
            SOAPBinding binding = (SOAPBinding) ((BindingProvider) _proxy).getBinding();
            binding.setMTOMEnabled(enable);
        }
        
        public void setConnectTimeout(String timeout) {
            BindingProvider bp = (BindingProvider) _proxy;
            bp.getRequestContext().put(com.ibm.wsspi.webservices.Constants.CONNECTION_TIMEOUT_PROPERTY, timeout);
        }
        
        public void setResponseTimeout(String timeout) {
            BindingProvider bp = (BindingProvider) _proxy;
            bp.getRequestContext().put(com.ibm.wsspi.webservices.Constants.RESPONSE_TIMEOUT_PROPERTY, timeout);
        }
        
        public void setProxyHostAndPort(String host, String port) {
        	proxyhost = host;
        	try {
        		Integer.parseInt(port); 
        		proxyport = port;
        	} catch (NumberFormatException e) {
        		proxyport = "8080";
        	}
        }
        
        public void setProxyEnabled(boolean enable) {
        	if (enable) {
	            BindingProvider bp = (BindingProvider) _proxy;
	            bp.getRequestContext().put(com.ibm.wsspi.webservices.Constants.HTTP_PROXYHOST_PROPERTY, proxyhost);
	            bp.getRequestContext().put(com.ibm.wsspi.webservices.Constants.HTTP_PROXYPORT_PROPERTY, proxyport);
	            bp.getRequestContext().put(com.ibm.wsspi.webservices.Constants.HTTP_PROXYUSER_PROPERTY, "pdprof");
	            bp.getRequestContext().put(com.ibm.wsspi.webservices.Constants.HTTP_PROXYPASSWORD_PROPERTY, "passw0rd");
	            bp.getRequestContext().put(com.ibm.wsspi.webservices.Constants.HTTPS_PROXYHOST_PROPERTY, proxyhost);
	            bp.getRequestContext().put(com.ibm.wsspi.webservices.Constants.HTTPS_PROXYPORT_PROPERTY, proxyport);
	            bp.getRequestContext().put(com.ibm.wsspi.webservices.Constants.HTTPS_PROXYUSER_PROPERTY, "pdprof");
	            bp.getRequestContext().put(com.ibm.wsspi.webservices.Constants.HTTPS_PROXYPASSWORD_PROPERTY, "passw0rd");
        	}
        }
    }

    public HelloPortProxy() {
        _descriptor = new Descriptor();
        _descriptor.setMTOMEnabled(false);
    }

    public HelloPortProxy(URL wsdlLocation, QName serviceName) {
        _descriptor = new Descriptor(wsdlLocation, serviceName);
        _descriptor.setMTOMEnabled(false);
    }
    
    public void setConnectTimeout(int timeout) {
    	_descriptor.setConnectTimeout(Integer.toString(timeout));
    }
    
    public void setResponseTimeout(int timeout) {
    	_descriptor.setResponseTimeout(Integer.toString(timeout));
    }

    public Descriptor _getDescriptor() {
        return _descriptor;
    }
    
    public void setProxyEnabled(boolean enable) {
    	_descriptor.setProxyEnabled(enable);
    }
    
    public void setProxyHostAndPort(String host, String port) {
    	_descriptor.setProxyHostAndPort(host, port);
    }

    public String sayMessage(String arg0) {
        return _getDescriptor().getProxy().sayMessage(arg0);
    }
    
}