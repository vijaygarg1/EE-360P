class BoundedBufferMonitor {
    final int size = 10;
    Object[] buffer = new Object[size];
    int inBuf = 0, outBuf = 0, count = 0;
    public synchronized void deposit(Object value) {
        while (count == size) // buffer full
            Util.myWait(this);
        buffer[inBuf] = value;
        inBuf = (inBuf + 1) % size;
        count++;
        if (count == 1) // items available for fetch
            notify();
    }
    public synchronized Object fetch() {
        Object value;
        while (count == 0) // buffer empty
            Util.myWait(this);
        value = buffer[outBuf];
        outBuf = (outBuf + 1) % size;
        count--;
        if (count == size - 1) // empty slots available
            notify();
        return value;
    }
}

