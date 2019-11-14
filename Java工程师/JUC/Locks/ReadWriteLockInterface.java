import java.util.concurrent.locks.Lock;

/**
 * 读写锁的接口
 */
public interface ReadWriteLock{
    Lock readLock();
    Lock writelLock();
}