/**
 * 实现Runnable接口
 */
public class RunnableDemo{
    class NewThread implements Runnable{
        Thread t;
        NewThread(){
            t=new Thread(this,"demo thread");
            System.out.println("child thread"+t);
            t.start();
        }
        @Override
        public void run(){
            try{
                for(int j=5;j>0;j--){
                    System.out.println("child thread"+i);
                    Thread.sleep(500);
                }
            }catch(InterruptedException e){
                System.out.println("child thread interrupted");
            }
            System.out.println("child thread exiting");
        }
    }

    public static void main(String[]args){

        try{
            for(int n=5;n>0;n--){
                System.out.println("main thread"+n);
                Thread.sleep(1000);
            }
        }catch(InterruptedException ie){
            System.out.println("main thread interrupted");
        }
    }
}
