class Cell {
    int value;
    public synchronized int getValue() {
        return value;
    }
    public synchronized void setValue(int i) {
        value = i;
    }
    protected void doSwap(Cell x) {
        int temp = getValue();
        setValue(x.getValue());
        x.setValue(temp);
    }
    public void swap(Cell x) {
        if (this == x)
            return;
        if (System.identityHashCode(this)
                < System.identityHashCode(x))
            synchronized(this) {
              synchronized(x) {
                 doSwap(x);
              }
            }
        else
              synchronized(x) {
                synchronized(this) {
                 x.doSwap(this);
              }
            }
    }
}
