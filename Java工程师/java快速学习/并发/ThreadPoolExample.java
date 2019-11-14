import java.util.LinkedList;

/**
 * 自定义开发一个简单的线程池
 * 线程可以回收反复使用
 */
public class ThreadPoolExample{
    int threadPoolSize;
    LinkedList<Runnable> tasks=new LinkedList<Runnable>();

    public ThreadPoolExample(){
        threadPoolSize=10;
        // 启动10个任务消费者线程
        synchronized(tasks){
            for(int i=0;i<threadPoolSize;++i){
                new TaskConsumerThread("任务消费者线程"+i).start();
            }
        }
    }
    public void add(Runnable r){
        synchronized(tasks){
            tasks.add(r);
            // 唤醒等待的任务消费者线程
            tasks.notifyAll();
        }
    }

    class TaskConsumerThread extends Thread{
        public TaskConsumerThread(String name){
            super(name);
        }
        Runnable task;
        public void run(){
            System.out.println("启动"+this.getName());
            while(true){
                synchronized(tasks){
                    while(tasks.isEmpty()){
                        try{
                            tasks.wait();
                        }catch(InterruptedException e){
                            e.printStackTrace();
                        }
                    }
                    task=tasks.removeLast();
                    tasks.notifyAll();
                }
            }
            System.out.println(this.getName()+"获取到任务，并执行");
            task.run();
        }
    }
}