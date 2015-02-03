public class LockList { // adapted from Herlihy's implementation
  private Node head, tail;
  public LockList() {
    head  = new Node(Integer.MIN_VALUE);
    tail  = new Node(Integer.MAX_VALUE);
    head.next = this.tail;
  }
  
  public synchronized boolean insert(Object item) {
    Node pred, curr;
    int key = item.hashCode();
      pred = head;
      curr = pred.next;
      while (curr.key < key) {
        pred = curr;
        curr = curr.next;
      }
      if (key == curr.key) {
        return false;
      } else {
        Node node = new Node(item);
        node.next = curr;
        pred.next = node;
        return true;
      }
  }

  public boolean remove(Object item) {
    Node pred, curr;
    int key = item.hashCode();
      pred = this.head;
      curr = pred.next;
      while (curr.key < key) {
        pred = curr;
        curr = curr.next;
      }
      if (key == curr.key) { 
        pred.next = curr.next;
        return true;
      } else {
        return false;         
      }
  }

  public synchronized boolean contains(Object item) {
    Node pred, curr;
    int key = item.hashCode();
      pred = head;
      curr = pred.next;
      while (curr.key < key) {
        pred = curr;
        curr = curr.next;
      }
      return (key == curr.key);
  }
  
  private class Node {
    Object item;
    int key;
    Node next;
    Node(Object item) {
      this.item = item;
      this.key = item.hashCode();
    }
    Node(int key) {
      this.item = null;
      this.key = key;
    }
  }
}
