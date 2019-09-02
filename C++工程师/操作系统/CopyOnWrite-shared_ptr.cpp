// 借助智能指针，实现copy-on-write
// ** 注意** 此函数不是可运行函数,只是用来学习
// 借助的基础函数
#include <iostream>
#include <pthread.h>
#include <vector>
#include <map>
#include <string>
using namespace std;

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
class MutexLock:noncopyable{
public:
    MutexLock(){
        pthread_mutex_init(&mutex,NULL);
    }
    ~MutexLock(){
        pthread_mutex_lock(&mutex);
        pthread_mutex_destroy(&mutex);
    }
    // 这两个函数只能由mutexlock调用，严禁用户代码调用
    void lock(){
        pthread_mutex_lock(&mutex);
    }
    void unlock(){
        pthread_mutex_unlock(&mutex);
    }
    
    pthread_mutex_t *get(){
        return &mutex;
    }

private:
    pthread_mutex_t mutex;

// 定义友元,友元类不受访问权限的限制
private:
    friend class Condition;
};

// 不手动调用lock和unlock函数，一切交给栈上的Guard函数

class MutexLockGuard:noncopyable{
public:
    // 只能直接初始化，不能拷贝初始化
    // 抑制隐式类类型转换
    explicit MutexLockGuard(MutexLock &_mutex):mutex(_mutex){
        mutex.lock();
    }
    ~MutexLockGuard(){
        mutex.unlock();
    }
private:
    MutexLock &mutex;
};
//****************************************************************************//
/**
 * 问题1 post()和traverse() 死锁
*/

class Foo
{
public:
    void doit();
};

MutexLock mtx;
std::vector<Foo> foos;

void post(const Foo& f)
{
    MutexLockGuard lock(mtx);
    foos.push_back(f);
}

void traverse()
{
    MutexLockGuard lock(mtx);
    for(std::vector<Foo>::iterator it=foos.begin();it!=foos.end();++it)
    {
        it->doit();
    }
}

//*********** 这个函数 一旦doit函数间接调用post就会产生死锁 
//*********** 解锁这个死锁问题
/**
 * 解决问题1 post()和traverse() 死锁
*/

typedef std::vector<Foo> FooList;
typedef std::shared_ptr<FooList> FooListPtr;
MutexLock mtx_2;//防止一个文件里重复定义
FooListPtr g_foos;

void traverse2()
{
    FooListPtr foos;
    {
        MutexLockGuard lock(mtx_2);
        foos=g_foos;                        //  增加了引用计数
        assert(!g_foos.unique());
    }
    for(std::vector<Foo>::iterator it=foos->begin();it!=foos->end();++it)
    {
        it->doit();
    }
}

void post2(const Foo& f)
{
    MutexLockGuard lock(mtx_2);
    if(!g_foos.unique())                      // 写的时候，引用计数大于1
    {
        g_foos.reset(new FooList(*g_foos));   // 复制一份副本，在副本上修改
        printf("copy the whole list\n");
    }
    assert(g_foos.unique());
    g_foos->push_back(f);
}


//******************************************************************************************
/**
 * 问题 2 用普通的mutex替换读写锁来实现
 * 防止死锁，对写更新，当指针的引用计数大于2，可以实现副本的复制，在副本上进行更新，再交换数据
*/

typedef map<string,vector<pair<string,int> > >Map;

class CustomerData:noncopyable
{
public:
    CustomerData():data_(new Map)
    {}
    int query(const string & customer,const string & stcok) const;
private:
    typedef std::pair<string,int>Entry;
    typedef std::vector<Entry>   EntryList;
    typedef std::map<string,EntryList> Map;
    typedef std::shared_ptr<Map>   MapPtr;
    MapPtr data_;
    void update(const string& customer,const EntryList& entries);
    static int findEntry(const EntryList& entries,const string& stock);

    MapPtr getData() const
    {
        MutexLockGuard lock(mtx_3);
        return data_;
    }

    mutable MutexLock mtx_3;
};

int CustomerData::query(const string& customer,const string& stock) const
{
    MapPtr data=getData();
    Map::iterator entries=data->find(customer);
    if(entries!=data->end())
        return findEntry(entries->second,stock);
    else
    {
        return -1;
    }   
}

// 主要是更新如何加锁
void CustomerData::update(const string& customer,const EntryList& entries)
{
    MutexLockGuard lock(mtx_3);
    if(!data_.unique())
    {
        MapPtr newData(new Map(*data_));
        data_.swap(newData);               // 更新数据
    }
    assert(data_.unique());
    (*data_)[customer]=entries;
}