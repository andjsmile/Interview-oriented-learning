import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * lambda表达式的语法及书写
 * lambda表达式就是一个代码块
 * 
 * 参数+箭头+表达式
 */

public class LambdaTest{
    public class static void main(String[] args){
        Random r=new Random();
        List<Hero> heros=new ArrayList<Hero>();

        for(int i=0;i<5;++i){
            heros.add(new Hero("hero"+i,r.nextInt(1000),r.nextInt(100)));
        }
        System.out.println("初始化后的结果:");
        System.out.println(heros);

        System.out.println("使用匿名类的方式筛选:");

        //要写一个接口类 HeroFliter
        HeroFilter hf=new HeroFilter(){
            @Override
            public boolean test(Hero h){
                return (h.hp> 100 && h.damage<50);
            }
        }
        filter(heros,hf);

        System.out.println("参数传递方式:");
        filter(heros,h->h.hp>100 && h.damage<50);

    }
    private static void filter(List<hero> heros,HeroFilter hf){
        for(hero temp:heros){
            if(hf.test(temp))
                System.out.print(temp);
        }
    }
}