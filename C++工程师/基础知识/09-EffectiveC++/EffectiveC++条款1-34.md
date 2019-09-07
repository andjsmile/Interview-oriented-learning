## effective c++ 

## 改善程序设计的55个具体做法


### 导读

注意区分调用拷贝构造函数 和拷贝赋值构造函数

一个新对象定义，一定会有个构造函数，而不是赋值操作

拷贝构造函数- 定义了一个对象passed by value 以值传递

不明确行为

对一个空指针取值，可能会造成不明确的行为发生

### 第一部分，习惯c++

**条款01 将C++看成一个语言联邦**

c++ 多重范型编程语言 multiparadigm programming language

支持   过程形式            procedural
      面向对象形式         object-oriented
      函数形式            functional
      泛型形式            generic
      元编程形式           metaprogramming

c++可以分成四个模块

-  c 基础的c，包含 块block，语句statements，预处理器 preprocessor，内置的数据类型 bulit-in data types,arrays pointers

- object-oriented c++ ,  类 class ，构造，析构函数 ，封装，继承，多态，虚函数-动态绑定

- template c++  泛型编程部分

- STL



**条款02 用const、enum。inline 代替#define**

翻译过来就是用编译器来代替预处理器
”#define 是由预处理器处理“，预处理器是在编译器处理之前

这个名称预处理器那种以后，有可能没有进入 记号表中(symbol table)


类中的 staic const 常量

一般在类中只进行常量的声明，需要在类外(文件内)进行常量的定义

不想让别人获得一个pointer 或者 reference 指向你的整数常量，enum可以实现这个约束


总结:

1.单纯的常量，用const 或者 enum 代替 “#define”

2.行似函数的宏 macros ，最好改成inline 函数替换 "#define"



**条款03 尽可能的使用const**

声明迭代器为const 就是将 指针声明为const  T * const ，指针不能改变            指针常量

迭代器的 const_iterator  是一个  const T *        ，指向的元素不能改变       常量指针


常量成员函数,调用可改变的的值 可以在定义前加上 mutable 

**常量成员函数**

1.this ，this指针是一个常量指针const pointer 不允许改变this中保存的地址
         区别于 指向常量的指针(const to pointer)

2.成员函数加上const关键字，const 的作用是修改隐式 this指针的类型
  默认情况下  this的类型是 指向类类型非常量版本的 常量指针
            不能把this绑定到一个常量对象上
  这就导致不能在一个常量对象上调用普通的成员函数

  普通指针（包括非常量指针和常量指针）无法绑定一个常量对象
  需要用一个指向常量的指针来绑定常量对象

  在成员函数后加上const，隐式调用的this指针变成指向常量的常量指针


我们可以使用非常量对象调用常量成员函数，这样做是合法的，
因为我们可以使用指向常量的指针来绑定一个非常量对象


**条款04 确定对象被使用前已先被初始化**

永远在对象使用前，将其初始化

无任何成员的内置类型   --  必须使用手动初始化

内置类型以外的        --  初始化责任落在构造函数上，每个构造函数都将对象的每一个成员进行初始化


**区别 赋值assigenmet 和 初始化initialization**

使用赋值

- 成员变量的初始化动作发生在进入构造函数本体之前

- 先调用default构造函数，为变量赋予初始值，再调用 拷贝赋值操作符 copy assignment 

使用初始化

- 只调用一次拷贝构造函数 copy构造函数是比较高效的


对于构造函数使用  成员初值列 member initialization list   替换赋值函数


**C++ 固定的成员初始化次序**

1.基类base class 要早于 derived class 被初始化

2.类 class的成员变量 总是以其声明的次序被初始化


单例模式 singleton 使用单例模式


总结:
(避免对象初始化之前过早的使用它们)

1. 手工初始化内置类型 no-memmber 对象

2. 使用成员初值列 member initialization list 初始化对象所有成分

3. 初始化顺序与其在class中声明顺序相同



### 第二部分，构造/析构/赋值运算

**条款05** C++默认编写并调用哪些函数

在类中如果你没有定义下列的函数，编译器都会自动构建

