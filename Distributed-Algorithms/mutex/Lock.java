package mutex;
import dist.*;
public interface Lock extends MsgHandler {
    public void requestCS(); //may block
    public void releaseCS();
}
