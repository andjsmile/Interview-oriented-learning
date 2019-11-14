/**
 * 相互引用
 * 观察GC回收的判断法则
 */

public class ReferenceCountingGC{
    public Object instance=null;

    private static final int _1MB=1024*1024;

    private byte[] bigSize=new byte[2* _1MB];

    public static void testGC(){
        ReferenceCountingGC obj1=new ReferenceCountingGC();
        ReferenceCountingGC obj2=new ReferenceCountingGC();

        obj1.instance=obj2;

        obj2.instance=obj1;

        obj1=null;
        obj2=null;

        System.gc();
    }
    ClassLoader
}