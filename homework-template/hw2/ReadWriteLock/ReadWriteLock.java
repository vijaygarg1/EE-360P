
public class ReadWriteLock {
    // This class has to provide the following properties:
    // a. There is no read-write or write-write conflict.
    // b. A writer thread that invokes beginWrite() will be block only when
    //    there is a thread holding the lock.
    // c. A reader thread that invokes beginRead() will be block if either
    //    the lock is held by a writer or there is a waiting writer thread.
    // d. A reader thread cannot be blocked if all preceding writer threads
    //    have acquired and released the lock or no preceding writer thread
    //    exists.

	public void beginRead() {

	}

	public void endRead() {

	}

	public void beginWrite() {

	}

	public void endWrite() {

	}
}


