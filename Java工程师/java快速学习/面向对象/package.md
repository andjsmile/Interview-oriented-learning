### package


将比较接近的类，规划在同一个包下

借助包可以方便的组织自己的代码，并将自己的代码与别人提供的代码库分开管理

一个类可以使用所属包中的所有类，以及其他包中的公有类 public class

当需要使用其他包下的类，使用import语句来读取类


成员变量有四种修饰符

- private 私有的

- package/friendly/default 不写

- protected 受保护的

- public 公共的


类之间关系:

- self自身
- 同包子类
- 不同包子类
- 同包类
- 其他类

![](/source/findAndInhrient.png)

**类属性**

类属性- 静态属性
对象属性- 非静态属性(实例属性)

属性，使用private封装起来

方法，使用public方法被调用

子类继承，使用protected

作用范围最小原则，能用private就用private

private>default>protected>public



**static**

属性被static修饰的时候，叫做类属性(静态属性)

所有的对象，都共享一个值，所有对象的类属性的值都是一样的

类属性，尽量使用类名来进行访问(不推荐使用对象名来进行访问)


**类方法**

类方法 - 静态方法
对象方法 - 非静态方法(实例方法)

静态方法是一种不能向对象实施操作的方法


一般使用类.类方法的方式进行

随机数的获取办法 Math.random()


**类、对象属性初始化**

对象属性初始化
- 声明该属性
- 构造方法中初始化
- 初始化块

初始化顺序: 属性>初始化块>构造方法

类属性初始化(static)

- 声明时候初始化
- 静态初始化块














https://interview.nowcoder.com/test/video/interview?callback=%2Finterview%2F358749%2Finterviewee%3Fcode%3DnAdVTLeE%26testDone%3Dtrue