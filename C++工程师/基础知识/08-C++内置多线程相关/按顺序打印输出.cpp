/**
 * 三个不同的线程将会共用一个 Foo 实例。

线程 A 将会调用 one() 方法
线程 B 将会调用 two() 方法
线程 C 将会调用 three() 方法
请设计修改程序，以确保 two() 方法在 one() 方法之后被执行，three() 方法在 two() 方法之后被执行

example
输入: [1,2,3]
输出: "onetwothree"
解释: 
有三个线程会被异步启动。
输入 [1,2,3] 表示线程 A 将会调用 one() 方法，线程 B 将会调用 two() 方法，线程 C 将会调用 three() 方法。
正确的输出是 "onetwothree"。


输入: [1,3,2]
输出: "onetwothree"
解释: 
输入 [1,3,2] 表示线程 A 将会调用 one() 方法，线程 B 将会调用 three() 方法，线程 C 将会调用 two() 方法。
正确的输出是 "onetwothree"。

*/
#include <iostream>
#include <string>
#include <thread>
#include <mutex>
#include <condition_variable>
using namespace std;
class Foo {
public:
    int count;
    mutex mtx;
    condition_variable cv;
    Foo() {
        count=1;
    }

    void first(function<void()> printFirst) {
        
        unique_lock<mutex> lock (mtx);
        // printFirst() outputs "first". Do not change or remove this line.
        printFirst();
        count=2;
        cv.notify_all();
    }

    void second(function<void()> printSecond) {
        unique_lock<mutex> lock(mtx);
        //cv.wait(lock,[](int &count){return count==2;});
        while(count!=2)
        {
            cv.wait(lock);
        }
        // printSecond() outputs "second". Do not change or remove this line.
        printSecond();
        count=3;
        cv.notify_all();
        
    }

    void third(function<void()> printThird) {
        
        unique_lock<mutex> lock(mtx);
        //cv.wait(lock,[](int &count){return count==3;});
        while(count!=3)
        {
            cv.wait(lock);
        }
        // printThird() outputs "third". Do not change or remove this line.
        printThird();
    }
};
