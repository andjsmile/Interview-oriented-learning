### C++中常见的容器与算法

#### map和set有什么区别，分别是怎么实现的
map&set都是关联容器，其底层实现都是红黑树
map和set的接口都是红黑树实现的接口，都是直接转调RB-tree来实现的。
区别:
map 是 key-value  set 是关键字的简单集合 
map 支持下标操作，set 不支持下标操作
map 可以修改 value值  ，set的迭代器是const，无法进行修改

map，set的是利用红黑树来实现的
unordered_map,unordered_set底层的数据结构是哈希表

#### STL allocator
1.new & delete 
 new 将 内存分配+对象构造 结合在一起
 delete 将 对象析构+内存释放 结合在一起
 对于单个对象使用 new&delete 非常方便

当分配一块大内存的时候，在内存上按需构造对象。希望可以将内存分配和对象构造进行分离

2.allocator
定义在头文件 <memeory>中
allocator是一个模版，定义的时候需要确定对象的类型

```c++
allocator<T> a;
a.allocate(n);         // 1.分配内存
a.construct(p,args);   // 2.对象构造
a.destory(p);          // 3.对象析构
a.deallocate(p,n);     // 4.释放内存  
//  a example  allocator.cpp 
为了提升内存的管理效率，减少因为申请小内存而造成的内存碎片问题。
采用两级配置器
分配的内存空间 小于128B,会使用一级空间配置器，直接使用 malloc(),realloc(),free()等函数
             大于128B,使用二级空间配置器，采用内存池技术，使用空闲链表来管理内存

#### STL 迭代器删除元素
考虑迭代器失效的相关问题
1.顺序容器(序列容器)- erase(p) 删除指定元素，并返回一个被删除元素之后元素的迭代器，最后一个元素 返回 end()
删除erase(iterator)后面每个元素都会失效，但是后面每个元素都会前移一个位置

2.erase(p) 当前元素失效，对于map,set容器是利用红黑树实现的。删除当前元素不会影响到下一个元素的迭代器
在调用erase之前，记录下一个迭代器即可

3.list，使用了不连续的内存，erase方法会返回下一个有效的iterator


#### STL 基本组成
1.容器
2.迭代器
3.算法
4.仿函数
5.分配器   (空间配置器)
6.配接器   (适配器)

相互之间协调运转

分配器 给 容器 分配存储空间;
算法 通过 迭代器 获取 容器 内容;
仿函数 可以协助 算法 完成各种工作;
配接器 用来 套接 适配仿函数

#### map and multimap
map 映射 key_value 的键值对 pair ，元素会根据 key自动排序   
基于红黑树实现，应用于有序键值对不重复映射

multimap  pair key-value ，元素也是按 key自动排序
基于红黑树实现，应用于有序键值对的重复映射

#### vector & list的区别

vector详细描述
vector 
1.连续存储容器，动态数组。分配在堆上 heap,其底层实现是 array数组
2.vector扩容是 两倍容量增长。
3.增删查:  访问:O(1)
          插入: 如果是最后，且空间足够 - fast
                           空间不够- 内存申请释放，拷贝
                中间       内存足够 - fast
                           空间不够- 内存申请释放，拷贝
          删除: 最后删除 fast
                中间删除 需要内存拷贝

4.适用场景:经常随机访问，且不经常对非尾结点进行插入删除。


list详细描述
1.动态链表，堆上分配内存，底层实现是 双向链表 double-linked list
2.每插入一个元素都分配空间，每删除一个元素都释放空间
3.增删查 : 访问 随机访问的性能很差
          删除 const 
          插入 const
4.经常 插入删除大量数据


vector&list对比区别
1.vector的底层实现是数组，list是双向链表
2.vector可以随机访问，list不支持
3.vector是顺序内存，list不是
4.vector在中间插入、删除元素，会导致内存拷贝，list不会
5.vector一次分配好内存，不够时进行2倍扩容,list每次插入都会进行内存申请
6.vector随机访问性能好，插入性能差。list随机访问性能差，插入删除性能好

vector拥有一段连续的内存空间，因此支持随机访问，如果需要高效的随机访问，而不在乎插入和删除的效率，使用vector。

list拥有一段不连续的内存空间，如果需要高效的插入和删除，而不关心随机访问，则应使用list。

#### STL的迭代器 iterator，指针及迭代器
1.iterator 迭代器模式 又叫 Cursor游标模式
  可以在不暴露内部表示的情况下访问各个元素(聚合对象)

2.迭代器不是指针，是类模版 ，只是表现的像指针
  迭代器封装来指针
3.迭代器的返回 是对象的引用reference 不是对象本身，需要进行解引用 *

#### epoll
开发高性能网络程序  windows-iocp
                 linux-epoll
epoll是一个IO多路复用计数

调用顺序：
```c++
int epoll_create(int size);

int epoll_ctl(int epfd, int op, int fd, struct epoll_event *event);

int epoll_wait(int epfd, struct epoll_event *events,int maxevents, int timeout);
```

首先创建一个epoll对象，然后使用epoll_ctl对这个对象进行操作，把需要监控的描述添加进去，这些描述如将会以epoll_event结构体的形式组成一颗红黑树，接着阻塞在epoll_wait，进入大循环，当某个fd上有事件发生时，内核将会把其对应的结构体放入到一个链表中，返回有事件发生的链表


#### STL中resize和reverse的区别

1.resize() 改变当前容器内含有元素的数量 size()  改变大小，新增元素的值为 0

2.reserve() 改变容器的当前最大容量 capacity() ,不会生成元素，只是确定这个容器允许放入多少对象;
如果reserve(len)的值大于当前的capacity()，那么会重新分配一块能存len个对象的空间，然后把之前v.size()个对象通过copy construtor复制过来，销毁之前的内存；