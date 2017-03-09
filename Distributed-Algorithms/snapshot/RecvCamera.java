package snapshot;
import java.util.*;
import dist.*;
public class RecvCamera  extends MyProcess implements Camera {
    Color myColor = Color.white;
    boolean closed[];   
    LinkedList chan[] = null;
    int n = neighbors.size();
    public RecvCamera(MsgHandler initComm) {
        super(initComm);
        closed = new boolean[n]; Arrays.fill(closed, false);
        chan = new LinkedList[n];
        for (int i =0; i < n; i++) 
                chan[i] = new LinkedList();        
    }
    public synchronized void globalState() {
        myColor = Color.red;
        ((CamUser) app).localState(); // record local State;
        sendMsg(neighbors,"marker", myId);  // send Markers
    }
    public synchronized void handleMsg(Msg m, int src, String tag) {
    	int nid = neighbors.indexOf(src);
        if (tag.equals("marker")) {
            if (myColor == Color.white) globalState();
            closed[nid] = true;
            if (isDone())
                for (int i = 0; i < n; i++)
                         System.out.println(chan[i].toString());
        } else { // application message
            if ((myColor == Color.red) && (!closed[nid]))
                chan[nid].add(m);
            app.handleMsg(m, src, tag); // give it to app
        }
    }
    boolean isDone() {
        if (myColor == Color.white) return false;
        for (int i = 0; i < n; i++)
            if (!closed[i]) return false;
        return true;
    }
}
