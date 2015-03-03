import java.io.*;
public class ListenerThread extends Thread {
    int channel;
    Linker comm = null;
    public ListenerThread(int channel, Linker comm) {
        this.channel = channel;
        this.comm = comm;
    }
    public void run() {
        while (!comm.appFinished) {
            // System.out.println("Listening on " + channel);
			Msg m = comm.receiveMsg(channel);
			comm.executeMsg(m);
           
        }
    }
}