default 构造函数
析构函数 (默认是non-virtual)
copy 构造函数
copy assignment 操作符


对于编译器创造的版本 copy构造函数和 copy assignmnet操作符

只是单纯将来源对象的每一个non-static成员变量拷贝到目标对象


如果打算在一个内含  refernce 成员的calss类支持赋值操作 
则必须自定义copy assignment操作符 ,const成员也一样。
编译器默认生成的不能实现。


**条款06 若不想编译器自动生成函数，就该明确拒绝**

explicitly 明确的

1.可以将响应的成员函数声明为private 并且不予实现
2.使用uncopyable这个的基类,使用类进行继承就可以
```cpp
class Uncopyable
{
protected:
    Uncopyable();
    ~Uncopyable();
private:
    Uncopyable(const Uncopyable&);
    Uncopyable& operator=(const Uncopyable&);
};
```

**条款07 为多态基类声明virtual析构函数**

当一个derived class对象经由一个base class指针被删除
该base class 带着一个non-virtual析构函数
那么结果是未定义的（derived 对象没有被销毁）


纯虚函数 pure virtual 函数导致abstract class 抽象类(不能被实例化instantiated 的类,不能为类创建对象)


1.polymorphic多态性质的 base class 应该声明一个virtual析构函数
  类中带有任何virtual函数，就应该拥有一个virtual析构函数

2.如果类的设计不是为了作为基类 base class 使用，不该声明 virtual析构函数


**条款08 别让异常逃离析构函数**

1.析构函数绝对不要吐出异常，如果一个被析构函数调用的函数可能抛出异常
  这个析构函数应该捕获任何异常，然后吞下(不传播)或结束程序 abort

2.如果客户需要对某个操作函数运行期间抛出异常作出反应，class应该提供一个普通函数(不是在析构函数中)执行该操作



**条款09 绝不在构造和析构过程中调用virtual函数**

在构造和析构期间不要调用virtual函数，因为这类调用从不下降至derived class

**条款10 令赋值操作符 operator= 返回一个 reference to *this**

return *this ，返回一个左侧的对象


**条款11 在operator中处理 自我赋值**

自我赋值实现前要进行认同性测试

要区分来源对象和目标对象的地址，以及安全的语序


**条款12 复制对象时勿忘每一个成分**

自己声明copy函数(copy 构造函数、copy assignment 操作符),就是告诉编译器不使用缺省实现中的某些行为,
这时候编译器，会用一种奇怪的方式回应，当copy函数的实现代码出现错误，编译器也不会提醒


- copy函数应该确保复制 1.对象内的所有成员变量 2.所有的基类成员base class。

- 不要尝试以某个copying 函数实现另一个 copying 函数,可以将共同机能放入第三个函数中，并由两个copying函数共同调用。

copy assignment 操作符调用 copy构造函数不合理,试图构造一个已经存在的对象

copy 构造函数调用 copy assignment 操作符,这也是没有意义 copy构造函数初始化新对象，assignment操作符只施行于已初始化对象身上。



### 第三部分，资源管理

**条款13 以对象管理资源**

把资源放进对象内，可以依赖c++析构函数自动调用机制,确保资源被释放。

**条款14 在资源管理类中小心copying行为**

资源取的的时间便是初始化时机 resource acquisition is initialization RAII

- 复制RAII对象必须一并复制它管理的资源，所以资源的copying行为决定RAII对象的copying行为
- 普遍的RAII class的copying行为:抑制 copying 、采用引用计数法。



**条款15 在资源管理类中提供对原始资源的访问**

**get()**
   item->get()，item是一个迭代器
   返回其函数保存的指针
智能指针 shared_ptr 和unique_ptr 都提供了get成员函数，返回其保存的指针
shared_ptr<int> p;
*p 实现解引用
p->mem  等同于  (*p).mem



**条款16 成对使用new 和delete是采取相同的形式**

delete时加上方括号[] ,delete便认定指针指向一个数组
否则，delete便认定指针指向单一对象

new使用[],delete也必须使用 delete[]

new不使用[],delete就也不能使用 []

**条款17 以独立的语句将newed对象置入智能指针**

