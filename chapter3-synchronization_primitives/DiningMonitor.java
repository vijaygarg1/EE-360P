class DiningMonitor implements Resource {
    int n = 0;
    int state[] = null;
    static final int thinking = 0, hungry = 1, eating = 2;
    public DiningMonitor(int initN) {
        n = initN;
        state = new int[n];
        for (int i = 0; i < n; i++) state[i] = thinking;
    }
    int left(int i) {
        return (n + i - 1) % n;
    }
    int right(int i) {
        return (i + 1) % n;
    }
    public synchronized void acquire(int i) {
        state[i] = hungry;
        test(i);
        while (state[i] != eating)
            Util.myWait(this);
    }
    public synchronized void release(int i) {
        state[i] = thinking;
        test(left(i));
        test(right(i));
    }
    void test(int i) {
        if ((state[left(i)] != eating) &&
        (state[i] == hungry) &&
        (state[right(i)] != eating)) {
            state[i] = eating;
            notifyAll();
        }
    }
    public static void main(String[] args) {
        DiningMonitor dm = new DiningMonitor(5);
        for (int i = 0; i < 5; i++)
            new Philosopher(i, dm);
    }
}
