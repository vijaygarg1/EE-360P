import java.util.*;
import java.net.*; import java.io.*;
public class Name {
	Scanner din;
	PrintStream pout;
	Socket server;
	public void getSocket() throws IOException {
		server = new Socket(Symbols.nameServer, Symbols.ServerPort);
		din = new Scanner(server.getInputStream());
		pout = new PrintStream(server.getOutputStream());
	}
	public int insertName(String procName, String hostName, int portnum)
			throws IOException {
		getSocket();
		pout.println("insert " + procName + " " + hostName + " " + portnum);
		pout.flush();
		int retValue = din.nextInt();
		server.close();
		return retValue;
	}
	public InetSocketAddress searchName(String procName, boolean isBlocking) 
			throws IOException {
		getSocket();
		if (isBlocking) pout.println("blockingFind " + procName);
		else pout.println("search " + procName);
		pout.flush();
		String result = din.nextLine();
		System.out.println("NameServer returned" + result);
		Scanner sc = new Scanner(result);
		server.close();
		int portnum = sc.nextInt();
		String hostName = sc.next();
		if (portnum == 0) return null; 
		else return new InetSocketAddress(hostName, portnum);
	}
	public void clear() throws IOException {
		getSocket();
		pout.println("clear " );
		pout.flush();
		server.close();
	}
	public static void main(String[] args) {
		Name myClient = new Name();
		try {
			myClient.insertName("hello1", "oak.ece.utexas.edu", 1000);
			InetSocketAddress pa = myClient.searchName("hello1",false);
			System.out.println(pa.getHostName() + ":" + pa.getPort());
		} catch (Exception e) {
			System.err.println("Server aborted:" + e);
		}
	}
}
