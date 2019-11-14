import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Seamphore 信号量是一个计数信号量
 * 请求一个许可，计数器减1
 * 释放一个许可，计数器加1
 * 计数器不为0的时候，对想成进行放行
 * 当计数器为0的时候，请求资源的新线程都会被阻塞，包括增加到请求许可的线程，seamphore是不可重入的
 * 
 * 这是缓存池(对象池，链接池)
 */
public class SeamphoreTest{

    public static void main(String[] args){
        final int clientCount=3;
        final int totalRequestCount=10;
        Semaphore semaphore=new Semaphore(clientCount);

        ExecutorService executorService=Executors.newCachedThreadPool();

        for(int i=0;i<totalRequestCount;i++){
            executorService.execute(()->{
                try{
                    semaphore.acquire();
                    System.out.print(semaphore.availablePermits()+" ,");

                }catch(InterruptedException e){
                    e.printStackTrace();
                }finally{
                    semaphore.release();
                }
            });
        }
        executorService.shutdown();
    }
}