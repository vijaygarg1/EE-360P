package mutex;
import dist.*;
public class LockTester {
	public static void main(String[] args) throws Exception {
		Linker comm = new Linker(args);
		Lock lock = null;
		if (args[3].equals("Lamport"))
			lock = new LamportMutex(comm);
		else if (args[3].equals("RicartAgrawala"))
			lock = new RAMutex(comm);
		else if (args[3].equals("Dining"))
			lock = new DinMutex(comm);
		else if (args[3].equals("Central"))
			lock = new CentMutex(comm);
		else if (args[3].equals("CircToken"))
			lock = new CircToken(comm);
		lock.init(null);
		for (int i = 0; i < 5; i++) {
			System.out.println(comm.getMyId() + " is requesting CS ");
			lock.requestCS();
			System.out.println(comm.getMyId() + " is in CS ******");
			Util.mySleep(200);
			lock.releaseCS();
			System.out.println(comm.getMyId() + " is not in CS");
		}
	}
}
