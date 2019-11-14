/**
 * 指令的重排序
 * 指令的顺序和代码的顺序不一致,jvm会根据处理器的特性，重新排序，使其顺序更复合cpu执行，更好发挥机器性能
 */

public class ReorderingDemo{

    static int x=0,y=0,a=0,b=0;

    public static void main(String[] args) throws Exception{
        int loopTime=100;
        for(int i=0;i<loopTime;++i){
            x=y=a=b=0;

            Thread thread1=new Thread(()->{
                a=1;
                x=b;
            });

            Thread thread2=new Thread(()->{
                b=1;
                y=a;
            });

            thread1.start();
            thread2.start();

            thread1.join();
            thread2.join();

            System.out.println("x= "+ x +", y= "+y);
        }
    }

}