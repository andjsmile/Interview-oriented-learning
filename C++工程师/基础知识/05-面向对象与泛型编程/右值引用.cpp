// 简单介绍一下 对象移动中的 右值引用
#include <iostream>
//#include <algorithm>
#include <utility>
void demo(){
    int i=42;

    // int &i=42;   
    // initial value of reference to non-const must be an lvalue


    int &r=i;      // r 引用 i

    // int &&r=i;
    // an rvalue reference cannot be bound to an lvalue
    // 不能将一个右值引用绑定到一个左值上

    // int &r2=i*42;
    // i*42 是一个右值

    const int &r3=i*42;   // 可以将一个const的引用绑定到右值上
    
    int &&r2=i*42;        // rr2 绑定到乘法结果

    // 变量表达式都是 左值
    int &&rr1=42;         // 正确
    //int &&rr2=rr1;      // 表达式rr1是左值
    // 不可以将一个右值引用直接绑定到一个变量上，即使这个变量是右值引用类型也不行

    // use move 
    int &&rr3=std::move(rr1);     //std::move  utility中

}