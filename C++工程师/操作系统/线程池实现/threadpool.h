#ifndef THREADPOOL_H_
#define THREADPOOL_H_

#include "condition.h"

// 封装线程池中的对象和需要执行任务的对象
typedef struct task{
	void *(*run)(void *args);     // 函数指针
	void *arg;                    // 参数
	struct task *next;            // 任务队列的下一个任务

}task_t;


// 线程池结构体
typedef struct threadpool{
	condition_t ready;            // 状态量
	task_t *first;                // 第一个任务
	task_t *last;                 // 最后一个任务
	int counter;                  // 线程中工作线程数
	int idle;                     // 线程空闲程数
	int max_threads;              // 线程池最大线程数
	int quit;                     // 是否退出标志

}threadpool_t;

// 线程池初始化
void threadpool_init(threadpool_t *pool,int threads);

// 线程池加入任务
void threadpool_add_task(threadpool_t *pool,void *(*run)(void *args),void *arg);

// 摧毁线程池
void threadpool_destroy(threadpool_t *pool);


#endif