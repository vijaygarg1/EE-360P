public class CountingSemaphore {
    int value;
    public CountingSemaphore(int initValue) {
        value = initValue;
    }
    public synchronized void P() {
        while (value == 0) Util.myWait(this);
        value--;
         
    }
    public synchronized void V() {
        value++;
        notify();
    }
}
