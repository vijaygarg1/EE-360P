package kvpaxos;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * This is the interface of KVPaxos RMI call. You should implement each method defined below.
 * Please don't change the interface.
 */
public interface KVPaxosRMI extends Remote{
    Response Get(Request req) throws RemoteException;
    Response Put(Request req) throws RemoteException;
}
