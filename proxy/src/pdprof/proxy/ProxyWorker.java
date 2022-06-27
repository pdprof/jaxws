package pdprof.proxy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ProxyWorker implements Runnable {

	static int count = 0;
	static Object countLock = new Object();

	int id = 0;
	Socket cPort = null;

	ProxyWorker(Socket p) {
		synchronized (countLock) {
			id = count;
			countUp();
		}
		cPort = p;
	}

	@Override
	public void run() {
		BufferedReader reader = null;
		PrintWriter writer = null;
		try {
			try {
				reader = new BufferedReader(new InputStreamReader(cPort.getInputStream()));
				writer = new PrintWriter(cPort.getOutputStream(), true);
				String line = reader.readLine();
				
				while(!line.equals("")) {
					System.out.println(line);
					line = reader.readLine();
				}
				
				writer.println("HTTP/1.0 200 Connection Established");
				writer.println("Proxy-agent: Apache/2.4.53 (Unix)");
				writer.println("");
				
				while (true) {
					line = reader.readLine();
					if(line == null) {
						System.out.println("readLine() retruned null");
						break;
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (reader != null)
					reader.close();
				if (writer != null)
					writer.close();
				if (cPort != null)
					cPort.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		countDown();
	}

	public static void countUp() {
		count = count + 1;
	}

	public static void countDown() {
		synchronized (countLock) {
			count = count - 1;
		}
	}
}
