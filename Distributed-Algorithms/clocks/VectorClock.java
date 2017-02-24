package clocks;
import java.util.*;
public class VectorClock {
    public int[] v;
    int myId;
    int N;
    public VectorClock(int numProc, int id) {
        myId = id;
        N = numProc;
        v = new int[numProc];
        Arrays.fill(v,0);
        v[myId] = 1;
    }
    public VectorClock(VectorClock vc) { 
        myId = vc.myId; N = vc.N; v = new int[N];
        for(int i=0;i<N;i+=1)
            v[i] = vc.v[i];
    }

    public void tick() {
        v[myId]++;
    }
    public void sendAction() { //include the vector in the message
        v[myId]++;
    }
    public void receiveAction(int[] sentValue) {
        for (int i = 0; i < N; i++)
            v[i] = Math.max(v[i], sentValue[i]);
        v[myId]++;
    }
    public int getValue(int i) {
        return v[i];
    }
    public String toString(){
        return Arrays.toString(v);
    }
    public boolean isLessThan(VectorClock vc){
        for(int i=0; i< N;i++){
            if(v[i] >vc.v[i]) return false;
        }
        return true;
    }
    public boolean isGreaterThan(VectorClock vc){
         return vc.isLessThan(this);
    }
    public boolean isIncomparableWith(VectorClock vc){
      return (!isLessThan(vc) && !isGreaterThan(vc));
    }

}
