/**
 * Runnable 实现多线程接口
 */

public class RunnableTest{
    public static void main(String[] args){
        MyRunnable run1=new MyRunnable("thread-1");
        //new Thread(run1).start();   
        run1.start();

        MyRunnable run2=new MyRunnable("thread-2");
        //new Thread(run2).start();
        run2.start();

    }
}

class MyRunnable implements Runnable{
    private Thread t;
    private String threadName;

    public MyRunnable(String name){
        threadName=name;
        System.out.println("creating "+threadName);
    }

    public void run(){
        System.out.println("running "+threadName);
        try{
            for(int i=0;i<5;++i){
                System.out.println("thread"+this.getClass()+i);
                Thread.sleep(500);
            }
        }catch(InterruptedException e){
            e.printStackTrace();
        }
        System.out.println("thread"+threadName+"exiting");
    }
    public void start(){
        System.out.println("starting"+threadName);
        if(t==null){
            t=new Thread(this,threadName);
            t.start();
        }
    }
}