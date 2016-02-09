import java.util.NoSuchElementException;

public class Stack<T> {
    Node<T> top = null;
    
    public synchronized void push(T value) {
    	Node<T> node = new Node(value);
	node.next = top;
	top = node;
    }
    
    public synchronized T pop() throws NoSuchElementException {
		if(top == null) {
		    throw new NoSuchElementException();
		} else {
                Node<T> oldTop = top;
		top = top.next;
		return oldTop.value;
               }
   
    }
    
}
