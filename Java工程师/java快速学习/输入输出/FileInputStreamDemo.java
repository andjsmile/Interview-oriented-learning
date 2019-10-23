import java.io.FileInputStream;
import java.io.IOException;

/**
 * 
 */

public class FileInputStreamDemo{

    public static void main(String[] args) throws IOException{
        int size;

        try{
            File f=new File("FileInputStreamDemo.java");
            FileInputStream fis=new FileInputStream(f);

            System.out.println("total available byte:"+fis.available());

            int n=size/40;

            System.out.println("first"+n+"byte of file read at time");
            for(int i=0;i<n;++i){
                System.out.print((char)fis.read());
            }
            System.out.println("\n still have:"+fis.available());

            System.out.println("reading the next "+n+"with one read(b[])");

            byte[] b=new byte[n];
            if(fis.read(b)!=n){
                System.err.println("couldn't read " + n + " bytes.");
            }
            System.out.println(new String(b,0,n));

            f.close();

            
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}