/**
 * volatile变量只能确保可见行，不能保证原子性
 * 
 */

public class AtomicIncrementDemo{

    private static volatile int num=0;

    private static void increase(){
        num++;
    }

    public static void main(String[] args) throws InterruptedException{
        int threadSize=100;
        Thread[] ts=new Thread[threadSize];

        for(int i=0;i<threadSize;++i){
            ts[i]=new Thread(()->{
                for(int j=0;j<100;j++){
                    increase();
                }
            });
        }
        for(Thread t:ts){
            t.start();
        }
        for(Thread t:ts){
            t.join();
        }

        System.out.println("num value= "+num);

    
    }
}