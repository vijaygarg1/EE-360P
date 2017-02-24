package dist;
import java.io.*;
import java.util.*;
public class Topology {
	// returns true if read neighbors successfully
	public static boolean readNeighbors(int myId,
			List<Integer> neighbors) {
		System.out.println("Reading topology");
		try {
			Scanner sc = new Scanner(new FileReader("topology" + myId));
			while (sc.hasNext()) {
				int neighbor = sc.nextInt();
				neighbors.add(neighbor);
			}
		} catch (FileNotFoundException e) {
			return false;
		}
		System.out.println(neighbors.toString());
		return true;
	}
	public static void setComplete(int myId,
			List<Integer> neighbors, int numProc) {
		for (int i = 0; i < numProc; ++i) {
			if (i != myId) neighbors.add(i);
		}
	}
	public static void main(String [] args) {
		LinkedList<Integer> l = new LinkedList<Integer>();
		Topology.readNeighbors(Integer.parseInt(args[0]),l);
	}
}
