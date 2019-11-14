
## Comparable 及 Comparator 的区别


### Comparable

一个内比较器，类可以和自己比较

compareTo()  自然比较方法

```java
public interface Comparable<T>{
    public int compareTo(T o);
}

public class Book implements Comparable<Book>{
    String name;
    int price;

    public Book(String name,int price){
        super();
        this.name=name;
        this.price=price;
    }
    public int getPrice(){
        return price;
    }

    @override
    public int CompareTo(Book b){
        return this.price - b.getPrice();
    }

}
```

一个对象Collections,Arrays想自己调用sort进行自动排序，对象必须实现Comparable接口



### Comparator 比较器

类本身不支持排序(没有实现comparable接口)
可建立一个类比较器来进行排序

```java
public interface Comparator<T>{
    // 这个函数必须实现
    int compare(T o1,T o2);

    // 这个函数可以不用实现
    boolean equals(Object obj);
}

public class BookComparator implements Comparator<Book>{
    @override
    public int compare(Book b1,Book b2){
        return b1.getAge()- b2.getAge();
    }
}

public class Book{
    String name;
    int price;

    public Book(String name,int price){
        super();
        this.name=name;
        this.price=price;
    }
    public int getPrice(){
        return price;
    }
    public static void main(String[] args){
        Book[] allBook=new Book[]{new Book("math",3),new Book("java",16)};
        Arrays.sort(allBook,new BookComparator());
    }

}
```


### 总结

Comparable 是排序接口，只要类实现，该类就支持排序(内部排序)

Comparator 是比较器，控制某个类的次序，建立类的比较器来进行排序(外部比较器)

Comparator 是一种策略模式



### lambda表达式

由匿名类逐渐简化实现 lambda表达式

将方法作为参数进行传递的编程方法

在函数运行过程中，lambda都会还原成匿名类方式。

引入lambda表达式，会使代码更加紧凑，而不是各种接口和匿名类到处都有



**聚合操作**

- 1.stream 一系列元素，一串一串

- 2.管道 一系列的聚合操作


**管道**

- 管道源，类集，数组

类集 Collection.stream

数组 Arrays.stream(hs)

- 中间操作  ，不会进行遍历(返回Stream)

filter  匹配

distinct 去重

sorted  排序

sorted(Comparator<T>)

limit   保留

skip    忽略

mapToDouble  转换为double的流

map          转换为任意类型的流

- 结束操作， 流使用完，最后一个操作，结束操作进行真正的遍历行为


forEach() 遍历每个数组

toArray()  转换为数组

min(Comparator<T>)  取最小的元素

max(Comparator<T>)  取最大的元素

count()     总数

findFirst()   第一个元素

get()