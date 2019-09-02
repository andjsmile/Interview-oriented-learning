/*
 * 区别与单生产者-单消费者问题
 * 允许多个消费者同时从产品库中取出产品
 * 1.产品库在读写情况下互斥
 * 2.消费者取走产品的计数器
 */

#include <iostream>
#include <cstdio>
#include <thread>
#include <unistd.h>
#include <condition_variable>
#include <cstdlib>


const int kItemRepositorySize=4;        // 缓冲区的大小
const int kItemToProduce=10;            // 要生产的数量

struct ItemRepository
{
    /* data */
    int Item_buffer[kItemRepositorySize];

    size_t read_postition;
    size_t write_postition; 

    std::mutex   mtx;
    std::condition_variable repo_not_full;
    std::condition_variable repo_not_empty;

    // 多消费者模型添加
    size_t item_counter;              // 缓冲区的计数
    std::mutex item_counter_mtx;
}gItemRepository;

typedef struct ItemRepository ItemRepository;       // 进行类型定义


void ProduceItem(ItemRepository *ir,int item)
{
    // 加锁
    std::unique_lock<std::mutex> lock(ir->mtx);
    // 使用环形缓冲区
    while(((ir->write_postition+1)%kItemRepositorySize)==ir->read_postition)
    {
        // item is full
        std::cout<<"the item is full ,waiting for the empty slor"<<std::endl;
        (ir->repo_not_full).wait(lock);
    }
    (ir->Item_buffer)[ir->read_postition]=item;

    (ir->write_postition)++;

    if((ir->write_postition)==kItemRepositorySize)
    {
        (ir->read_postition)=0;
    }

    (ir->repo_not_empty).notify_all();

    lock.unlock();

}

int  ConsumeItem(ItemRepository *ir)
{
    int data;                                      // 要写入的数据
    std::unique_lock<std::mutex> lock(ir->mtx);
    while(ir->read_postition==ir->write_postition)
    {
        std::cout<<"the item is empty ,waiting for the iteming produce"<<std::endl;
        (ir->repo_not_empty).wait(lock);
    }
    data=(ir->Item_buffer)[ir->read_postition];
    (ir->read_postition)++;

    if(ir->read_postition==kItemRepositorySize)
    {
        (ir->read_postition)=0;
    }

    (ir->repo_not_full).notify_all();
    
    lock.unlock();
    
    return data;
}


// 生产任务

void ProduceTask()
{
    for(int i=1;i<=kItemToProduce;++i)
    {
        std::cout<<"producer thread"<<std::this_thread::get_id()<<"produce the "<<i<<"^th item"<<std::endl;
        ProduceItem(&gItemRepository,i);
    }
}

// 多消费者任务

void ConsumeTask()
{
    bool read_to_exit=false;
    while(1)
    {
        sleep(1);
        // 锁
        std::unique_lock<std::mutex> lock(gItemRepository.item_counter_mtx);
        if(gItemRepository.item_counter< kItemToProduce)
        {
            int item=ConsumeItem(&gItemRepository);
            ++(gItemRepository.item_counter);
            std::cout << "Consumer thread " << std::this_thread::get_id()
                << " is consuming the " << item << "^th item" << std::endl;
        }
        else
        {
            read_to_exit=true;
        }
        lock.unlock();
        if(read_to_exit)
            break;
        
    }
    std::cout << "Consumer thread " << std::this_thread::get_id()
                << " is exiting..." << std::endl;
}


void initItemRepository(ItemRepository *ir)
{
    ir->read_postition=0;
    ir->write_postition=0;
    ir->item_counter=0;
}

int main()
{
    initItemRepository(&gItemRepository);
    std::thread producer(ProduceTask);
    std::thread consumer1(ConsumeTask);
    std::thread consumer2(ConsumeTask);
    std::thread consumer3(ConsumeTask);
    std::thread consumer4(ConsumeTask);

    producer.join();
    consumer1.join();
    consumer2.join();
    consumer3.join();
    consumer4.join();
}










