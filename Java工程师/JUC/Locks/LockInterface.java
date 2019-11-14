import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;

/**
 * lock接口
 * java.util.concurrent.locks包下
 * 接口实现的类
 * ReentrantLock,ReentrantReadWriteLock.ReadLock,ReentrantReadWriteLock.WriteLock
 */

public interface Lock{
    void lock();

    void lockInterruptibly() throws InterruptedException;

    boolean tryLock();
    boolean tryLock(long time,TimeUnit unit) throws InterruptedException;
    
    void unlock();

    Condition newCondition;
}

