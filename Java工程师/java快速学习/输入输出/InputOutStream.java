import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


/**
 * 文件输入，输出流
 */

public class InputOutStream{

    public static void main(String[] args){
        try{
            File f=new File("/Users/dengshuodengshuo/Code/x.txt");
            FileInputStream fis=new FileInputStream(f);

            File f2=new File("/Users/dengshuodengshuo/Code/d.java");
            FileOutputStream fos=new FileOutputStream(f2)
        }catch(FileNotFoundException ne){
            ne.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}