import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;
import java.net.*;
public class NameServiceImpl extends UnicastRemoteObject
        implements NameService {
    NameTable table;
    public NameServiceImpl() throws RemoteException {
	table = new NameTable();
    }
    public InetSocketAddress search(String s) throws RemoteException {
        return table.search(s);
    }
    public InetSocketAddress blockingFind(String s) throws RemoteException {
        return table.blockingFind(s);
    }
    public int insert(String s, String hostName, int portNumber)
            throws RemoteException {
            return table.insert(s, hostName, portNumber);
    }
    public static void main(String args[]) {
        // create security manager
	if (System.getSecurityManager() == null) {
        	System.setSecurityManager(new SecurityManager());
    	}

        try {
            NameServiceImpl obj = new NameServiceImpl();
            Naming.rebind("MyNameServer", obj);
            System.out.println("MyNameServer bound in registry");
        } catch (Exception e) {
            System.out.println("NameServiceImpl err: " + e.getMessage());
        }
    }
}

