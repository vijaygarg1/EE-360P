import java.rmi.*;
import java.net.*;
public interface NameService extends Remote {
    public InetSocketAddress search(String s) throws RemoteException;
    public InetSocketAddress blockingFind(String s) throws RemoteException;
    public int insert(String s, String hostName, int portNumber)
            throws RemoteException;
}

