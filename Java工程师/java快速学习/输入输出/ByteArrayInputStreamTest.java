/**
 * 字节数组输入流
 */

import java.io.ByteArrayInputStream;
public class ByteArrayInputStreamTest{

    public static void main(String[]args){

        String str="study hard and get a good job";
        byte[] b_arr=str.getBytes();

        ByteArrayInputStream bais=new ByteArrayInputStream(b_arr);

        for(int i=0;i<2;++i){
            
            int c;
            while((c=bais.read()!=-1){
                if(i==0){
                    System.out.print((char)c);
                }
                else{
                    System.out.print(Character.toUpperCase((char)c));
                }
            }
            System.out.println();
            // 重新设置流指针位置
            bais.reset();
        }
    }
}
/***
 * 输入 第一遍是小写
 *      第二遍实现字符的全部大写
 */