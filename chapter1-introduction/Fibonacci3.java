import java.util.concurrent.RecursiveTask;
import java.util.concurrent.ForkJoinPool;

class Fibonacci3 extends RecursiveTask<Integer> {
  final int n;
  Fibonacci3(int n) { this.n = n; }
  protected Integer compute() {
    if ((n == 0)||(n == 1 )) return 1;
    Fibonacci3 f1 = new Fibonacci3(n - 1);
    f1.fork();
    Fibonacci3 f2 = new Fibonacci3(n - 2);
    return f2.compute() + f1.join();
  }
  public static void main(String[] args) {
    int processors = Runtime.getRuntime().availableProcessors();
    System.out.println("Number of processors: " + processors);
    Fibonacci3 f = new Fibonacci3(Integer.parseInt(args[0]));
    ForkJoinPool pool = new ForkJoinPool(processors);
    int result = pool.invoke(f);
    System.out.println("Result: " + result);
  }
}
