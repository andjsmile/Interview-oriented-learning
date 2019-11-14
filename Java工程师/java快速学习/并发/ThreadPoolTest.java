import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * FutureTask Callable
 */
/*
两个接口

public interface Callable<V>{
    V call() throws Exception;
}

public interface Future<V>{
    V get() throws ...;
    V get(long timeout,TimeUnit unit) throws ...;
    void cancel(boolean mayInterrupt);
    boolean isCancelled();
    boolean isDone();
}

*/
public class ThreaPoolTest{
    public static void main(String[] args)throws Exception{
        try(Scanner in=new Scanner(System.in)){
            System.out.print("enter a directory(e.g. /usr/local/jdk5.0/src):");
            String directory=in.nextLine();
            System.out.print("enter keyword (e.g.volatile)");
            String keyword=in.nextLine();

            // 带缓存的线程池
            ExecutorService pool=Executors.newCachedThreadPool();

            MatchCounter counter=new MatchCounter(new File(directory),keyword,pool);
            FutureTask<Integer> result=pool.submit(counter);
            //Thread t=new Thread(task);
            t.start();
            try{
                System.out.println(result.get()+"matching Files");
            }catch(ExecutionException e){
                e.printStackTrace();
            }catch(InterruptedException e){

            }
            pool.shutdown();

            int largestPoolSize=((ThreadPoolExecutor)pool).getLargestPoolSize();
            System.out.println("largest pool size="+largestPoolSize);


        }
    }
}

class MatchCounter implements Callable<Integer>{
    private File directory;
    private String keyword;
    private ExecutorService pool;
    private int count;

    public MatchCounter(File directory,String keyword,ExecutorService pool){
        this.directory=directory;
        this.keyword=keyword;
        this.pool=pool;

    }
    public Integer call(){
        int count=0;
        try{
            File []files=directory.listFiles();
            List<FutureTask<Integer>> results=new ArrayList<>();

            for(File file:files){
                if(file.isDirectory()){
                    MatchCounter counter=new MatchCounter(file, keyword,pool);
                    FutureTask<Integer> result=pool.submit(counter);
                    results.add(result);
                    //Thread t=new Thread(task);
                    t.start();
                }else{
                    if(search(file))
                        count++;
                }
            }
            for(Future<Integer> result:results){
                try{
                    count+=result.get();
                }catch(ExecutionException e){
                    e.printStackTrace();
                }
            }
        }catch(InterruptedException e){
            //e.printStackTrace();
        }
        return count;
    }

    public boolean search(File file){
        try{
            try(Scanner in=new Scanner(file,"UTF-8")){
                boolean found=false;
                while(!found && in.hasNextLine()){
                    String line=in.nextLine();
                    if(line.contains(keyword){
                        found=true;
                    }
                }
                return found;
            }
        }catch(IOException e){
            return false;
        }
    }
}