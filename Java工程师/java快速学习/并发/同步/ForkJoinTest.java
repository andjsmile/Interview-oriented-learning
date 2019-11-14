import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.function.DoublePredicate;

import jdk.jfr.Threshold;

/**
 * Fork-join框架
 * 大的计算转换为小的计算
 * 和mapreduce的原理类似
 * 
 *  if(problem size< threshold){
 *     slove problem directly
 *  }
 *  else{
 *     break the problem into subproblem
 *     recursively slove each problem
 *     combine the result
 *  }
 * 
 * ForkJoinPool 实现了工作窃取算法来提高 CPU 的利用率。
 * 每个线程都维护了一个双端队列，用来存储需要执行的任务。
 * 工作窃取算法允许空闲的线程从其它线程的双端队列中窃取一个任务来执行。
 * 窃取的任务必须是最晚的任务，避 免和队列所属线程发生竞争
 */

public class  ForkJoinTest{
    public static void main(String[] args){
        final int SIZE=100000;
        double[] numbers=new double[SIZE];
        for(int i=0;i<SIZE;++i){
            numbers[i]=Math.random();
        }
        Counter counter=new Counter(numbers, 0, numbers.length, x->x>0.5);
        ForkJoinPool pool=new ForkJoinPool();
        pool.invoke(counter);
        System.out.prinltn(counter.join());
    }
}
class Counter extends RecursiveTask<T>{
    public static final int THRESHOLD=1000;
    private double[] values;
    private int from;
    private int to;
    private DoublePredicate filter;

    public Counter(double[] values,int from,int to,DoublePredicate filter){
        this.values=values;
        this.from=from;
        this.to=to;
        this.filter=filter;
    }

    @Override
    protected Integer compute(){
        if(to -from < THRESHOLD){
            int count=0;
            for(int i=from;i<to;++i){
                if(filter.test(values[i])) count++;
            }
            return count;
        }else{
            int mid=from+(to-from)/2;
            Counter first=new Counter(values, from, mid, filter);
            Counter second=new Counter(values, mid, to, filter);
            invokeAll(first,second);
            return first.join()+second.join();
        }
        
    }
}