import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

public class AtomicIntegerFieldUpdaterDemo{

    class dataDemo{
        public volatile int value1=1;
        volatile int value2=2;
        protected volatile int value3=3;
        private volatile int value4=4;
    }
    // 利用反射机制
    AtomicIntegerFieldUpdater<dataDemo> getUpdater(String fieldName){
        return AtomicIntegerFieldUpdater.newUpdater(dataDemo.class, fieldName);
    }
    void doit(){
        dataDemo data=new dataDemo();
        System.out.println("1 ==> "+getUpdater("value1").getAndSet(data, 10));
        System.out.println("3 ==> "+getUpdater("value2").incrementAndGet(data));
        System.out.println("2 ==> "+getUpdater("value3").decrementAndGet(data));
        System.out.println("true ==> "+getUpdater("value4").compareAndSet(data, 4, 5));
    }
    public static void main(String[] args){
        AtomicIntegerFieldUpdaterDemo demo=new AtomicIntegerFieldUpdaterDemo();
        demo.doit();
    }
}