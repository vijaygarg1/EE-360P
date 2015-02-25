import java.rmi.*;
import java.util.*;
public interface MsgHandler extends Remote {
    public void handleMsg(Msg m, int src, String tag);
    public void executeMsg(Msg m);
    public void sendMsg(int destId, Object ...objects);
	public void init(MsgHandler app);
	public void close();
	public int getMyId();	
	public List<Integer> getNeighbors();
	public void turnPassive();
	public Properties getProp();	
   }
