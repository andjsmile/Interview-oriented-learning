import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * BlockingQueue阻塞队列
 * 
 * 实现 
 * 程序在一个目录及它的所有子目录下搜索所有文件，
 * 打印出包含指定关键字的行
 */

public class BlockingQueueTest{

    private static final int FILE_QUEUE_SIZE=10;
    private static final int SEARCH_THREAD=100;
    private static final File DUMMY=new File("");

    private static BlockingQueue<File> queue=new ArrayBlockingQueue<>(FILE_QUEUE_SIZE);

    public static void main(String[] args){

        try(Scanner in=new Scanner(System.in)){
            System.out.println("Enter a base directory ,e.g. (/opt/jdk1.8.8/src)");
            String directory=in.nextLine();
            System.out.println("Enter a keyword");
            String keyword=in.nextLine();

            Runnable enumerator=()->{
                try{
                    enumerate(new File(directory));
                    queue.put(DUMMY);
                }catch(InterruptedException e){

                }
            };
            new Thread(enumerator).start();

            for(int i=1;i<SEARCH_THREAD;++i){
                Runnable serach=()->{
                    try{
                        boolean done=false;
                        while(!done){
                            File file=queue.take();
                            if(file==DUMMY){
                                done=true;
                            }else{
                                serach(file,keyword);
                            }
                        }
                    }catch( IOException e){
                        e.printStackTrace();
                    }catch(InterruptedException ie){
                        ie.printStackTrace();
                    }
                };
                new Thread(serach).start();
            }
        }
    }

    public static void enumerate(File directory) throws InterruptedException{
        File[] files=directory.listFiles();
        for(File f:files){
            // 递归的读取文件下内容
            if(f.isDirectory()) enumerate(f);
            else
                queue.put(f);
        }
    }

    public static void serach(File file,String keyword) throws IOException,InterruptedException{
        try(Scanner in =new Scanner(file,'uff-8')){
            int lineNumber=0;
            while(in.hasNextLine()){
                lineNumber++;
                String line=in.nextLine();
                if(line.contains(keyword)){
                    System.out.printf("%s:%d:%s%n",file.getPath(),lineNumber,line);
                }
            }
        }
    }
}