#### 可以通过 weak_ptr能探查对象的生死

shared_ptr 控制对象的生命期，是强引用，只要有一个指向x对象的 shared_ptr 存在，该对象就不会析构
weak_ptr   不控制对象的生命期，但是它知道对象是否还活着
           - 如果对象还活着，可以提升promote 为有效的shared_ptr
           - 对象死了，提升失败，返回一个空的 shared_ptr

```cpp
class Observer
{
public:
    virtual ~Observer();
    virtual void update() =0;   // 纯虚函数，应用与抽象类
    // ....
};

// 无法应用于多线程
class Observable
{
public:
    void register_(Observer * x);
    void unregister(Observer *x);

    void notifyObservers()
    {
        for(Observer* x:observers_)
        {
            x->update();
        }
    }
private:
    std::vector<Observer *>observers_;
};

// 借助 weak_ptr指针(能探查对象的生死)
// 实现观察者模式
class Observable
{
public:
    void register_(weak_ptr<Observer> x);
    //void unregister(weak_ptr<Observer> x);    no need
    void notifyObservers();
private:
    mutable MutexLock mutex_;
    std::vector<weak_ptr<Observer> > observers_;
    typedef std::vector<weak_ptr<Observer> >::iterator Iterator;
};

void Observable::notifyObservers()
{
    MutexLockGuard lock(mutex_);
    Iterator it=observers_.begin();
    while(it!=observers_.end()){
        shared_ptr<Observer> obj(it->lock());   // 进行提升
        if(obj){
            // 提升成功,引用计数至少为2
            obj->update();
            it++;
        }
        else
        {
            // 对象销毁,拿掉 weak_ptr
            it=observers_.erase(it); 
        }
    }
}
```