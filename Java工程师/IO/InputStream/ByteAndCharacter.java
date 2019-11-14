import java.io.UnsupportedEncodingException;

/**
 * 字符和字节之间的转换
 * 
 * 字节 Byte 是一种计量单位
 * 字符 是计算机中使用的文字和符号
 * 
 * String 就是字符序列
 * byte[] 就是字节序列
 */

public class ByteAndCharacter{

    public static void main(String[] args) throws UnsupportedEncodingException{
        String str1="中文";

        byte[] bytes=str1.getBytes("UTF-8");

        System.out.println(bytes);

        for(byte b:bytes){
            System.out.print(b);
        }
        System.out.println();

        String str2=new String(bytes,"UTF-8");

        System.out.println(str2);
    }
}