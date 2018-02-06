public class testThreadSynch implements Runnable {
	final static int SIZE = 5;
	final static int ROUND = 5;
	
	final ThreadSynch gate;
	
	public testThreadSynch(ThreadSynch gate) {
		this.gate = gate;
	}
	
	public void run() {
		int index = -1;

		for (int round = 0; round < ROUND; ++round) {
			System.out.println("Thread " + Thread.currentThread().getId() + " is WAITING round:" + round);
			try {
				index = gate.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("Thread " + Thread.currentThread().getId() + " is leaving round:" + round);
		}
	}
	
	public static void main(String[] args) {
		ThreadSynch gate = new ThreadSynch(SIZE);
		Thread[] t = new Thread[SIZE];
		
		for (int i = 0; i < SIZE; ++i) {
			t[i] = new Thread(new testThreadSynch(gate));
		}
		
		for (int i = 0; i < SIZE; ++i) {
			t[i].start();
		}
    }
}
