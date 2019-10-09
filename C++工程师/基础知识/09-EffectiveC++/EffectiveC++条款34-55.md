**条款34.区分接口继承和实现继承**

接口继承   function interfaces
实现继承   function implementations

1.成员函数的接口总会被继承

2.pure virtual 函数

- 纯虚函数,在抽象class中没有定义，继承其的class必须重新声明
- 声明一个纯虚函数的目的是为了让 derived classes 只继承函数接口，派生类必须自己实现函数

3.impure virtual 函数 

- 非纯虚函数的目的，derived classes 继承该函数的接口和缺省实现

4.non-virtual 函数

- 成员函数是non-virtual函数的目的是令derived classes继承函数的接口及一份强制性实现
- 任何的派生类都不应该尝试改变其行为


**条款35.考虑virtual函数以外的其他选择**

1.客户通过 public non-virtual成员函数间接调用 private virtual函数

std::function,相当于一个指向函数的泛化指针。

用function 函数成员变量替换virtual函数，因而允许使用任何可调用物(callable entity)
搭配一个需求的签名式，类似于 strategy 设计模式

strategy 模式- 将继承体系内的虚函数替换为另一个继承体系内的virtual函数,这是其传统的实现手法

**条款36.绝不重新定义继承而来的non-virtual函数**

非虚函数non-virtual 都是静态绑定的 statically bound

virtual函数都是动态绑定的 dynamically bound

**条款37.绝不重新定义继承而来的缺省参数值**

virtual函数属于动态绑定(dynamically bound)
缺省参数值 却是静态绑定(statically bound)


静态绑定(前期绑定 early binding)
动态绑定(后期绑定 late binding)


无论它们真正指向什么，静态类型都是shape*

对象所谓的动态类型 dynamic type 是指现在所指向的类型

动态调用哪一份代码，取决于发出**调用那个对象的动态类型**

C++工程师/基础知识/09-EffectiveC++/静态动态绑定.cpp

NVI non-virtual interface 
将virtual进行改装

绝对不要重新定义一个继承而来的缺省参数值，因为缺省参数值都是静态绑定，而virtual函数
一个唯一应该覆写的东西，却是动态绑定


**条款38.利用符合塑模出has-a 或根据某物实现出(is-implemented-in-terms-of)**

public 继承是一种  is-a(是一种)的关系

复合 composition 是一种 has-a(有一个) is_implement_in_term_of (根据某物实现出)


- 注意区分复合composition 和 public继承
- 在应用域 application domain，复合意味着 has-a(有一个)
  在实现域 implementation domain 复合意味着 is_implemented_in_terms_of(根据某物实现出))

**条款39.明智而审慎地使用private继承**

private继承规则:

- 编译器不会将一个derived class对象转换成一个base class对象
- private base class 继承而来的所有成员，在derived class中都会变成private属性

private继承纯粹只是一种实现技术，只用于软件实现层面

**条款40.多重继承**

多重继承要比单一继承复杂的多

### 模版与泛型编程

**条款41.了解隐式接口和编译期多态**

编译期多态:

template函数，哪一个重载函数该被调用

运行期多态:

哪一个virtual函数该被绑定


显式接口(explicit interface)由函数的签名式(函数名称，参数类型，返回类型)构成

隐式接口(implicit interface) 由有效的表达式组成(valid expression)

对class而言，接口都是显式的，多态需要virtual
对template而言，接口都是隐式的，基于有效表达式，多态通过template实现具现化和函数重载解析,发生于编译期间


**条款42.了解typename的双重意义**

声明template参数时候，前缀关键字class和typename可互换


**条款43.学习处理模版化基类内的名称**

在类模版中的派生继承，使用基类中的函数

由于并不知道继承什么样的基类 class， 其是一个template 参数

可以针对一个类产生一个特化版本
```cpp
template<>    // 这个既不是template也不是class
class MsgSender<CompanyZ>{
public:
    ...
    void sendSecret(const MsgInfo& info)
    {
        ...
    }
};
```

一旦基类被特化，特化版本可能不提供和一般性template相同的接口，因此拒绝在模版化基类内寻找继承的函数


该模版化基类如何使用基类中定义的函数

1. this->  ，

2. 使用using声明式
告诉编译器使用基类的函数

base class 资格修饰符



**条款44.将参数无关的代码抽离template**

使用template可能会导致代码的膨胀 code bloat


函数中或者class类中的重复代码很容易被观察到，可以将其放到一个函数(一个类中)

在template中重复的代码是隐晦的，难以发现的


**条款45.运用成员函数模版接受所有的类型兼容**

泛型编程 generic programming

```cpp
// 泛化拷贝构造函数
template<typename T>
class SmartPtr
{
public:
    template<typename U>                  // member template
    SmartPtr(const SmartPtr<U>& other);   // 生成copy构造函数
    ...
}
```
任何类型T和任何类型U 都可以根据SmartPtr<U>生成一个SmartPtr<T> 

```cpp
template<class T>
class shared_ptr
{
public:
    shared_ptr(shared_ptr const &r);
    template<class Y>
    shared_ptr(shared_ptr<Y> const &r);             // 泛化拷贝

    shared_ptr& operator=(shared_ptr const &r);

    template<class Y>
    shared_ptr& operator=(shared_ptr<Y>) const &r);  // 泛化拷贝赋值
}
```

**条款47.使用traits calsses表现类型信息**

