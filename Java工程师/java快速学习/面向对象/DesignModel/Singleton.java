
/**
 * 单例模式
 */

// 饿汉式
public class Signleton{

    private static Signleton instance=new Signleton();

    private Signleton(){

    }

    public static Signleton getInstance{
        return instance;
    } 

    public void showMessage(){
        System.out.println("get the signleton");
    }
}

// 懒汉式
// 线程不安全
public class Singleton2{

    private static Signleton2 instance;

    private Singeleton2(){

    }

    public static Singleton2 getInstance(){
        // 线程堆叠出现创建两个instance
        if(instance==null){
            instance=new Singleton2();
        }
        return instance;
    }
}

// 懒汉式
// 方法加上synchronized
public class Singleton3{

    private static Signleton3 instance;

    private Singeleton3(){

    }
    public static synchronized Singleton3 getInstance(){
        if(instance==null){
            instance=new Singleton3();
        }
        return instance;
    }
}