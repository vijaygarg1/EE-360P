public class testBarrier implements Runnable {
	final static int SIZE = 5;
	final static int ROUND = 5;
	
	final CyclicBarrier barrier;
	
	public testBarrier(CyclicBarrier barrier) {
		this.barrier = barrier;
	}
	
	public void run() {
		int index = -1;

		for (int round = 0; round < ROUND; ++round) {
			System.out.println("Thread " + Thread.currentThread().getId() + " is WAITING round:" + round);
			try {
				index = barrier.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("Thread " + Thread.currentThread().getId() + " is leaving round:" + round);
		}
	}
	
	public static void main(String[] args) {
		CyclicBarrier barrier = new CyclicBarrier(SIZE);
		Thread[] t = new Thread[SIZE];
		
		for (int i = 0; i < SIZE; ++i) {
			t[i] = new Thread(new testBarrier(barrier));
		}
		
		for (int i = 0; i < SIZE; ++i) {
			t[i].start();
		}
    }
}
