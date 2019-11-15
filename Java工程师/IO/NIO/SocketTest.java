import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

/**
 * socket connection
 */
public class SocketTest{
    public static void main(String[] args){
        try{
            Socket s=new Socket("time-a.nist.gov",13);
            InetAddress address=InetAddress.getByName("time-a.nist.gov");
            System.out.println(address);
            Scanner in=new Scanner(s.getInputStream(),"UTF-8");

            while(in.hasNextLine()){
                String line=in.nextLine();
                System.out.print(line);
            }
            System.out.println();

        }catch(IOException e){
            e.printStackTrace();
        }
    }
}