## 类型信息

### 1.java如何在运行时识别对象和类的信息

- "传统的"RTTI run-time type identification ,假设我们在编译时已经知道了所有类型,在编译的时候打开和检查.class文件

- 反射机制,允许在运行时发现和使用类的信息,在运行的时候打开和检查.class文件

运行时的类型信息使得你可以在程序运行时发现和使用类型信息


### 2.Class对象

Class对象这个特殊对象，包含了类有关的信息
每个类都有一个Class对象，保存在编译后的同名的 .class文件中

名词

**类加载子系统**

**原生类加载器**

所有的类都是对其第一次使用的时候，动态加载到JVM中,当程序创建第一个对**类的静态成员引用**时，就会加载这个类

构造器也是静态方法,new 在创建类的新对象的时候，也会被当做对类静态成员的引用

java程序运行时，不是将所有的类全部加载，而是需要的时候才进行加载

static初始化是在类加载的时候进行的


**无论何时，只要想在运行时使用类型信息，就必须首先获得恰当的Class对象的引用**


**如何获得Class对象的引用？**

- Class.forName("the class name"),不需要持有该类型的对象 就可以获得对象

- T.class  ,类字面常量 (注意，这个方法创建对Class对象的引用时，不会自动地初始化该Class对象)

- Object.getClass(),拥有类型对象，直接使用方法获得


- getSuperclass() ,拥有Class对象，直接查询基类



**类的实际工作步骤**

- 加载，类加载器执行
- 链接，验证类中字节码,静态域分配存储空间
- 初始化，超类的初始化，执行静态初始化器和静态初始化块

初始化被延迟到对静态方法(构造器也是静态的)或者非常数静态域首次引用才执行

```java
import java.util.Random;
/**
 * ClassInitialization
 * @@author thinking in java
 * @@version 1.1
 */
class Initable{
    // 编译器常量
    static final int staticFinal=1;
    // 不是编译器常量
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
        //  使用.class获得类的引用不会引发初始化
        Class initable=Initable.class;
        System.out.println("after creating initable ref");
        //  static final 编译器常量，可以不对类进行初始化就可进行读取
        System.out.println(Initable.staticFinal);
        // 触发类的初始化
        System.out.println(Initable.staticFinal2);
        // static 或 final 在读取前必须进行链接(分配存储空间)和初始化(初始化存储空间)
        System.out.println(Initable2.staticNonFinal);
        // Class.forName() 立即进行初始化
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
```


###  instanceof

返回一个布尔值，告诉我们对象是不是某个特定类型的实例

Class中还有一个方法
```java
public boolean isInstance(Object obj);
```



### 反射

RTTI的限制,想知道某个对象的确切类型，有个限制,这个类型在编译时必须已知

获取某个不在程序空间的引用, Class.forName("com.mysql.cj.DriverManger");
或者从网络，磁盘的字节中获取一个代表类


Class类 和 java.lang.reflect类库 完整支持整个**反射**概念

- Filed   ，使用get ，set来 修改Field相关字段
- Method  , invoke()来实现方法调用
- Constructor，构建新的对象


反射是用来支持其他创建动态代码会用到的特性(序列化、javaBean)


**创建反射实例**

```java
// Class的newInstance()
Class<?> c=String.class;
Object str=c.newInstance();

// Class的Constructor对象，可以指定构造器来实现
Class<?> c=String.class;
Constructor constructor=c.getConstructor(String.class);
Object obj=constructor.newInstance("456");
```

**获取构造器信息**
Class类的getConstructor方法得到Constructor类的一个实例
而Constructor类有一个newInstance方法可以创建一个对象实例

```java
// 类的所有公有构造器
public Constructor[] getConstructors()

// 类的所有构造器
public Constructor[] getDeclareConstructors()

```

**获取方法**
获取某个Class对象的方法集合

```java
// 返回类的公有方法，包含继承的公有方法数组
public Method[] getMethods() throws SecurityException

// 返回类的所有方法，不包括由超类继承的方法
public Method[] getDeclareMethods() throws SecurityException

// 返回一个特定的方法

public Method getMethod(String name,Class<?>...parameterTypes);

```

**获取字段信息**

```java
// 返回一个字段域，记录了类和超类的公有域
public Filed[] getFields()

// 记录类的全部域,以声明的成员变量，不能得到父类的成员变量
public Field[] getDeclareFields()
```

**调用方法**

```java
// 函数原型
public Object invoke(Object obj,Object ...args)
              throws IllegalAccessException, IllegalArgumentException,InvocationTargetException


// 一个测试用例
public class test1 {
    public static void main(String[] args) throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        Class<?> klass = methodClass.class;
        //创建methodClass的实例
        Object obj = klass.newInstance();
        //获取methodClass类的add方法
        Method method = klass.getMethod("add",int.class,int.class);
        //调用method对应的方法 => add(1,4)
        Object result = method.invoke(obj,1,4);
        System.out.println(result);
    }
}
class methodClass {
    public final int fuck = 3;
    public int add(int a,int b) {
        return a+b;
    }
    public int sub(int a,int b) {
        return a+b;
    }
}
```


**反射的优缺点**

如果一个功能不用反射完成，就不要用反射

优点:

- 可拓展性，可以使用全限定名来创建可拓展实例对象，使用来自外部的自定类

- 类浏览器和可视化开发工具,idea,escipse 中的显示类提示，就是反射的应用

- 调试器和测试工具，

缺点:

- 性能开销，动态类型解析，JVM无法对代码进行优化，反射的效率较低

- 安全限制，必须在没有安全限制的环境中运行

- 内部暴露，反射代码破坏了抽象性





### 参考:

[深入解析Java反射（1） - 基础(sczyh30)](https://www.sczyh30.com/posts/Java/java-reflection-1/)
**Thinking in java**
**Java核心技术(卷-)**
**技术面试必备-CyC2018**