import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * FutureTask
 * 可以作为一个任务执行，可以有返回值
 * 异步获取执行结果或者取消执行任务的场景
 * 
 * 一个任务需要执行很长时间，可以futuretask进行封装，主线程完成自己任务之后
 * 再去获取结果
 */
public class FutureTaskExample{
    public static void main(String[] args)throws ExecutionException,InterruptedException{
        FutureTask<Integer> futuretask=new FutureTask<Integer>(new Callable<Integer>(){
            @Override
            public Integer call() throws Exception{
                int result=0;
                for(int i=0;i<100;++i){
                    Thread.sleep(100);
                    result+=i;
                }
                return result;
            }
        });
        Thread computeThread=new Thread(futuretask);
        computeThread.start();

        Thread otherThread=new Thread(()->{
            System.out.println("the other thread is running...");
            try{
                Thread.sleep(1000);
            }catch(InterruptedException e){
                e.printStackTrace();
            }
        });
        otherThread.start();
        System.out.println(futuretask.get());
    }
}