import java.util.NoSuchElementException;
import java.util.concurrent.atomic.AtomicReference;

public class LockFreeStack<T> {
    AtomicReference<Node<T>> top = new AtomicReference<Node<T>>(null);
    
    public void push(T value) {
    	Node<T> node = new Node<T>(value);
		while(true) {
		   Node<T> oldTop = top.get();
		   node.next = oldTop;
		   if (top.compareAndSet(oldTop, node)) return;
		    else Thread.yield();
		}
    }


    public T pop() throws NoSuchElementException {
		while(true) {
		    Node<T> oldTop = top.get();
		    if(oldTop == null) throw new NoSuchElementException();
		    T val = oldTop.value;
		    Node<T> newTop = oldTop.next;
		    if(top.compareAndSet(oldTop, newTop)) return val;
		    else Thread.yield();
		}
    }


}
