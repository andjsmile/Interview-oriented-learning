import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * CountDownLatch
 * 倒计时门栓
 * 实现一个线程集等待直到计数变成0
 * 一次性结构实现
 * 
 * countdown实现计数的减小
 */
public class CountDownLatchExample{
    public static void main(String[]args) throws InterruptedException{
        final int THREAD=10;
        CountDownLatch countdownlatch=new CountDownLatch(THREAD);

        ExecutorService executorservice=Executors.newCachedThreadPool();

        for(int i=0;i<THREAD;++i){
            executorservice.execute(()->{
                System.out.print("run... ");
                countdownlatch.countDown();
            });
        }
        countdownlatch.await();
        System.out.println("end");
        executorservice.shutdown();
    }
}