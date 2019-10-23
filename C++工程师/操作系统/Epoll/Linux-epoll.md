### Epoll实现

linux epoll是通过 红黑树+双向链表实现的

1.epoll_create() 系统调用在内核中创建一个eventpoll类型的句柄,包含红黑树根结点，双向链表头结点

2.epoll_ctl()  系统调用，向epoll对象红黑树中添加、删除、修改感兴趣的事件fd(socket)

3.epoll_wait() 系统调用，判断双向链表是否为空，为空则阻塞，不为空返回就绪链表中的数据
               当文件描述符状态改变，fd上的回调函数被调用，该函数将fd加入双向链表中，epoll_wait()被唤醒，返回就绪好的事件




[参考博客](https://www.cnblogs.com/apprentice89/p/3234677.html)