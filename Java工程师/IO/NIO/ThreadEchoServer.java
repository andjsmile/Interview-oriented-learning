import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * 实现一个可以处理多线程的服务器
 */
public class ThreadEchoServer{

    public static void main(String[] args)throws IOException{

        try{
            // 创建一个服务器
            ServerSocket ssocket=new ServerSocket(8189);
            int i=1;
            while(true){
                Socket incoming=ssocket.accept();
                System.out.println("spawning"+i);
                Runnable r=new threadEchoHandler(incoming);
                Thread t=new Thread(r);
                t.start();
                i++;
            }
        }catch(IOException e){
            e.printStackTrace();
        } 
    }
}

class threadEchoHandler implements Runnable{
    private Socket incoming;

    public threadEchoHandler(Socket incoming){
        this.incoming=incoming;
    }
    @Override
    public void run(){
        try(InputStream inStream=incoming.getInputStream();OutputStream outStream=incoming.getOutputStream()){
            Scanner in=new Scanner(inStream,"UTF-8");
            PrintWriter out=new PrintWriter(new OutputStreamWriter(outStream,"UTF-8"),true);

            out.println("hello! enter bye to exit");

            boolean done=false;
            while(!done && in.hasNextLine()){
                String line=in.hasNext();
                System.out.println("echo"+line);
                if(line.trim().equalsIgnoreCase("BYE")){
                    done=true;
                }
            }
        }
    }
}