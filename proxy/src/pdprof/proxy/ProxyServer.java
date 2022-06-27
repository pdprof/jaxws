package pdprof.proxy;

import java.net.ServerSocket;
import java.net.Socket;

public class ProxyServer {

	public static void main(String[] args) {
		try {
			ServerSocket server = new ServerSocket(8080);
			while (true) {
				Socket p = server.accept();
				ProxyWorker worker = new ProxyWorker(p);
				new Thread(worker).start();
			}
		} catch (Exception e) {
		}
	}
}
