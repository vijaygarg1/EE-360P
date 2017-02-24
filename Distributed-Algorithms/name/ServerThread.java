package name;
import java.net.*; import java.io.*; import java.util.*;
public class ServerThread extends Thread {
	NameTable table;
	Socket theClient;
	public ServerThread(NameTable table, Socket s) {
		this.table = table;
		theClient = s;
	}
	public void run() {
		try {
			Scanner sc = new Scanner(theClient.getInputStream());
			PrintWriter pout = new PrintWriter(theClient.getOutputStream());
			String command = sc.nextLine();
			System.out.println("received:" + command);
			Scanner st = new Scanner(command);          
			String tag = st.next();
			if (tag.equals("search")) {
				InetSocketAddress addr = table.search(st.next());
				if (addr == null) pout.println(0 + " " + "nullhost");
				else pout.println(addr.getPort() + " " + addr.getHostName());
			} else if (tag.equals("insert")) {
				String name = st.next();
				String hostName = st.next();
				int port = st.nextInt();
				int retValue = table.insert(name, hostName, port);
				pout.println(retValue);
			} else if (tag.equals("blockingFind")) {
				InetSocketAddress addr = table.blockingFind(st.next());
				pout.println(addr.getPort() + " " + addr.getHostName());
			} else if (tag.equals("clear")) {
				table.clear();
			}
			pout.flush();
			theClient.close();
		} catch (IOException e) {
			System.err.println(e);
		}

	}
}

