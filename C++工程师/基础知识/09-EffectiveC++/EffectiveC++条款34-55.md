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



