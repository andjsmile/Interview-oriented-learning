#include <memory>
#include <string>
using namespace std;
/*
allocator<T> a;
a.allocate(n);         // 1.分配内存
a.construct(p,args);   // 2.对象构造
a.destory(p);          // 3.对象析构
a.deallocate(p,n);     // 4.释放内存  
*/
//  a example 
void allocator_implement(int n){
    allocator<string> alloc;
    auto const p=alloc.allocate(n);
    auto  q=p;
    alloc.construct(q++);              // *q 为空字符串
    alloc.construct(q++,10,'c');       // *q 为 cccccccccc
    alloc.construct(q++,"hi");         // *q 为 hi

    while(q!=p){
        alloc.destroy(--q);            // 析构释放构造的string
    }
    alloc.deallocate(p,n);             // 释放内存
}
