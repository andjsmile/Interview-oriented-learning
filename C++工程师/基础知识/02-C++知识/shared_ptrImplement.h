/* shared_ptr 智能指针的实现，及如何使用 */

#ifndef _SHARED_PTR_H_
#define _SHARED_PTR_H_

//#include <memory>              // shared_ptr 是属于动态内存中的
#include <utility>

template<typename T>
class SmartPtr{
private:
    T *ptr;                      // 底层指针
    int *use_count;              // 当前引用计数

public:
    SmartPtr(T *p);                                 // SmartPtr<int>p(new int (2));   构造函数
    SmartPtr(const SmartPtr<T>& orig);              // SmartPtr<int>p(q);             拷贝构造函数
    SmartPtr<T>& operator=(const SmartPtr<T>& rhs); // q=p;                           赋值
    ~SmartPtr();
    // define series operation
    T operator *();                                 // 解引用
    T* operator ->();                               // 取成员操作
    T* operator+(int i);                            // 指针加常数操作
    int operator-(SmartPtr<T>&t1,SmartPtr<T>&t2);                                // 指针相减

    void get_count(){ return use_count;}

};

// implement member function 
template<typename T>
int SmartPtr<T>::operator-(SmartPtr<T>&t1,SmartPtr<T>&t2){
    return t1.ptr-t2.ptr;
}

template<typename T>
SmartPtr<T>::SmartPtr(T *p)
{
    ptr=p;
    try{
        use_count=new int(1);
    }
    catch(...){                                      // 需要确定错误的类型
        delete ptr;                                  // 申请失败释放真实指针，和引用计数的内存
        ptr=nullptr;
        delete use_count;
        use_count=nullptr;
    }
}

template<typename T>
SmartPtr<T>::SmartPtr(const SmartPtr<T>& orig)       // 复制构造函数
{
    use_count=orig.use_count;
    this->ptr=orig.ptr;
    ++(*use_count);                                  // 当前引用计数加1
}

template<typename T>                                 // 重载运算符的规律有些复杂
SmartPtr<T>& SmartPtr<T>::operator=(const SmartPtr<T>& rhs)   //p=q
{
    // q的指针先加一
    ++ *(rhs.use_count );
    // p指向的指针减一
    if((-- *(use_count))==0){                         // 如果==0 则将指针进行释放
        delete ptr;
        ptr=nullptr;
        delete use_count;
        use_count=nullptr;
    }

    ptr=rhs.ptr;
    *use_count=*(rhs.use_count);
    return *this;
}

template<typename T>
SmartPtr<T>::~SmartPtr(){
    get_count();                                  // 计数函数的使用？
    if(*(use_count)==0){
        get_count();
        delete ptr;
        ptr=nullptr;
        delete use_count;
        use_count=nullptr;
    }
}

template<typename T>
T SmartPtr<T>::operator*(){
    return *ptr;
}

template<typename T>
T* SmartPtr<T>::operator->(){
    return ptr;
}

template<typename T>
T* SmartPtr<T>::operator+(int i){
    T* temp=ptr+i;
    return temp;
}

#endif