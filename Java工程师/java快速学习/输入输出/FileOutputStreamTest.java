import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 测试
 */

public class FileOutputStreamTest{

    public static void main(String[]args) throws IOException{
        String str="Now is today,get meeting with dongfeng motor mangement";
        byte[] b_arr=str.getBytes();


        FileOutputStream fos=new FileOutputStream("a.txt");
        for(int i=0;i<b_arr.length;++i){
            fos.write(b_arr[i]);
        }
        fos.close();

        FileOutputStream fos2=new FileOutputStream("b.txt");
        fos2.write(b_arr);

        fos2.close();

        FileOutputStream fos3=new FileOutputStream("c.txt");
        fos3.write(b_arr,(b_arr.length-(b_arr.length/4)),b_arr.length/4);

        fos3.close();

    }
}