package mutex;
import java.util.*;
import dist.*;

public class CentMutex extends MyProcess implements Lock {
    // assumes that P_0 coordinates and does not request locks.
    boolean haveToken;
    final int leader = 0;
    LinkedList<Integer> pendingQ = new LinkedList<Integer>();
    public CentMutex(Linker initComm) {
        super(initComm);
        haveToken = (myId == leader);
    }
    public synchronized void requestCS() {
	if (myId != leader){
           sendMsg(leader, "request");
           while (!haveToken) myWait();
        }
    }
    public synchronized void releaseCS() {
	if (myId != leader){
           sendMsg(leader, "release");
           haveToken = false;
        }
    }
    public synchronized void handleMsg(Msg m, int src, String tag) {
        if (tag.equals("request")) {
            if (haveToken){
                sendMsg(src, "okay");
                haveToken = false;
            }
            else
                pendingQ.add(src);
        } else if (tag.equals("release")) {
            if (!pendingQ.isEmpty()) {
                int pid = pendingQ.removeFirst();
                sendMsg(pid, "okay");
            } else
                haveToken = true;
        } else if (tag.equals("okay")) {
            haveToken = true;
	    notifyAll();
	}
    }
}
