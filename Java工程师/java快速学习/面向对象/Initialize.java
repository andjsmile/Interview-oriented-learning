/**
 * 对象属性的初始化顺序
 * 
 * 1. 声明时初始化
 * 2. 构造函数初始化
 * 3. 代码块初始化块
 */

public class Initialize{
    public String name=Initialize.getName("属性声明");

    public Initialize(){
        name=Initialize.getName("构造方法");
    }

    {
        name=Initialize.getName("初始化块")
    }

    public static String getString(String cur){
        String res="name"+cur;
        System.out.println(res);
        return res;
    }
    
    public static void main(String[]args){
        new Initialize();
    }
}

/**
 * 顺序: 对象属性声明> 构造方法> 初始化块
 */