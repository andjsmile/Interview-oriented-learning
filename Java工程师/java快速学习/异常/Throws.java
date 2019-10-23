/**
 * 异常的处理机制
 */
import java.lang.Exception;
public class Throws(){

    public static void main(String[]args){
        method1();
    }

    public static void method1(){

        try{
            method2();
        }catch(NullPointerException e){
            e.printStackTrace();
        }
        
    }


    public static void method2() throws NullPointerException{

    }
}