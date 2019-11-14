## 数组 及Arrays实用功能

### 1.问题来源

在学习容器的时候，对容器(集合)和数组在使用时如何选择有较大的问题?

### 2.数组

**数组及其他类容器的区别**

- 效率
- 类型
- 保存基本类型的能力

java中时内置数组Array,就是一个简单的线性序列


对于简单的数组

- 创建并组装他们
- 通过index访问元素 ,[]
- 尺寸不能改变
- 只读成员length

数组的标识符只是一个引用，指向堆中创建的真实对象，数组中的对象保存指向其他对象的引用

对象数组是**引用**
基本类型数组是**保存基本类型的值**


**java与C/C++的区别**

java中的方法是可以直接返回数组元素
c/c++不能返回数组元素，是要返回指向数组的指针，导致难以控制数组的生命周期，导致内存泄漏


### 3.Arrays的功能

一个重要知识点:

**1.快速的复制数组**
System.arraycopy() 实现的数组复制要比for-each循环快的多

```java
public static void arraycopy​(Object src, int srcPos, Object dest, int destPos, int length)
```
需要注意的是

- 1.基本类型和对象类型都可以复制
- 2.对象类型的复制是复制了引用，不是对象本身的拷贝(**浅拷贝**)
- 3.不会进行自动的装箱、拆箱;src，dest必须具有相同的类型


**2.Arrays中的常用static方法**

- 1.equals() ，数组的比较
- 2.asList() ，将任意序列或数组转换为List容器(适配器模式)
- 3.fill()   ，快速填充数组
- 4.sort()   ，排序
- 5.binarySearch() ，二分查找
- 6.toString()     ，产生数组的String表示
- 7.hashCode()     ，数组的散列码


**3.数组元素的比较 Comparble Comparator**

为了满足代码对各种类型的复用性

- 将保持不变的事物与会发生改变的事物相分离
- 不变的是通用排序算法，变化的是各种对象的比较形式
- 使用策略设计模式，将会发生变换的代码封装在类中(策略对象)



**java.lang.Comparable接口**

- compareTo()方法

实现方法后，直接Arrays.sort()

**Comparator接口**

- compare()方法

实现方法后，Arrays.sort(a,new Comparator())

基本类型无法使用Comparator进行排序




### 4.容易混淆的概念

- Array 数组，是居于索引的数据结构。获取数据的时间复杂度是O(1),但是要删除数据却是开销很大，因为这需要重排数组中的数据

- List 是一个有序的集合(接口)，可以包含重复元素

- List 的两个实现类  ArrayList，LinkedList

- ArrayList 是基于数组实现的能够自动增长容量的数组。

- LinkedList 是双链表，添加和删除元素时具有比ArrayList更好的性能.但在get与set方面弱于ArrayList





#### reference

1.thinking in java 中文版(第四版) 16章数组
2.[CyC2018](https://github.com/CyC2018/CS-Notes)







