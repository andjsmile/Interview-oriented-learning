import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * ConcurrentMap 接口中的方法 putIfAbsent
 * 这个方法可以实现单例模式
 */
public class ConcurrentDemo{

    private static final ConcurrentMap<String,ConcurrentDemo> map=new ConcurrentHashMap<>();

    private static ConcurrentDemo instance;

    public static ConcurrentDemo getInstance(){
        if(instance==null){
            //如果不存在key对应的值，则将value以key加入Map，否则返回key对应的旧值
            map.putIfAbsent("INSTANCE", new ConcurrentDemo());
            instance=map.get("INSTANCE");
        }
        return instance;
    }
    private ConcurrentDemo(){
        
    }
}