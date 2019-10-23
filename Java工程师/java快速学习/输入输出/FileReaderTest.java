import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * 文件字符流的读取
 */

public class FileReaderTest{
    public static void main(String[] args){
        try{
            File f=new File("FileReaderTest.java");
            FileReader fr=new FileReader(f);

            char[] c_arr=new char[int(f.length())];

            fr.read(c_arr);
            for(char c:c_arr){
                System.out.println(c);
            }
            fr.close();
        }catch(IOException e){
            e.printStackTrace();
        }

    }
}