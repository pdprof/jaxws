package pdprof.jaxws;

public class Hello {
	public String sayMessage(String msg) {
		System.out.println("Hello.sayMessage > ");
		try {
			Thread.sleep(6000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Hello.sayMessage : " + msg);
		System.out.println("Hello.sayMessage < ");
		return msg;
	}
}
