/**
 * 字节流
 */

import java.io.InputStream;
import java.io.File;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class StreamTest{

    public static void main(String[] args){

        try{
            File f=new File("/Users/dengshuodengshuo/code/t.txt");
        

            // 创建基于文件的输入流
            FileInputStream fis=new FileInputStream(f);

            // 输入流读取到字节数组
            // file.length() 默认是返回  long
            byte[] b_all=new byte[(int)f.length()];

            fis.read(all);

            for(byte b:b_all){
                System.out.println(b);
            }

            fis.close();

            // 文件不存在，写出自动创建文件
            // 当前文件夹不存在会抛出错误

            File f2=new File("/Users/dengshuodengshuo/code/x.txt");
            byte[] data={56,90};

            // 基于文件的输入流
            FileOutputStream fos=new FileOutputStream(f2);

            fos.write(data);

            fos.close();


        }catch(IOException e){
            // 
            e.printStackTrace();
        }

        try{
            File fe=new File("Users/local/lol.txt");

            // 获取文件所在的目录
            File dir=fe.getParentFile();

            if(dir.exists()){
                dir.mkdirs()
            }
            FileOutputStream fos=new FileOutputStream(fe);
            byte[] b_arr={23,24,56,89};
            fos.write(b_arr);
            fos.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}