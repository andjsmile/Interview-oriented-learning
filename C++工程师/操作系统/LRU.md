### LRU -Least Recently Used 

**LRU-最近最少使用算法**

最近最少使用算法（LRU）是大部分操作系统为最大化页面命中率而广泛采用的一种页面置换算法。该算法的思路是，发生缺页中断时，选择未使用时间最长的页面置换出去

实际上就是设计一种数据结构，实现缓存淘汰策略

LRU 缓存淘汰算法就是一种常用策略。LRU 的全称是 Least Recently Used，也就是说我们认为最近使用过的数据应该是是「有用的」，很久都没用过的数据应该是无用的，内存满了就优先删那些很久没用过的数据。

1.确定一个capacity参数作为缓存的最大容量

2.put(key,val)放入键值，使用get(key)获取key对应的val，key不存在返回-1，时间复杂度都是O(1)

```cpp
// 先写用例的接口API函数调用

// cache 近似为一个queue，左边是队头，右边是队尾
// 常使用的在队头，不常使用的在队尾
LRUcache cache=new LRUcache(2);

// put API
cache.put(1,1);
// cache=[(1,1)]

cache.put(2,2);
// cache =[(2,2),(1,1)]

// get API
cache.get(1);
// 返回1
// cache=[(1,1),(2,2)] 因为使用1，(1,1)到队头

cache.put(3,3);
// cache=[(3,3),(1,1)]
// 缓存容量已满，需要删除内容空出位置
// 优先删除久未使用的数据，也就是队尾的数据
// 然后把新的数据插入队头

cache.get(2);
// 返回-1

cache.put(1,4);
// cache=[(1,4),(3,3)]
// 键 1 已存在，把原始值 1 覆盖为 4
// 不要忘了也要将键值对提前到队头

```

分析上面的操作过程，要让 put 和 get 方法的时间复杂度为 
O(1)，我们可以总结出 cache 这个数据结构必要的条件：查找快，插入快，删除快，有顺序之分。

设计新的数据结构**哈希链表**

双向链表和哈希表的结合体
```cpp
// 实现数据结构

class LRUcache
{
private:
    // 定义大小
    int cap;
    // 定义双链表
    list<pair<int,int>>cache;
    // 哈希表，映射key 到(key,value)在的cache中
    unorderd_map<int,list<pair<int,int> >::iterator >map;

public:
    LRUcache(capacity)
    {
        this->cap=capacity;
    }

    int get(int key)
    {
        auto it=map.find(key);

        // 访问的key不存在
        if(it==map.end())  return -1;
        // key存在，把(key,val)放到队头
        pair<int,int>kv=*map[key];
        cache.erase(map[key]);    // 删除迭代器
        cache.push_front(kv);
        // 更新key-val在cache中的位置
        map[key]=cache.begin();

        return kv.second;
    }

    void put(int key,int val)
    {
        // 先判断key是否存在
        auto it=map.find(key);
        // key不存在
        if(it==map.end())
        {
            // 判断cache是否满
            if(cache.size()==cap)
            {
                // 删除尾部的键值
                auto lastPair=cache.back();
                int lastKey=lastPair.first;
                // map 和 cache都要删除
                map.erase(lastKey);
                cache.pop_back();
            }
            // cache未满，直接添加
            cache.push_front(make_pair(key,value));
            map[key]=cache.begin();
        }
        else
        {
            //key 存在，更改val值后 ,放到队头
            cache.erase(map[key]);     // 直接进行删除处理
            cache.push_front(make_pair(key,val));
            map[key]=cache.begin();
        }
    }

};
```


[参考博客](https://leetcode-cn.com/problems/lru-cache/solution/lru-ce-lue-xiang-jie-he-shi-xian-by-labuladong/)




### FIFO

先进先出置换算法


### LFU

最少使用置换算法

按照访问的频率来淘汰
