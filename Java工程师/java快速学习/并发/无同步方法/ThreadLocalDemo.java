/**
 * 线程的本地存储
 * ThreadLocal 
 * 实现共享数据的代码在同一个线程内执行
 * 线程本地存储来解决线程安全问题
 */
import java.lang.ThreadLocal;
import java.util.*;

public class ThreadLocalDemo{

    public static void main(String[] args){
        ThreadLocal threadLocal=new ThreadLocal<>();
        Thread thread1=new Thread(()->{
            threadLocal.set(1);
            try{
                Thread.sleep(100);
            }catch(InterruptedException e){
                e.printStackTrace();
            }
            System.out.println(threadLocal.get(1));
            threadLocal.remove();
        });

        Thread thread2=new Thread(()->{
            threadLocal.set(2);
            threadLocal.remove();
        });

        thread1.start();
        thread2.start();
    }
    

}