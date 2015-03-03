import java.util.*; import java.net.*;
public class NameTable {
	class NameEntry {
		public String procName;
		public InetSocketAddress addr;
		public NameEntry(String pName, String host, int port){
			procName = pName;
			addr = new InetSocketAddress(host, port);
		}
	}
	ArrayList<NameEntry> table = new ArrayList<NameEntry>(); 
	public synchronized InetSocketAddress search(String procName) {
		System.out.println("Searching " + procName);
		for (NameEntry entry: table)
			if (procName.equals(entry.procName)) return entry.addr;
				return null;
	}
	// returns 0 if old value replaced, otherwise 1
	public synchronized int insert(String procName, String hostName, int portNumber) {
		System.out.println("Inserting " + procName);
		int retValue = 1;
		for (NameEntry entry: table)
			if (procName.equals(entry.procName)) { 
				table.remove(entry);
				retValue = 0;
			}
		table.add(new NameEntry(procName,hostName, portNumber));
		notifyAll();
		return retValue;
	}
	public synchronized InetSocketAddress blockingFind(String procName) {
		System.out.println("blockingFind " + procName);
		InetSocketAddress addr = search(procName);
		while (addr == null) {
			Util.myWait(this);
			addr = search(procName);
		}
		return addr;
	}
	public synchronized void clear() {
		table.clear();
	}
}
