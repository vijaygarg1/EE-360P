
public class PriorityQueue {

	public PriorityQueue(int maxSize) {
        // Creates a Priority queue with maximum allowed size as capacity
	}

	public int add(String name, int priority) {
        // Adds the name with its priority to this queue.
        // Returns the current position in the list where the name was inserted;
        // otherwise, returns -1 if the name is already present in the list.
        // This method blocks when the list is full.
	}

	public int search(String name) {
        // Returns the position of the name in the list;
        // otherwise, returns -1 if the name is not found.
	}

	public String poll() {
        // Retrieves and removes the name with the highest priority in the list,
        // or blocks the thread if the list is empty.
	}
}

