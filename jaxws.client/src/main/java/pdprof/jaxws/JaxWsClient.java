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
		
		HelloPortProxy proxy = new HelloPortProxy();
		proxy.setConnectTimeout(25); // CONNECT_TIMEOUT
		proxy.setResponseTimeout(15); // RESPONSE_TIMEOUT
		if (mode != null) {
			proxy.setProxyHostAndPort(proxyhost, proxyport);
			proxy.setProxyEnabled(true);
		}
		System.out.println("> call sayHello");
		String msg = proxy.sayMessage("Hello!");
		System.out.println("< call sayHello");
		response.getWriter().append("Served at: ").append(request.getContextPath()).append(" & msg = ").append(msg);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
