import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Semaphore 信号量
 * 类似于操作系统的信号量
 * 控制对互斥资源的访问线程数
 * 
 * A counting semaphore. Conceptually, a semaphore maintains a 
 * set of permits. Each acquire() blocks if necessary 
 * until a permit is available, and then takes it. 
 * Each release() adds a permit, potentially releasing a blocking a
 * cquirer. However, no actual permit objects are used; 
 * the Semaphore just keeps a count of the number available and acts accordingly.
 * Semaphores are often used to restrict the number of threads than 
 * can access some (physical or logical) resource
 */

 public class SemaphoreExample{
     public static void main(String[] args){
         final int CLIENTCOUNT=3;
         final int totalRequestCount=10;

         Semaphore semaphore=new Semaphore(CLIENTCOUNT);
         ExecutorService executorservice=Executors.newCachedThreadPool();
         for(int i=0;i<totalRequestCount;++i){
             executorservice.execute(()->{
                 try{
                     semaphore.acquire();
                     System.out.print(semaphore.availablePermits()+" ");
                 }catch(InterruptedException e){
                     e.printStackTrace();
                 }finally{
                     semaphore.release();
                 }
             });
         }
         executorservice.shutdown();
     }
 }