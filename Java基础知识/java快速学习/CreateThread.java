package com.thread;

import java.lang.*;
public class FirstThreadTest extends Thread{
    int i=0;
    // rewrite the run 
    public void run(){
        for(;i<100;++i){
            System.out.println(getName()+" "+i);
        }
    }
    public static void main(String args[]){
        for(int i=0;i<100;++i){
            System.out.println(Thread.currentThread().getName()+" "+i);
            if(i==20){
                new FirstThreadTest().start();
                new FirstThreadTest().start();
            }
        }
    }
}

// use Runnable implement

public class RunnableThreadTest implement Runnable{
    private int i;
    public void run(){
        for(int i=0;i<100;++i){
            System.out.println(Thread.currentThread.getName()+" "+i);
        }
    }
    public static void main(String args[]){
        for(int i=0;i<100;++i){
            System.out.println(Thread.currentThread.getName()+":"+i);
            if(i==20){
                RunnableThreadTest rtt=new RunnableThreadTest();
                new Thread(rtt,"new thread1").start();
                new Thread(rtt,"new thread2").start();
            }
        }
    }
}

// use Callable and Future

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class CallableThreadTest implement Callable<Integer>{
    public static void main(String args[]){
        CallableThreadTest ctt=new CallableThreadTest();
        FutureTask<Integer> ft=new FutureTask<>(ctt);
        for(int i=0;i<100;++i){
            System.out.println(Thread.currentThread.getName()+" "+i);
            if(i==20){
                new Thread(ft,"have a return value").start();
            }
        }
        try{
            System.out.println("subThread return value:"+ft.get());
        }catch(InterruptedException e){
            e.printStackTree();
        }catch(ExecutionException e){
            e.printStackTree();
        }
    }
    @Override
    public Integer call() throws Exception{
        int i=0;
        for(;i<100;++i){
            System.out.println(Thread.currentThread.getName()+" "+i);
        }
        return i;
    }

}