import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

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
public class FutureTaskTest{
    public static void main(String[] args){
        try(Scanner in=new Scanner(System.in)){
            System.out.print("enter a directory(e.g. /usr/local/jdk5.0/src):");
            String directory=in.nextLine();
            System.out.print("enter keyword (e.g.volatile)");
            String keyword=in.nextLine();

            MatchCounter counter=new MatchCounter(new File(directory),keyword);
            FutureTask<Integer> task=new FutureTask<>(counter);
            Thread t=new Thread(task);
            t.start();
            try{
                System.out.println(task.get()+"matching Files");
            }catch(ExecutionException e){
                e.printStackTrace();
            }catch(InterruptedException e){

            }
        }
    }
}

class MatchCounter implements Callable<Integer>{
    private File directory;
    private String keyword;

    public MatchCounter(File directory,String keyword){
        this.directory=directory;
        this.keyword=keyword;

    }
    public Integer call(){
        int count=0;
        try{
            File []files=directory.listFiles();
            List<FutureTask<Integer>> result=new ArrayList<>();

            for(File file:files){
                if(file.isDirectory()){
                    MatchCounter counter=new MatchCounter(file, keyword);
                    FutureTask<Integer> task=new FutureTask<>(counter);
                    result.add(task);
                    Thread t=new Thread(task);
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

