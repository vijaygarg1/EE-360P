package snapshot;
import dist.*;
public class CamCircToken extends mutex.CircToken implements CamUser {
	public CamCircToken(Camera initComm) {
        super(initComm);
    }
    public synchronized void localState() {
        println("local state: haveToken=" + getHaveToken());
    }
}
