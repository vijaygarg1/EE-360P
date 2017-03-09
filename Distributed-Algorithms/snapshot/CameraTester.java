package snapshot;
import dist.*;

public class CameraTester {
	public static void main(String[] args) throws Exception {
		
		int myId = Integer.parseInt(args[1]);
		
		Camera camera = null;
		CamCircToken sp = null;

		Linker comm = new Linker(args);
		
		if (args[3].equals("RecvCamera")) {
			camera = new RecvCamera(comm);
			
			sp = new CamCircToken(camera);
			
		}
        sp.init(null);
		//if (myId == 0) 
			camera.globalState();
	}
}
