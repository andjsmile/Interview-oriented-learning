/**
 * 多线程打印问题
 * 理解 wait()及 notify()的工作原理
 * A线程打印10次A，B线程打印10次B,C线程打印10次C，要求线程同时运行，交替打印10次ABC
 */

public class MultiThreadPrinter implements Runnable{

    private String name;
    private Object prev;
    private Object self;

    MultiThreadPrinter(String name,Object prev,Object self){
        this.name=name;
        this.prev=prev;
        this.self=self;
    }
    @Override
    public void run(){
        int count=10;
        while(count>0){
            synchronized(prev){
                
                synchronized(self){
                    System.out.print(name);
                    count--;
                    self.notify();
                }
                try{
                    prev.wait();
                }catch(InterruptedException e){
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) throws Exception{
        Object a=new Object();
        Object b=new Object();
        Object c=new Object();

        MultiThreadPrinter pa=new MultiThreadPrinter("A", c, a);
        MultiThreadPrinter pb=new MultiThreadPrinter("B", a, b);
        MultiThreadPrinter pc=new MultiThreadPrinter("C", b, c);

        new Thread(pa).start();
        Thread.sleep(100);
        new Thread(pb).start();
        Thread.sleep(100);
        new Thread(pc).start();
        Thread.sleep(100);

    }
}