// new 以及 delete 函数的重载

#include <cstdlib>
#include <cstdio>
#include <memory>

void *operator new(std::size_t sz) //throw(std::bad_alloc)
{
    printf("operator new:%zu bytes\n",sz);
    void *m=malloc(sz);
    if(!m)
    {
        puts("out of memory");
    }
    return m;
}

void operator delete(void *m) throw()
{
    puts("operator delete");
    free(m);
}

class S{
    int arr[100];
public:
    S()
    {
        puts("constror s");
    }
    ~S()
    {
        puts("deconstroctor s");
    }
};

int main()
{
    puts("creating and destory an int");
    int *p=new int(47);
    delete p;

    puts("creating and destory an Class s");
    S *s=new S;
    delete s;

    puts("creating and destory an Array of Class");
    S *sa=new S[3];
    delete []sa;

    return 0;
}

/** 函数输出
creating and destory an int
operator new:4 bytes
operator delete
creating and destory an Class s
operator new:400 bytes
constror s
deconstroctor s
operator delete
creating and destory an Array of Class
operator new:1208 bytes
constror s
constror s
constror s
deconstroctor s
deconstroctor s
deconstroctor s
operator delete
*/

/**
 * 1.为什么使用 puts ,printf 而不使用 iostream？
 *   这是对全局进行重载
 *   创建iostream对象的时候(cin，cout，cerr)会调用new去分配内存，造成operator new函数造成死锁
 *   使用printf()，不会进入死锁状态，因为不会调用new来初始化自身
 * 
 * 2.数组输出1208 ,而不是1200，说明额外的内存被分配于包含其对象的数量信息
 * 
 * 
 * 3.输出都有operator new 和operator delete 说明调用了全局重载版本的new delete
*/

