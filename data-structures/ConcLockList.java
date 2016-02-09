import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
public class ConcLockList { //Adapted from Herlihy's Book
  private Node head;
  public ConcLockList() {
    head      = new Node(Integer.MIN_VALUE);
    head.next = new Node(Integer.MAX_VALUE);
  }
  public boolean insert(Object item) {
    int key = item.hashCode();
    head.lock();
    Node pred = head;
    try {
      Node curr = pred.next;
      curr.lock();
      try {
        while (curr.key < key) {
          pred.unlock();
          pred = curr;
          curr = curr.next;
          curr.lock();
        }
        if (curr.key == key) {
          return false;
        }
        Node newNode = new Node(item);
        newNode.next = curr;
        pred.next = newNode;
        return true;
      } finally {
        curr.unlock();
      }
    } finally {
      pred.unlock();
    }
  }
  public boolean remove(Object item) {
    Node pred = null, curr = null;
    int key = item.hashCode();
    head.lock();
    try {
      pred = head;
      curr = pred.next;
      curr.lock();
      try {
        while (curr.key < key) {
          pred.unlock();
          pred = curr;
          curr = curr.next;
          curr.lock();
        }
        if (curr.key == key) {
          pred.next = curr.next;
          return true;
        }
        return false;
      } finally {
        curr.unlock();
      }
    } finally {
      pred.unlock();
    }
  }
  public boolean contains(Object item) {
    Node last = null, pred = null, curr = null;
    int key = item.hashCode();
    head.lock();
    try {
      pred = head;
      curr = pred.next;
      curr.lock();
      try {
        while (curr.key < key) {
          pred.unlock();
          pred = curr;
          curr = curr.next;
          curr.lock();
        }
        return (curr.key == key);
      } finally {
        curr.unlock();
      }
    } finally {
      pred.unlock();
    }
  }
  private class Node {
    Object item;
    int key;
    Node next;
    Lock lock;
    Node(Object item) {
      this.item = item;
      this.key = item.hashCode();
      this.lock = new ReentrantLock();
    }
    Node(int key) {
      this.item = null;
      this.key = key;
      this.lock = new ReentrantLock();
    }
    void lock() {lock.lock();}
    void unlock() {lock.unlock();}
  }
}

