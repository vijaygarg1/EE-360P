import java.util.concurrent.locks.*;

class MBoundedBufferMonitor {
   final int size = 10;
   final ReentrantLock monitorLock = new ReentrantLock();
   final Condition notFull  = monitorLock.newCondition(); 
   final Condition notEmpty = monitorLock.newCondition(); 

   final Object[] buffer = new Object[size];
   int inBuf=0, outBuf=0, count=0;

   public void put(Object x) throws InterruptedException {
     monitorLock.lock();
     try {
       while (count == buffer.length)
         notFull.await();
       buffer[inBuf] = x;
       inBuf = (inBuf + 1) % size;
       count++;
       notEmpty.signal();
     } finally {
       monitorLock.unlock();
     }
   }

   public Object take() throws InterruptedException {
     monitorLock.lock();
     try {
       while (count == 0)
         notEmpty.await();
       Object x = buffer[outBuf];
       outBuf = (outBuf + 1) % size;
       count--;
       notFull.signal();
       return x;
     } finally {
       monitorLock.unlock();
     }
   }
 }
 
