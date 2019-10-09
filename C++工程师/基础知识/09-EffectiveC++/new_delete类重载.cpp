// 类内重载，可以使用iostream

#include <iostream>
using namespace std;

class widget
{
    int arr[10];
public:
    widget(){cout<<"construction widget";}
    ~widget(){cout<<"destory widget";}

    void *operator new(size_t size)
    {
        cout<<"widget new::"<<size<<"bytes size"<<endl;
        return ::new char[size];    // 调用全局的new
    }

    void operator delete(void *mem) throw()
    {
        cout<<"widget delete"<<endl;
        ::delete mem;
    }

    void *operator new[](size_t size)
    {
        cout<<"widget new[]"<<size<<"bytes size"<<endl;
        return  ::new char[size];
    }

    void operator delete[](void *p)
    {
        cout<<"widget delete[]"<<endl;
        ::delete[] p;
    }

};

int main()
{
    cout<<"new widget"<<endl;
    widget *w=new widget;
    cout<<"delete widget"<<endl;
    delete w;
    cout<<"new widget[25]"<<endl;
    widget *const wa=new widget[25];
    cout<<"delte widget[25]"<<endl;
    delete []wa;

    return 0;
}