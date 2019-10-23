import java.io.UnsupportedEncodingException;

/**
 * 编码方式学习
 */

public class EncodeWay{

    public static void showCode(String str,String encode){
        try{
            String printMode="字符:\"%s \" 在\"%s\" 编码下的十六进制值%n";
            System.out.printf(printMode,str,encode);

            byte[] buffer=str.getBytes(encode);

            for(byte b:buffer){
                int i=b&0xff;
                System.out.print(Integer.toHexString(i)+"\t");
            }
            System.out.println();
            System.out.println();
        }catch(UnsupportedEncodingException ee){
            ee.printStackTrace();
        }
    }
    public static void showCode(String str){
        String[] encodes={"BIG5","GBK","GB2312","UTF-8","UTF-16","UTF-32"};

        for(string encode:encodes){
            showCode(str,encode);
        }
    }
    public static void main(String[] args){
        String str="邓";
        showCode(str);
    }
}