**以对象管理资源**要时刻记得这一句话

```cpp
int priority();
void processWidget(std::shared_ptr<Wdiget> pw,int priority);


processWidget(new Widget,priority());   // 没有用对象管理资源
// 使用对象管理资源，但是仍可能出现调用资源泄漏
processWidget(std::shared_ptr<Widget>(new Widget),priority());

// 调用 processWidget之前，编译器必须创建代码
// 1.调用 priority()函数
// 2.执行 new Widget
// 3.调用 std::shared_ptr 构造函数

// 因为c++对编译器完成的次序没有具体的要求

// 可能会出现下面的调用顺序
// 1.执行 new Widget
// 2.调用 priority()函数
// 3.调用 std::shared_ptr 构造函数

// 如果第二步出现错误，priority函数调用出现异常 第一步的 new Widget返回的指针将被遗失
// 发生资源的泄漏

//  采用分离语句，以独立的语句将newed对象置入智能指针
std::shared_ptr<Widget> pw(new Widget);
processWidget(pw,priority());

// 这样编译器对重新排序 语句的各项操作没有权利
```

用独立的语句将newed对象放入智能指针中，实现资源管理


### 第四部分，设计与声明


**条款18 让接口容易被正确使用，不易被误用**

shared_ptr 支持定制型删除器 (custom deleter) 可以防范DLL问题，可被用来自动解除互斥锁


**条款19 设计class犹如设计type**

**条款20 宁以 pass-by-reference-to-const 替换 pass-by-value**

- 尽量用 pass-by-reference-to-const替换 pass-by-value ，前者更高效也可以解决切割问题 slicing problem
- 对于内置类型，STL迭代器，函数对象，采用pass-by-value比较适当

**条款21 必须返回对象时，别妄想返回其 reference**

不能返回pointer 或reference 指向一个 local stack 对象
不能返回reference 指向一个heap-allocated对象
不能返回pointer 或reference 指向一个local static对象而又可能同时需要多个这样的对象

注意区分单例模式

**条款22 将成员变量声明为private**

赋予客户访问数据的一致性，

protected并不比public更具封装性

**条款23 宁以 non-member、no-friend 替换member 函数**

能够访问private成员变量的函数只有class的member函数加上friend函数

non-member、no-friend 替换member函数可以增加封装性，包裹弹性(packaging flexibility)和机能扩充性



**条款24 若所有参数都需要类型转换,请为此采用non-member函数**


只有当参数位于参数列parameter list 内,这个参数才是隐式类型转换的合格参与者

member成员函数的反面是 non-member函数，而不是friend函数

- 如果需要为某个函数的所有参数(包括被this指针所指的那个隐喻参数)进行类型转换，这个函数必须是non-member




**条款25 考虑写出一个不抛出异常的swap函数**

```cpp
// 缺省的情况下 swap 动作可由标准程序库提供的swap算法完成
// 标准的 std::swap的实现
namespace std
{
  template<typename T>
  void swap(T &a,T &b)
  {
    T temp(a);
    a=b;
    b=temp;
  }
}
```

pimpl手法(pointer to implementation)
指针指向一个对象，内含真正的数据

```cpp
class WidgetImpl
{
public:
  ...

private:
  int a,b,c;
  std::vector<double>v;
};

class Widget
{
public:
  Widget(const Widget& rhs);
  Widget& operator=(const Widget& rhs)
  {
    ...
    *pImpl=*(rhs.pImpl);
    ...
  }
private:
  WidgetImpl *pImpl;            // 指针，所指的对象包含交换的数据
};
```

缺省的std::swap 不知道交换指针即可,在交换的时候复制三个 Widgets，还复制三个 WigetImpl。非常缺乏效率

```cpp
// 声明一个 non-member 函数来调用一个 member函数
class Widget
{
public:
  ...
  void swap(Widget& other)
  {
    using std::swap;
    swap(pImpl,other.pImpl);
  }
  ...
};

namespace std
{
  template<>
  void swap<Widget> (Widget &a,Widget &b)
  {
    a.swap(b);
  }
}
```