traits的**目的**：让属于同一个概念的、具有不同特性的模型，对外暴露一致的接口

利用traits,允许你在编译期间取得某些类型信息

traits必须能够施行于内置类型,类型的traits信息必须位于类型自身之外

标准技术是将它放进一个template及其一个或者多个特化版本中


加上函数重载的技术:

- 建立一组重载函数或函数模版,彼此间的差异只在于各自的tarits参数，令每个函数实现代码与其接受只tarits信息相应和

- 建立一个控制函数或函数模版，调用上述的重载函数，并传递traits所提供的信息


**条款48.认识template元编程**

模版元编程是C++中最强大的编程范式，可以创建和操纵程序的程序

- 模版元编程完全是在编译期
- 模版元编程的数据不能是运行时变量，只能是编译器常量，不能修改
- 不能使用 if-else ,for 语句
- 需要使用类型重定义，枚举常量，继承，模版偏特化


常用的语法元素:

- enum,const ,static 用来定义编译期的整数常量
- typedef/using      用来定义元数据
- T,Args...          用来声明元数据类型
- template           用来定义函数
- ::                 域运算符，用于解析类型作用域获得结果


模板元中的if-else可以通过type_traits来实现，它不仅仅可以在编译期做判断，还可以做计算、查询、转换和选择。

模板元中的for等逻辑可以通过递归、重载、和模板特化（偏特化）等方法实现。


TMP:template metaprogramming 模版元编程

以C++写成、执行于C++编译器内的程序，一旦TMP程序结束执行，其输出也就是从template具现出来的C++源码

最基本的TMP程序-计算阶乘(factorial)
```cpp
template<unsigned n>
struct Factorial{
    enum{value= n * Factorial<n-1>::value};
};

template<>
struct Factorial<0>
{
    enum{value=1};
};
```

模版元编程可将工作由运行期移往编译期，实现早起侦测错误和更高的执行效率




### 定制new和delete

学习c++内存管理

这一章的概念 难以理解

堆内存由 operator new 和 operator delete 进行管理
当 operator new无法满足客户的内存需求时所调用的函数 new handler
当 operator无法满足内存申请的时候，会不断调用new-handler函数



- STL容器中所使用的heap内存是由容器所拥有的分配器对象 allocator objects 管理

new:
- 1.先调用operator new函数分配内存(大多数是调用malloc)
- 2.调用构造函数来初始化内存

delete:

- 首先调用析构函数
- 调用operator delete释放内存(调用 free)

我们无法控制构造函数和析构函数(编译器决定))，但是可以改变内存分配函数(operator new ,operator delete)


全局new实现的pseudocode 伪代码
```cpp
void *operator new(std::size_t size)throw(std::bad_alloc)
{
    using namespace std;
    if(size==0)
        size=1;
    while(true)
    {
        try to malloc the size bytes;
    }
    if(分配成功)
        return 一个指针指向分配而来的内存
    
    // 分配失败
    new_handler globalHandler=set_new_handler(0);
    set_new_handler(globalHandler);
    if(globalHandler)
    {
        (*globalHandler)();
    }
    else
        throw std::bad_alloc();

}

void operator delete(void *mem)throw()
{
    if(mem==0)
        return;
    归还mem的内存;
}
```


**对类重载 new and delete**

- 1.一个类重载new delete，尽管不必显式的创建static，实际上仍是static函数
- 2.编译器看到new创建自定义的类的对象的时候，会选择成员版本的operatot new()而不是全部版本的new()
    如果创建一个类数组的时候，全局的operator new()就会被立即调用,用来为数组分配内存
    也可以使用类的重载运算符的数组版本operator new[] operator delete[]来控制对象数组内存分配
- 3.使用继承的时候，重载的new 和delete不能使用

```cpp
// 类内(member)版本示例

class Base
{
public:
	static void *operator new(std::size_t size)throw(std::bad_alloc);
	static void operator delete(void *rawMemory,std::size_t size)throw();
	...
};
void *Base::operator new(std::size_t size)throw(std::bad_alloc)
{
	if(size!=sizeof(Base))//sizeof(空类)为1，因此不需要判断size==0的情况
		return ::operator new(size);
	...
}
void Base::operator delete(void *rawMemory, std::size_t size)throw()
{
	if(rawMemory==0)return;
	if(size!=sizeof(Base))
	{
		::operator delete(rawMemory);
		return;
	}
	归还rawMemory所指的内存;
	return;
```


- 重载operator new() 和operator delete() 只是改变了原有内存的分配方法
  重载operator new()唯一需要做的就是返回一个足够大的内存块的指针）。
  编译器将用重载的new代替默认版本去分配内存并调用构造函数

- 在重载的operator news内应该包含一个循环，反复调用某个new_handler函数


**条款49.了解new-handler行为**

[new-handler](https://blog.csdn.net/zxx910509/article/details/64904965)

```cpp
typedef void (*new_handler)();
new_handler set_new_handler(new_handler p)throw();

```
当new函数找不到足够大的内存连续内存来安排对象，new_handler函数将会被调用
new_handler是个typedef，定义出一个指针指向函数，该函数没有参数也不返回任何东西。
set_new_handler则是设置新的new_handler并返回当前的new_handler


**条款50.编写new和delete时需要固守常规**

**条款52.placement new placement delete**

这两个必须同时出现，同时定义，不呢个单独出现


### 杂项讨论

**条款53.不要忽略编译器的警告**

**条款54.55了解boost**

都已经纳入标准库了

