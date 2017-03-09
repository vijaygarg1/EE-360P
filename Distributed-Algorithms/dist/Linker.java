package dist;
import java.util.*; import java.io.*;
public class Linker implements MsgHandler {
	public int myId;	
	public int n; // number of neighbors including myself
	final boolean debug = true;
	Connector connector = null;
	MsgHandler app = null;// upper layer
	MsgHandler comm = null;// lower layer
	public boolean appFinished = false;
	public List<Integer> neighbors = new ArrayList<Integer>();	
	//public Properties prop = new Properties();
	public Linker (String args[]) throws Exception { 
		String basename = args[0];
		myId = Integer.parseInt(args[1]);
		if (!Topology.readNeighbors(myId, neighbors)) 
			Topology.setComplete(myId, neighbors, Integer.parseInt(args[2]));
		if (debug) System.out.println("Neighbors are " + neighbors);
		n = neighbors.size() + 1;
		//prop.loadFromXML(new FileInputStream("LinkerProp.xml"));
		connector = new Connector();
		connector.Connect(basename, myId, neighbors);
	}
	public void init(MsgHandler app){
		this.app = app;	
		for (int pid : neighbors)
			(new ListenerThread(pid, this)).start();		    	
	}
	public void sendMsg(int destId, Object ... objects) {	
			int j = neighbors.indexOf(destId);
			try {
				LinkedList<Object> objectList = Util.getLinkedList(objects);
				ObjectOutputStream os = connector.dataOut[j];
				os.writeObject(Integer.valueOf(objectList.size()));
				if (debug) System.out.print("Sending to " + destId + ":");
				for (Object object : objectList) {
					os.writeObject(object);
					if (debug) System.out.print(object +",");
				}
				os.flush();
				if (debug) System.out.println();
			} catch (IOException e) {System.out.println(e);close();	}
	}
	public Msg receiveMsg(int fromId) {
		int i = neighbors.indexOf(fromId);
		try {
			ObjectInputStream oi = connector.dataIn[i];
			int numItems = ((Integer) oi.readObject()).intValue();
			LinkedList<Object> recvdMessage = new LinkedList<Object>();
			for (int j = 0; j < numItems; j++) 
				recvdMessage.add(oi.readObject());
			String tag = (String) recvdMessage.removeFirst();
			return new Msg(fromId, myId, tag, recvdMessage);
		} catch (Exception e) { System.out.println(e);
			close(); return null;		
		}

	}
	public synchronized void handleMsg(Msg m, int src, String tag) { if (app != null) app.handleMsg(m, m.src, m.tag);
	}
//	public synchronized void executeMsg(Msg m) {	
//		if (m != null)
//		   handleMsg(m, m.src, m.tag);
//		notifyAll();
//		if (app != null) app.executeMsg(m);		
//	}
	public synchronized int getMyId() { return myId; }
	//public Properties getProp() { return prop;}
	public List<Integer> getNeighbors() { return neighbors; }
	public void close() { appFinished = true; connector.closeSockets(); }
	public void turnPassive() {	}
}
