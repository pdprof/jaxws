package pdprof.jaxws;

public class Hello {
	public String sayMessage(String msg) {
		System.out.println("Hello.sayMessage > ");
		try {
			int wait=20000;
			try { wait = Integer.parseInt(msg); } catch (NumberFormatException nfe) {}
			Thread.sleep(wait);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Hello.sayMessage : " + msg);
		System.out.println("Hello.sayMessage < ");
		return msg;
	}
}
