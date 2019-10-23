
##  单例模式(Signleton)


### 定义
- 什么是单例模式？

单例模式是指类在各种情况下在只有一个实例

单例模式三元素:
- 1.构造函数私有化
- 2.静态属性执行实例
- 3.静态方法返回静态属性


### 应用场景
- 单例模式用来干什么?

类频繁的创建和销毁,控制实例数目，节省系统资源

多进程多线程操作同一个文件，文件的处理必须是单例模式

设备管理器，网站的计数器为单例模式，

多线程的线程池设计一般也是单例模式，实现线程池对池中线程进行控制



### 实现

最重要的两个要求:

1.构造函数私有化
2.任何时候保证，只有一个实例

- 1.饿汉式

首选模式

实例立刻进行创建,使用时候进行返回
可以实现线程安全

```java
public class Singleton{

    private static Singleton instance=new Singleton();
    private Singleton(){

    }
    public static Singleton getInstance(){
        return instance;
    }
    public void showMessage(){
        System.out.println("the instance created");
    }
}

public class SingletonDemo{
    public static void main(String[] args){
        Singleton sg=Singleton.getInstance();
        sg.showMessage();
    }
}
```


- 2.懒汉式

在需要用到的实例的时候，进行创建
线程不安全


**线程不安全**
假设有两个线程t1,t2;
线程t1在运行到位置1的时候，cpu的使用权被交换
线程t2继续运行,建立一个instance_t2
线程t1获得运行权，继续执行位置2创建一个instance_t1

```java
public class Singleton{

    private static Singleton instance;
    private Singleton(){

    }
    public static Singleton getInstance(){
        if(instance==null){            // 1
            instance==new Singleton(); // 2
        }
        return instance;
    }
}
```


- 3.实现线程安全

java每个对象都有一个内部锁
synchorinzed 关键字声明实现getInstace整个方法的锁
```java
public class Singleton{

    private static Singleton instance;
    private Singleton(){

    }
    public static synchronized Singleton getInstance(){
        if(instance==null){            // 1
            instance==new Singleton(); // 2
        }
        return instance;
    }
}
```

- 4.DCL(double-checked locking)双检锁

1位置进行判空，不为空就是已创建，直接5返回

1位置判断为空，进入synchronized同步块,synchronized加类锁，确保同时只有一个线程进入

3位置为什么还要判空?

对于第一个拿锁的，3位置instance==null 创建新的实例

对于第一个**拿锁在创建对象期间，也会有线程调用getInstance,此时实例还没有创建，1位置继续运行到位置2，发生阻塞**。

创建实例完成后，所有其他都不会进入1位置,**期间的阻塞进程唤醒，需要3位置的if语句来防止再次创建对象**


**volatile关键字**

**避免指令重排序**

4语句  instance= new Singleton()；

对象初始化过程中，JVM主要做的事情

这个语句进行分解:

- 1.堆上分配内存
- 2.填充对象信息，具体数据初始化，末位填充
- 3.将引用指向这个对象堆地址

完成操作1 后，2，3可能存在指令重排序的可能

3的操作明显比2少，2，3一起执行，具体的顺序变成 1-3-2

先完成3，2慢慢的执行

**这个时候，被synchronized挡在外面的阻塞线程无影响,会等到对象创建完，，首个拿锁者才会释放锁**

**在3完成，2未完成，有新的线程调用getInstance，对于位置1处的if，3完成，instance已经指向具体的内存地址，非空。导致直接返回未完成2语句，未完成初始化的instance实例**


volatile 避免指令重排序，按照1-2-3的顺序执行

```java
public class Singleton{

    private static volatile Singleton instance;
    private Singleton(){

    }
    public static Singleton getInstance(){
        if(instance==null){                   //1          
            synchronized(Singleton.class){    //2
                if(instance==null){           //3
                    instance==new Singleton();//4
                }
            } 
        }
        return instance;                       //5
    }
}
```


- 5.静态内部类

可以到达双检锁的同样的功效
静态域使用延迟初始化

```java
public class Singleton{

    private static class SingletonHolder{
        private static final Singleton INSTANCE=new Singleton();
    } 
    private Singleton(){

    }
    public static final Singleton getInstance(){
        return SingletonHolder.INSTANCE;
    }
}
```

- 6.枚举

实现单例模式的最佳最佳方式

简洁，自动支持序列化机制，避免多线程同步问题


```java
public enum Singleton{
    INSTANCE;
    public void Method(){

    }
}
```



#### 使用

1.默认选用饿汉式,实例立即加载

如果构造函数性能消耗大，例如建立与数据库的连接，启动就会有卡顿

2.使用lazy loading效果，可以先选择静态内部类实现

 懒汉式优先考虑线程安全

3.特殊要求使用双检锁要求























参考文章

0.[双检锁（DCL）下volatile的作用](https://blog.csdn.net/Lin_coffee/article/details/79890361)

1.[菜鸟教程-单例模式](https://www.runoob.com/design-pattern/singleton-pattern.html)