package pdprof.jaxws;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.Source;
import javax.xml.ws.Dispatch;

/**
 * Servlet implementation class JaxWsClient
 */
@WebServlet("/client")
public class JaxWsClient extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public JaxWsClient() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mode = request.getParameter("mode");
		
		String proxyhost = request.getParameter("proxyhost");
		String proxyport = request.getParameter("proxyport");
		if (proxyhost == null) 
			proxyhost = "localhost";
		if (proxyport == null)
			proxyport = "8080";
		
		String msg = request.getParameter("time");
		if (msg == null)
			msg = "20000";
		
		String host = request.getParameter("host");
		String port = request.getParameter("port");
		if (host == null)
			host = "localhost";
		if (port == null)
			port = "9443";
		
		HelloPortProxy proxy = new HelloPortProxy();
		proxy.setEndpoint(proxy.getEndpoint().replace("localhost", host).replace("9443", port));
		proxy.setConnectTimeout(30); // CONNECT_TIMEOUT
		proxy.setResponseTimeout(25); // RESPONSE_TIMEOUT
		if (mode != null) {
			proxy.setProxyHostAndPort(proxyhost, proxyport);
			proxy.setProxyEnabled(true);
		}
		System.out.println("> call sayHello");
		msg = proxy.sayMessage(msg);
		System.out.println("< call sayHello");
		response.getWriter().append("Served at: ").append(request.getRequestURL()).append("?time=").append(msg)
			.append("&proxyhost=").append(proxyhost).append("&proxyport=").append(proxyport)
			.append("&host=").append(host).append("&port=").append(port)
			.append(" ... Add &mode=noproxy not to use proxy");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
