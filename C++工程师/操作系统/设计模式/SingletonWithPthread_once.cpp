// 使用 pthread_once 来实现单例模式
#include <iostream>
#include <pthread.h>
class noncopyable{
protected:
    noncopyable();
    ~noncopyable();
private:
    // copy constructor and copy assignment is private
    // 将拷贝构造函数和拷贝赋值函数定义为私有,派生类无法继承
    noncopyable(const noncopyable&);
    const noncopyable& operator=(const noncopyable&);
};

template<typename T>
class singleton:noncopyable
{
public:
    static T& instance()
    {
        pthread_once(&ponce_,&singleton::init);
        return *value_;
    }
private:
    singleton();
    ~singleton();
    static void init()
    {
        value_=new T();
    }
private:
    static pthread_once_t ponce_;
    static T *value_;
};

// 静态变量一定要在头文件中定义
template<typename T>
pthread_once_t singleton<T>::ponce_=PTHREAD_ONCE_INIT;

template<typename T>
T* singleton<T>::value_=NULL;



