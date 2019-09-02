#ifndef CONDITION_H_
#define CONDITION_H_

#include <pthread.h>
#include <time.h>                            // timespec need headfile 

//封装一个条件变量和互斥量作为状态(condition)
//封装条件变量和互斥量实现锁
typedef struct condition
{
	pthread_mutex_t pmutex;
	pthread_cond_t  pcond;
	
}condition_t;


// 状态的操作函数
int condition_init  (condition_t *cond);
int condition_lock  (condition_t *cond);
int condition_unlock(condition_t *cond);
int condition_wait  (condition_t *cond);
int condition_timewait(condition_t *cond,const struct timespec *abstime);
int condition_signal(condition_t *cond);
int condition_broadcast(condition_t *cond);
int condition_destroy(condition_t *cond);

#endif