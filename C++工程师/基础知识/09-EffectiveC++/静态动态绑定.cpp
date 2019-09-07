// 注意区分动态绑定和静态绑定

#include <iostream>
using namespace std;

class Shape{
public:
    enum ShapeColor {Red,Green,Blue};
    // 定义虚函数
    // 所有形状都必须提供一个函数，用来实现自己
    virtual void draw(ShapeColor color=Red) const;
};

class Rectangle:public Shape
{
public:
    // 注意这段代码有错误
    virtual void draw(ShapeColor color=Green) const;
};

class Circle:public Shape
{
public:
    virtual void draw(ShapeColor color) const;
    // 客户以对象调用此函数，一定要指定参数
    // 静态绑定以下函数并不从其base继承缺省参数值
    // 但是若以指针(reference)调用此函数，可以不指定参数值
    // 动态绑定这个函数会从base继承缺省参数值
};

// 无论其指向什么，其静态类型都是 shape*
Shape* ps;                           // 静态类型为 shape *  无动态类型
Shape* pc=new Circle;                // 静态类型   shape * ，动态类型 Circle *
Shape* pr=new Rectangle;             // 静态类型   shape * , 动态类型 Rectangle *

// 动态类型是指，目前所指对象的类型，将会表现出一个对象将会有什么行为
// 动态类型可以在函数执行过程中改变

//Shape *ps=pc;                        // ps 动态类型 Circle *
//Shape *ps=pr;