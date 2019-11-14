/**
 * Latch闭锁的实现
 * 
 */

public class CountDownLatchTest{
    public long timecost(final int time,final Runnable task) throws InterruptedException{
        if(time<=0) throws new IllegalArgumentException();
        final CountDownLatch startLatch = new CountDownLatch(1);
        final CountDownLatch overLatch = new CountDownLatch(times);
        for (int i = 0; i < times; i++) {
            new Thread(new Runnable() {
                public void run() {
                    try {
                        startLatch.await();
                        //
                        task.run();
                    } catch (InterruptedException ex) {
                        Thread.currentThread().interrupt();
                    } finally {
                        overLatch.countDown();
                    }
                }
            }).start();
        }
        long start = System.nanoTime();
        startLatch.countDown();
        overLatch.await();
        return System.nanoTime() - start;
    }
}