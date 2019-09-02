/*
 * 单例模式主要解决一个全局使用的类频繁的创建和销毁的问题
 * 可以确保一个类只有一个实例
 * 可以自行实例化，并向整个系统提供实例
 */

//利用静态变量和私有化构造函数的特性来实现单例模式。
//搞一个静态的自身类指针，然后把构造函数私有化，
//这样new的时候就只能让本类中的成员调用，然后不择手段在类内部new出这个对象，
//并提供一种方法供外部得到这个对象的地址


// 单例模式就是一个类只能被实例化一次，
// 只能有一个实例化对象的类

// 把构造函数进行私有化，private，protected
// 这样构造函数则不能从外部进行调用

/*
class CSingleton
{
private:
    CSingleton()
    {

    }

};


int main()
{
    CSingleton t;                     // 编译器都会出现错误
    CSingleton *tt=new CSingleton; 
}
*/
// 既然构造函数是私有了，那么他就只能被类内部的成员函数调用，
// 所以我们可以搞一个共有函数去供外部调用，然后这个函数返回一个对象，
// 为了保证多次调用这个函数返回的是一个对象，我们可以把类内部要返回的对象设置为静态的

// 线程不安全的  懒汉式，用到的时候才进行加载
#include <cstdlib>
#include <iostream>
#include <thread>
using namespace std;
class cSingleton
{
private:
    cSingleton()
    {}
    static cSingleton *p;
public:
    static cSingleton*  getInstance()
    {
        if(p==nullptr)
        {
            p=new cSingleton();
        }
        return p;
    }
};

cSingleton* cSingleton::p = NULL;       // 指针的预先定义


int main()
{
    cSingleton *t = cSingleton::getInstance();
    cSingleton *tt = cSingleton::getInstance();
    cout << t << endl << tt << endl;
}

// 如果两个线程同时获取实例化对象呢？
// 显然是不行的，会出现两个线程同时要对象的时候指针还都是空的情况就完了
// 加锁
// 懒汉式进行加锁

class cSingletonLock
{
private:
    cSingletonLock()
    {}
    static cSingletonLock *p;
public:
    static pthread_mutex_t mtx;
    static cSingletonLock* getInstance()
    {
        if(p==nullptr)
        {
            pthread_mutex_lock(&mtx);
            p=new cSingletonLock();
            pthread_mutex_unlock(&mtx);
        }
        return p;
    }
};
pthread_mutex_t cSingletonLock::mtx=PTHREAD_MUTEX_INITIALIZER;
cSingletonLock* cSingletonLock::p=NULL;


// 饿汉式 线程安全
// 优点：没有加锁，执行效率会提高。
// 缺点：类加载时就初始化，容易产生垃圾对象，浪费内存

class CSingleton
{
private:
    CSingleton()
    {
    }
    static CSingleton *p;
public:
    static CSingleton* getInstance()
    {
        return p;
    }
};
//基于 classloader 机制避免了多线程的同步问题，
CSingleton* CSingleton::p = new CSingleton();  

class FileName{};
FileName& tfs()
{
    static FileName fs;
    return fs;
}

/**
 *  另外一种使用 pthread_once 来实现
*/


