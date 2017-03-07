package dist;
import java.util.*;
public class Msg {
    public int src, dest;
    public String tag;
    LinkedList<Object> msgBuf;
    public Msg(int s, int t, String msgType, LinkedList<Object> buf) {
        this.src = s;
        dest = t;
        tag = msgType;
        msgBuf = buf;
    }    
    public LinkedList<Object> getMsgBuf() {
        return msgBuf;
    }
    public int getMessageInt() {
         return (Integer) msgBuf.removeFirst();
    }
    
    public String toString(){
        String s = String.valueOf(src)+" " +
                    String.valueOf(dest)+ " " +
                    tag + " " + msgBuf.toString() ;
        return s;
    } 
}
