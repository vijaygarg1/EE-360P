import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class TwoLockQueue { //adapted from Herlihy's
  
  ReentrantLock enqLock, deqLock;
  Condition notEmptyCondition, notFullCondition;
  AtomicInteger size;
  Entry head;
  Entry tail;
  int capacity;
  public TwoLockQueue(int capacity) {
    this.capacity = capacity;
    this.head = new Entry(null);
    this.tail = head;
    this.size = new AtomicInteger(capacity);
    this.enqLock = new ReentrantLock();
    this.notFullCondition = enqLock.newCondition();
    this.deqLock = new ReentrantLock();
    this.notEmptyCondition = deqLock.newCondition();
  }
  public Object deq() {
    Object result;
    boolean mustWakeEnqueuers = true;
    deqLock.lock();
    try {
      while (size.get() == capacity) {
        try {
          notEmptyCondition.await();
        } catch (InterruptedException ex) {}
      }
      result = head.next.value;
      head = head.next;
      if (size.getAndIncrement() == 0) {
        mustWakeEnqueuers = true;
      }
    } finally {
      deqLock.unlock();
    }
    if (mustWakeEnqueuers) {
      enqLock.lock();
      try {
        notFullCondition.signalAll();
      } finally {
        enqLock.unlock();
      }
    }
    return result;
  }
  public void enq(Object x) {
    if (x == null) throw new NullPointerException();
    boolean mustWakeDequeuers = false;
    enqLock.lock();
    try {
      while (size.get() == 0) {
        try {
          notFullCondition.await();
        } catch (InterruptedException e) {}
      }
      Entry e = new Entry(x);
      tail.next = e;
      tail = e;
      if (size.getAndDecrement() == capacity) {
        mustWakeDequeuers = true;
      }
    } finally {
      enqLock.unlock();
    }
    if (mustWakeDequeuers) {
      deqLock.lock();
      try {
        notEmptyCondition.signalAll();
      } finally {
        deqLock.unlock();
      }
    }
  }
  protected class Entry {
    public Object value;
    public Entry next;
    public Entry(Object x) {
      value = x;
      next = null;
    }
  }
}
