package dist;
import java.io.*; import java.util.*;

public class Util {
	final static boolean debugFlag = true;
	public static void mySleep(int time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
		}
	}

	public static void myWait(Object obj) {
		println("waiting");
		try {
			obj.wait();
		} catch (InterruptedException e) {
		}
	}

	public static boolean lessThan(int A[], int B[]) {
		for (int j = 0; j < A.length; j++)
			if (A[j] > B[j])
				return false;
		for (int j = 0; j < A.length; j++)
			if (A[j] < B[j])
				return true;
		return false;
	}

	public static int maxArray(int A[]) {
		int v = A[0];
		for (int i = 0; i < A.length; i++)
			if (A[i] > v)
				v = A[i];
		return v;
	}

	public static String writeArray(int A[]) {
		StringBuffer s = new StringBuffer();
		for (int j = 0; j < A.length; j++)
			s.append(String.valueOf(A[j]) + " ");
		return new String(s.toString());
	}
	public static String readLine(InputStream sin) throws IOException{
		BufferedReader inp = new BufferedReader(
            new InputStreamReader(sin));
		return inp.readLine();
	}
	public static String readLine(String fileName) throws IOException{
		BufferedReader inp = new BufferedReader(
            new FileReader(fileName));
		return inp.readLine();
	}
	public static void readArray(String s, int A[]) {
		StringTokenizer st = new StringTokenizer(s);
		for (int j = 0; j < A.length; j++)
			A[j] = Integer.parseInt(st.nextToken());
	}

	public static void readList(String s, LinkedList<Integer> q) {
		StringTokenizer st = new StringTokenizer(s);
		q.clear();
		while (st.hasMoreTokens()) {
			q.add(Integer.parseInt(st.nextToken()));
		}
	}

	public static int searchArray(int A[], int x) {
		for (int i = 0; i < A.length; i++)
			if (A[i] == x)
				return i;
		return -1;
	}

	public static void println(String s) {
		if (debugFlag) {
			System.out.println(s);
			System.out.flush();
		}
	}

	public static LinkedList<Object> getLinkedList(Object... objects) {
		LinkedList<Object> list = new LinkedList<Object>();
		for (int i = 0; i < objects.length; i += 1) {
			list.add(objects[i]);
		}
		return list;
	}
}
