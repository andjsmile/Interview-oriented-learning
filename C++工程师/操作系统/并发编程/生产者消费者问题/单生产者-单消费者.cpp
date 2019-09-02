// https://www.cnblogs.com/haippy/p/3252092.html

#include <unistd.h>

#include <cstdlib>
#include <condition_variable>
#include <iostream>
#include <mutex>
#include <thread>

static const int kItemRepositorySize=10;   // item buffer size 就是缓冲区的大小

static const int kItemToProduce=1000;      //  How many items we plan to produce.

struct ItemRepository
{
    int item_buffer[kItemRepositorySize];

    // 队列缓冲区可能存在的性能问题及解决方法：环形缓冲区
    // 一个环，read追上write就是空，write追上read缓冲区为满

    size_t read_position;                    // 消费者读取产品位置
    size_t write_position;                   // 生产者写入产品位置

    std::mutex mtx;                          // 互斥量,保护缓冲区
    std::condition_variable repo_not_full;   // 条件变量，缓冲区不为满
    std::condition_variable repo_not_empty;

}gItemRepository;

typedef struct ItemRepository ItemRepository;



void ProduceItem(ItemRepository *ir,int item)
{
    std::unique_lock<std::mutex> lock(ir->mtx);
    // write追上read缓冲区为满
    while(((ir->write_position+1)%kItemRepositorySize)== ir->read_position)
    {
        std::cout<<"the item repository is full,produce is waiting for an empty repo"<<std::endl;
        (ir->repo_not_full).wait(lock);             // 生产者等待"产品库缓冲区不为满"这一条件发生
    }
    (ir->item_buffer)[ir->write_position+1]=item;   // 写入产品
    (ir->write_position)++;                         // 写入位置后移

    // 写入位置若是在队列最后则重新设置为初始位置
    if(ir->write_position==kItemRepositorySize)
    {
        ir->write_position=0;
    } 
    (ir->repo_not_empty).notify_all();              // 通知消费者产品库不为空
    lock.unlock();                                  // 解锁

}

int ConsumerItem(ItemRepository *ir)
{
    int data;
    std::unique_lock<std::mutex> lock(ir->mtx);

    // item buffer is empty, just wait here
    while(ir->read_position == ir->write_position)
    {
        std::cout<<"Consumer is waiting for items"<<std::endl;
        (ir->repo_not_empty).wait(lock);           // 消费者等待"产品库缓冲区不为空"这一条件发生.

    }
    data=(ir->item_buffer)[ir->read_position];     // 读取产品
    (ir->read_position)++;

    if(ir->read_position>=kItemRepositorySize)     // 读取位置若移到最后，则重新置位.
        ir->read_position=0;
    
    (ir->repo_not_full).notify_all();              //  通知消费者产品库不为满

    lock.unlock();

    return data;

}

// 生产者任务
void ProduceTask()
{
    for(int i=1;i<=kItemToProduce;++i)
    {
        std::cout<<"produce the "<<i<<"^th item.."<<std::endl;
        ProduceItem(&gItemRepository,i);            // 循环生产 kItemsToProduce 个产品.
    }
}

// 消费者任务

void ConsumerTask()
{
    static int cnt=0;
    while(1)
    {
        sleep(1);
        int item=ConsumerItem(&gItemRepository);
        std::cout<<"consume the "<<item<<"^th item"<<std::endl;
        if(++cnt==kItemToProduce)
            break;

    }
}

// 初始化缓冲区

void initItemRepository(ItemRepository *ir)
{
    (ir->read_position)=0;    // 初始化产品写入位置.
    (ir->write_position)=0;   // 初始化产品读取位置.
}

int main()
{
    initItemRepository(&gItemRepository);
    std::thread producer(ProduceTask);     // 创建生产者线程.
    std::thread consumer(ConsumerTask);

    producer.join();           // 等待生产线程终止
    consumer.join();
}
