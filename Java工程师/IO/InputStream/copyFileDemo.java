import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 实现字节操作
 * InputStream
 */
public class copyFileDemo{
    public static void copyFile(String src,String dest) throws IOException{
        FileInputStream fis=new FileInputStream(src);
        FileOutputStream fos=new FileOutputStream(dist);

        byte[] buffer=new byte[20*1024];
        int cnt;

        while((cnt=fis.read(buffer,0,buffer.length))!=-1){
            fos.write(buffer,0,cnt);
        }
        fis.close();
        fos.close();
    }
}