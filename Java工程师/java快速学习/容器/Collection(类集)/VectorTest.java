import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * vector的实现和ArrayList类似
 * 使用 synchronized进行同步
 * 
 * 不同点:
 * 
 *      1.Vector是同步的开销要比ArrayList,能用ArrayList就使用，同步可以程序员自己编程实现
 *      2.Vector的扩容是请求其空间大小的2倍，ArrayList是1.5倍
 */

public class VectorTest{
    // 一般Vector都是被替代的
    // 1.第一种替代方式
    List<String> list=new Arraylist();
    List<String> synlist=Collections.synchronizedList(list);

    // 2.第二种替代方式 concurrent 并发包下的
    List<String> list2=new CopyOnWriteArrayList<>();

}