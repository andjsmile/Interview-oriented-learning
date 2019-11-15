import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * 实现一个最简单的服务器
 */
public class EchoServer{
    public static void main(String[] args){
        // 建立一个服务器
        try{
            ServerSocket ssocket=new ServerSocket(8196);
            Socket s=ssocket.accept();

            InputStream ips=s.getInputStream();
            OutputStream ops=s.getOutputStream();

            Scanner in=new Scanner(ips,"UTF-8");
            PrintWriter out=new PrintWriter(new OutputStreamWriter(ops,"UTF-8"),true);

            out.println("hello! client,you can enter bye to exit");

            boolean done=false;
            while(!done && in.hasNextLine()){
                String line=in.nextLine();
                out.println("echo:"+line);
                if(line.trim().equalsIgnoreCase("BYE"))
                    done=true;
            }

        }catch(IOException e){
            e.printStackTrace();
        }
        
    }
}