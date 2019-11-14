import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * CyclicBarrier 
 * 障栅
 * 控制多个线程相互等待,可以循环使用
 * 一旦所有的线程都到达
 * 障栅就会撤销，线程就可以继续运行(所有的线程)
 * 
 * 执行await()方法之后计数器减1，进行等待，直到计数器为0
 */

public class CyclicBarrierExample{
    public static void main(string[] args){
        final int TOTALTHREAD=10；
        CyclicBarrier cyclicbarrier=new CyclicBarrier(TOTALTHREAD);
        ExecutorService executorservice=Executors.newCachedThreadPool();
        for(int i=0;i<TOTALTHREAD;++i){
            executorservice.execute(()->{
                System.out.print("before.. ");
                try{
                    cyclicbarrier.await();
                }catch(InterruptedException |BrokenBarrierException e){
                    e.printStackTrace();
                }
                System.out.print("after...");
            });
        }
        executorservice.shutdown();

    }
}

/*
//两个构造函数
public CyclicBarrier(int parties,Runnable barrierAction){
    if(parties<=0) throw new IllegalArgumentException();
    this.parties=parties;
    this.count=parties;
    this.barrierCommand=barrierAction;
}
public CyclicBarrier(int parties){
    this(parties,null);
}
*/