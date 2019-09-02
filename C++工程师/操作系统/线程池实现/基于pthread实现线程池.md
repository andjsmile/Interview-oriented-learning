
## 线程池

项目主页 [threadpool](https://github.com/mbrossard/threadpool)
        [博客-详解](https://www.cnblogs.com/tureno/articles/6270416.html)

线程池有一个好处就是减少线程创建和销毁的时间，在任务处理时间比较短的时候这个好处非常显著，
可以提升任务处理的效率

## 线程池简单实现

**线程池**

简单来说就是有一堆已经创建好的线程（最大数目一定），初始时他们都处于空闲状态，
当有新的任务进来，从线程池中取出一个空闲的线程处理任务，然后当任务处理完成之后，
该线程被重新放回到线程池中，供其他的任务使用，当线程池中的线程都在处理任务时，
就没有空闲线程供使用，此时，若有新的任务产生，只能等待线程池中有线程结束任务空闲才能执行

**我们为什么要使用线程池呢？**

简单来说就是线程本身存在开销，我们利用多线程来进行任务处理，单线程也不能滥用，
无止禁的开新线程会给系统产生大量消耗，而线程本来就是可重用的资源，不需要每次使用时都进行初始化，
因此可以采用有限的线程个数处理无限的任务。

### condition.h

使用条件变量和互斥量封装一个状态，用于保护线程池的状态。

基于pthread_mutex 和pthread_cond 实现初始化、加锁、解锁、等待、定时等待、销毁、唤醒单进程、唤醒全部进程


### threadpool.h

定义线程池的头文件

线程池

const struct timespec

可以使用 colck_gettime函数获取timespec结构表示的当前时间

如果不支持,gettimeofday函数获取timeval结构表示当前时间，再转换为timespec结构。



### 1.数据结构

**threadpool_task_t**

用来保存一个等待执行的任务

任务需要指明：要运行的对应函数及函数的参数。
这里的 struct 里有函数指针和 void 指针。

```c
typedef struct {
    void (*function)(void *);
    void *argument;
}threadpool_task_t;
```


**threadpool_t**

线程池结构

任务队列是数组，维护队列头和队列尾(因为c语言)

```c
struct threadpool_t {
   pthread_mutex_t   lock;        //互斥锁
   pthread_cond_t    notify;      //条件变量
   pthread_t         *threads;    //线程数组起始指针
   threadpool_task_t *queue;      //任务队列数组起始指针
   int thread_count;              //线程数量
   int queue_size;                //任务队列长度
   int head;                      //任务队列头
   int tail;                      //任务队列尾
   int count;                     //当前待运行任务数
   int shutdown;                  //线程池当前状态是否关闭
   int started;                   //正在运行的线程数
};
```

### 2.函数(API)

#### 对外接口函数

```c
// 创建线程池，用 thread_count 指定派生线程数，queue_size 指定任务队列长度，flags 为保留参数，未使用
threadpool_t *threadpool_create(int thread_count, int queue_size, int flags); 

// 添加需要执行的任务，第二个参数应为函数指针，第三个为对应函数参数，flags未使用
int threadpool_add(threadpool_t *pool, void (*routine)(void *),void *arg, int flags);

// 销毁存在的线程池。flags 可以指定是立刻结束还是平和结束。立刻结束指不管任务队列是否为空，立刻结束。
// 平和结束指等待任务队列的任务全部执行完后再结束，在这个过程中不可以添加新的任务
int threadpool_destroy(threadpool_t *pool, int flags);
```

#### 内部辅助函数

```c
// 线程池每个线程所执行的函数
static void *threadpool_thread(void *threadpool); 

// 释放线程池所申请的内存资源
int threadpool_free(threadpool_t *pool);
```