### 第四部分，实现

**implementation**

类的定义 class template 和 函数声明 function template 是花费时间最多的两件事，一旦正确定义，相应的实现大多简单直接


**条款26 尽量延后变量定义式的出现时间**

使用拷贝构造函数要比使用默认构造函数+赋值  效率要高的多

尽量延后变量定义式的出现，可以增加程序的清晰度并改善程序效率


**条款27 尽量少做转型动作**

cast会破坏类型系统 type system

先介绍四种新式转型

const_cast<T>(expression)
dynaminc_cast<T>(expression)
static_cast<T>(expression)
reinterpret<T>(expression)

const_cast  将对象的常量性质移除，将const转换为non-const(只有其能办到)

dynamic_cast 用来执行"安全向下转型",用来决定某个对象是否归顺继承体系中的某个类型

static_cast  强迫执行隐式转换 non_const转换为const，int转double，void* 转typeid指针

reinterpret_cast 意图执行低级转型


- 如果可以，尽量避免转型，特别是在注重效率的代码中避免 dynamic_cast
  无需转型的替代设计(virtual函数运行时的动态绑定)

- 尽量使用新式转型


**条款28 避免返回handles指向对象内部成分**

避免返回handles(references,指针，迭代器)指向对象内部
这样可以增加封装性，帮助const成员函数行为像一个const


**条款29 为异常安全的努力是值得的**


这一条款学习的不是很好

异常抛出:

当异常被抛出，带有异常安全性的函数:
- 不泄漏任何资源
- 不允许数据破坏

不泄漏任何资源，可将函数加锁操作放入构造函数，解锁放入析构函数中
异常安全函数


**条款30 透彻了解inlining的里里外外**

inline 函数，动作像函数，比宏macro好，不用承受函数调用所招致的额外开销

类内的函数的实现，是一个隐喻的inline申请

明确申请inline就是在定义式前面加上inline关键字

inline函数通常置于头文件内部，大多数环境是在编译过程中进行 inlining

80-20法则:
一个程序将80%的执行事件花费在20%的代码上

先不编写inline函数，最后对20%重要的代码进行精简


**条款31 将文件间的编译依存关系降至最低** 

pImpl

pointer to implementation 
负责实现的是 pimpl idiom

像person这样使用pimpl idiom的classes ,往往称之为 Handle classes;

将对象实现细目隐藏于一个指针背后
一个类提供接口，一个类负责实现该接口

- 尽量使用class声明式替换class定义式
- 为声明式和定义式提供不同的头文件

```cpp
#include "person.h"
#include "personImpl.h"
// person 和 personImpl有着相同成员函数，两者的接口也相同
person::person(const std::string& name,const Date& birthday,const Address& addr)
        :pImpl(new personImpl(name,birthday,addr))
{}

std::string person::name() const
{
  return pImpl->name();
}
```

另外实现Handle class的办法是，使person成为一个特殊的 abstract base class 抽象基类 称之为 Interface class

Handle classes 和 Interface classes 解除了接口和实现之间的耦合关系,降低了文件间的编译依存性



### 第六部分，继承于面向对象设计

**条款32.确定你的public继承塑模出is-a关系**

public inheritance 公开继承 意味 "is-a"的关系

每一个derived继承对象同时也是一个为base类型的对象

public 继承意味着is-a ,适用于base classes身上的每一件事情都适用于 derived classes 


**条款33.避免遮掩继承而来的名称**

遮掩名称，只是遮掩名字,与其类型无关
只要是名称相同，就会被遮掩


编译器查找个作用域:
- 1.查找local作用域
- 2.查找外围作用域
- 3.查找其基类base
- 4.查找含基类的 namespace
- 5.查找global 作用域

名称遮掩的目的:

防止你在程序库或应用框架(application framework)内建立新的 derived classes继承(public)base classes的重载函数
(违反了 is-a的关系)

如果需要继承重载函数:

使用using 声明式 
using Base::mf1;
using Base::mf3;

using 声明会令继承而来的某给定名称之所有的同名函数在derived class中都可见

在私有继承(private)之下:

使用转交函数(forwarding function)







