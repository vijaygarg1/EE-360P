package lockTest;

import edu.umd.cs.mtc.MultithreadedTestCase;

public class TestFairReadWriteAfterWrite extends MultithreadedTestCase{
	
	public void thread1() {
		Run.lock.beginWrite();
		waitForTick(3);
		Run.lock.endWrite();
	}
	
	public void thread2() {
		waitForTick(1);
		Run.lock.beginRead();
		waitForTick(4);
		Run.lock.endRead();
		int tick = getTick();
		if(tick==3) {
			System.out.println("reader wins!");
			assertTrue(true);
		} else if(tick==4) {
			System.out.println("writer wins!");
			assertTrue(true);
		} else {
			assertTrue(false);
		}
	}
	
	public void thread3() {
		waitForTick(2);
		Run.lock.beginWrite();
		waitForTick(4);
		Run.lock.endWrite();
		int tick = getTick();
		if(tick==3) {
			System.out.println("writer wins!");
			assertTrue(true);
		} else if(tick==4) {
			System.out.println("reader wins!");
			assertTrue(true);
		} else {
			assertTrue(false);
		}
	}
}	
