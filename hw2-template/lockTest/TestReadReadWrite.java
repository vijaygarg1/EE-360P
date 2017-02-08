package lockTest;

import edu.umd.cs.mtc.MultithreadedTestCase;

public class TestReadReadWrite extends MultithreadedTestCase{
	
	public void thread1 () throws InterruptedException {
		Run.lock.beginRead();
		waitForTick(3);
		Run.lock.endRead();
	}
	
	public void thread2 () throws InterruptedException {
		waitForTick(1);
		Run.lock.beginRead();
		assertTick(1);
		Run.lock.endRead();
	}
	
	public void thread3() throws InterruptedException {
		waitForTick(2);
		Run.lock.beginWrite();
		assertTick(3);
		Run.lock.endWrite();
	}
	
	public void thread4() throws InterruptedException {
		waitForTick(3);
		Run.lock.beginRead();
		assertTick(3);
		Run.lock.endRead();;
	}
}
