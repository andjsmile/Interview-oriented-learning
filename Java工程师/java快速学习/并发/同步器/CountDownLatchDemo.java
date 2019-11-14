import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * 将程序分成n个相独立的可解决问题
 */

// perform some portion of task
class TaskPortion implements Runnable{
    private static int counter=0;
    private final int id=counter++;

    private static Random rand=new Random(47);
    private final CountDownLatch latch;

    TaskPortion(CountDownLatch latch){
        this.latch=latch;
    }

    public void doWork()throws InterruptedException{
        TimeUnit.MILLISECONDS.sleep(rand.nextInt(2000));
        System.out.println(this+" completed");

    }

    public String toString(){
        return String.format("%1$-3d", id);
    }
    @Override
    public void run(){
        try{
            doWork();
            latch.countDown();
        }catch(InterruptedException e){

        }
    }
}

// wait on the countDownLatch
class waitingTask implements Runnable{
    private static int counter=0;
    private static int id=counter++;
    private final CountDownLatch latch;
    waitingTask(CountDownLatch latch){
        this.latch=latch;
    }

    @Override
    public void run(){
        try{
            latch.await();
            System.out.println("latch barrier passed for "+this);
        }catch(InterruptedException e){
            System.out.println(this+"interrupted");
        }
    }
    public String toString(){
        return 
    }
}