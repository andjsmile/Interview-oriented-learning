#include "condition.h"
#include <stdlib.h>

// 实现函数

// 初始化
int condition_init(condition_t *cond){
	int status;
	if((status=pthread_mutex_init(&cond->pmutex,NULL)))    // 执行成功就不返回了
		return status;
	if((status=pthread_cond_init(&cond->pcond,NULL)))
		return status;
	return 0;
}

// 加锁
int condition_lock(condition_t *cond){
	return pthread_mutex_lock(&cond->pmutex);
}

// 解锁
int condition_unlock(condition_t *cond){
	return pthread_mutex_unlock(&cond->pmutex);
}

// 等待
int condition_wait(condition_t *cond){
	return pthread_cond_wait(&cond->pcond,&cond->pmutex);
}

// 限定时间等待
int condition_timewait(condition_t *cond,const struct timespec *abstime){
	return pthread_cond_timedwait(&cond->pcond,&cond->pmutex,abstime);
}

// 唤醒一个睡眠进程
int condition_signal(condition_t *cond){
	return pthread_cond_signal(&cond->pcond);
}

// 唤醒全部睡眠进程
int condition_broadcast(condition_t *cond){
	return pthread_cond_broadcast(&cond->pcond);
}

// 释放
int condition_destroy(condition_t *cond){
	int status;
	if((status=pthread_mutex_destroy(&cond->pmutex))){
		return status;
	}
	if((status=pthread_cond_destroy(&cond->pcond))){
		return status;
	}
	return 0;
}









