// http://tutorials.jenkov.com/java-concurrency/threadlocal.html
public class ThreadLocalExample2 {


    public static class MyRunnable implements Runnable {

        private ThreadLocal<Integer> threadLocal =
               new ThreadLocal<Integer>();
        private int plain;

        @Override
        public void run() {
            threadLocal.set( (int) (Math.random() * 100D) );
            System.out.println(Thread.currentThread().getName() + " has ThreadLocal " + threadLocal.get());
    
            plain =  (int) (Math.random() * 100D) ;
            System.out.println(Thread.currentThread().getName() + " has plain " + plain);
    
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
            }
    
            System.out.println(Thread.currentThread().getName() + " has ThreadLocal " + threadLocal.get());
            System.out.println(Thread.currentThread().getName() + " has plain " + plain);
        }
    }


    public static void main(String[] args) throws InterruptedException {
        MyRunnable sharedRunnableInstance = new MyRunnable();

        Thread thread1 = new Thread(sharedRunnableInstance, "Thread 1");
        Thread thread2 = new Thread(sharedRunnableInstance, "Thread 2");

        thread1.start();
        thread2.start();

        thread1.join(); //wait for thread 1 to terminate
        thread2.join(); //wait for thread 2 to terminate
    }

}

