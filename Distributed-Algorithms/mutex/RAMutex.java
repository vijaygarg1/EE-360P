package mutex;
import dist.*;
import clocks.*;
import java.util.*;
public class RAMutex extends dist.MyProcess implements Lock {  
	int myts;
	LamportClock c = new LamportClock();
	LinkedList<Integer> pendingQ = new LinkedList<Integer>();
	int numOkay = 0;
	public RAMutex(MsgHandler initComm) {
		super(initComm);
		myts = Integer.MAX_VALUE;
	}
	public synchronized void requestCS() {
		c.tick();
		myts = c.getValue();
		sendMsg(neighbors,"request", myts);
		numOkay = 0;
		while (numOkay < n-1)
			myWait(); 
	}
	public synchronized void releaseCS() {
		myts = Integer.MAX_VALUE;
		while (!pendingQ.isEmpty()) 
			sendMsg(pendingQ.remove(), "okay", c.getValue());
	}
	boolean okayCS() {
		if(myts == Integer.MAX_VALUE || numOkay <n-1) return false;
		return true;
	}
	public synchronized void handleMsg(Msg m, int src, String tag) {
		int timeStamp = m.getMessageInt();
		c.receiveAction(src, timeStamp);
		if (tag.equals("request")) {
			if ((timeStamp < myts) || ((timeStamp == myts) && (src < myId)))
				sendMsg(src, "okay", c.getValue());
			else
				pendingQ.add(src);
		} else if (tag.equals("okay")) 
			numOkay++;
		notifyAll();
	}
}
