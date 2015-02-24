import java.net.*; import java.io.*; import java.util.*;
public class NameServer {
	NameTable table;
	public NameServer() {
		table = new NameTable();
	}
	public static void main(String[] args) {
		NameServer ns = new NameServer();
		System.out.println("NameServer started:");
		try {
			ServerSocket listener = new ServerSocket(Symbols.ServerPort);
			Socket s;
			while ( (s = listener.accept()) != null) {
				Thread t = new ServerThread(ns.table, s);
				t.start();
			}
		} catch (IOException e) {
			System.err.println("Server aborted:" + e);
		}
	}
}
