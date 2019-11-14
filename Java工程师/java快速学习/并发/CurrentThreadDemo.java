/**
 * 获得当前执行线程的引用
 * return a reference to the currently executing thread object
 */

public class CurrentThreadDemo{
    public static void main(String[] args){
        Thread curr=Thread.currentThread();

        System.out.println("current thread name:"+curr);

        curr.setName("dengshuo thread");

        System.out.println("after change name:"+curr);

        try{
            for(int n=5;n>0;--n){
                System.out.println(n);
                Thread.sleep(1000);
            }
        }catch(InterruptedException ie){
            System.out.println("main thread interrupted");
        }

    }
}