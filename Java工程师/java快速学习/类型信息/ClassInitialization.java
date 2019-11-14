import java.util.Random;

/**
 * ClassInitialization
 * @@author thinking in java
 * @@version 1.1
 */

class Initable{
    static final int staticFinal=1;
    static final int staticFinal2=ClassInitialization.rand.nextInt(1000);
    static{
        System.out.println("initializing Initable");
    }
}

class Initable2{
    static int staticNonFinal=2;
    static{
        System.out.println("initializing Initable2");
    }
}

class Initable3{
    static int staticNonFinal=3;
    static{
        System.out.println("initializing Initable3");
    }
}

public class ClassInitialization{

    public static Random rand=new Random(47);

    public static void main(String[] args) throws Exception{

        Class initable=Initable.class;
        System.out.println("after creating initable ref");

        System.out.println(Initable.staticFinal);
        // 触发类的初始化
        System.out.println(Initable.staticFinal2);

        System.out.println(Initable2.staticNonFinal);
        Class initable3=Class.forName("Initable3");
        System.out.println("after creating initable3 ref");
        System.out.println(Initable3.staticNonFinal);

    }
}
/*output:
after creating initable ref
1
initializing Initable
258
initializing Initable2
2
initializing Initable3
after creating initable3 ref
3
*///:~