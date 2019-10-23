/**
 *  创建文件对象File
 * */

import java.io.File;

public class FileTest{

    public static void main(String []args){
        File f1=new File("/Users/dengshuodengshuo/Code/Interview/Interview-oriented learning/Java工程师/java快速学习/输入输出")

        string pathf1=f1.getAbsolutePath();
        System.out.println("f1的绝对路径"+pathf1);

        // 当前目录下的工作环境

        File f2=new File("lol.py");
        System.out.println("f2的绝对路径"+f2.getAbsolutePath());

        // string parent，string child
        File f3=new File(f1, "lol.py");
        System.out.println("f3的绝对路径:"+f3.getAbsolutePath());

    }
}
