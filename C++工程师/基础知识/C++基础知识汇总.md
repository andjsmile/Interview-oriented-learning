先努力学习消化！！！

#### const && static

| |const |static|
|--|--|--|
|普通变量|变量不可以被修改| 修改存储区域和生命周期,main函数运行前分配空间|
|普通函数|null|函数的作用范围，仅在该函数的文件内才能使用|
|指针|分为指向常量的指针，指针常量|null|
|成员函数|该成员函数内不能修改成员变量|不需要生成对象就可以访问函数|
|成员变量|null|所有的对象只保存一个该变量|

顶层const top-level const:表示指针就是一个常量
底层const low-level const:指针所指的对象是一个常量

#### this pointer this指针
>this指针是一个隐藏的特殊指针，指向成员函数操作的对象
>this是一个常量指针

#### inline函数
> 简单的概括就是宏定义，调用内联函数的调用点上进行展开
```cpp
// 原函数
const string &shorterString(const string &s1,const string &s2)
{
    return s1.size()<=s2.size() ? s1:s2;
}
// 声明为内联函数inline
inline const string &shorterString(const string &s1,const string &s2)
{
    return s1.size()<=s2.size() ? s1:s2;
}
```
>调用函数
```cpp
cout<<shorterString(s1,s2)<<endl;
```
>内联函数在调用点进行展开
```cpp
cout<<(s1.size()<=s2.size() ? s1:s2)<<endl;
```

>内联函数的一些性质
>> 0.不用执行进入函数的步骤，直接执行函数体
>> 
>> 1.不能包含循环、递归、switch等复杂操作
>> 
>> 2.在类声明中定义的函数，除了虚函数其他函数都会自动隐式的当成内联函数


#### assert()
>断言不是函数，是宏
>通过NDEBUG来关闭 assert
> expr为假，assert输出信息并终止程序
> expr为真，assert则什么也不做
> 
```cpp
#define NDEBUG
#include <assert.h>
// include<cassert>
assert(expr);
```

#### struct & class
>struct 适合作为一个**数据结构的实现体**
>class 适合看成一个**对象的实现体**

|区别|struct|class|
|---|---|----|
|默认访问控制|public|private|


#### c++类内定义 引用数据成员

可以定义引用数据成员，必须通过成员初始化列表初始化








