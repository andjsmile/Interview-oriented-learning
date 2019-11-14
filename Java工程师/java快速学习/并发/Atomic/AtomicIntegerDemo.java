import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;


/**
 * int类型满足原子性操作，只是说明在工作内存和主内存之间交互的单个操作具有原子性
 * load ,assign ,store 等单个操作具有原子性
 * 
 * 使用AtomicInteger可以实现保证在多个线程的操作下保证原子性
 */

public class AtomicIntegerDemo{
    
    static class AtomicExample{

        private AtomicInteger cnt=new AtomicInteger();

        public void add(){
            cnt.incrementAndGet();
        }

        public int get(){
            return cnt.get();
        }
    }

    public static void main(String[] args) throws InterruptedException{
        final int threadSize=100;
        AtomicExample example=new AtomicExample();
        final CountDownLatch latch=new CountDownLatch(threadSize);
        ExecutorService executorService=Executors.newCachedThreadPool();

        for(int i=0;i<threadSize;++i){
            executorService.execute(()->{
                example.add();
                latch.countDown();
            });
        }
        latch.await();
        executorService.shutdown();

        System.out.println(example.get());

    }
}