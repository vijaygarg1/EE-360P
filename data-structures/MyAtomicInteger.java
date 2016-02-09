import java.util.concurrent.atomic.*;
class MyAtomicInteger extends  AtomicInteger {
    public MyAtomicInteger(int val) { super(val);}
    public int myAddAndGet(int delta) {
        for (;;) {
            int current = get();
            int next = current + delta;
            if (compareAndSet(current, next))
                return next;
        }
    }
    public static void main(String[] args) throws Exception {
        MyAtomicInteger x = new MyAtomicInteger(10);
        System.out.println(x.myAddAndGet(5));
    }
}
