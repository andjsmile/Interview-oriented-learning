//定义全局变量的单链表，实现头插头删等操作
//实现线程的同步与互斥定义了全局的互斥锁和条件变量
#include<stdio.h>
#include<pthread.h>
#include<stdlib.h>
#include<unistd.h>
#include<errno.h>

typedef struct ListNode
{
    int data;
    struct ListNode* next;
}node_t,*node_p,**node_pp;

node_p head=NULL;
// 互斥量和条件变量的静态分配
pthread_cond_t cond=PTHREAD_COND_INITIALIZER;
pthread_mutex_t lock=PTHREAD_MUTEX_INITIALIZER;

node_p AllocNode(int x,node_p node)//申请结点
{
    node_p temp=(node_p)malloc(sizeof(node_t));
    if(temp==NULL)
    {
        perror("malloc");
        exit(1);
    }
    temp->data=x;
    temp->next=node;
    return temp;

}
void InitList(node_pp node)//初始化
{
    *node=AllocNode(0,NULL);
}
int IsEmpty(node_p node)//判空
{
    return node->next==NULL?1:0;
}
void FreeNode(node_p node)//释放结点
{
    if(node!=NULL)
    {
        free(node);
        node=NULL;
    }
}
void PushFront(int d,node_p node)//头插
{
    node_p temp=AllocNode(d,NULL);
    temp->next=node->next;
    node->next=temp;
}
void PopFront(node_p node,int* out)//头删
{
    if(!IsEmpty(node))
    {
        node_p p=node->next;
        node->next=p->next;
        *out=p->data;
        FreeNode(p);
    }
}
void Destory(node_p node)
{
    int out=0;
    while(!IsEmpty(node))
    {
        PopFront(node,&out);
    }
    free(node);
}
void ShowList(node_p node)
{
    node_p cur=node->next;
    while(!IsEmpty(cur))
    {
        printf("%d ",cur->data);
        cur=cur->next;
    }
    printf("\n");
}
//作为生产者，生产数据，拿到锁进行操作，往缓存区里放数据，在生产一个数据后，释放锁并唤醒在条件变量等到的第一个线程
void* Productor(void* arg)
{
    int data=0;
    while(1)
    {
        pthread_mutex_lock(&lock);
        data=rand()%1234;
        PushFront(data,head);
        printf("productor done..%d\n",data);
        pthread_mutex_unlock(&lock);
        pthread_cond_signal(&cond);
        sleep(1);

    }
}
//消费者缓冲区里拿数据，上锁，当缓冲区里没有数据时，就用条件变量阻塞等待。如果有，就消费数据。释放锁
void* Consumer(void* arg)
{
    while(1)
    {
        pthread_mutex_lock(&lock);
        int data=0;
        while(IsEmpty(head))
        {
            pthread_cond_wait(&cond,&lock);
            printf("consumer wait...\n");
        }   
        PopFront(head,&data);
        printf("consume done..%d\n",data);
        pthread_mutex_unlock(&lock);
    }
}
int main()
{
    InitList(&head);
    pthread_t consumers,productor;
    pthread_create(&consumers,NULL, Consumer,NULL);
    pthread_create(&productor,NULL,Productor,NULL);
    pthread_join(consumers,NULL);
    pthread_join(productor,NULL);
    Destory(head);
    pthread_mutex_destroy(&lock);
    pthread_cond_destroy(&cond);
    return 0;
}
