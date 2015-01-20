import java.util.concurrent.*;
class Fibonacci2 implements Callable<Integer> {
  public static ExecutorService threadPool = Executors.newCachedThreadPool();
  int n;
  public Fibonacci2(int n) {
    this.n = n;
  }
  public Integer call() {
    try {
       if ((n == 0)||(n == 1 )) return 1;
       else {
        Future<Integer> f1 = threadPool.submit(new Fibonacci2(n-1));
        Future<Integer> f2 = threadPool.submit(new Fibonacci2(n-2));
        return f1.get() + f2.get();
      } 
    } catch (Exception e) { System.err.println (e); return 1;}
  }

  public static void main(String[] args) {
    try {
	ExecutorService es = Executors.newSingleThreadExecutor();
        Fibonacci2 f = new Fibonacci2(Integer.parseInt(args[0]));
        Future<Integer> f1 = es.submit(f);
        System.out.println("Answer is " + f1.get());
        es.shutdown ();
        f.threadPool.shutdown();
    } catch (Exception e) { System.err.println (e); }
  }
}
