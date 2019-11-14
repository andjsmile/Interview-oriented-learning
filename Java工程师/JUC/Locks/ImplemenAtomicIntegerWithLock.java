import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/***
 * ImplementAtomicIntegerWithLock
 * 实现显示锁lock的特性来实现atomicInteger的类似操作
 */
public class ImplementAtomicIntegerWithLock{

    private int value;
    private Lock lock=new ReentrantLock();

    public ImplementAtomicIntegerWithLock(){
        super();
    }

    public ImplementAtomicIntegerWithLock(int value){
        this.value=value;
    }

    public final int get(){
        lock.lock();
        try{
            return value;
        }finally{
            lock.unlock();
        }
    }

    public final void set(int newValue){
        lock.lock();
        try{
            value=newValue;
        }finally{
            lock.unlock();
        }
    }

    public final int getAndSet(int newValue){
        lock.lock();
        try{
            int ret=value;
            value=newValue;
            return ret;
        }finally{
            lock.unlock();
        }
    }

    public final boolean compareAndSet(int expect,int newValue){
        lock.lock();
        try{
            if(value==expect){
                value=newValue;
                return true;
            }
            return false;
        }finally{
            lock.unlock();
        }
    }

    public final int getAndIncrement(){
        lock.lock();
        try{
            return value++;
        }finally{
            lock.unlock();
        }
    }

    public final int getAndDecrement(){
        lock.lock();
        try{
            return value--;
        }finally{
            lock.unlock();
        }
    }

    public final int incrementAndGet(){
        lock.lock();
        try{
            return ++value;
        }finally{
            lock.unlock();
        }
    }
    public final int decrementAndGet(){
        lock.lock();
        try{
            return --value;
        }finally{
            lock.unlock();
        }
    }

    public String toString(){
        return Integer.toString(value);
    }

    // 一个开销测试

    public static void main(String[] args)throws Exception{
        final int max=10;
        final int loopCount=100000;
        long costTime=0;
        for(int m=0;m<max;m++){
            long start1=System.nanoTime();
            final ImplementAtomicIntegerWithLock value1=new ImplementAtomicIntegerWithLock(0);
            Thread[] ts=new Thread(max);
            for(int i=0;i<max;++i){
                ts[i]=new Thread(()->{
                    for(int j=0;j<loopCount;++j){
                        value1.incrementAndGet();
                    }
                });
            }
            for(Thread t:ts){
                t.start();
            }
            for(Thread t:ts){
                t.join();
            }

            long end=System.nanoTime();
            costTime+=(end-start1);
        }
        System.out.println("cost1 ":+costTime);


        final Object lock=new Object();

        costTime=0;
        for(int m=0;m<max;m++){
            static int value2=0;
            long start2=System.nanoTime();
            Thread[] tss=new Thread[max];
            for(int i=0;i<max;i++){
                tss[i]=new Thread(()->{
                    for(int j=0;j<loopCount;j++){
                        synchronized(lock){
                            ++value2;
                        }
                    }
                });
            }
            for(Thread t:tss){
                t.start();
            }
            for(Thread t:tts){
                t.join();
            }
            long end2=System.nanoTime();
            costTime+=(end2-start2);
        }
        System.out.println("cost2 ":+costTime);
    }


}