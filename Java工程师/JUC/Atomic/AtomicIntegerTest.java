import java.util.concurrent.atomic.AtomicInteger;

/***
 * 基础的原子类
 */

public class AtomicIntegerTest{
    public static void main(String[] args) throws InterruptedException{
        final AtomicInteger act=new AtomicInteger(10);
        System.out.println("act value: "+act.get());
        boolean b=act.compareAndSet(10, 15);
        System.out.println(b);

        act.set(20);
        System.out.println("after set value: "+act.get());

        // 使用线程
        final int threadSize=10;
        Thread[] ts=new Thread[threadSize];
        for(int i=0;i<threadSize;++i){
            ts[i]=new Thread(()->{
                // 原子的方式加1
                act.incrementAndGet();
            });
        }
        for(Thread t:ts){
            t.start();
        }
        for(Thread t:ts){
            t.join();
        }

        System.out.println("after atomic increnment: "+act.get());
        
    }

}