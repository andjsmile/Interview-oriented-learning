import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * 哲学家
 */

public class Philosophers implements Runnable{
    private Chopsticks left;
    private Chopsticks right;
    private final int id;
    private final int ponderFactor;  // 考虑因子
    private Random rand=new Random(47);
    private void pause() throws InterruptedException{
        if(ponderFactor==0) return;
        TimeUnit.MICROSECONDS.sleep(rand.nextInt(ponderFactor* 250));
    }

    public Philosophers(Chopsticks left,Chopsticks right,int indent,int ponder){
        this.left=left;
        this.right=right;
        this.id=indent;
        this.ponderFactor=ponder;

    }

    @Override
    public void run(){
        try{
            while(!Thread.interrupted()){
                System.out.println(this +" "+"thining");
                pause();
                // hungry get the chopstick
                System.out.println(this+" "+"grap the right chopstick");
                right.take();
                System.out.println(this+" "+"grap the left chopstick");
                left.take();
                System.out.println(this+" "+"eating");

                pause();

                right.drop();
                left.drop();
            }
        }catch(InterruptedException e){
            System.out.println(this+"  exiting via interrupt");
        }

    }
    public string toString(){
        return "philosopher"+id;
    }

}