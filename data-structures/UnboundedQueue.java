import java.util.concurrent.locks.ReentrantLock;
public class UnboundedQueue<T> {

  ReentrantLock enqLock, deqLock;
  Node<T> head;
  Node<T> tail;
  int size;
  public UnboundedQueue() {
    head = new Node<T>(null);
    tail = head;
    enqLock = new ReentrantLock();
    deqLock = new ReentrantLock();
  }  
  public T deq() throws EmptyException {
    T result;
    deqLock.lock();
    try {
      if (head.next == null) {
        throw new EmptyException();
      }
      result = head.next.value;
      head = head.next;
    } finally {
      deqLock.unlock();
    }
    return result;
  }
  public void enq(T x) {
    if (x == null) throw new NullPointerException();
    enqLock.lock();
    try {
      Node<T> e = new Node<T>(x);
      tail.next = e;
      tail = e;
    } finally {
      enqLock.unlock();
    }
  }

}
