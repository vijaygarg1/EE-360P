package dist;
public class ListenerThread extends Thread {
	int channel;
	Linker comm = null;

	public ListenerThread(int channel, Linker comm) {
		this.channel = channel;
		this.comm = comm;
	}
	public void run() {
		while (!comm.appFinished) {
			Msg m = comm.receiveMsg(channel);
			if (m!=null)
				comm.handleMsg(m, m.src, m.tag);

		}
	}
}
