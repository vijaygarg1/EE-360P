package mutex;
import dist.*;
public class CircToken extends MyProcess implements Lock {
    boolean haveToken;
    boolean wantCS = false;
    final int leader = 0;
    public CircToken(MsgHandler initComm) {
        super(initComm);
        haveToken = (myId == leader);
       // if (haveToken) sendToken();
    }
    public void init(MsgHandler app) {
    	super.init(app);
        if (haveToken) sendToken();
    }
    public synchronized void requestCS() {
        wantCS = true;
        while (!haveToken) myWait();
    }
    public synchronized void releaseCS() {
        wantCS = false;
        sendToken();
    }
    public synchronized boolean getHaveToken(){
    	return haveToken;
    }
    void sendToken() {
        if (haveToken && !wantCS) {
            int next = (myId + 1) % N;
            Util.println("Process " + myId + "has sent the token");
            sendMsg(next, "token");
            haveToken = false;
        }
    }
    public synchronized void handleMsg(Msg m, int src, String tag) {
        if (tag.equals("token")) {
            haveToken = true;
            if (!wantCS) {
                Util.mySleep(1000);
                sendToken();
            }
            notifyAll();
        }
    }
}
