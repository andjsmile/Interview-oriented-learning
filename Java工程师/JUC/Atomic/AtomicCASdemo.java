import jdk.internal.misc.Unsafe;

/**
 * 乐观锁机制CAS compare and swap
 * 利用cpu的CAS指令来完成非阻塞式算法
 * AtomicInteger在没有锁的情况下保证数据的正确性
 */

public class AtomicCASdemo{

    private volatile int value;

    public final int get(){
        return value;
    }
    //++i操作
    public final int incrementAndGet(){
        int current=get();
        int next=current+1;
        if(compareAndSet(current,next)){
            return next;
        }
    }

    public final boolean compareAndSet(int expected,int nextValue){
        return Unsafe.compareAndSwapInt(this,valueoffset,expected,nextValue);

    }

    // 还有另外一种解释
    private AtomicInteger cnt = new AtomicInteger();
    public void add() { 
        cnt.incrementAndGet();
    }
    public final int incrementAndGet() {
        return unsafe.getAndAddInt(this, valueOffset, 1) + 1;
    }
    /**
     * 
     * @param var1 对象内存地址
     * @param var2 字段对对象内存地址的偏移
     * @param var4 需要加的数值
     * @return
     */
    public final int getAndAddInt(Object var1, long var2, int var4) {
        int var5;
        do {
            // 得到就的预期值
            var5 = this.getIntVolatile(var1, var2);
        } while(!this.compareAndSwapInt(var1, var2, var5, var5 + var4));
        return var5;
    }
}