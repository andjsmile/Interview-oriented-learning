import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;

/**
 * 实现lambda的方法引用
 * 
 * 1.静态方法
 * 2.对象方法
 * 3.容器中对象方法
 * 4.引用构造器
 * 
 */

public class LambdaObjectReference{

    public static void main(String[] args){
        List<Hero> heros=new ArrayList<Hero>();

        Random r=new Random();

        for(int i=0;i<5;++i){
            heros.add(new Hero("name"+i,r.nextInt(1000),r.nextInt(100);
        }
        System.out.println("构造完成:");
        System.out.print(heros);

        // 三方方法是一样的
        filter(heros,h->h.hp>100 && h.damge<50);

        // lambda表达式中使用静态方法
        filter(heros,h->LambdaObjectReference.testHero(h));
        // 直接引用静态方法
        filter(heros,LambdaObjectReference::testHero)

        LambdaObjectReference lambdaObject=new LambdaObjectReference();
        // 引用对象方法
        filter(heros,lambdaObject::testHero);


        /****************** 构造方法 *******************/
        Supplier<List> s=new Supplier<List>(){
            public List get(){
                return new ArrayList<>();
            }
        }
    }
    // 匿名类
    List list1=getList(s);
    // lambda表达式
    List list2=getList(()->new ArrayList());

    // 引用构造器
    List list3=getList(ArrayList::new);

    public static List getList(Supplier<List> s){
        return s.get();
    }
    public static boolean testHero(Hero h){
        return (h.hp>100 && h.damage<50);
    }

    private void filter(List<Hero> h,HeroFiler hf){
        for(Hero h:heros){
            if(hf.test(hero)){
                System.out.print(h);
            }
        }
    }
}