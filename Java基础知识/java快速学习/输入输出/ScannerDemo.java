import java.io.*;
import java.util.Scanner;
public class ScannerDemo{
    public static void main(String args[]){
        Scanner scan=new Scanner(System.in);

        System.out.println("next 方式接收:");

        if(scan.hasNext()){                    // 当然还有nextLine()方法
            String str1=scan.next();
            System.out.println("输入的数据为"+str1);
        }
        scan.close();
    }
}