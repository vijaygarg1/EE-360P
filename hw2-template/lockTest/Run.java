package lockTest;

import edu.umd.cs.mtc.TestFramework;

public class Run {
	final static FairReadWriteLock lock = new FairReadWriteLock();
	
	public static void main(String[] args) throws Throwable {
		TestFramework.runOnce(new TestReadRead());
		TestFramework.runOnce(new TestReadWrite());
		TestFramework.runOnce(new TestWriteRead());
		TestFramework.runOnce(new TestReadReadWrite());
		//TestFramework.runOnce(new TestFairReadWriteAfterWrite());
		System.out.println("All tests passed!");
	}
}
