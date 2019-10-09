
[redis的查询文档](https://doc.redisfans.com/)

[tryredis](http:/try.redis.io/))


[redis学习](https://blog.csdn.net/guchuanyun111/article/category/6335900)

[redis面试总结](https://www.cnblogs.com/jiahaoJAVA/p/6244278.html)

[redis软件杯项目](http://www.cnsoftbei.com/bencandy.php?fid=155&aid=1675)

### Redis

1.什么是redis?

redis是基于内存的高性能 key-value数据库

优点:

整个数据库统统加载在内存当中进行操作，定期通过异步操作把数据库数据flush到硬盘上进行保存。
因为是纯内存操作，Redis的性能非常出色，每秒可以处理超过 10万次读写操作，是已知性能最快的Key-Value DB。

Redis最大的魅力是支持保存多种数据结构

缺点:

Redis的主要缺点是数据库容量受到物理内存的限制，不能用作海量数据的高性能读写，因此Redis适合的场景主要局限在较小数据量的高性能操作和运算上。

2.redis支持的数据类型

- string 字符串
- hash   哈希
- list   列表
- set    集合
- zset   有序集合(sorted set)

