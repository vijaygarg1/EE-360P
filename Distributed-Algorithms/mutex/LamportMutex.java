package mutex;
import dist.*; import clocks.*; import java.util.*;
public class LamportMutex extends MyProcess implements Lock {
	LamportClock c;
	int numAcks;
	Queue<Timestamp> q; // request queue
	public LamportMutex(Linker initComm) {
		super(initComm);
		c = new LamportClock();
		q = new PriorityQueue<Timestamp>(n, 	
				new Comparator<Timestamp>() {
			public int compare(Timestamp a, Timestamp b) {
				return Timestamp.compare(a, b);
			}
		});
		numAcks = 0;
	}
	public synchronized void requestCS() {
		c.tick();
		q.add(new Timestamp(c.getValue(), myId));
		sendMsg(neighbors, "request", c.getValue());
		numAcks = 0;
		while ((q.peek().pid != myId) || (numAcks < n-1))
			myWait();
	}
	public synchronized void releaseCS() {
		q.remove();
		sendMsg(neighbors, "release", c.getValue());
	}
	public synchronized void handleMsg(Msg m, int src, String tag) {
		int timeStamp = m.getMessageInt();
		c.receiveAction(src, timeStamp);
		if (tag.equals("request")) {
			q.add(new Timestamp(timeStamp, src));
			sendMsg(src, "ack",c.getValue());
		} else if (tag.equals("release")) {

			Iterator<Timestamp> it =  q.iterator();			    
			while (it.hasNext()){
				if (it.next().getPid() == src) it.remove();
			}
		} else if (tag.equals("ack"))
			numAcks++;
		notifyAll();
	}
	public static void main(String args[]) throws Exception {
		Linker comm = new Linker(args);
		Lock lock = new LamportMutex(comm);
		lock.init(null);
		for (int i = 0; i < 5; i++) {
			lock.requestCS();
			System.out.println(comm.getMyId() + " is in CS ******");
			Util.mySleep(2000);
			lock.releaseCS();
			System.out.println(comm.getMyId() + " is not in CS");
		}
	}
}
